package com.n11.UserApp.service;

import com.n11.UserApp.client.RestaurantClient;
import com.n11.UserApp.common.CustomException;
import com.n11.UserApp.common.enums.ErrorMessages;
import com.n11.UserApp.dao.dto.RestaurantDTO;
import com.n11.UserApp.dao.dto.UserReviewDetailDTO;
import com.n11.UserApp.dao.entity.User;
import com.n11.UserApp.dao.entity.UserReview;
import com.n11.UserApp.dao.mapper.UserReviewMapper;
import com.n11.UserApp.repository.UserReviewRepository;
import com.n11.UserApp.request.restaurant.UpdateScoreRequest;
import com.n11.UserApp.request.userReview.CreateUserReviewRequest;
import com.n11.UserApp.request.userReview.EditUserReviewRequest;
import com.n11.UserApp.response.userReview.UserReviewListResponse;
import com.n11.UserApp.response.userReview.UserReviewResponse;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserReviewService {

    private final UserReviewRepository userReviewRepository;
    private final UserService userService;
    private final RestaurantClient restaurantClient;

    @Transactional
    public UserReviewResponse save(CreateUserReviewRequest userReviewRequest) throws CustomException {
        UserReview userReview = UserReviewMapper.INSTANCE.requestToEntity(userReviewRequest);
        User user = userService.getUserEntityById(userReviewRequest.getUserId());
        userReview.setUser(user);

        checkRestaurant(userReviewRequest.getRestaurantId());

        UserReview temp = userReviewRepository.save(userReview);

        double avgScore = userReviewRepository.calculateAverageScoreOfRestaurants(userReviewRequest.getRestaurantId());
        UpdateScoreRequest updateScoreRequest = new UpdateScoreRequest(userReviewRequest.getRestaurantId(), avgScore);

        Response feignResponse = restaurantClient.updateScore(updateScoreRequest);
        if(feignResponse.status() != 200){
            throw new CustomException(ErrorMessages.SCORE_UPDATE_ERROR);
        }
        return UserReviewMapper.INSTANCE.entityToResponse(temp);
    }

    @Transactional(readOnly = true)
    public UserReviewListResponse getByUserId(UUID userId){
        List<UserReview> userReviews = userReviewRepository.findByUserId(userId);
        List<UserReviewDetailDTO> userReviewDTOs = convertToUserReviewDetailDTOs(userReviews);
        return new UserReviewListResponse(userReviewDTOs, 0, "");
    }

    @Transactional(readOnly = true)
    public UserReviewListResponse getAll(){
        List<UserReview> userReviews = userReviewRepository.findAll();
        List<UserReviewDetailDTO> userReviewDTOs = convertToUserReviewDetailDTOs(userReviews);
        return new UserReviewListResponse(userReviewDTOs, 0, "");
    }

    @Transactional(readOnly = true)
    public UserReviewListResponse findAllByRestaurantId(String restaurantID) throws CustomException {

        RestaurantDTO restaurantDTO = checkRestaurant(restaurantID);
        List<UserReview> userReviews = userReviewRepository.findByRestaurantId(restaurantID);

        List<UserReviewDetailDTO> userReviewDTOs = new ArrayList<>();

        for (UserReview userReview : userReviews) {
            userReviewDTOs.add(convertToUserReviewDetailDTO(userReview, restaurantDTO));
        }

        return new UserReviewListResponse(userReviewDTOs, 0, "");

    }

    @Transactional(readOnly = true)
    public UserReviewDetailDTO findById(UUID id) throws CustomException {
        UserReview userReview = checkUserReview(id);
        RestaurantDTO restaurantDTO = checkRestaurant(userReview.getRestaurantId());
        return convertToUserReviewDetailDTO(userReview, restaurantDTO);
    }

    @Transactional
    public UserReviewResponse updateReview(EditUserReviewRequest editUserReviewRequest) throws CustomException {

        UserReview userReview = checkUserReview(editUserReviewRequest.getId());

        userReview.setComment(editUserReviewRequest.getComment());
        userReview.setRate(editUserReviewRequest.getRate());
        UserReview response = userReviewRepository.save(userReview);

        updateScore(userReview.getRestaurantId());

        return UserReviewMapper.INSTANCE.entityToResponse(response);
    }

    @Transactional
    public void delete(UUID id) {
        UserReview userReview = checkUserReview(id);
        try {
            userReviewRepository.deleteById(id);
        }
        catch (Exception e){
            throw new CustomException(ErrorMessages.DEFAULT);
        }
        log.info("Review with id: " + id + " has deleted at " + LocalDateTime.now() + "!");
        updateScore(userReview.getRestaurantId());
    }

    private void updateScore(String restaurantId){
        double avgScore = userReviewRepository.calculateAverageScoreOfRestaurants(restaurantId);
        UpdateScoreRequest updateScoreRequest = new UpdateScoreRequest(restaurantId, avgScore);

        Response feignResponse = restaurantClient.updateScore(updateScoreRequest);
        if(feignResponse.status() != 200){
            throw new CustomException(ErrorMessages.SCORE_UPDATE_ERROR);
        }
    }

    private UserReview checkUserReview(UUID id){

        Optional<UserReview> userReview = userReviewRepository.findById(id);
        if(userReview.isEmpty()){
            throw new CustomException(ErrorMessages.REVIEW_NOT_FOUND);
        }
        return userReview.get();

    }

    private RestaurantDTO checkRestaurant(String restaurantId){
        try{
            ResponseEntity<RestaurantDTO> restaurantDTO = restaurantClient.findById(restaurantId);
            if(restaurantDTO.getStatusCode() == HttpStatus.OK){
                return restaurantDTO.getBody();
            }
            else
                throw new CustomException(ErrorMessages.RESTAURANT_NOT_FOUND);
        }
        catch (Exception e){
            throw new CustomException(ErrorMessages.RESTAURANT_NOT_FOUND);
        }
    }

    private UserReviewDetailDTO convertToUserReviewDetailDTO(UserReview userReviewTemp, RestaurantDTO restaurantDTO) throws CustomException {
        UserReviewDetailDTO userReviewDetailDTO = new UserReviewDetailDTO();
        userReviewDetailDTO.setUserId(userReviewTemp.getUser().getId());
        userReviewDetailDTO.setUserName(userReviewTemp.getUser().getName());
        userReviewDetailDTO.setUserSurname(userReviewTemp.getUser().getSurname());
        userReviewDetailDTO.setUserFullName(userReviewTemp.getUser().getName() + " " +userReviewTemp.getUser().getSurname());
        userReviewDetailDTO.setComment(userReviewTemp.getComment());
        userReviewDetailDTO.setRate(userReviewTemp.getRate());
        userReviewDetailDTO.setId(userReviewTemp.getId());
        userReviewDetailDTO.setRestaurantId(restaurantDTO.getId());
        userReviewDetailDTO.setRestaurantName(restaurantDTO.getName());
        return userReviewDetailDTO;
    }

    private List<UserReviewDetailDTO> convertToUserReviewDetailDTOs(List<UserReview> userReviews) {
        List<UserReviewDetailDTO> userReviewDTOs = new ArrayList<>();
        for (UserReview userReview : userReviews) {
            try {
                RestaurantDTO restaurantDTO = checkRestaurant(userReview.getRestaurantId());
                userReviewDTOs.add(convertToUserReviewDetailDTO(userReview, restaurantDTO));
            }
            catch (Exception e){
                log.error("Couldn't find restaurant with id: " + userReview.getRestaurantId() + "! \n Skipping...");
            }
        }
        return userReviewDTOs;
    }

}
