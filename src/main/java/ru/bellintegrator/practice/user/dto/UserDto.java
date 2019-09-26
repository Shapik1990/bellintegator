package ru.bellintegrator.practice.user.dto;


import com.fasterxml.jackson.annotation.JsonView;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.validation.DtoByFilter;
import ru.bellintegrator.practice.validation.DtoSave;
import ru.bellintegrator.practice.validation.DtoUpdate;
import ru.bellintegrator.practice.validation.ShowDto;
import ru.bellintegrator.practice.validation.ShowDtoFull;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserDto {

    @JsonView(ShowDto.class)
    @NotNull(groups = DtoUpdate.class)
    private Integer id;

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

    private Integer docCode;

    @JsonView(ShowDtoFull.class)
    private String docName;

    @JsonView(ShowDtoFull.class)
    private String docNumber;

    @JsonView(ShowDtoFull.class)
    private Date docDate;

    @JsonView(ShowDtoFull.class)
    private String citizenshipName;

    @JsonView(ShowDtoFull.class)
    private Integer citizenshipCode;

    @JsonView(ShowDtoFull.class)
    private Boolean isIdentified;

    @NotNull(groups = {DtoByFilter.class, DtoSave.class})
    private Integer officeId;

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
            this.docDate = user.getDocumentId().getDocDate();
        }
        if (user.getCitizenshipCode() != null){
            this.citizenshipName = user.getCitizenshipCode().getName();
            this.citizenshipCode = user.getCitizenshipCode().getCode();
        }
        this.isIdentified = user.getIdentified();
    }

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
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

    public Boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }
}
