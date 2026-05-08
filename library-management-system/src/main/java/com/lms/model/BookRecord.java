package com.lms.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
public class BookRecord {
    private final String recordId;
    private Member member;
    private BookItem bookItem;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private static final int BORROW_PERIOD = 15;

    public BookRecord(Member member, BookItem bookItem){
        this.member = member;
        this.recordId = UUID.randomUUID().toString();
        this.bookItem = bookItem;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(BORROW_PERIOD);
        this.returnDate = null;
    }

    public void close(){
        if(returnDate!=null) throw new IllegalAccessError("Already returned");
        returnDate = LocalDate.now();
    }

    public boolean isOverdue(){
        LocalDate checkDate = (returnDate!=null) ? returnDate : LocalDate.now();
        return checkDate.isAfter(dueDate);
    }

    public long getDaysOverdue() {
        if (!isOverdue()) return 0;
        LocalDate checkDate = (returnDate != null) ? returnDate
                                                      : LocalDate.now();
        return ChronoUnit.DAYS.between(dueDate, checkDate);
    }

    public boolean isActive()        { return returnDate == null; }
    public Member    getMember()    { return member; }
    public BookItem  getBookItem()  { return bookItem; }
    public LocalDate getDueDate()   { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
}
