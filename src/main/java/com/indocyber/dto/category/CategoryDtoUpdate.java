package com.indocyber.dto.category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CategoryDtoUpdate {

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "[\\s]*[1-9]+",message="lantai yang tersedia hanya 1-9")
    private String floor;

    @NotNull
    private String isle;

    @NotNull
    @Pattern(regexp = "[\\s]*[1-9]+",message="rak yang tersedia hanya 1-9")
    private String bay;


    public CategoryDtoUpdate() {}

    public CategoryDtoUpdate(String name, String floor, String isle, String bay) {
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
