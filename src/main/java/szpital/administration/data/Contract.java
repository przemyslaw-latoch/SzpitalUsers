/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.data;

import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

/**
 *
 * @author lion
 */
@Entity
@Table(name = "contract")
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "Contract.findAll", query = "SELECT c FROM Contract c"),})
public class Contract implements Serializable {

    @Id
    @SequenceGenerator(name = "contract_id_seq",
            sequenceName = "contract_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "contract_id_seq")
    Long id;

    @Column(name = "dateFrom")
    java.sql.Date dateFrom;

    @Column(name = "dateTo")
    Date dateTo;

    @Column(name = "salary")
    float salary;

    @Column(name = "bonus")
    float bonus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idContractType")
    private ContractType contractType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idWorkTime")
    private WorkTime workTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmployee")
    private Employee employee;

    public Date getDateFrom() {
        return dateFrom;
    }

    public float getBonus() {
        return bonus;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

}
