/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.data;

import lombok.Getter;
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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author lion
 */
@Entity
@Table(name = "employee")
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
//    @NamedQuery(name = "Employee.findByname", query = "SELECT e FROM employee e WHERE e.name = :name"),
    @NamedQuery(name = "Employee.find", query = "SELECT e FROM Employee e WHERE e.name = 'matt'")

})
public class Employee implements Serializable {

    @Id
    @SequenceGenerator(name = "employee_id_seq",
            sequenceName = "employee_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "employee_id_seq")
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "middleName")
    String middleName;
    @Column(name = "surname")
    String surname;
    @Column(name = "phoneNumber")
    String phoneNumber;
    @Column(name = "adress")
    String adress;
    @Column(name = "pesel")
    String pesel;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Contract> contracts;

    public List<Contract> getContracts() {
        return contracts;
    }

    private Contract getLatestContract() {
        if (!contracts.isEmpty()) {

            Contract last = contracts.get(0);

            for (Contract c : contracts) {
                if (c.getDateFrom().after(last.getDateFrom())) {
                    last = c;
                }
            }
            return last;

        }
        return null;
    }

    public float getSalaryValue() {

        Contract c = getLatestContract();
        if (c != null) {
            return c.salary * (1 + c.bonus);
        }
        return 0;
    }

    public String getContractParameters() {
        Contract c = getLatestContract();
        if (c != null) {
            ContractType ct = c.getContractType();
            String result = (ct != null) ? ct.name : "";
            WorkTime wt = c.getWorkTime();
            result += " " + wt.getName();
            return result;
        }
        return " ";
    }

    public String getContractDataPeriod() {
        Contract c = getLatestContract();
        if (c != null) {
            String result = c.getDateFrom() != null ? c.getDateFrom().toString() : " ";
            result += " - ";
            result += c.getDateTo() != null ? c.getDateTo().toString() : " ";
            return result;
        }
        return " ";
    }
}
