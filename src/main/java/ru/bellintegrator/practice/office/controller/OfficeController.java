package ru.bellintegrator.practice.office.controller;

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
import ru.bellintegrator.practice.office.dto.OfficeDto;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.validation.DtoByFilter;
import ru.bellintegrator.practice.validation.DtoSave;
import ru.bellintegrator.practice.validation.DtoUpdate;
import ru.bellintegrator.practice.validation.ShowDto;
import ru.bellintegrator.practice.validation.ShowDtoFull;
import ru.bellintegrator.practice.view.SuccessResponseView;

import java.util.List;


@RestController
@RequestMapping(value = "/office", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeController {

    private OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @JsonView(ShowDto.class)
    @PostMapping(value = "/list" )
    public List<OfficeDto> getOfficeList(@Validated(DtoByFilter.class) @RequestBody OfficeDto officeDto){
        return officeService.getOfficesListByFilter(officeDto);
    }

    @JsonView(ShowDtoFull.class)
    @GetMapping(value = "/{id:\\d+}")
    public OfficeDto getOfficenById(@PathVariable int id) {
        return officeService.getOfficeById(id);
    }

    @PostMapping(value = "/update")
    public SuccessResponseView updateOffice(@Validated (DtoUpdate.class) @RequestBody OfficeDto officeDto) {
        officeService.update(officeDto);
        return new SuccessResponseView();
    }

    @PostMapping(value = "/save")
    public SuccessResponseView saveOffice(@Validated (DtoSave.class) @RequestBody OfficeDto officeDto){
        officeService.save(officeDto);
        return new SuccessResponseView();
    }
}