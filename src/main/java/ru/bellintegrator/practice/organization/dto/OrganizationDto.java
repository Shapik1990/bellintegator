package ru.bellintegrator.practice.organization.dto;

import com.fasterxml.jackson.annotation.JsonView;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.validation.DtoByFilter;
import ru.bellintegrator.practice.validation.DtoSave;
import ru.bellintegrator.practice.validation.DtoUpdate;
import ru.bellintegrator.practice.validation.ShowDto;
import ru.bellintegrator.practice.validation.ShowDtoFull;

import javax.validation.constraints.NotNull;

public class OrganizationDto {

    @JsonView(ShowDto.class)
    @NotNull(groups = { DtoUpdate.class})
    private Integer id;

    @JsonView(ShowDto.class)
    @NotNull(groups = {DtoByFilter.class, DtoSave.class, DtoUpdate.class})
    private String name;

    @JsonView(ShowDtoFull.class)
    @NotNull(groups = {DtoSave.class, DtoUpdate.class})
    private String fullName;

    @JsonView(ShowDtoFull.class)
    @NotNull(groups = {DtoSave.class, DtoUpdate.class})
    private String inn;

    @JsonView(ShowDtoFull.class)
    @NotNull(groups = {DtoSave.class, DtoUpdate.class})
    private String kpp;

    @JsonView(ShowDtoFull.class)
    @NotNull(groups = {DtoSave.class, DtoUpdate.class})
    private String address;

    @JsonView(ShowDtoFull.class)
    private String phone;

    @JsonView(ShowDto.class)
    private Boolean isActive;

    public OrganizationDto() {
    }

    public OrganizationDto(Organization org) {
        this.id = org.getId();
        this.name = org.getName();
        this.fullName = org.getFullName();
        this.inn = org.getInn();
        this.kpp = org.getKpp();
        this.address = org.getAddress();
        this.phone = org.getPhone();
        this.isActive = org.isActive();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
