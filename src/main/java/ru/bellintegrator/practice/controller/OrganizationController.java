package ru.bellintegrator.practice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.organization.OrganizationService;
import ru.bellintegrator.practice.dto.OrganizationDto;
import ru.bellintegrator.practice.validation.DtoByFilter;
import ru.bellintegrator.practice.validation.DtoSave;
import ru.bellintegrator.practice.validation.DtoUpdate;
import ru.bellintegrator.practice.validation.ShowDto;
import ru.bellintegrator.practice.validation.ShowDtoFull;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.SuccessResponceView;

import java.util.List;


@RestController
@RequestMapping(value = "/organization", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController {

    private OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @JsonView(ShowDto.class)
    @PostMapping(value = "/list" )
    public DataResponseView getOrganizationsList(@Validated (DtoByFilter.class) @RequestBody OrganizationDto organizationDto){
        return organizationService.getOrganizationsListByFilter(organizationDto);
    }

    @JsonView(ShowDtoFull.class)
    @GetMapping(value = "/{id:\\d+}")
    public DataResponseView getOrganizationById(@PathVariable int id) {
        return organizationService.getOrganizationById(id);
    }

    @PostMapping(value = "/update")
    public SuccessResponceView updateOrganization(@Validated (DtoUpdate.class) @RequestBody OrganizationDto organizationDto) {
        organizationService.update(organizationDto);
        return new SuccessResponceView(true);
    }

    @PostMapping(value = "/save")
    public SuccessResponceView saveOrganization(@Validated (DtoSave.class) @RequestBody OrganizationDto organizationDto){
        organizationService.save(organizationDto);
        return new SuccessResponceView(true);
    }
}
