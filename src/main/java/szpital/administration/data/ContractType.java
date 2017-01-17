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
import lombok.Getter;

/**
 *
 * @author lion
 */

@Entity
@Table(name = "contractType")
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "ContractType.findAll", query = "SELECT c FROM ContractType c"),})
public class ContractType implements Serializable {

    @Id
    @SequenceGenerator(name = "contracttype_id_seq",
            sequenceName = "contracttype_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "contracttype_id_seq")
    Long id;

    @Column(name = "name")
    String name;

    
}
