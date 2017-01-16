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
import szpital.administration.data.Employee;

@Stateless
public class EmployeeService {

    @PersistenceContext(unitName = "medsystem")
    private EntityManager em;

    public List<Employee> getAllEmployeeList() {
        return em.createNamedQuery("Employee.findAll", Employee.class)
                .getResultList();
    }

    public Employee getEmployee(Long id) {
        return em.find(Employee.class, id);
    }

    public void save(Employee employee) {
        em.persist(employee);
    }

    public void update(Employee employee) {
        em.merge(employee);
    }

    public void remove(Employee employee) {
        employee = em.merge(employee);
        em.remove(employee);
    }
}
