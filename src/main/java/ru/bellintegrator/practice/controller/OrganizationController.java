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
import ru.bellintegrator.practice.transfer.DtoByFilter;
import ru.bellintegrator.practice.transfer.DtoSave;
import ru.bellintegrator.practice.transfer.DtoUpdate;
import ru.bellintegrator.practice.transfer.ShowDto;
import ru.bellintegrator.practice.transfer.ShowDtoFull;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.SuccessResponceView;


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
    public DataResponseView<OrganizationDto> getOrganizationsList(@Validated (DtoByFilter.class) @RequestBody OrganizationDto organizationDto){
        return organizationService.getOrganizationsListByFilter(organizationDto);
    }

    @JsonView(ShowDtoFull.class)
    @GetMapping(value = "/id")
    public DataResponseView<OrganizationDto> getOrganizationById(@PathVariable int id) {
        return organizationService.getOrganizationById(id);
    }

    @PostMapping(value = "/update")
    public SuccessResponceView updateOrganization(@Validated (DtoUpdate.class) @RequestBody OrganizationDto organizationDto) {
        return organizationService.update(organizationDto);
    }

    @PostMapping(value = "/save")
    public SuccessResponceView saveOrganization(@Validated (DtoSave.class) @RequestBody OrganizationDto organizationDto){
        return organizationService.save(organizationDto);
    }
}