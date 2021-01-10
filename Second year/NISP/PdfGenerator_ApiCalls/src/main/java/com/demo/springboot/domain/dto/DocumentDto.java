package com.demo.springboot.domain.dto;

public class DocumentDto {
    private  String name;
    private  String extension;
    private Double size;

    public DocumentDto() {}

    public DocumentDto(String name,String extension, Double size) {
        this.name = name;
        this.extension = extension;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public Double getSize() {
        return size;
    }
}
