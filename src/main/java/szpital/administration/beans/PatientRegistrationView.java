/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import szpital.administration.data.Visit;
import szpital.administration.data.VisitTableData;
import szpital.administration.services.VisitService;
import szpital.users.data.User;
import szpital.users.services.AuthService;
import szpital.users.services.UserService;
import szpital.users.session.UserContext;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class PatientRegistrationView implements Serializable {

    private String currentDate;

    @PostConstruct
    public void init() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date today = Calendar.getInstance().getTime();
        currentDate = df.format(today);
    }

    @Inject
    private UserContext userContext;
    @Inject
    private AuthService authService;
    @Inject
    private UserService userService;
    @Inject
    private VisitService visitsService;

    private Long id_doc;
    private Long id_pat;
    private Date visitDate;

    public List<User> getPatientsList() {
        List<User> users = userService.getUsersByRole("PACJENT");
        return users;
    }

    public List<User> getDoctorsList() {
        List<User> users = userService.getUsersByRole("LEKARZ");
        return users;
    }

    public List<VisitTableData> getVisitsList() {
        List<Visit> users = visitsService.getVisits();
        List<VisitTableData> visits = new ArrayList<VisitTableData>();
        for (Visit user : users) {
            VisitTableData visitTableData = new VisitTableData();
            visitTableData.setId(user.getId());
            visitTableData.setId_doc(user.getId_doc());
            visitTableData.setId_pat(user.getId_pat());
            visitTableData.setVisitDate(user.getVisitDate());
            User userId = userService.getUserById(visitTableData.getId_pat());
            visitTableData.setPatientName(userId.getName());
            visitTableData.setPatientLastname(userId.getLastName());
            userId = userService.getUserById(visitTableData.getId_doc());
            visitTableData.setDoctorName(userId.getName());
            visitTableData.setDoctorLastname(userId.getLastName());
            visits.add(visitTableData);
        }
        return visits;
    }

    public void patientOnSelect(Long user_id) {
        id_pat = user_id;
    }

    public void doctorOnSelect(Long user_id) {
        id_doc = user_id;
    }

    Visit visit = new Visit();

    public void registerVisit() {
        visit.setId_doc(id_doc);
        visit.setId_pat(id_pat);
        long milis = visitDate.getTime(); // or instant.getMillis() or whatever
        //ps.setTimestamp(colNum,new Timestamp(milis), calendarUTC);  // column is TIMESTAMPTZ!
        Timestamp dt = new Timestamp(milis);
        visit.setVisitDate(dt);
        //visit.setVisitDate(new java.sql.Date(visitDate.getTime()));
        visitsService.save(visit);
        visit = new Visit();
    }
}
