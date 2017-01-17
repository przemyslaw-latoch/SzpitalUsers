/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import szpital.administration.data.*;
import szpital.administration.services.*;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class AddEmployeeView implements Serializable {

    @Inject
    private EmployeeService employeeService;

    private String msg;

    private Employee employee = new Employee();

    private ContractType contractType = new ContractType();

    private WorkTime workTime = new WorkTime();

    private Contract contract = new Contract();

    @Setter
    @Getter
    private java.util.Date dateFrom;

    @Setter
    @Getter
    private java.util.Date dateTo;

    public void saveEmployee() {
        if (!employee.getName().isEmpty() && employee.getSurname() != null) {

            contract.setDateFrom(new java.sql.Date(dateFrom.getTime()));
            contract.setDateTo(new java.sql.Date(dateTo.getTime()));

            employeeService.save(employee);
            contract.setWorkTime(workTime);
            contract.setContractType(contractType);
            contract.setEmployee(employee);

            employeeService.save(contract);
            employee.contracts = new ArrayList<Contract>();
            employee.contracts.add(contract);
            
            
        }
    }

    public List<String> getContractTypesNames() {
        List<ContractType> ct = employeeService.getAllContractTypes();
        List<String> list = new ArrayList<String>();
        for (ContractType t : ct) {
            list.add(t.getName());
        }
        return list;
    }

    public List<String> getWorkTimeNames() {
        List<WorkTime> wt = employeeService.getAllWorkTime();
        List<String> list = new ArrayList<String>();
        for (WorkTime t : wt) {
            list.add(t.getName());
        }
        return list;
    }

    private final Converter typeConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
            if (s == null) {
                return null;
            }
            List<ContractType> list = employeeService.getAllContractTypes();
            for (ContractType x : list) {
                if (x.getName().equals(s)) {
                    contractType = x;
                    return s;
                }
            }
            return null;
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

            String result = o != null ? ((String) o) : " ";
            return result;
        }
    };

    private final Converter timeConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
            if (s == null) {
                return null;
            }
            List<WorkTime> list = employeeService.getAllWorkTime();
            for (WorkTime x : list) {
                if (x.getName().equals(s)) {
                    workTime = x;
                    return s;
                }
            }
            return null;
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

            String result = o != null ? ((String) o) : " ";
            return result;
        }
    };

}
