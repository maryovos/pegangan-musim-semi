package com.indocyber.restcontroller;

import com.indocyber.dto.book.BookDto;
import com.indocyber.dto.book.BookDtoUpdate;
import com.indocyber.dto.customer.CustomerDtoUpdate;
import com.indocyber.entity.Author;
import com.indocyber.entity.Book;
import com.indocyber.entity.Category;
import com.indocyber.exceptionhandling.ObjectNotFound;
import com.indocyber.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookRestController {


    @Autowired
    private BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBook(){

        List<Book> authorList = bookService.findAll();

        return new ResponseEntity<>(authorList, HttpStatus.FOUND);

    }

    @GetMapping("{idBook}")
    public ResponseEntity<Book> getBookById(@PathVariable("idBook") String idBook){

        Book book = bookService.findById(idBook);

        if (book == null){
            throw new ObjectNotFound("Book With ID: " + idBook + " not found!!!");
        }

        return new ResponseEntity<>(book, HttpStatus.FOUND);

    }

    @GetMapping("{idBook}/author")
    public ResponseEntity<Author> getAuthorByBook(@PathVariable("idBook") String idBook){

        Book book = bookService.findById(idBook);

        if (book == null){
            throw new ObjectNotFound("Book With ID: " + idBook + " not found!!!");
        }

        return new ResponseEntity<>(book.getAuthor(), HttpStatus.FOUND);

    }

    @GetMapping("{idBook}/category")
    public ResponseEntity<Category> getCategoryByBook(@PathVariable("idBook") String idBook){

        Book book = bookService.findById(idBook);

        if (book == null){
            throw new ObjectNotFound("Book With ID: " + idBook + " not found!!!");
        }

        return new ResponseEntity<>(book.getCategoryName(), HttpStatus.FOUND);

    }

    @PostMapping()
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDto bookDto){

        bookService.save(bookDto);

        return new ResponseEntity<>("book has been created", HttpStatus.ACCEPTED);
    }


    @DeleteMapping("{idBook}")
    public ResponseEntity<String> deleteBook(@PathVariable("idBook") String idBook){

        Book book = bookService.findById(idBook);

        if (book == null){
            throw new ObjectNotFound("Book With ID: " + idBook + " not found!!!");
        }

        bookService.deleteById(idBook);

        return new ResponseEntity<>("book has been deleted", HttpStatus.OK);
    }

    @PutMapping("{idBook}")
    public ResponseEntity<String> updateBook(@PathVariable("idBook") String idBook,
                                             @Valid @RequestBody BookDtoUpdate bookDtoUpdate){

        Book book = bookService.findById(idBook);

        if (book == null){
            throw new ObjectNotFound("Book With ID: " + idBook + " not found!!!");
        }

        bookService.save(bookDtoUpdate);

        return new ResponseEntity<>("book has been deleted", HttpStatus.OK);
    }


}
