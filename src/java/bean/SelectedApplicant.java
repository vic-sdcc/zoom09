/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import model.CoopApplicant;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class SelectedApplicant implements Serializable {

    /**
     * Creates a new instance of SelectedApplicant
     */
    public SelectedApplicant() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    private List<CoopApplicant> applicantList;
    private DataModel<CoopApplicant> applicantModel;

    /*
     * getter setter
     */
    public List<CoopApplicant> getApplicantList() {
        if (applicantList == null) {
            Query a = entityManagerFactory.createEntityManager().createNamedQuery("CoopApplicant.findAll");
            applicantList = a.getResultList();
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

    public CoopApplicant abc(Integer applicantNo) {
        setApplicantList(null);
        setApplicantModel(null);
        for (int i = 0; i != getApplicantModel().getRowCount(); i++) {
            if (getApplicantList().get(i).getApplicantNo().equals(applicantNo)) {
                return getApplicantList().get(i);
            }
        }
        return null;
    }

}
