package ru.pvn.objclasses.fincore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.pvn.objclasses.ObjectInDB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Client implements ObjectInDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="full_name")
    private String fullName;

    @Column
    private String inn;


}
