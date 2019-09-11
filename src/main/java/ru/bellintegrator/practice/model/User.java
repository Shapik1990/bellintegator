package ru.bellintegrator.practice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(name = "second_name", length = 35)
    private String secondName;

    @Column(name = "middle_name", length = 25)
    private String middleName;

    @Column(length = 30, nullable = false)
    private String position;

    @Column(length = 11)
    private String phone;

    @Column(name = "is_identified", nullable = false)
    private Boolean isIdentified;

    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office officeId;

    @OneToOne(mappedBy = "user", optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    private Document documentId;

    @ManyToOne
    @JoinColumn(name = "citizenship_code")
    private Country citizenshipCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Office getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Office officeId) {
        this.officeId = officeId;
    }

    public Document getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Document documentId) {
        this.documentId = documentId;
    }

    public Country getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Country citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}
