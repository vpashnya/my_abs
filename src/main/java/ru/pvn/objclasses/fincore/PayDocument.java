package ru.pvn.objclasses.fincore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pay_document")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PayDocument implements ObjectInDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "debet")
    private Account debet;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "credit")
    private Account credit;

    @Column(name = "doc_sum")
    private BigDecimal docSum;

    @Column(name = "doc_date")
    private LocalDate docDate;

    @Column
    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        NEW, EXECUTED, REFUSED
    }

    @Override
    public String toString() {
        return "PayDocument{" +
                "id=" + id +
                ", debet=" + debet.getAccNum() +
                ", credit=" + credit.getAccType() +
                ", docSum=" + docSum +
                ", docDate=" + docDate +
                ", state=" + state +
                '}';
    }
}
