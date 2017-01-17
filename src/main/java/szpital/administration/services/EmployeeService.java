/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.services;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import szpital.administration.data.Contract;
import szpital.administration.data.ContractType;
import szpital.administration.data.Employee;
import szpital.administration.data.WorkTime;

@Stateless
public class EmployeeService {

    @PersistenceContext(unitName = "medsystem")
    private EntityManager em;

    public List<Employee> getAllEmployeeList() {
        return em.createNamedQuery("Employee.findAll", Employee.class)
                .getResultList();
    }

    public List<ContractType> getAllContractTypes() {
        return em.createNamedQuery("ContractType.findAll", ContractType.class)
                .getResultList();
    }

    public List<WorkTime> getAllWorkTime() {
        return em.createNamedQuery("WorkTime.findAll", WorkTime.class)
                .getResultList();
    }

    public Employee getEmployee(Long id) {
        return em.find(Employee.class, id);
    }

    public void save(Employee employee) {
        em.persist(employee);
        em.getEntityManagerFactory().getCache().evictAll();
    }

    public void save(ContractType ct) {
        em.persist(ct);
        em.getEntityManagerFactory().getCache().evictAll();
    }

    public void save(WorkTime wt) {
        em.persist(wt);
        em.getEntityManagerFactory().getCache().evictAll();
    }

    public void save(Contract c) {
        em.persist(c);
        em.getEntityManagerFactory().getCache().evictAll();
    }

    public void update(Employee employee) {
        em.merge(employee);
    }

    public void remove(Employee employee) {
        employee = em.merge(employee);
        em.remove(employee);
        em.getEntityManagerFactory().getCache().evictAll();
    }

    public void fireEmployee(Long id) {
        Employee e = getEmployee(id);
        if (e != null) {
            Contract c = e.getLatestContract();
            if (c != null) {
                c.setDateTo(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                em.merge(c);
                em.getEntityManagerFactory().getCache().evictAll();
            }

        }
    }
}
