package com.sg.booktracker.dao;

import com.sg.booktracker.dto.Book;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @Component used to tell Spring to instantiate this class
 * annotation comes from the Spring library
 * @author Kyle David Rudy
 */
@Component
public interface BookDao {
    Book getBookByTitle(String title);
    List<Book> getAllBooks();
    Book addBook(Book book);
    void updateBook(Book book);
    void deleteBookByTitle(String title);
}
