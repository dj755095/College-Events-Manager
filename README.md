# EventInsti

## Project Overview

**EventInsti** is a web application designed to facilitate seamless interaction between Students, Institutes, and Admin. The platform allows users to sign up, log in, and manage their profiles. Students can explore and participate in events hosted by Institutes, while Institutes can efficiently organize and manage events for specific student categories. Admin plays a crucial role in verifying and approving Institutes.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## Features

1. **Authentication and Registration:**
   - Users can sign up with their basic details, including email, password, and additional information based on their role (Student or Institute).
   - Firebase Realtime Database is utilized for secure storage of user data.

2. **Student Page:**
   - Upon login, students are directed to their personalized page where they can view and edit their profiles.
   - Students can access a list of events uploaded by Institutes based on their selected branch and field during registration.
   - Search functionality enables students to find Institutes, event details, and profiles.

3. **Event Details:**
   - Students can view event details, including Event Title, Event Image, Venue, Institute Name, and Verification Status.
   - A link is provided to redirect students to the registration website of the respective Institute.

4. **Institute Page:**
   - Institutes can check their verification status and edit their profiles.
   - Events can be uploaded by Institutes with details such as name, link, image, etc.
   - Institutes can specify the category of students (field and branch) who can view their events.

5. **Admin Page:**
   - Admin can approve Institutes based on their legitimacy, marking them as verified.
   - Admin has a comprehensive view of all registered Institutes.

## Installation

1. Clone the repository to your local machine.
2. Install the necessary dependencies using `npm install`.
3. Set up Firebase Realtime Database and configure the project with your Firebase credentials.
4. Run the application using `npm start`.

## Usage

1. Access the application through the provided URL.
2. Sign up as a Student or Institute with the required details.
3. Log in using the created credentials.
4. Navigate to the respective pages (Student, Institute, or Admin) and utilize the features based on your role.

## Screenshots

- Student Page
  ![Student Page](screenshots/student_page.png)

- Event Details
  ![Event Details](screenshots/event_details.png)

- Institute Page
  ![Institute Page](screenshots/institute_page.png)

- Admin Page
  ![Admin Page](screenshots/admin_page.png)

## Contributing

If you'd like to contribute to this project, please follow the [Contribution Guidelines](CONTRIBUTING.md).

## License

This project is licensed under the [Apache License](LICENSE).
