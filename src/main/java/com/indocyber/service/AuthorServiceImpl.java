package com.indocyber.service;

import com.indocyber.dto.AuthorDto;
import com.indocyber.entity.Author;
import com.indocyber.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    private final int rowsInPage = 3;

    @Override
    public List<Author> findAll() {

        List<Author> authorList = authorRepository.findAll();

        return authorList;
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void save(AuthorDto authorDto) {

        Author author = new Author();


        author.setTitle(authorDto.getTitle());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setBirthDate(authorDto.getBirthDate());
        author.setDeceasedDate(authorDto.getDeceasedDate());
        author.setEducation(authorDto.getEducation());
        author.setSummary(authorDto.getSummary());

        if (authorDto.getId() !=  null){

            author.setId(authorDto.getId());

        }

        authorRepository.save(author);

    }

    @Override
    public Author findById(Integer idAuthor) {

        Optional<Author> optionalAuthor = authorRepository.findById(idAuthor);
        Author tempAuthor = null;

        if (optionalAuthor.isPresent()){
            tempAuthor = optionalAuthor.get();
        }

        return tempAuthor;
    }

    @Override
    public void deleteById(Integer idAuthor) {
        authorRepository.deleteById(idAuthor);
    }

    @Override
    public List<Author> findByFullName(String search) {

        List<Author> optionalAuthorList = authorRepository.findBySearch(search);

        return optionalAuthorList;
    }

    @Override
    public List<Author> getAuthorGrid(Integer page, String name) {

        Pageable pagination = PageRequest.of(page, rowsInPage, Sort.by("id"));
        List<Author> grid = authorRepository.findAll(name, pagination);

        return grid;
    }

    @Override
    public Page<Author> getAuthorGrid(Integer page) {

        Pageable pagination = PageRequest.of(page, rowsInPage, Sort.by("id"));
        Page<Author> grid = authorRepository.findByPage(pagination);

        return grid;
    }

    @Override
    public long getTotalPages(String name) {
        double totalData = (double)(authorRepository.count(name));
        double hasil = Math.ceil(totalData/rowsInPage);
        long totalPage = (long)(hasil);
        return totalPage;
    }
}
