package com.example.demo.dto;

/**
 * This class is category data transfer object
 */
public class CategoryDto {
    private String category;

    public CategoryDto() {}

    public CategoryDto(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
