package ru.bellintegrator.practice.dto;


import com.fasterxml.jackson.annotation.JsonView;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.transfer.DtoByFilter;
import ru.bellintegrator.practice.transfer.DtoSave;
import ru.bellintegrator.practice.transfer.DtoUpdate;
import ru.bellintegrator.practice.transfer.ShowDto;
import ru.bellintegrator.practice.transfer.ShowDtoFull;

import javax.validation.constraints.NotNull;

public class UserDto {

    @JsonView(ShowDto.class)
    @NotNull(groups = {DtoByFilter.class, DtoUpdate.class})
    private int id;

    @JsonView(ShowDto.class)
    @NotNull(groups = {DtoSave.class, DtoUpdate.class})
    private String firstName;

    @JsonView(ShowDto.class)
    private String secondName;

    @JsonView(ShowDto.class)
    private String middleName;

    @JsonView(ShowDto.class)
    @NotNull(groups = {DtoSave.class, DtoUpdate.class})
    private String position;

    @JsonView(ShowDtoFull.class)
    private String phone;

    @JsonView(ShowDtoFull.class)
    private String docName;

    @JsonView(ShowDtoFull.class)
    private Long docNumber;

    @JsonView(ShowDtoFull.class)
    private String docDate;

    @JsonView(ShowDtoFull.class)
    private String citizenshipName;

    @JsonView(ShowDtoFull.class)
    private Integer citizenshipCode;

    @JsonView(ShowDtoFull.class)
    private boolean isIdentified;

    @NotNull(groups = DtoSave.class)
    private int officeId;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.middleName = user.getMiddleName();
        this.position = user.getPosition();
        this.phone = user.getPhone();
        if (user.getDocumentId() != null){
            this.docName = user.getDocumentId().getDocCode().getName();
            this.docNumber = user.getDocumentId().getDocNumber();
            this.docDate = user.getDocumentId().getDocDate().toString();
        }
        if (user.getCitizenshipCode() != null){
            this.citizenshipName = user.getCitizenshipCode().getName();
            this.citizenshipCode = user.getCitizenshipCode().getCode();
        }
        this.isIdentified = user.getIdentified();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Long getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(Long docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
}
