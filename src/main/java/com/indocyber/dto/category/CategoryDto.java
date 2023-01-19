package com.indocyber.dto.category;

import com.indocyber.entity.Book;
import com.indocyber.validation.UniqueCategoryName;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class CategoryDto {

    @NotNull
    @UniqueCategoryName(message = "category name sudah tersedia")
    private String name;

    @NotNull
    @Pattern(regexp = "[\\s]*[1-9]+",message="lantai yang tersedia hanya 1-9")
   private String floor;

    @NotNull
   private String isle;

    @NotNull
    @Pattern(regexp = "[\\s]*[1-9]+",message="rak yang tersedia hanya 1-9")
   private String bay;


    public CategoryDto() {}

    public CategoryDto(String name, String floor, String isle, String bay) {
        this.name = name;
        this.floor = floor;
        this.isle = isle;
        this.bay = bay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getIsle() {
        return isle;
    }

    public void setIsle(String isle) {
        this.isle = isle;
    }

    public String getBay() {
        return bay;
    }

    public void setBay(String bay) {
        this.bay = bay;
    }

}
