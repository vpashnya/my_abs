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
@Table(name = "fin_record")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FinRecord implements ObjectInDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "account")
    private Account account;

    @Column(name = "rec_date")
    private LocalDate recDate;

    @Column(name = "rec_sum")
    private BigDecimal recSum;

    @Enumerated(EnumType.STRING)
    private RecType recType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pay_document")
    private PayDocument payDocument;

    public enum RecType {
        DEBET(BigDecimal.valueOf(1)), CREDIT(BigDecimal.valueOf(-1));

        public final BigDecimal sign;

        RecType(BigDecimal sign) {
            this.sign = sign;
        }
    }

    @Override
    public String toString() {
        return "FinRecord{" +
                "id=" + id +
                ", account=" + account.getAccNum() +
                ", recDate=" + recDate +
                ", recSum=" + recSum +
                ", recType=" + recType +
                ", payDocument={" + payDocument.getId() + "}" +
                '}';
    }
}
