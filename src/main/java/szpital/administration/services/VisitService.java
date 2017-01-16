/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import szpital.administration.data.Visit;

@Stateless
public class VisitService {

    @PersistenceContext(unitName = "medsystem")
    private EntityManager em;

    public List<Visit> getVisits() {
        return em.createNamedQuery("Visit.findAll", Visit.class)
                .getResultList();
    }
    
    public void save(Visit visit) {
    em.persist(visit);
  }
}
