package ru.bellintegrator.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Set;

@Entity
public class Country {

    @Id
    @Column(name = "code")
    private Integer code;

    @Version
    private Integer version;

    @Column(name = "name", length = 130, nullable = false)
    private String name;

    @OneToMany(mappedBy = "citizenshipCode")
    private Set<User> users;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}