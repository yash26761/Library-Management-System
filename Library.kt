class Book(bookId: String, bookTitle: String, bookAuthor: String, bookGenre: String, totalQty: Int) {
    var id: String = bookId
    var title: String = bookTitle
    var author: String = bookAuthor
    var genre: String = bookGenre
    var quantity: Int = totalQty
    var available: Int = totalQty
}

class LibraryMember(memId: String, memName: String, memEmail: String, memPhone: String) {
    var memberId: String = memId
    var name: String = memName
    var email: String = memEmail
    var phone: String = memPhone
    var borrowedCount: Int = 0
}

class LoanRecord(bId: String, mId: String, loanDay: Int) {
    var bookId: String = bId
    var memberId: String = mId
    var dayBorrowed: Int = loanDay
    var isReturned: Boolean = false
}

class LibraryManagement {
    var books = arrayOfNulls<Book>(20)
    var members = arrayOfNulls<LibraryMember>(20)
    var loans = arrayOfNulls<LoanRecord>(50)
    var currentDay: Int = 1
    var loanLimit: Int = 3
    var loanDays: Int = 7
    var finePerDay: Int = 10

    fun advanceDay() {
        currentDay++
        println("\n>>> Day advanced. It is now Day $currentDay <<<")
        checkOverdue()
    }

    fun checkOverdue() {
        println("\n--- Overdue Check (Day $currentDay) ---")
        var found = false
        for (i in 0 until 50) {
            var l = loans[i]
            if (l != null && !l.isReturned) {
                var daysKept = currentDay - l.dayBorrowed
                if (daysKept > loanDays) {
                    var fine = (daysKept - loanDays) * finePerDay
                    println("OVERDUE: Book ID ${l.bookId} | Member ID ${l.memberId} | Fine: Rs.$fine")
                    found = true
                }
            }
        }
        if (!found) println("No overdue books.")
    }

    // ── BOOK OPERATIONS ──────────────────────

    fun addBook(id: String, title: String, author: String, genre: String, quantity: Int) {
        for (i in 0 until 20) {
            if (books[i] != null && books[i]?.id == id) {
                println("Error: Book ID '$id' already exists.")
                return
            }
        }
        for (i in 0 until 20) {
            if (books[i] == null) {
                books[i] = Book(id, title, author, genre, quantity)
                println("SUCCESS: Added $quantity copy/copies of '$title'")
                return
            }
        }
        println("Error: Book shelf is full!")
    }

    fun deleteBook(bookId: String) {
        for (i in 0 until 20) {
            var b = books[i]
            if (b != null && b.id == bookId) {
                if (b.available < b.quantity) {
                    println("Error: Cannot delete! Some copies are currently borrowed.")
                    return
                }
                books[i] = null
                println("SUCCESS: Book '$bookId' deleted.")
                return
            }
        }
        println("Error: Book ID not found.")
    }

    fun searchBook(query: String) {
        println("\n--- Search Results for '$query' ---")
        var found = false
        for (i in 0 until 20) {
            var b = books[i]
            if (b != null) {
                if (b.title.lowercase().contains(query.lowercase()) ||
                    b.author.lowercase().contains(query.lowercase()) ||
                    b.id.lowercase().contains(query.lowercase()) ||
                    b.genre.lowercase().contains(query.lowercase())) {
                    println("ID: ${b.id} | Title: ${b.title} | Author: ${b.author} | Genre: ${b.genre} | Available: ${b.available}/${b.quantity}")
                    found = true
                }
            }
        }
        if (!found) println("No books found for '$query'")
    }

    fun displayBooks() {
        println("\n--- All Books ---")
        var count = 0
        for (i in 0 until 20) {
            var b = books[i]
            if (b != null) {
                println("ID: ${b.id} | Title: ${b.title} | Author: ${b.author} | Genre: ${b.genre} | Available: ${b.available}/${b.quantity}")
                count++
            }
        }
        if (count == 0) println("No books in the library.")
        else println("Total: $count book(s)")
    }

    fun displayAvailableBooks() {
        println("\n--- Available Books ---")
        var count = 0
        for (i in 0 until 20) {
            var b = books[i]
            if (b != null && b.available > 0) {
                println("ID: ${b.id} | Title: ${b.title} | Author: ${b.author} | Available: ${b.available}/${b.quantity}")
                count++
            }
        }
        if (count == 0) println("No books available right now.")
        else println("Total: $count book(s) available")
    }

    // ── MEMBER OPERATIONS ────────────────────

    fun registerMember(id: String, name: String, email: String, phone: String) {
        for (i in 0 until 20) {
            if (members[i] != null && members[i]?.memberId == id) {
                println("Error: Member ID '$id' already exists.")
                return
            }
        }
        for (i in 0 until 20) {
            if (members[i] == null) {
                members[i] = LibraryMember(id, name, email, phone)
                println("SUCCESS: Member '$name' registered.")
                return
            }
        }
        println("Error: Member list is full!")
    }

    fun deleteMember(memberId: String) {
        for (i in 0 until 20) {
            var m = members[i]
            if (m != null && m.memberId == memberId) {
                if (m.borrowedCount > 0) {
                    println("Error: Cannot delete! Member has ${m.borrowedCount} unreturned book(s).")
                    return
                }
                members[i] = null
                println("SUCCESS: Member '$memberId' deleted.")
                return
            }
        }
        println("Error: Member ID not found.")
    }

    fun displayMembers() {
        println("\n--- Registered Members ---")
        var count = 0
        for (i in 0 until 20) {
            var m = members[i]
            if (m != null) {
                println("ID: ${m.memberId} | Name: ${m.name} | Email: ${m.email} | Phone: ${m.phone} | Books Held: ${m.borrowedCount}")
                count++
            }
        }
        if (count == 0) println("No members registered.")
        else println("Total: $count member(s)")
    }

    fun viewMemberDetails(memberId: String) {
        var found = false
        for (i in 0 until 20) {
            var m = members[i]
            if (m != null && m.memberId == memberId) {
                println("\n--- Member Details ---")
                println("ID     : ${m.memberId}")
                println("Name   : ${m.name}")
                println("Email  : ${m.email}")
                println("Phone  : ${m.phone}")
                println("Loans  : ${m.borrowedCount} / $loanLimit")
                println("Active Loans:")
                var loanFound = false
                for (j in 0 until 50) {
                    var l = loans[j]
                    if (l != null && l.memberId == memberId && !l.isReturned) {
                        println("  - Book ID: ${l.bookId} | Borrowed on Day: ${l.dayBorrowed}")
                        loanFound = true
                    }
                }
                if (!loanFound) println("  No active loans.")
                found = true
                break
            }
        }
        if (!found) println("Error: Member ID not found.")
    }

    // ── BORROW / RETURN ──────────────────────

    fun borrowBook(bookId: String, memberId: String) {
        var foundBook: Book? = null
        var foundMember: LibraryMember? = null

        for (i in 0 until 20) {
            if (books[i] != null && books[i]?.id == bookId) foundBook = books[i]
            if (members[i] != null && members[i]?.memberId == memberId) foundMember = members[i]
        }

        if (foundBook == null) { println("Error: Book ID not found."); return }
        if (foundMember == null) { println("Error: Member ID not found."); return }
        if (foundBook.available <= 0) { println("Error: No copies available right now."); return }
        if (foundMember.borrowedCount >= loanLimit) { println("Error: Member has reached borrow limit of $loanLimit books."); return }

        for (i in 0 until 50) {
            var l = loans[i]
            if (l != null && l.bookId == bookId && l.memberId == memberId && !l.isReturned) {
                println("Error: Member already borrowed this book.")
                return
            }
        }

        for (i in 0 until 50) {
            if (loans[i] == null) {
                loans[i] = LoanRecord(bookId, memberId, currentDay)
                foundBook.available--
                foundMember.borrowedCount++
                println("SUCCESS: '${foundBook.title}' borrowed by ${foundMember.name} on Day $currentDay.")
                println("Please return within $loanDays days (by Day ${currentDay + loanDays}).")
                return
            }
        }
        println("Error: Loan system is full.")
    }

    fun returnBook(bookId: String, memberId: String) {
        var foundLoan: LoanRecord? = null

        for (i in 0 until 50) {
            var l = loans[i]
            if (l != null && l.bookId == bookId && l.memberId == memberId && !l.isReturned) {
                foundLoan = l
                l.isReturned = true
                break
            }
        }

        if (foundLoan == null) {
            println("Error: No active loan found for this Book ID and Member ID.")
            return
        }

        var daysKept = currentDay - foundLoan.dayBorrowed
        if (daysKept > loanDays) {
            var fine = (daysKept - loanDays) * finePerDay
            println("Warning: Book is late by ${daysKept - loanDays} day(s)! Fine: Rs.$fine")
        } else {
            var daysRemaining = loanDays - daysKept
            println("Book returned on time. $daysRemaining day(s) remaining.")
        }

        for (i in 0 until 20) {
            if (books[i] != null && books[i]?.id == bookId) books[i]?.available = (books[i]?.available ?: 0) + 1
            if (members[i] != null && members[i]?.memberId == memberId) members[i]?.borrowedCount = (members[i]?.borrowedCount ?: 1) - 1
        }
        println("SUCCESS: Book returned on Day $currentDay.")
    }

    fun displayLoans() {
        println("\n--- All Loan Records ---")
        var count = 0
        for (i in 0 until 50) {
            var l = loans[i]
            if (l != null) {
                var status = if (l.isReturned) "Returned" else "Active"
                println("Book ID: ${l.bookId} | Member ID: ${l.memberId} | Day Borrowed: ${l.dayBorrowed} | Status: $status")
                count++
            }
        }
        if (count == 0) println("No loan records found.")
        else println("Total: $count loan(s)")
    }

    // ── STATISTICS ───────────────────────────

    fun displayStatistics() {
        var totalBooks = 0
        var totalCopies = 0
        var availableCopies = 0
        var totalMembers = 0
        var activeLoans = 0
        var overdueLoans = 0

        for (i in 0 until 20) {
            if (books[i] != null) {
                totalBooks++
                totalCopies += books[i]?.quantity ?: 0
                availableCopies += books[i]?.available ?: 0
            }
            if (members[i] != null) totalMembers++
        }
        for (i in 0 until 50) {
            var l = loans[i]
            if (l != null && !l.isReturned) {
                activeLoans++
                if (currentDay - l.dayBorrowed > loanDays) overdueLoans++
            }
        }

        println("\n--- Library Statistics ---")
        println("Current Day       : $currentDay")
        println("Unique Titles     : $totalBooks")
        println("Total Copies      : $totalCopies")
        println("Available Copies  : $availableCopies")
        println("Borrowed Copies   : ${totalCopies - availableCopies}")
        println("Total Members     : $totalMembers")
        println("Active Loans      : $activeLoans")
        println("Overdue Loans     : $overdueLoans")
    }
}

// ──────────────────────────────────────────
//  MAIN
// ──────────────────────────────────────────

fun main() {
    var library = LibraryManagement()
    var running = true

    // Sample data
    library.addBook("B001", "The Great Gatsby",      "F. Scott Fitzgerald", "Fiction",     3)
    library.addBook("B002", "1984",                  "George Orwell",       "Dystopian",   4)
    library.addBook("B003", "Clean Code",            "Robert C. Martin",    "Technology",  5)
    library.addBook("B004", "To Kill a Mockingbird", "Harper Lee",          "Fiction",     2)
    library.addBook("B005", "Sapiens",               "Yuval Noah Harari",   "Non-Fiction", 3)

    library.registerMember("M001", "Alice Johnson", "alice@email.com", "555-0101")
    library.registerMember("M002", "Bob Smith",     "bob@email.com",   "555-0102")
    library.registerMember("M003", "Carol White",   "carol@email.com", "555-0103")

    println("=== Library Management System ===")

    while (running) {
        println("\n--- Menu (Today is Day " + library.currentDay + ") ---")
        println("1.  Display All Books")
        println("2.  Display Available Books")
        println("3.  Search Book")
        println("4.  Add Book")
        println("5.  Delete Book")
        println("6.  Display All Members")
        println("7.  View Member Details")
        println("8.  Register Member")
        println("9.  Delete Member")
        println("10. Borrow Book")
        println("11. Return Book")
        println("12. Display All Loans")
        println("13. Check Overdue Books")
        println("14. Library Statistics")
        println("15. Advance to Next Day")
        println("0.  Exit")
        print("Enter choice: ")

        var choice = readln()

        if (choice == "1") library.displayBooks()
        else if (choice == "2") library.displayAvailableBooks()
        else if (choice == "3") {
            print("Enter search (title / author / genre / ID): ")
            var query = readln()
            library.searchBook(query)
        }
        else if (choice == "4") {
            print("Enter Book ID (e.g. B006): ")
            var id = readln()
            print("Enter Title: ")
            var title = readln()
            print("Enter Author: ")
            var author = readln()
            print("Enter Genre: ")
            var genre = readln()
            print("Enter Quantity: ")
            var qty = readln().toIntOrNull() ?: 0
            library.addBook(id, title, author, genre, qty)
        }
        else if (choice == "5") {
            library.displayBooks()
            print("Enter Book ID to delete: ")
            var id = readln()
            library.deleteBook(id)
        }
        else if (choice == "6") library.displayMembers()
        else if (choice == "7") {
            print("Enter Member ID: ")
            var id = readln()
            library.viewMemberDetails(id)
        }
        else if (choice == "8") {
            print("Enter Member ID (e.g. M004): ")
            var id = readln()
            print("Enter Name: ")
            var name = readln()
            print("Enter Email: ")
            var email = readln()
            print("Enter Phone: ")
            var phone = readln()
            library.registerMember(id, name, email, phone)
        }
        else if (choice == "9") {
            library.displayMembers()
            print("Enter Member ID to delete: ")
            var id = readln()
            library.deleteMember(id)
        }
        else if (choice == "10") {
            library.displayAvailableBooks()
            library.displayMembers()
            print("Enter Book ID to borrow: ")
            var bookId = readln()
            print("Enter Member ID: ")
            var memberId = readln()
            library.borrowBook(bookId, memberId)
        }
        else if (choice == "11") {
            library.displayMembers()
            print("Enter Book ID to return: ")
            var bookId = readln()
            print("Enter Member ID: ")
            var memberId = readln()
            library.returnBook(bookId, memberId)
        }
        else if (choice == "12") library.displayLoans()
        else if (choice == "13") library.checkOverdue()
        else if (choice == "14") library.displayStatistics()
        else if (choice == "15") library.advanceDay()
        else if (choice == "0") {
            running = false
            println("Goodbye!")
        }
        else println("Invalid choice. Please try again.")
    }
}
