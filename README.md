# Kotlin Library Management System

🚀 [Click here to run the application live in your browser!](https://github.com/yash26761/LibraryManagementSystem)

A beginner-friendly, console-based Library Management application written in Kotlin. This project demonstrates core object-oriented programming concepts, allowing users to easily manage book inventory, members, and track borrowing statuses.

## Features
- **Add Books:** Enter new books into the library system with title, author, genre and quantity.
- **View Inventory:** Display a complete list of all books including their unique IDs and current availability.
- **Add Members:** Register new library members with name, email and phone number.
- **Borrow Books:** Check out books using Book ID and Member ID, which automatically updates availability.
- **Return Books:** Process book returns to make them available for others again.
- **Search Functionality:** Quickly look up a specific book by title, author, genre or ID.
- **Fine Calculation:** Automatically calculates fine of Rs.10 per day for overdue books.
- **Library Statistics:** View total books, members, active loans and overdue count.
- **Day System:** Advance days to simulate time and track overdue books.

## Prerequisites
To run this project on your local machine, you will need to have the following installed:
- Java Development Kit (JDK)
- Kotlin Compiler

## How to Run Locally

**Clone the repository:** Download or clone this repository to your local machine using the green "Code" button above, or run this command in your terminal:
```
git clone https://github.com/yash26761/LibraryManagementSystem.git
```

**Navigate to the folder:** Open your Command Prompt and navigate to the project directory:
```
cd path/to/your/folder
```

**Compile the Kotlin file:**
```
kotlinc Library.kt
```

**Run the application:**
```
kotlin LibraryKt
```

## Sample Data (Pre-loaded)

| Book ID | Title | Author | Genre |
|---------|-------|--------|-------|
| B001 | The Great Gatsby | F. Scott Fitzgerald | Fiction |
| B002 | 1984 | George Orwell | Dystopian |
| B003 | Clean Code | Robert C. Martin | Technology |
| B004 | To Kill a Mockingbird | Harper Lee | Fiction |
| B005 | Sapiens | Yuval Noah Harari | Non-Fiction |

| Member ID | Name | Email |
|-----------|------|-------|
| M001 | Alice Johnson | alice@email.com |
| M002 | Bob Smith | bob@email.com |
| M003 | Carol White | carol@email.com |

Developed by Yash
