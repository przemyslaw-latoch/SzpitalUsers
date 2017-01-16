package szpital.users.services;

import java.util.List;
import szpital.users.data.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import szpital.users.data.UserRole;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "medsystem")
    private EntityManager em;

    public List<User> getUsers() {
        return em.createNamedQuery("User.findAll", User.class)
                .getResultList();
    }

    public List<User> getUsersByRole(String role) {
        return em.createNamedQuery("User.findByRole", User.class)
                .setParameter("role", UserRole.valueOf(role))
                .getResultList();
    }

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
