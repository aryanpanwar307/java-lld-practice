package com.lms;

import com.lms.model.*;
import com.lms.exceptions.*;

public class Main {
    public static void main(String[] args) {

        // ── 1. Build the library ──────────────────────────────
        Library library = new Library("City Central Library");

        // ── 2. Librarian adds books ───────────────────────────
        Librarian lib = new Librarian("E001", "Priya", "priya@lib.com");
        Book cleanCode = new Book("978-0132350884", "Clean Code", "Robert Martin");
        BookItem copy1 = new BookItem("BC001", cleanCode);
        BookItem copy2 = new BookItem("BC002", cleanCode);

        lib.addBookToCatalog(library.getCatalog(), cleanCode);
        lib.addBookItem(cleanCode, copy1);
        lib.addBookItem(cleanCode, copy2);

        // ── 3. Register members ───────────────────────────────
        Member ravi = new Member("M001", "Ravi", "ravi@x.com");
        Member anil = new Member("M002", "Anil", "anil@x.com");
        library.registerMember(ravi);
        library.registerMember(anil);

        // ── 4. Polymorphism demo ──────────────────────────────
        Person p = ravi; // Person reference, Member object
        System.out.println(p); // prints: Member: Ravi (ravi@x.com)

        // ── 5. Borrow books ───────────────────────────────────
        BookRecord r1 = library.borrowBook(ravi, "978-0132350884");
        BookRecord r2 = library.borrowBook(anil, "978-0132350884");
        System.out.println("Ravi borrowed, due: " + r1.getDueDate());
        System.out.println("Anil borrowed, due: " + r2.getDueDate());
        System.out.println("Available copies: " + cleanCode.getAvailableCount());

        // ── 6. Search catalog ────────────────────────────────
        library.getCatalog().searchByTitle("clean code")
                .forEach(b -> System.out.println("Found: " + b.getTitle()));

        // ── 7. Return a book ─────────────────────────────────
        double fine = library.returnBook(ravi, r1.getBookItem());
        System.out.printf("Ravi returned. Fine: ₹%.2f%n", fine);

        // ── 8. Edge cases ─────────────────────────────────────
        try {
            library.borrowBook(ravi, "978-0132350884"); // only 1 copy left, anil has it
            // wait, copy1 is now free (ravi returned) — borrow succeeds
            // try an ISBN that doesn't exist:
            library.borrowBook(ravi, "FAKE-ISBN");
        } catch (LibraryException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
