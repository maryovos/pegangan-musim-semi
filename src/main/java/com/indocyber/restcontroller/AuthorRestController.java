package com.indocyber.restcontroller;

import com.indocyber.dto.AuthorDto;
import com.indocyber.entity.Author;
import com.indocyber.entity.Book;
import com.indocyber.exceptionhandling.ObjectNotFound;
import com.indocyber.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/author")
public class AuthorRestController {


    @Autowired
    private AuthorService authorService;

    @GetMapping()
    public ResponseEntity< Page<Author>> getAllAuthor(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "") String name){


//        List<Author> authors = authorService.getAuthorGrid(page, name);
        Page<Author> authors = authorService.getAuthorGrid(page);

        return new ResponseEntity<>(authors, HttpStatus.FOUND);

    }

    @GetMapping("{idAuthor}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("idAuthor") Integer idAuthor){

        Author author = authorService.findById(idAuthor);

        if (author == null){
            throw new ObjectNotFound("Author With ID: " + idAuthor + " not found!!!");
        }


        return new ResponseEntity<>(author, HttpStatus.FOUND);

    }

    @GetMapping("{idAuthor}/book")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable("idAuthor") Integer idAuthor){

        Author author = authorService.findById(idAuthor);

        if (author == null){
            throw new ObjectNotFound("Author With ID: " + idAuthor + " not found!!!");
        }

        return new ResponseEntity<>(author.getBookList(), HttpStatus.FOUND);

    }



    @PostMapping()
    public ResponseEntity<String> addAuthor(@Valid @RequestBody AuthorDto authorDto){

        authorService.save(authorDto);

        return new ResponseEntity<>("author has been created", HttpStatus.ACCEPTED);
    }

    @PutMapping("{idAuthor}")
    public ResponseEntity<String> updateAuthor(@Valid @RequestBody AuthorDto authorDto,
                                               @PathVariable("idAuthor") Integer idAuthor){

        Author author = authorService.findById(idAuthor);

        if (author == null){
            throw new ObjectNotFound("Author With ID: " + idAuthor + " not found!!!");
        }

        authorDto.setId(idAuthor);

        authorService.save(authorDto);
        return new ResponseEntity<>("author has been updated", HttpStatus.ACCEPTED);

    }

    @DeleteMapping("{idAuthor}")
    public ResponseEntity<String> deleteAuthor( @PathVariable("idAuthor") Integer idAuthor){

        Author author = authorService.findById(idAuthor);

        if (author == null){
            throw new ObjectNotFound("Author With ID: " + idAuthor + " not found!!!");
        }

        authorService.deleteById(idAuthor);
        return new ResponseEntity<>("author has been updated", HttpStatus.OK);

    }


}
