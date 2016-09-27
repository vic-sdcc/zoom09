/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class applicantFilterData {

    private String ouCodeID;
    private String applicationType;
    private String gender;
    private Date applicantDateFrom;
    private Date applicantDateTo;

    public applicantFilterData() {

    }
    
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("applicantFilterData", null);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public String getOuCodeID() {
        return ouCodeID;
    }

    public void setOuCodeID(String ouCodeID) {
        this.ouCodeID = ouCodeID;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getApplicantDateFrom() {
        return applicantDateFrom;
    }

    public void setApplicantDateFrom(Date applicantDateFrom) {
        this.applicantDateFrom = applicantDateFrom;
    }

    public Date getApplicantDateTo() {
        return applicantDateTo;
    }

    public void setApplicantDateTo(Date applicantDateTo) {
        this.applicantDateTo = applicantDateTo;
    }

}
