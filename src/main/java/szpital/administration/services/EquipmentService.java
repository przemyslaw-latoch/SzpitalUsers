package szpital.administration.services;

import java.util.List;
import szpital.administration.data.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EquipmentService {
    
    @PersistenceContext(unitName = "medsystem")
    private EntityManager em;
    
    public List<Equipment> getAllEquipment() {
        return em.createNamedQuery("Equipment.findAll", Equipment.class)
                .getResultList();
    }
    
    public Equipment getEquipment(Long id) {
        return em.createNamedQuery("Equipment.findById", Equipment.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public void save(Equipment equipment) {
        em.persist(equipment);
    }
    
    public void update(Equipment equipment) {
        em.merge(equipment);
    }
    
    public void remove(Equipment equipment) {
        em.getTransaction();
        em.remove(equipment);
        em.getTransaction().commit();
    }
}
