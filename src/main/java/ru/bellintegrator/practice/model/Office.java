package ru.bellintegrator.practice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Set;

@Entity
public class Office {

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
     * Название офиса
     */
    @Column(length = 20)
    private String name;

    /**
     * Адрес офиса
     */
    @Column(length = 50)
    private String address;

    /**
     * Телефон офиса
     */
    @Column(length = 11)
    private String phone;

    /**
     * Активность офиса
     */
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    /**
     * Пользователи, которые прикреплены к офису
     */
    @OneToMany(mappedBy = "officeId",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> userSet;

    /**
     * Организации которой принадлежит офис, внешний ключ на таблицу {@link Organization}
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization orgId;

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

    public Organization getOrgId() {
        return orgId;
    }

    public void setOrgId(Organization orgId) {
        this.orgId = orgId;
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

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
