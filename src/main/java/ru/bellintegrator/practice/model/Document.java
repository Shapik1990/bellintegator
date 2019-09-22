package ru.bellintegrator.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

/**
 * Документ пользователя
 */
@Entity
public class Document {

    @Id
    private Integer id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Номер документа
     */
    @Column(name = "doc_number", length = 20)
    private String docNumber;

    /**
     * Дата выдачи документа
     */
    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    /**
     * Код документа, внешний ключ на таблицу {@link DocType}
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_code")
    private DocType docCode;

    /**
     * Первичный ключ, совпадает с первичным ключом пользователя,
     * которому принадлежит документ в таблице {@link User}
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

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

    public DocType getDocCode() {
        return docCode;
    }

    public void setDocCode(DocType docCode) {
        this.docCode = docCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
