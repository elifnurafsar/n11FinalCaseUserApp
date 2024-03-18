
# Spring Boot UserApp

An advanced solution designed to efficiently manage user data and reviews, leveraging PostgreSQL for data storage. This application offers RESTful APIs for seamless user management, facilitating integration with various services. Developed using Spring Boot, it establishes a solid framework for user-related features, ensuring dependable performance and scalability.

> [!IMPORTANT]  
> Proje dosyalarının son commit'i 17 Martta yapılmasının ardından repository public'e alındığında README içerisindeki görsellerin benim dışımdaki diğer kullanıcılara gözükmediği tespit edilmiştir. Yapılan uyarı ardından sadece README dosyası güncellenip görseller tekrar yüklenerek son commit 18 Martta atılmıştır. Bu committe proje dosyalarında herhangi bir değişim olmamıştır. README'nin önceki sürümünde görsellerin 17 Mart tarihinde benim kullanıcım tarafından sorunsuz bir şekilde görüntülenebildiği projemin sunum videosunda gösterilmiştir.

## API Usage

#### Get top three restaurants

```http 
GET /api/restaurants/find-top-three?latitude=${latitude}&longitude=${longitude}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `latitude`      | `double` | **Required**. Latitude coordinate of the location.retrieve.  |
|`longitude`|`double`| **Required**. Longitude coordinate of the location.retrieve.|

#### Create a new user

```http 
POST /api/users
```

| Body | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `request` | `CreateUserRequest` | **Required.** User data to be created. |

#### Find User By ID

```http
GET /api/User/find-by-id?id=${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**.  ID of the user to retrieve.  |

#### Get all users

```http 
GET /api/users
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :------------------------- |
| `none` | | |


#### Update user information

```http 
PUT /api/users
```

| Body | Type     | Description                       |
| :-------- | :------- | :------------------------- |
| `request` | `UpdateUserRequest` | **Required.** Updated user data.|


#### Get users by name

```http 
GET /api/users/find-by-name?name=${name}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :------------------------- |
| `name` | `string` | **Required.** Name of the user to search. |

#### Delete user by ID

```http
DELETE /api/users?id=${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**.  ID of the user to delete.  |


#### Get all reviews by user ID

```http
GET /api/reviews/find-all-by-user-id?userId=${userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**.  ID of the user to delete.  |


#### Get all reviews

```http 
GET /api/reviews
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :------------------------- |
| `none` | | |


#### Get all reviews by restaurant ID

```http
GET /api/reviews/find-all-by-restaurant-id?restaurantId=${restaurantId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `restaurantId`      | `String` | **Required**. ID of the restaurant.  |


#### Get review by ID

```http
GET /api/reviews/find-by-id?id=${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**. ID of the review. |


#### Create a new review

```http 
POST /api/reviews
```

| Body | Type     | Description                       |
| :-------- | :------- | :------------------------- |
| `request` | `CreateUserReviewRequest` | **Required.** Review data to be created.|


#### Edit a review

```http 
PUT /api/reviews
```

| Body | Type     | Description                       |
| :-------- | :------- | :------------------------- |
| `request` | `EditUserReviewRequest` | **Required.** Review data to be updated.|


#### Delete a review

```http
DELETE /api/reviews?id=${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**.  ID of the review to delete.  |


## Unit and Integration Test Coverage

![Unit and Integration Test Coverage](https://github.com/elifnurafsar/xxx/assets/60623941/418f512a-ab68-4814-87f0-f5a6601622e9)


## Screenshot Examples

### Find top 3

![find_top_3](https://github.com/elifnurafsar/n11FinalCaseUserApp/assets/60623941/4aab91fe-cb59-4ea2-b21c-039b9f0cf008)


### Create Comment

![create_comment](https://github.com/elifnurafsar/n11FinalCaseUserApp/assets/60623941/10975033-95d8-41e2-8242-e893fb9405d5)


### Get Comments By Restaurant ID

![get_comments_by_restaurant_id](https://github.com/elifnurafsar/n11FinalCaseUserApp/assets/60623941/7810c6d4-c017-4fc4-89c3-b7979e591b66)

### Get All Users

![get_all_users](https://github.com/elifnurafsar/n11FinalCaseUserApp/assets/60623941/4f988f02-761b-4ccd-934e-cc278c0987ce)

### Update Comment

#### Before Update

<img src="https://github.com/elifnurafsar/n11FinalCaseUserApp/assets/60623941/8ddad553-09d8-4e71-ac27-5dfeb7bb60ca" alt="review before update" width="500">
 
<img src= "https://github.com/elifnurafsar/n11FinalCaseUserApp/assets/60623941/2bbafe8f-01b3-4160-8916-89dd73c50471" alt="restaurant before update" width="500">

#### Update

![update_review](https://github.com/elifnurafsar/n11FinalCaseUserApp/assets/60623941/3cc5a1eb-c452-493d-8e11-86fcf05a741b)

#### After Update

<img src="https://github.com/elifnurafsar/n11FinalCaseUserApp/assets/60623941/0462f29a-711f-49f6-9aa8-f77e0c33aa93" alt="review after update" width="500">

<img src= "https://github.com/elifnurafsar/n11FinalCaseUserApp/assets/60623941/247a9c4d-4628-4f18-8b5d-2beeaa3f3d87" alt="restaurant after update" width="500">

### Example Error Case

![error_case_example](https://github.com/elifnurafsar/n11FinalCaseUserApp/assets/60623941/60711010-31ca-4452-865a-0d5a8a1912ef)


## Technologies

**Service:** Spring Boot, Solr

  


