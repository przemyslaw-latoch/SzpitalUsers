/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import szpital.administration.data.Employee;
import szpital.administration.data.Equipment;
import szpital.administration.services.EmployeeService;


@ManagedBean
@SessionScoped
@Getter
@Setter
public class EmployeeListView implements Serializable {
     @Inject
    private EmployeeService employeeService;

    private String msg;
    private Long employeeId;
    
    public List<Employee> getEmployeeList() {
        List<Employee> employees = employeeService.getAllEmployeeList();
        return employees;
    }

    public void employeeOnSelect(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void removeEquipment() {  
        if (employeeId != null) {
            employeeService.remove(employeeService.getEmployee(employeeId));
        }
    }
}
