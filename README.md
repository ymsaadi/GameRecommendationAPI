# Game Recommendation API

Welcome to the Game Recommendation API! This API is designed to provide a seamless game browsing experience, offering personalized game recommendations, user reviews, and social interactions.


## Features

- **IGDB Integration:** Connects to the IGDB database to fetch detailed information about a vast array of games.

- **User Reviews:** Users can review and rate games, with a robust service layer and repository tests ensuring data integrity.

- **Friend Requests:** Implements a feature for users to send friend requests by email, enhancing the social aspect of the platform.

- **Game Reviews Handling:** Ensures data consistency by checking if a game exists before allowing a review. Updates existing reviews if a user tries to review the same game again.

- **Mapstruct Integration:** Utilizes Mapstruct for object mapping, enhancing code readability and maintainability.

- **JWT Authentication:** Implements a secure JWT-based authentication system with a custom UserDetailsService.

- **Genre Filtering:** Enables users to filter games by genre, name, and release date, providing a tailored gaming experience.

- **Sorting and Pagination:** Allows sorting games and implements pagination for efficient retrieval of game data.

- **OpenAPI Support:** Integrates OpenAPI for API documentation, ensuring clear and comprehensive information for developers.


## Getting Started

### Prerequisites
* Ensure you have Java, Maven, and your preferred IDE installed.

* Set up a PostgreSQL database and configure the connection properties in `application.properties`.

### Installation
1. Clone the repository:
`git clone https://github.com/your-username/game-recommendation-api.git`

2. Build the project:
`cd game-recommendation-api`
`mvn clean install`

3. Run the application:
`mvn spring-boot:run`

4. Access the API at **http://localhost:8080**.

### Documentation
Explore the API endpoints and documentation using the Swagger UI at **http://localhost:8080/swagger-ui.html**.

### Contributing
Contributions are welcome! Feel free to open issues, submit pull requests, or suggest new features.

### Acknowledgments
Thanks to IGDB for providing a rich source of game data.

Happy coding!