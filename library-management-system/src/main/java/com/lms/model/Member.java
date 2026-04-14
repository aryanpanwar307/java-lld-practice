package com.lms.model;

import java.util.*;

public class Member extends Person {

    private final int MAX_BORROW_LIMIT = 5;

    private final String memberId;
    private List<String> activeBorrowings;
    private int borrowCount;
    private List<BorrowItem> borrowHistory;

    public Member(String memberId, String name, String email) {
        super(name, email);
        this.memberId = memberId;
        this.activeBorrowings = new ArrayList<>();
        this.borrowHistory = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "Member";
    }

    public boolean canBorrow() {
        return (borrowCount < MAX_BORROW_LIMIT) ? true : false;
    }

    public void addBorrowRecord(BorrowItem borrowItem) {
        borrowHistory.add(borrowItem);
        borrowCount++;
    }

    public void onReturn() {
        borrowCount--;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getBorrowedCount() {
        return borrowCount;
    }

    public List<BorrowItem> getBorrowHistory() {
        return Collections.unmodifiableList(borrowHistory);
    }

}
