package ru.bellintegrator.practice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "doc_type")
public class DocType {

    @Id
    @Column(name = "code")
    private int code;

    @Column(name = "name", length = 113, nullable = false)
    private String name;

    @OneToMany(mappedBy = "docCode")
    private Set<Document> documents;

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
