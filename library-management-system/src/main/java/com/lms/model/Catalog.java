package com.lms.model;

import com.lms.exceptions.*;
import java.util.*;

// ── Strategy interface ─────────────────────────────────────
interface SearchStrategy {
    List<Book> search(Map<String, List<Book>> index, String query);
}

// ── Concrete strategies ────────────────────────────────────
class SearchByTitle implements SearchStrategy {
    @Override
    public List<Book> search(Map<String, List<Book>> index, String query) {
        return index.getOrDefault(query.toLowerCase(), Collections.emptyList());
    }
}

class SearchByAuthor implements SearchStrategy {
    @Override
    public List<Book> search(Map<String, List<Book>> index, String query) {
        return index.getOrDefault(query.toLowerCase(), Collections.emptyList());
    }
}

// ── Catalog — Context class that uses a strategy ───────────
public class Catalog {

    private final Map<String, Book> booksByIsbn;
    private final Map<String, List<Book>> booksByTitle;
    private final Map<String, List<Book>> booksByAuthor;

    public Catalog() {
        booksByIsbn = new HashMap<>();
        booksByTitle = new HashMap<>();
        booksByAuthor = new HashMap<>();
    }

    public void addBook(Book book) {
        booksByIsbn.put(book.getIsbn(), book);
        booksByTitle.computeIfAbsent(
                book.getTitle().toLowerCase(), k -> new ArrayList<>()).add(book);
        booksByAuthor.computeIfAbsent(
                book.getAuthor().toLowerCase(), k -> new ArrayList<>()).add(book);
    }

    // Context method — accepts any SearchStrategy at runtime
    public List<Book> search(SearchStrategy strategy,
            Map<String, List<Book>> index,
            String query) {
        return strategy.search(index, query);
    }

    public List<Book> searchByTitle(String title) {
        return search(new SearchByTitle(), booksByTitle, title);
    }

    public List<Book> searchByAuthor(String author) {
        return search(new SearchByAuthor(), booksByAuthor, author);
    }

    public Book searchByIsbn(String isbn) {
        Book book = booksByIsbn.get(isbn);
        if (book == null)
            throw new BookNotFoundException(isbn);
        return book;
    }

    public Map<String, List<Book>> getTitleIndex() {
        return booksByTitle;
    }

    public Map<String, List<Book>> getAuthorIndex() {
        return booksByAuthor;
    }
}