/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.administration.data;

import java.sql.Timestamp;

/**
 *
 * @author Start
 */
public class VisitTableData {
    Long id;
    Long id_pat;
    Long id_doc;
    Timestamp visitDate;
    String patientName;
    String patientLastname;
    String doctorName;
    String doctorLastname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_pat() {
        return id_pat;
    }

    public void setId_pat(Long id_pat) {
        this.id_pat = id_pat;
    }

    public Long getId_doc() {
        return id_doc;
    }

    public void setId_doc(Long id_doc) {
        this.id_doc = id_doc;
    }

    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientLastname() {
        return patientLastname;
    }

    public void setPatientLastname(String patientLastname) {
        this.patientLastname = patientLastname;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorLastname() {
        return doctorLastname;
    }

    public void setDoctorLastname(String doctorLastname) {
        this.doctorLastname = doctorLastname;
    }
    
        
}
