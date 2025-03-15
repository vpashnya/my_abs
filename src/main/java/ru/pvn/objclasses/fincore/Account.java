package ru.pvn.objclasses.fincore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.pvn.objclasses.ObjectInDB;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Account implements ObjectInDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "acc_type")
    private AccType accType;

    @Column(name = "acc_num")
    private String accNum;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "client")
    private Client client;

    public enum AccType {ACTIVE, PASSIVE, MIXED}
}
