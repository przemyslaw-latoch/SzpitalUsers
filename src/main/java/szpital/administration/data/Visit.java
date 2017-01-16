/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_visits")
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "Visit.findAll", query = "SELECT v FROM Visit v WHERE v.visitDate > CURRENT_TIMESTAMP"),})
public class Visit implements Serializable {

    @Id
    @SequenceGenerator(name = "adm_visits_id_seq",
            sequenceName = "adm_visits_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "adm_visits_id_seq")
    Long id;
    @Column(name = "id_pat")
    Long id_pat;
    @Column(name = "id_doc")
    Long id_doc;
    @Column(name = "date")
    Timestamp visitDate;
}
