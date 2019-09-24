package ru.bellintegrator.practice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.validation.DtoByFilter;
import ru.bellintegrator.practice.validation.DtoSave;
import ru.bellintegrator.practice.validation.DtoUpdate;
import ru.bellintegrator.practice.validation.ShowDto;
import ru.bellintegrator.practice.validation.ShowDtoFull;

import javax.validation.constraints.NotNull;

public class OfficeDto {

    @JsonView(ShowDto.class)
    @NotNull(groups = DtoUpdate.class)
    private Integer id;

    @JsonView(ShowDto.class)
    @NotNull(groups = DtoUpdate.class)
    private String name;

    @JsonView(ShowDtoFull.class)
    @NotNull(groups = DtoUpdate.class)
    private String address;

    @JsonView(ShowDtoFull.class)
    private String phone;

    @JsonView(ShowDto.class)
    private Boolean isActive;

    @NotNull(groups = {DtoByFilter.class, DtoSave.class})
    private Integer orgId;

    public OfficeDto() {
    }

    public OfficeDto(Office office) {
        this.id = office.getId();
        this.name = office.getName();
        this.address = office.getAddress();
        this.phone = office.getPhone();
        this.isActive = office.isActive();
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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}
