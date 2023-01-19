package com.indocyber.controller;


import com.indocyber.dto.book.BookDto;
import com.indocyber.dto.book.BookDtoUpdate;
import com.indocyber.dto.category.CategoryDto;
import com.indocyber.dto.category.CategoryDtoUpdate;
import com.indocyber.entity.Author;
import com.indocyber.entity.Book;
import com.indocyber.entity.Category;
import com.indocyber.entity.Customer;
import com.indocyber.service.AuthorService;
import com.indocyber.service.BookService;
import com.indocyber.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/index")
    public String index(Model model){

        List<Category> categoryList = categoryService.findAll();

        model.addAttribute("categoryList", categoryList);



        return "book/index";
    }

    @GetMapping("/addCategory")
    public String addCategory(Model model){

        Category category = new Category();

        model.addAttribute("category", category);


        return "book/addCategory";

    }

    @PostMapping("/proccessCategory")
    public String proccessCategory(@Valid @ModelAttribute("category") CategoryDto categoryDto,
                                   BindingResult bindingResult){


        if (bindingResult.hasErrors()){
            return "book/addCategory";
        }

        categoryService.save(categoryDto);

        return "redirect:index";
    }

    @GetMapping("editCategory/{nameCategory}")
    public String editCategory(@PathVariable("nameCategory") String nameCategory, Model model){

        Category categoryById = categoryService.findById(nameCategory);

        model.addAttribute("category", categoryById);

        return "book/editCategory";

    }

    @PostMapping("/proccessUpdateCategory")
    public String proccessUpdateCategory(@Valid @ModelAttribute("category") CategoryDtoUpdate categoryDtoUpdate,
                                         BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "book/editCategory";
        }

        categoryService.save(categoryDtoUpdate);

        return "redirect:index";

    }

    @GetMapping("/deleteCategory/{categoryName}")
    public String deleteCategory(@PathVariable("categoryName") String categoryName, Model model){

        categoryService.deleteById(categoryName);

        return "redirect:../index";

    }

    @GetMapping("/showBooks/{categoryName}")
    public String showBooks(@PathVariable("categoryName") String categoryName, Model model){

        Category category = categoryService.findById(categoryName);

        List<Book> bookList = bookService.findByCategory(categoryName);

        model.addAttribute("category", category);
        model.addAttribute("books", bookList);
        return "book/showBook";

    }

    @GetMapping("/addBookByCategory/{categoryName}")
    public String addBookByCategory(@PathVariable("categoryName") String categoryName, Model model){

        Book book = new Book();
        List<Author> authorList = authorService.findAll();

        book.setCategoryName(categoryService.findById(categoryName));

        model.addAttribute("book", book);
        model.addAttribute("authorList", authorList);

        return "book/addBookByCategory";

    }


    @PostMapping("/proccessBook")
    public String proccessBook(@Valid @ModelAttribute("book") BookDto bookDto,
                                   BindingResult bindingResult, Model model){


        if (bindingResult.hasErrors()){

            List<Author> authorList = authorService.findAll();
            model.addAttribute("authorList", authorList);
            return "book/addBookByCategory";
        }

        bookService.save(bookDto);

        return "redirect:index";
    }


    @GetMapping("/editBookByCategory/{codeBook}")
    public String editBookByCategory(@PathVariable("codeBook") String codeBook, Model model){

        Book book = bookService.findById(codeBook);
        List<Author> authorList = authorService.findAll();

        model.addAttribute("book", book);
        model.addAttribute("authorList", authorList);

        return "book/editBookByCategory";

    }

    @PostMapping("/proccessUpdateBook")
    public String proccessUpdateBook(@Valid @ModelAttribute("book") BookDtoUpdate bookDtoUpdate,
                               BindingResult bindingResult, Model model){


        if (bindingResult.hasErrors()){

            List<Author> authorList = authorService.findAll();
            model.addAttribute("authorList", authorList);
            return "book/editBookByCategory";
        }


        bookService.save(bookDtoUpdate);

        return "redirect:index";
    }


    @GetMapping("/deleteBookByCategory/{codeBook}")
    public String deleteBookByCategory(@PathVariable("codeBook") String codeBook, Model model){


        Book book = bookService.findById(codeBook);

        if (book.getBorrowed().booleanValue() == true){

            Category category = categoryService.findById(book.getCategoryName().getName());

            List<Book> bookList = bookService.findByCategory(category.getName());


            model.addAttribute("category", category);
            model.addAttribute("books", bookList);
            model.addAttribute("error", "buku " + book.getTitle() + " masih dipinjam");
            return "book/showBook";
        }


        bookService.deleteById(codeBook);

        return "redirect:../index";

    }


    @RequestMapping("/processSearching")
    public String processSearching(@RequestParam(defaultValue = "null") String search, Model model){

        List<Category> categoryList = new ArrayList<>();

        if (search.equals("null")){
            categoryList = categoryService.findAll();
        } else {
            categoryList = categoryService.findByName(search);
        }

        model.addAttribute("categoryList", categoryList);

        return "book/index";

    }




}
