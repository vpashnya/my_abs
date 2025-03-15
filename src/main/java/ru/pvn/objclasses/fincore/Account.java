package ru.pvn.objclasses.fincore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.pvn.objclasses.ObjectInDB;

import javax.persistence.*;


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
    private AccType accType;

    @Column(name = "acc_num")
    private String accNum;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "client")
    private Client client;

    public enum AccType {ACTIVE, PASSIVE, MIXED}
}
