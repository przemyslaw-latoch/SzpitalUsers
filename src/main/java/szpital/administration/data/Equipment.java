package szpital.administration.data;

import szpital.users.data.*;
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

@Entity
@Table(name = "equipment")
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "Equipment.findAll", query = "SELECT e FROM Equipment e"),
    @NamedQuery(name = "Equipment.findById", query = "SELECT e FROM Equipment e WHERE e.id = :id"),})
public class Equipment implements Serializable {
    
    @Id
    @SequenceGenerator(name = "equipment_id_seq",
            sequenceName = "equipment_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "equipment_id_seq")
    Long id;
    @Column(name = "name")
    String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    EquipmentType type;
    @Column(name = "description")
    String description;
}
