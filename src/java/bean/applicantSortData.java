/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class applicantSortData {

    private boolean alphabetical;
    private boolean applicantDate;
    private boolean orgUnit;

    public applicantSortData() {
    }
    
    public void beanclear() {
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("memberSortData", null);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public boolean isAlphabetical() {
        return alphabetical;
    }

    public void setAlphabetical(boolean alphabetical) {
        this.alphabetical = alphabetical;
    }

    public boolean isApplicantDate() {
        return applicantDate;
    }

    public void setApplicantDate(boolean applicantDate) {
        this.applicantDate = applicantDate;
    }

    public boolean isOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(boolean orgUnit) {
        this.orgUnit = orgUnit;
    }
    
}
