/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.CoopApplicant;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class ApplicantDataComp extends ApplicantProfile implements Serializable {

    /**
     * Creates a new instance of ApplicantDataComp
     */
    @ManagedProperty(value = "#{applicantType}")
    private ApplicantType applicantType;

    /*
     * getter setter
     */
    public ApplicantType getApplicantType() {
        if (applicantType == null) {
            applicantType = new ApplicantType();
        }
        return applicantType;
    }

    public void setApplicantType(ApplicantType applicantType) {
        this.applicantType = applicantType;
    }

    public ApplicantDataComp() {
    }

    private List<CoopApplicant> appProfileList;
    private Integer indexList;

    /*
     * getter setter
     */
    public List<CoopApplicant> getAppProfileList() {
        if (appProfileList == null) {
            appProfileList = new ArrayList<>();
        }
        return appProfileList;
    }

    public void setAppProfileList(List<CoopApplicant> appProfileList) {
        this.appProfileList = appProfileList;
    }

    public Integer getIndexList() {
        if (indexList == null) {
            indexList = 0;
        }
        return indexList;
    }

    public void setIndexList(Integer indexList) {
        this.indexList = indexList;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("applicantDataComp", null);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public void applicantComplete() {
        String query = "";
        setAppProfileList(null);
        if (super.getLastName() != null || super.getFirstName() != null || super.getMiddleName() != null || super.getOuCode() != null) {
            try {
                query = "SELECT a FROM CoopApplicant a WHERE a.applicantNo = a.applicantNo ";
                if (super.getLastName() != null) {
                    query += "AND a.lastName ='" + super.getLastName() + "' ";
                }
                if (super.getFirstName() != null) {
                    query += "AND a.firstName ='" + super.getFirstName() + "' ";
                }
                if (super.getMiddleName() != null) {
                    query += "AND a.middleName ='" + super.getMiddleName() + "' ";
                }
                if (super.getOuCode() != null) {
                    query += "AND a.ouCode.ouShortName ='" + super.getOuCode() + "' ";
                }

                query += "AND a.applicantType = '" + getApplicantType().getMyType() + "' ";
                if (getApplicantType().getMyType().equals("REGULAR")) {
                    query += "AND a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopMemberApplicant b) ";
                }
                if (getApplicantType().getMyType().equals("ASSOCIATE")) {
                    query += "AND a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopAssociateApplicant b) ";
                }

                query += "ORDER BY a.applicantNo";
                setAppProfileList(entityManagerFactory.createEntityManager().createQuery(query).getResultList());
            } catch (Exception e) {
                super.setLastName(null);
                super.setFirstName(null);
                super.setMiddleName(null);
                super.setOuCode(null);
            }
        }
    }

}

