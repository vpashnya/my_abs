package ru.pvn.levelup.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.pvn.levelup.abscore.ObjectInDB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "deposit")
public class Deposit implements ObjectInDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="client_ext_id")
    private Integer clientExtId;

    @Column(name="client_name")
    private String clientName;

    @Column(name="account_ext_id")
    private Integer accountExtId;

    @Column(name="account_num")
    private String accountNum;

    @Column
    private BigDecimal amount;

    @Column
    private BigDecimal rate;

    @Column
    private Integer duration;

    @Column(name="date_open")
    private LocalDate dateOpen;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name="pay_to")
    private PayTo payTo;

    @Transient
    private BigDecimal accountRest;

    public enum Status {NEW, WORK, CLOSED, REFUSED}

    public enum PayTo{TO_CASH, IN_BANK}


}
