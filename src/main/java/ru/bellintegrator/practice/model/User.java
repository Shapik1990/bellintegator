package ru.bellintegrator.practice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

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

    @Column(name = "doc_number")
    private Long docNumber;

    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @Column(name = "is_identified", nullable = false)
    private Boolean isIdentified;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office officeId;

    @ManyToOne
    @JoinColumn(name = "doc_code")
    private Doc docCode;

    @ManyToOne
    @JoinColumn(name = "citizenship_code")
    private Country citizenshipCode;
}
