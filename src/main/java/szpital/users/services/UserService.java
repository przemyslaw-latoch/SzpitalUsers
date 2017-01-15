package szpital.users.services;

import szpital.users.data.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {

  @PersistenceContext(unitName = "medsystem")
  private EntityManager em;

  public User getUser(String user) {
    return em.createNamedQuery("User.findByLogin", User.class)
        .setParameter("login", user)
        .getSingleResult();
  }

  public void save(User user) {
    em.persist(user);
  }

  public void update(User user) {
    em.merge(user);
  }
}