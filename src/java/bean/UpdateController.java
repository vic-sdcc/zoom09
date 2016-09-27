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
public class UpdateController implements Serializable {

    /**
     * Creates a new instance of UpdateController
     */
    public UpdateController() {
    }

    @ManagedProperty(value = "#{applicantData}")
    private ApplicantData applicantData;
    @ManagedProperty(value = "#{orgUnitData}")
    private OrgUnitData orgUnitData;
    @ManagedProperty(value = "#{navigation}")
    private Navigation navigation;
    @ManagedProperty(value = "#{applicantType}")
    private ApplicantType applicantType;
    @ManagedProperty(value = "#{applicantDataComp}")
    private ApplicantDataComp applicantDataComp;
    @ManagedProperty(value = "#{selectedApplicant}")
    private SelectedApplicant selectedApplicant;

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

    public ApplicantType getApplicantType() {
        if (applicantType == null) {
            applicantType = new ApplicantType();
        }
        return applicantType;
    }

    public void setApplicantType(ApplicantType applicantType) {
        this.applicantType = applicantType;
    }

    public ApplicantDataComp getApplicantDataComp() {
        if (applicantDataComp == null) {
            applicantDataComp = new ApplicantDataComp();
        }
        return applicantDataComp;
    }

    public void setApplicantDataComp(ApplicantDataComp applicantDataComp) {
        this.applicantDataComp = applicantDataComp;
    }

    public SelectedApplicant getSelectedApplicant() {
        if (selectedApplicant == null) {
            selectedApplicant = new SelectedApplicant();
        }
        return selectedApplicant;
    }

    public void setSelectedApplicant(SelectedApplicant selectedApplicant) {
        this.selectedApplicant = selectedApplicant;
    }

    /*
     * methods
     */
    public void init() {
        getOrgUnitData().beanclear();
        getApplicantData().beanclear();
        getApplicantType().beanclear();
        getApplicantDataComp().beanclear();
        getApplicantData().newSet();
    }

    public void reset() {
        init();
        getNavigation().navigateToMainUpdate();
    }

    public void load() {
        getApplicantData().setApplicant(getSelectedApplicant().abc(getApplicantDataComp().getAppProfileList().get(getApplicantDataComp().getIndexList()).getApplicantNo()));
        getNavigation().navigateToUpdatePage();
    }

    public void load2() {
        getApplicantData().setApplicant(getSelectedApplicant().abc(getApplicantDataComp().getAppProfileList().get(getApplicantDataComp().getIndexList()).getApplicantNo()));
        getNavigation().viewPrintPage();
    }

    public void mainViewApp() {
        getNavigation().viewMainPage();
    }

}
