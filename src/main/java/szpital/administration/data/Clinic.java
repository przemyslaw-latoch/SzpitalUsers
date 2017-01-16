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

//@Entity
//@Table(name = "clinic")
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "Clinic.findAll", query = "SELECT c FROM Clinic c"),

})
public class Clinic implements Serializable {

    @Id
    @SequenceGenerator(name = "clinic_id_seq",
            sequenceName = "clinic_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "clinic_id_seq")
    Long id;
    @Column(name = "preference")
    String preference;
    @Column(name = "value")
    String value;

}
