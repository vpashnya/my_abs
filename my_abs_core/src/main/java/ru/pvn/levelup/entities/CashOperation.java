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
@Table(name = "cash_operation")
public class CashOperation implements ObjectInDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cash_point")
    private CashPoint cashPoint;

    @Column(name = "account_num")
    private String accountNum;

    @Column(name = "sum_doc")
    private BigDecimal sumDoc;

    @Column
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Column
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "refuse_reason")
    private String refuseReason;

    @Column
    private String purpose;

    public enum Direction {
        TO_BANK, FROM_BANK
    }

    public enum State {
        NEW, SENDED, EXECUTED, REFUSED
    }


}
