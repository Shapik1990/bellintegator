package ru.bellintegrator.practice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.util.Set;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Version
    private Integer version;

    @Column(length = 30,nullable = false)
    private String name;

    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(length = 10, nullable = false, unique = true)
    private Long inn;

    @Column(nullable = false, length = 9)
    private int kpp;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(length = 11)
    private String phone;

    @Column(name = "is_active",nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "orgId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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

    public Set<Office> getOfficeSet() {
        return officeSet;
    }

    public void setOfficeSet(Set<Office> officeSet) {
        this.officeSet = officeSet;
    }
}
