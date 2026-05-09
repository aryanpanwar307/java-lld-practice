package com.lms.model;

import com.lms.exceptions.*;
import java.util.*;

public class Library {

    private final String name;
    private final Catalog catalog;
    private final List<Member> members;
    private final Map<String, BookRecord> activeRecords; // barcode → record

    public Library(String name) {
        this.name = name;
        this.catalog = new Catalog(); // composition
        this.members = new ArrayList<>();
        this.activeRecords = new HashMap<>();
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    // ── Core borrow flow ──────────────────────────────────────
    public BookRecord borrowBook(Member member, String isbn) {

        // 1. Check member borrow limit
        if (!member.canBorrow()) {
            throw new BorrowLimitExceededException(member.getMemberId());
        }

        // 2. Find the book by ISBN
        Book book = catalog.searchByIsbn(isbn); // throws if not found

        // 3. Find an available copy
        BookItem item = book.getAvailableCopy();
        if (item == null) {
            throw new BookNotAvailableException(isbn);
        }

        // 4. Mark copy as borrowed
        item.checkout();

        // 5. Create the borrow record
        BookRecord record = new BookRecord(member, item);

        // 6. Update member and active records map
        member.addBorrowRecord(item);
        activeRecords.put(item.getBarcode(), record);

        return record;
    }

    // ── Core return flow ──────────────────────────────────────
    public double returnBook(Member member, BookItem item) {

        // 1. Find the active borrow record
        BookRecord record = activeRecords.get(item.getBarcode());
        if (record == null) {
            throw new LibraryException("No active record for this item");
        }

        // 2. Close the record (sets returnDate)
        record.close();

        // 3. Free the copy
        item.returnBook();

        // 4. Update member count
        member.onReturn();

        // 5. Calculate fine if overdue
        double fine = new FeeCalculator().calculateFine(record);

        // 6. Remove from active records
        activeRecords.remove(item.getBarcode());

        return fine;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public String getName() {
        return name;
    }
}