package com.indocyber.service;

import com.indocyber.dto.book.BookDto;
import com.indocyber.dto.book.BookDtoUpdate;
import com.indocyber.entity.Book;

import java.util.List;

public interface BookService {


    void save(BookDto bookDto);

    void save(BookDtoUpdate bookDtoUpdate);

    void save(Book book);

    Book findById(String codeBook);

    void deleteById(String codeBook);

    List<Book> findAll();

    List<Book> findByBorrowed();

    List<Book> findByCategory(String categoryName);

    boolean checkExistingAccount(String bookCode);


}
