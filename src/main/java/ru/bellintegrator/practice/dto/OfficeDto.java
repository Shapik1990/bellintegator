package ru.bellintegrator.practice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.transfer.DtoByFilter;
import ru.bellintegrator.practice.transfer.DtoSave;
import ru.bellintegrator.practice.transfer.DtoUpdate;
import ru.bellintegrator.practice.transfer.ShowDto;
import ru.bellintegrator.practice.transfer.ShowDtoFull;

import javax.validation.constraints.NotNull;

public class OfficeDto {

    @JsonView(ShowDto.class)
    @NotNull(groups = {DtoByFilter.class, DtoUpdate.class})
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
    private boolean isActive;

    @NotNull(groups = DtoSave.class)
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}
