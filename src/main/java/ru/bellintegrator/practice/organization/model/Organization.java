package ru.bellintegrator.practice.organization.model;


import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.Set;

@Entity
public class Organization {

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
     * Короткое название организации
     */
    @Column(length = 30,nullable = false)
    private String name;

    /**
     * Полное название организации
     */
    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    /**
     * ИНН организации
     */
    @Column(nullable = false, length = 10)
    private String inn;

    /**
     * КПП организации
     */
    @Column(nullable = false, length = 9)
    private String kpp;

    /**
     * Адрес организации
     */
    @Column(length = 50, nullable = false)
    private String address;

    /**
     * Телефон организации
     */
    @Column(length = 11)
    private String phone;

    /**
     * Активность организации
     */
    @Column(name = "is_active",nullable = false)
    private boolean isActive;

    /**
     * Офисы принадлежащие организации
     */
    @OneToMany(mappedBy = "orgId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Office> officeSet;

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Office> getOfficeSet() {
        return officeSet;
    }

    public void setOfficeSet(Set<Office> officeSet) {
        this.officeSet = officeSet;
    }

}
