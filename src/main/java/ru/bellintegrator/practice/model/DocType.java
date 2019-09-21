package ru.bellintegrator.practice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Справочная таблица с кодами и названиями документов РФ
 */
@Entity
@Table(name = "doc_type")
public class DocType {

    /**
     * Код документа, он же является первичным ключом таблицы
     */
    @Id
    @Column(name = "code")
    private Integer code;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Название документа
     */
    @Column(length = 113, nullable = false)
    private String name;


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
}
