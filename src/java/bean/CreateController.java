/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class CreateController implements Serializable {

    /**
     * Creates a new instance of CreateController
     */
    public CreateController() {
    }

    @ManagedProperty(value = "#{applicantData}")
    private ApplicantData applicantData;
    @ManagedProperty(value = "#{orgUnitData}")
    private OrgUnitData orgUnitData;
    @ManagedProperty(value = "#{navigation}")
    private Navigation navigation;

    /*
     * getter setter
     */
    public ApplicantData getApplicantData() {
        if (applicantData == null) {
            applicantData = new ApplicantData();
        }
        return applicantData;
    }

    public void setApplicantData(ApplicantData applicantData) {
        this.applicantData = applicantData;
    }

    public OrgUnitData getOrgUnitData() {
        if (orgUnitData == null) {
            orgUnitData = new OrgUnitData();
        }
        return orgUnitData;
    }

    public void setOrgUnitData(OrgUnitData orgUnitData) {
        this.orgUnitData = orgUnitData;
    }

    public Navigation getNavigation() {
        if (navigation == null) {
            navigation = new Navigation();
        }
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    /*
     * methods
     */
    public void complete() {
        getOrgUnitData().beanclear();
        getApplicantData().beanclear();
        getApplicantData().newSet();
    }

    public void reset() {
        getOrgUnitData().beanclear();
        getApplicantData().beanclear();
        getApplicantData().newSet();
        getNavigation().navigateToMainCreate();
    }

}
