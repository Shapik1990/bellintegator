package ru.bellintegrator.practice.user.model;

import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    /**
     * Первичный ключ
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Имя пользователя
     */
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "second_name", length = 35)
    private String secondName;

    /**
     * Отчество пользователя
     */
    @Column(name = "middle_name", length = 25)
    private String middleName;

    /**
     * Должность пользователя
     */
    @Column(length = 30, nullable = false)
    private String position;

    /**
     * Телефон пользователя
     */
    @Column(length = 11)
    private String phone;

    /**
     * Идентификация пользователя
     */
    @Column(name = "is_identified", nullable = false)
    private boolean isIdentified;

    /**
     * Офис к которому прикреплен клиент, внешний ключ на теблицу {@link Office}
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office officeId;

    /**
     * Документ клиента
     */
    @OneToOne(mappedBy = "user", optional = false, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserDocument documentId;

    /**
     * Код страны клиента, внешний ключ на таблицу {@link Country}
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_code")
    private Country citizenshipCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public UserDocument getDocumentId() {
        return documentId;
    }

    public void setDocumentId(UserDocument documentId) {
        this.documentId = documentId;
    }

    public Country getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Country citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

}
