package com.indocyber.service;

import com.indocyber.dto.AuthorDto;
import com.indocyber.entity.Author;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    void save(Author author);

    void save(AuthorDto authorDto);

    Author findById(Integer idAuthor);

    void deleteById(Integer idAuthor);

    List<Author> findByFullName(String search);

    List<Author> getAuthorGrid(Integer page, String name);

    Page<Author> getAuthorGrid(Integer page);

    long getTotalPages(String name);


}
