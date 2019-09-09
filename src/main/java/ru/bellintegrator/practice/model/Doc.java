package ru.bellintegrator.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Doc {

    @Id
    @Column(name = "code")
    private int code;

    @Column(name = "name", length = 113, nullable = false)
    private String name;

    @OneToMany(mappedBy = "docCode")
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
}
