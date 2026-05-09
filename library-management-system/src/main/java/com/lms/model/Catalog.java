package com.lms.model;

import java.util.*;
import com.lms.exceptions.LibraryException;

public class Catalog {
    private final Map<String, Book> booksByIsbn = new HashMap<>();
    private final Map<String, List<Book>> booksByTitle = new HashMap<>();
    private final Map<String, List<Book>> booksByAuthor = new HashMap<>();

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
        if (book == null) throw new LibraryException.BookNotFoundException(isbn);
        return book;
    }

    public Map<String, List<Book>> getTitleIndex()  { return booksByTitle; }
    public Map<String, List<Book>> getAuthorIndex() { return booksByAuthor; }
}

// Since SearchStrategy was used in the snippet, I'm including a basic implementation 
// if they were intended to be in the same file.
interface SearchStrategy {
    List<Book> search(Map<String, List<Book>> index, String query);
}

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
