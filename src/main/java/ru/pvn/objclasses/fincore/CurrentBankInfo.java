package ru.pvn.objclasses.fincore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pvn.objclasses.ObjectInDB;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "current_bank_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrentBankInfo implements ObjectInDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "oper_day")
    private LocalDate operDay;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "bank_client")
    private Client bankClient;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "corr_account")
    private Account corrAccount;

    @Column(name = "bank_code")
    private String bankCode;

    @Override
    public String toString() {
        return "CurrentBankInfo{" +
                "id=" + id +
                ", operDay=" + operDay +
                ", bankClient=" + bankClient +
                ", corrAccount=" + corrAccount.getAccNum() +
                ", bankCode='" + bankCode + '\'' +
                '}';
    }
}
