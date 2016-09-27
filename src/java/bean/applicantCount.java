/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopApplicant;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class applicantCount implements Serializable {

    @PersistenceUnit
    EntityManagerFactory emf;

    private Integer current;
    private Integer total;

    public applicantCount() {

    }

    public void initVal() {
        setTotal(regularList().size());
    }

    public List<CoopApplicant> regularList() {
        return emf.createEntityManager().createQuery("SELECT c FROM CoopApplicant c "
                + "WHERE c.applicantNo NOT IN (SELECT n.applicantNo.applicantNo FROM CoopAssociateApplicant n) "
                + "AND c.applicantNo NOT IN (SELECT x.applicantNo.applicantNo FROM CoopMemberApplicant x)").getResultList();
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
