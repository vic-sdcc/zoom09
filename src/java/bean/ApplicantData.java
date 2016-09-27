/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopApplicant;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class ApplicantData implements Serializable {

    /**
     * Creates a new instance of ApplicantData
     */
    public ApplicantData() {
        newSet();
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private CoopApplicant applicant, selectedApplicant;
    private List<CoopApplicant> applicantList, applicantViewList;
    private DataModel<CoopApplicant> applicantModel;
    @ManagedProperty(value = "#{currentDate}")
    private Date currentDate;
    @ManagedProperty(value = "#{orgUnitData}")
    private OrgUnitData orgUnitData;
    @ManagedProperty(value = "#{applicantType}")
    private ApplicantType applicantType;

    /*
     * getter setter
     */
    public CoopApplicant getApplicant() {
        if (applicant == null) {
            applicant = new CoopApplicant();
        }
        return applicant;
    }

    public void setApplicant(CoopApplicant applicant) {
        this.applicant = applicant;
    }

    public CoopApplicant getSelectedApplicant() {
        if (selectedApplicant == null) {
            selectedApplicant = new CoopApplicant();
        }
        return selectedApplicant;
    }

    public void setSelectedApplicant(CoopApplicant selectedApplicant) {
        this.selectedApplicant = selectedApplicant;
    }

    public List<CoopApplicant> getApplicantList() {
        //applicantList = entityManagerFactory.createEntityManager().createQuery("SELECT a FROM CoopApplicant a WHERE a.applicationStat = 'A' ORDER BY a.applicantNo").getResultList();
        if (getApplicantType().getMyType().equals("REGULAR")) {
            applicantList = entityManagerFactory.createEntityManager().createQuery("SELECT a FROM CoopApplicant a WHERE a.applicantType = 'REGULAR' AND "
                    + "a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopMemberApplicant b) "
                    + "ORDER BY a.applicantNo").getResultList();
        }
        if (getApplicantType().getMyType().equals("ASSOCIATE")) {
            applicantList = entityManagerFactory.createEntityManager().createQuery("SELECT a FROM CoopApplicant a WHERE a.applicantType = 'ASSOCIATE' AND "
                    + "a.applicantNo NOT IN (SELECT c.applicantNo.applicantNo FROM CoopAssociateApplicant c) "
                    + "ORDER BY a.applicantNo").getResultList();
        }
        return applicantList;
    }

    public void setApplicantList(List<CoopApplicant> applicantList) {
        this.applicantList = applicantList;
    }

    public DataModel<CoopApplicant> getApplicantModel() {
        if (applicantModel == null) {
            applicantModel = new ListDataModel<>(getApplicantList());
        }
        return applicantModel;
    }

    public void setApplicantModel(DataModel<CoopApplicant> applicantModel) {
        this.applicantModel = applicantModel;
    }

    public Date getCurrentDate() {
        if (currentDate == null) {
            currentDate = new Date();
        }
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
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

    public ApplicantType getApplicantType() {
        if (applicantType == null) {
            applicantType = new ApplicantType();
        }
        return applicantType;
    }

    public void setApplicantType(ApplicantType applicantType) {
        this.applicantType = applicantType;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("applicantData", null);
    }

    public void newSet() {
        //getApplicant().setApplicationDate(getCurrentDate());
        getApplicant().setApplicationStat("A");
        //getApplicant().setApplicationStat("N");
        getApplicant().setBoardAction(true);
        getApplicant().setBoardResoNo("0");
        getApplicant().setActionDate(getApplicant().getActionDate());
        getApplicant().setApplicantStatRem("NEW");
        getApplicant().setNationality(true);
    }

    public void applicantLoad() {
        getApplicant().setApplicantType(getApplicant().getApplicantType());
        getApplicant().setLastName(getApplicant().getLastName());
        getApplicant().setFirstName(getApplicant().getFirstName());
        getApplicant().setMiddleName(getApplicant().getMiddleName());
        getApplicant().setGender(getApplicant().getGender());
        getApplicant().setBirthdate(getApplicant().getBirthdate());
        getApplicant().setStreet(getApplicant().getStreet());
        getApplicant().setBarangay(getApplicant().getBarangay());
        getApplicant().setCityMun(getApplicant().getCityMun());
        getApplicant().setRegion(getApplicant().getRegion());
        getApplicant().setProvince(getApplicant().getProvince());
        getApplicant().setContactNumber(getApplicant().getContactNumber());
        getApplicant().setEmail(getApplicant().getEmail());
        getApplicant().setOuCode(getOrgUnitData().getOrgUnit());
        getApplicant().setOccupation(getApplicant().getOccupation());
        getApplicant().setApplicationDate(getApplicant().getApplicationDate());
        getApplicant().setCivilStatus(getApplicant().getCivilStatus());
        getApplicant().setEducation(getApplicant().getEducation());
        getApplicant().setResidentSince(getApplicant().getResidentSince());
        getApplicant().setNationality(getApplicant().getNationality());

        getApplicant().setApplicationStat(getApplicant().getApplicationStat());
        getApplicant().setBoardAction(getApplicant().getBoardAction());
        getApplicant().setBoardResoNo(getApplicant().getBoardResoNo());
        getApplicant().setActionDate(getApplicant().getActionDate());
        getApplicant().setApplicantStatRem(getApplicant().getApplicantStatRem());
    }

    public void dataTableLoad() {
        CoopApplicant a = (CoopApplicant) entityManagerFactory.createEntityManager().createQuery("SELECT a FROM CoopApplicant a WHERE a.applicantNo = '" + getSelectedApplicant().getApplicantNo() + "'").getResultList().get(0);
        setApplicant(a);
    }

    public void editOu() {
        getOrgUnitData().getOrgUnit().setOuCode(getApplicant().getOuCode().getOuCode());
        getApplicant().setOuCode(getOrgUnitData().getOrgUnit());
    }

    public List<CoopApplicant> getApplicantViewList() {
        if (applicantViewList == null) {
            applicantViewList = new ArrayList<>();
        }
        return applicantViewList;
    }

    public void setApplicantViewList(List<CoopApplicant> applicantViewList) {
        this.applicantViewList = applicantViewList;
    }

}
