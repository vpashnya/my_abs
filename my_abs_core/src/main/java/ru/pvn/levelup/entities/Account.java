package ru.pvn.levelup.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.pvn.levelup.abscore.ObjectInDB;

import javax.persistence.*;
import java.math.BigDecimal;


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

    @Transient
    private BigDecimal rest;

    public enum AccType {ACTIVE, PASSIVE, MIXED}
}
