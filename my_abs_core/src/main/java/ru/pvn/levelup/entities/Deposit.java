package ru.pvn.levelup.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.pvn.levelup.abscore.ObjectInDB;

import javax.persistence.*;
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
    Integer id;

    @Column(name="client_ext_id")
    Integer clientExtId;

    @Column(name="client_name")
    String clientName;

    @Column(name="account_ext_id")
    Integer accountExtId;

    @Column(name="account_num")
    String accountNum;

    @Column
    BigDecimal amount;

    @Column
    BigDecimal rate;

    @Column
    Integer duration;

    @Column(name="date_open")
    LocalDate dateOpen;

    @Enumerated(EnumType.STRING)
    @Column
    Status status;

    @Enumerated(EnumType.STRING)
    @Column(name="pay_to")
    PayTo payTo;

    public enum Status {NEW, WORK, CLOSED, REFUSED}

    public enum PayTo{TO_CASH, IN_BANK}

    @Transient
    private BigDecimal accountRest;
}
