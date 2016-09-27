/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopApplicant;

/**
 *
 * @author vic
 */
@ManagedBean
@RequestScoped
public class generateData {

    private dateFormat dFormat;
    private Date date;
    @PersistenceUnit
    EntityManagerFactory emf;

    /**
     * Creates a new instance of generateData
     */
    public generateData() {

    }

    public List<CoopApplicant> applicantList(applicantFilterData applicantValue, sortOrderString sortOrder) throws ParseException {
        String applicantQuery = "";
        applicantQuery += "SELECT c FROM CoopApplicant c "
                + "WHERE c.applicantNo NOT IN (SELECT n.applicantNo.applicantNo FROM CoopAssociateApplicant n) "
                + "AND c.applicantNo NOT IN (SELECT x.applicantNo.applicantNo FROM CoopMemberApplicant x) ";

        if (applicantValue.getOuCodeID() != null) {
            applicantQuery += "and c.ouCode.ouCode ='" + applicantValue.getOuCodeID() + "' ";
        }

        if (applicantValue.getApplicationType() != null) {
            applicantQuery += "and UPPER(c.applicantType) =UPPER('" + applicantValue.getApplicationType() + "') ";
        }

        if (applicantValue.getGender() != null) {
            applicantQuery += "and c.gender = '" + applicantValue.getGender() + "' ";
        }

        if ((applicantValue.getApplicantDateFrom() != null && applicantValue.getApplicantDateTo() != null)
                || (applicantValue.getApplicantDateFrom() != null && applicantValue.getApplicantDateTo() == null)) {
            if (applicantValue.getApplicantDateTo() == null) {
                //applicantValue.setApplicantDateTo(getToday());
                applicantQuery += "and c.applicationDate = '" + getdFormat().formatDate(applicantValue.getApplicantDateFrom(), "yyyy-MM-dd") + "' ";
            } else {
                applicantQuery += "and c.applicationDate >= '" + getdFormat().formatDate(applicantValue.getApplicantDateFrom(), "yyyy-MM-dd") + "' "
                        + "and c.applicationDate <= '" + getdFormat().formatDate(applicantValue.getApplicantDateTo(), "yyyy-MM-dd") + "' ";
            }
        }

        applicantQuery += "ORDER BY ";

        if (sortOrder.getOrderByStr().isEmpty()) {
            applicantQuery += "c.applicantNo";
        } else {
            for (int i = 0; i != sortOrder.getOrderByStr().size(); i++) {
                applicantQuery += sortOrder.getOrderByStr().get(i);
                if (sortOrder.getOrderByStr().size() - 1 != i) {
                    applicantQuery += ", ";
                }
            }
        }

        return emf.createEntityManager().createQuery(applicantQuery).getResultList();
    }

    public dateFormat getdFormat() {
        if (dFormat == null) {
            dFormat = new dateFormat();
        }
        return dFormat;
    }

    public void setdFormat(dateFormat dFormat) {
        this.dFormat = dFormat;
    }

    public Date getToday() {
        if (date == null) {
            date = new Date();
        }
        return date;
    }

    public void setToday(Date date) {
        this.date = date;
    }

}
