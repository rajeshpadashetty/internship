import java.util.ArrayList;
import java.util.Scanner;

// ==========================
// Book Class
// ==========================
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author +
                (isIssued ? " (Issued)" : " (Available)");
    }
}

// ==========================
// Member Class
// ==========================
class Member {
    private int memberId;
    private String name;
    private ArrayList<Book> borrowedBooks;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + ", Name: " + name + ", Borrowed Books: " + borrowedBooks.size();
    }
}

// ==========================
// Library Class
// ==========================
class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    // Add new book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("✅ Book added successfully!");
    }

    // View all books
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("\n--- Book List ---");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Register new member
    public void addMember(Member member) {
        members.add(member);
        System.out.println("✅ Member registered successfully!");
    }

    // View all members
    public void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
        } else {
            System.out.println("\n--- Member List ---");
            for (Member member : members) {
                System.out.println(member);
            }
        }
    }

    // Issue a book to a member
    public void issueBook(int bookId, int memberId) {
        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null) {
            System.out.println("❌ Book not found!");
            return;
        }
        if (member == null) {
            System.out.println("❌ Member not found!");
            return;
        }
        if (book.isIssued()) {
            System.out.println("❌ Book is already issued to someone else!");
            return;
        }

        book.issueBook();
        member.borrowBook(book);
        System.out.println("📘 Book issued successfully to " + member.getName() + ".");
    }

    // Return a book
    public void returnBook(int bookId, int memberId) {
        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null) {
            System.out.println("❌ Book not found!");
            return;
        }
        if (member == null) {
            System.out.println("❌ Member not found!");
            return;
        }
        if (!book.isIssued()) {
            System.out.println("❌ This book was not issued!");
            return;
        }

        book.returnBook();
        member.returnBook(book);
        System.out.println("📗 Book returned successfully by " + member.getName() + ".");
    }

    // Utility methods
    private Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    private Member findMember(int id) {
        for (Member member : members) {
            if (member.getMemberId() == id) return member;
        }
        return null;
    }
}

// ==========================
// Main Class (Menu-driven)
// ==========================
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;

        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Register Member");
            System.out.println("4. View Members");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author));
                }
                case 2 -> library.viewBooks();
                case 3 -> {
                    System.out.print("Enter Member ID: ");
                    int memberId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Member Name: ");
                    String name = sc.nextLine();
                    library.addMember(new Member(memberId, name));
                }
                case 4 -> library.viewMembers();
                case 5 -> {
                    System.out.print("Enter Book ID to issue: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter Member ID: ");
                    int memberId = sc.nextInt();
                    library.issueBook(bookId, memberId);
                }
                case 6 -> {
                    System.out.print("Enter Book ID to return: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter Member ID: ");
                    int memberId = sc.nextInt();
                    library.returnBook(bookId, memberId);
                }
                case 7 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("❌ Invalid choice! Please try again.");
            }
        } while (choice != 7);

        sc.close();
    }
}
