/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class AutoComplete implements Serializable {

    /**
     * Creates a new instance of AutoComplete
     */
    public AutoComplete() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
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

    /*
     * methods
     */
    public List<String> completeLastName(String query) {
        String b;
        b = "SELECT DISTINCT a.lastName FROM CoopApplicant a WHERE UPPER(a.lastName) LIKE UPPER('" + query + "%') AND a.applicantType = '" + getApplicantType().getMyType() + "'";
        if (getApplicantType().getMyType().equals("REGULAR")) {
            b += " AND a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopMemberApplicant b) ORDER BY a.lastName";
        }
        if (getApplicantType().getMyType().equals("ASSOCIATE")) {
            b += " AND a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopAssociateApplicant b) ORDER BY a.lastName";
        }
        return entityManagerFactory.createEntityManager().createQuery(b).getResultList();
    }

    public List<String> completeFirstName(String query) {
        String b;
        b = "SELECT DISTINCT a.firstName FROM CoopApplicant a WHERE UPPER(a.firstName) LIKE UPPER('" + query + "%') AND a.applicantType = '" + getApplicantType().getMyType() + "'";
        if (getApplicantType().getMyType().equals("REGULAR")) {
            b += " AND a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopMemberApplicant b) ORDER BY a.firstName";
        }
        if (getApplicantType().getMyType().equals("ASSOCIATE")) {
            b += " AND a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopAssociateApplicant b) ORDER BY a.firstName";
        }
        return entityManagerFactory.createEntityManager().createQuery(b).getResultList();
    }

    public List<String> completeMiddleName(String query) {
        String b;
        b = "SELECT DISTINCT a.middleName FROM CoopApplicant a WHERE UPPER(a.middleName) LIKE UPPER('" + query + "%') AND a.applicantType = '" + getApplicantType().getMyType() + "'";
        if (getApplicantType().getMyType().equals("REGULAR")) {
            b += " AND a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopMemberApplicant b) ORDER BY a.middleName";
        }
        if (getApplicantType().getMyType().equals("ASSOCIATE")) {
            b += " AND a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopAssociateApplicant b) ORDER BY a.middleName";
        }
        return entityManagerFactory.createEntityManager().createQuery(b).getResultList();
    }

    public List<String> completeOuCode(String query) {
        String b;
        b = "SELECT DISTINCT a.ouCode.ouShortName FROM CoopApplicant a WHERE "
                + "a.ouCode.ouShortName LIKE 'PT%' AND "
                + "UPPER(a.ouCode.ouShortName) LIKE UPPER('" + query + "%') ";
        if (getApplicantType().getMyType().equals("REGULAR")) {
            b += "AND a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopMemberApplicant b) ORDER BY a.ouCode.ouShortName";
        }
        if (getApplicantType().getMyType().equals("ASSOCIATE")) {
            b += "AND a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopAssociateApplicant b) ORDER BY a.ouCode.ouShortName";
        }
        return entityManagerFactory.createEntityManager().createQuery(b).getResultList();
    }

}

