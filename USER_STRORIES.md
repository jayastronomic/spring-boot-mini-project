## User Stories

### User Authentication

- As a user, I want to be able to create an account and log in so that I can access my book collection.
    - **Acceptance Criteria:**
        - Users can register with a username and password.
        - Registered users can log in using their credentials.
        - User sessions are maintained securely.

### Managing Book Collection

- As a user, I want to add books to my collection, so that I can keep track of the books I own.
    - **Acceptance Criteria:**
        - Users can add books to their collection by providing book details such as title, author, and summary.
        - Users can edit or delete books from their collection.

- As a user, I want to see a list of all the books in my collection, so that I can easily browse through them.
    - **Acceptance Criteria:**
        - Users can view a list of books they've added to their collection.

- As a user, I want to search for books in my collection by title or author, so that I can quickly find specific books.
    - **Acceptance Criteria:**
        - Users can search for books in their collection by entering keywords like title or author.

### Genre Categorization

- As a user, I want to categorize my books by genres, so that I can organize them more effectively.
    - **Acceptance Criteria:**
        - Users can assign one or more genres to each book in their collection.
        - Users can view books categorized by genre. 

## Book Stories

- As a book, I belong to one user.
    - **Acceptance Criteria:**
        - Each book is associated with one and only one user.

- As a book, I can have many genres.
    - **Acceptance Criteria:**
        - Each book can be associated with multiple genres.

## Genre Stories

- As a genre, I can belong to many books.
    - **Acceptance Criteria:**
        - Each genre can be associated with multiple books.