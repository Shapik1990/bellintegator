package ru.bellintegrator.practice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.transfer.DtoByFilter;
import ru.bellintegrator.practice.transfer.DtoSave;
import ru.bellintegrator.practice.transfer.DtoUpdate;
import ru.bellintegrator.practice.transfer.ShowDto;
import ru.bellintegrator.practice.transfer.ShowDtoFull;

import javax.validation.constraints.NotNull;

public class OrganizationDto {

    @JsonView(ShowDto.class)
    @NotNull(groups = {DtoByFilter.class, DtoUpdate.class})
    private Integer id;

    @JsonView(ShowDto.class)
    @NotNull(groups = {DtoSave.class, DtoUpdate.class})
    private String name;

    @JsonView(ShowDtoFull.class)
    @NotNull(groups = {DtoSave.class, DtoUpdate.class})
    private String fullName;

    @JsonView(ShowDtoFull.class)
    @NotNull(groups = {DtoSave.class, DtoUpdate.class})
    private Long inn;

    @JsonView(ShowDtoFull.class)
    @NotNull(groups = {DtoSave.class, DtoUpdate.class})
    private int kpp;

    @JsonView(ShowDtoFull.class)
    @NotNull(groups = {DtoSave.class, DtoUpdate.class})
    private String address;

    @JsonView(ShowDtoFull.class)
    private String phone;

    @JsonView(ShowDto.class)
    private boolean isActive;

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

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public int getKpp() {
        return kpp;
    }

    public void setKpp(int kpp) {
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
