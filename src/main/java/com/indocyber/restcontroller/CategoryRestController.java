package com.indocyber.restcontroller;

import com.indocyber.dto.category.CategoryDto;
import com.indocyber.dto.category.CategoryDtoUpdate;
import com.indocyber.entity.Book;
import com.indocyber.entity.Category;
import com.indocyber.exceptionhandling.ObjectNotFound;
import com.indocyber.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryRestController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategory(){

        List<Category> categoryList = categoryService.findAll();

        return new ResponseEntity<>(categoryList, HttpStatus.FOUND);

    }

    @GetMapping("{idCategory}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("idCategory") String idCategory){

        Category category = categoryService.findById(idCategory);

        if (category == null){
            throw new ObjectNotFound("Category With ID: " + idCategory + " not found!!!");
        }

        return new ResponseEntity<>(category, HttpStatus.FOUND);

    }

    @GetMapping("{idCategory}/book")
    public ResponseEntity<List<Book>> getBookByCategory(@PathVariable("idCategory") String idCategory){

        Category category = categoryService.findById(idCategory);

        if (category == null){
            throw new ObjectNotFound("Category With ID: " + idCategory + " not found!!!");
        }

        return new ResponseEntity<>(category.getBookList(), HttpStatus.FOUND);

    }

    @PostMapping()
    public ResponseEntity<String> addCategory(@Valid @RequestBody CategoryDto categoryDto){


        categoryService.save(categoryDto);


        return new ResponseEntity<>("category has been created", HttpStatus.ACCEPTED);

    }

    @PutMapping("{idCategory}")
    public ResponseEntity<String> editCategory(@Valid @RequestBody CategoryDtoUpdate categoryDtoUpdate,
                                               @PathVariable("idCategory") String idCategory){

        Category category = categoryService.findById(idCategory);

        if (category == null){
            throw new ObjectNotFound("Category With ID: " + idCategory + " not found!!!");
        }

        categoryService.save(categoryDtoUpdate);


        return new ResponseEntity<>("category has been created", HttpStatus.ACCEPTED);

    }

    @DeleteMapping("{idCategory}")
    public ResponseEntity<String> deleteCategory( @PathVariable("idCategory") String idCategory){

        Category category = categoryService.findById(idCategory);

        if (category == null){
            throw new ObjectNotFound("Category With ID: " + idCategory + " not found!!!");
        }

        categoryService.deleteById(idCategory);
        return new ResponseEntity<>("category has been updated", HttpStatus.OK);

    }


}
