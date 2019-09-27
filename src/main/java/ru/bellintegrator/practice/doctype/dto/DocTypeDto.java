package ru.bellintegrator.practice.doctype.dto;

import ru.bellintegrator.practice.doctype.model.DocType;

public class DocTypeDto {

    private String name;
    private int code;

    public DocTypeDto(DocType docType) {
        this.name = docType.getName();
        this.code = docType.getCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
