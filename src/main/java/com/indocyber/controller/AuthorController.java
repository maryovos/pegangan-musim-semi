package com.indocyber.controller;

import com.indocyber.dto.AuthorDto;
import com.indocyber.entity.Author;
import com.indocyber.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "") String name ,
                        Model model){
//        List<Author> authors = authorService.findAll();

        List<Author> authors = authorService.getAuthorGrid(page,name);
        long totalPages = authorService.getTotalPages(name);
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("authors", authors);

        return "author/index";
    }

    @GetMapping("/addAuthor")
    public String addAuthor(Model model){

        Author author = new Author();

        model.addAttribute("author", author);

        return "author/addAuthor";

    }

    @PostMapping("/proccessAuthor")
    public String proccessAuthor(@Valid @ModelAttribute("author") AuthorDto authorDto,
                                 BindingResult bindingResult,
                                 Model model){


        if(bindingResult.hasErrors()){

            return "author/addAuthor";
        }

        authorService.save(authorDto);

        return "redirect:index";
    }

    @GetMapping("/editAuthor/{idAuthor}")
    public String editAuthor(@PathVariable("idAuthor") Integer idAuthor, Model model){

        Author authorById = authorService.findById(idAuthor);

        model.addAttribute("author", authorById);


        return "author/editAuthor";

    }

    @PostMapping("/proccessUpdateAuthor")
    public String proccessUpdateAuthor(@Valid @ModelAttribute("author") AuthorDto authorDto,
                                       BindingResult bindingResult, Model model){


        if(bindingResult.hasErrors()){

            return "author/editAuthor";
        }

        authorService.save(authorDto);

        return "redirect:index";

    }


    @GetMapping("/deleteAuthor/{idAuthor}")
    public String deleteAuthor(@PathVariable("idAuthor") Integer idAuthor, Model model){

        System.out.println("Berhasil Masuk Delete");

        authorService.deleteById(idAuthor);

        return "redirect:../index";

    }


    @RequestMapping(value = "/showBooks/{idAuthor}", method = RequestMethod.GET)
    public String showBooks(@PathVariable("idAuthor") Integer idAuthor, Model model){

        Author authorById = authorService.findById(idAuthor);

        model.addAttribute("author", authorById );

        return "author/showBooks";

    }

    @RequestMapping("/processSearching")
    public String processSearching(@RequestParam(defaultValue = "null") String search, Model model){

        List<Author> authorList = new ArrayList<>();

        if (search.equals("null")){
            authorList = authorService.findAll();
        } else {
            authorList = authorService.findByFullName(search);
        }

        model.addAttribute("authors", authorList);

        return "author/index";

    }



}
