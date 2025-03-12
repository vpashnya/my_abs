package ru.pvn.objclasses.cashwindow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.pvn.objclasses.ObjectInDB;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cash_document_in")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CashDocumentInternal implements ObjectInDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer debet;

    @Column
    private Integer credit;

    @Column(name = "doc_sum")
    private BigDecimal docSum;

    @Column(name = "doc_date")
    private LocalDate docDate;

    @Column(name = "exec_info")
    private String executeInfo;

    @Column
    private State state;

    public enum State {
        NEW, SENDED, EXECUTED, REFUSED
    }

}
