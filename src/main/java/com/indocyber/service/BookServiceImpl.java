package com.indocyber.service;

import com.indocyber.dto.book.BookDto;
import com.indocyber.dto.book.BookDtoUpdate;
import com.indocyber.entity.Book;
import com.indocyber.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void save(BookDto bookDto) {

        Book newBook = new Book();
        newBook.setCode(bookDto.getCode());
        newBook.setCategoryName(bookDto.getCategoryName());
        newBook.setTitle(bookDto.getTitle().toUpperCase());
        newBook.setBorrowed(false);
        newBook.setAuthor(bookDto.getAuthor());
        newBook.setReleaseDate(bookDto.getReleaseDate());
        newBook.setSummary(bookDto.getSummary().toLowerCase());
        newBook.setTotalPage(bookDto.getTotalPage());


        bookRepository.save(newBook);
    }

    @Override
    public void save(BookDtoUpdate bookDtoUpdate) {

        Book newBook = new Book();
        newBook.setCode(bookDtoUpdate.getCode());
        newBook.setCategoryName(bookDtoUpdate.getCategoryName());
        newBook.setTitle(bookDtoUpdate.getTitle().toUpperCase());
        newBook.setBorrowed(false);
        newBook.setAuthor(bookDtoUpdate.getAuthor());
        newBook.setReleaseDate(bookDtoUpdate.getReleaseDate());
        newBook.setSummary(bookDtoUpdate.getSummary().toLowerCase());
        newBook.setTotalPage(bookDtoUpdate.getTotalPage());

        bookRepository.save(newBook);

    }

    @Override
    public void save(Book book) {

        bookRepository.save(book);
    }

    @Override
    public Book findById(String codeBook) {

        Optional<Book> bookOptional = bookRepository.findById(codeBook);
        Book tempBook = null;

        if (bookOptional.isPresent()){
            tempBook = bookOptional.get();
        }

        return tempBook;
    }

    @Override
    public void deleteById(String codeBook) {
        bookRepository.deleteById(codeBook);
    }

    @Override
    public List<Book> findAll() {

        List<Book> bookList = bookRepository.findAll();

        return bookList;
    }

    @Override
    public List<Book> findByBorrowed() {

        List<Book> bookList = bookRepository.findAll();
        List<Book> newBookList = new ArrayList<>();

        for (Book value : bookList){

            if (value.getBorrowed().booleanValue() == false){

                newBookList.add(value);
            }
        }

        return newBookList;
    }

    @Override
    public List<Book> findByCategory(String categoryName) {

        List<Book> bookList = findAll();
        List<Book> newBookList = new ArrayList<>();

        for (Book value : bookList){

            if (value.getCategoryName().getName().equals(categoryName)){

                newBookList.add(value);
            }

        }


        return newBookList;
    }

    @Override
    public boolean checkExistingAccount(String bookCode) {

        Long totalBookCode = bookRepository.count(bookCode);

        return  (totalBookCode > 0) ? true : false;
    }


}
