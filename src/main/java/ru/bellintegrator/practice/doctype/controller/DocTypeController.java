package ru.bellintegrator.practice.doctype.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.doctype.dto.DocTypeDto;
import ru.bellintegrator.practice.doctype.service.DocTypeService;

import java.util.List;

@RestController
@RequestMapping(value = "/docs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocTypeController {

    private DocTypeService service;

    @Autowired
    public DocTypeController(DocTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<DocTypeDto> getAllDocsType (){
        return service.getAllDocTypes();
    }
}
