package ru.pvn.levelup.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.pvn.levelup.abscore.ObjectInDB;

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
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "deposit_operation")
public class DepositOperation implements ObjectInDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "deposit")
    private Deposit deposit;

    @Column(name = "oper_date")
    private LocalDate operDate;

    @Column(name = "oper_sum")
    private BigDecimal operSum;

    @Column(name = "pay_doc_id")
    private Integer payDocId;

    @Column(name = "cash_oper_id")
    private Integer cashOperId;

    @Column(name = "purpose")
    private String purpose;

    @Enumerated(EnumType.STRING)
    @Column
    private State state;

    @Column(name = "refuse_reason")
    private String refuseReason;

    public enum State {NEW, SENDED, EXECUTED, REFUSED}

}
