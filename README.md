
# Spring Boot UserApp

An advanced solution designed to efficiently manage user data and reviews, leveraging PostgreSQL for data storage. This application offers RESTful APIs for seamless user management, facilitating integration with various services. Developed using Spring Boot, it establishes a solid framework for user-related features, ensuring dependable performance and scalability.


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
![findtop3](https://github.com/elifnurafsar/xxx/assets/60623941/c54555bc-eddb-4da4-b1a3-bd0ce8763443)

### Create Comment
![create_comment](https://github.com/elifnurafsar/xxx/assets/60623941/399370bc-8f16-4c60-8518-f669310cd135)

### Get Comments By Restaurant ID

![get_comments_by_restaurant_id](https://github.com/elifnurafsar/xxx/assets/60623941/d11748a1-33fb-4328-8c79-3b0ef4804e6a)

### Get All Users

![all_users](https://github.com/elifnurafsar/xxx/assets/60623941/db02a3b6-28db-42be-840b-1075e48c3246)

### Create User

![createUser](https://github.com/elifnurafsar/xxx/assets/60623941/dba97068-7a74-4e9c-9a8b-ea5305a130c8)

### Delete User
![delete_user](https://github.com/elifnurafsar/xxx/assets/60623941/883ab5a8-0fb4-446d-91af-a64812268625)

### Example Error Case

![save_user_error_case](https://github.com/elifnurafsar/xxx/assets/60623941/db8ed690-72c8-4660-88af-0ffd29860da3)


## Technologies

**Service:** Spring Boot, Solr

  


