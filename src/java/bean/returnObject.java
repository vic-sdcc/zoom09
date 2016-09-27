/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.portlet.RenderRequest;
import model.CoopMember;
import model.CoopOrgUnit;

/**
 *
 * @author vic
 */
@ManagedBean
@RequestScoped
public class returnObject implements Serializable {

    @PersistenceUnit
    EntityManagerFactory emf;

    public returnObject() {
        //CoopProspect q = (CoopProspect) emf.createEntityManager().createQuery("SELECT c FROM CoopProspect c").getResultList().get(0);
        //FacesContext context = FacesContext.getCurrentInstance();
        //context.addMessage(null, new FacesMessage("Successful", "New data has been created."));
    }

    public List<CoopOrgUnit> ouList() {
        return emf.createEntityManager().createQuery("SELECT o FROM CoopOrgUnit o WHERE o.ouShortName LIKE 'PT%' AND o.dateDissolved IS NULL ORDER BY o.ouShortName").getResultList();
        //return emf.createEntityManager().createQuery("SELECT c FROM CoopOrgUnit c WHERE c.ouShortName LIKE 'PT%'").getResultList();
    }

    public CoopOrgUnit ouCode(String ouCode) {
        return (CoopOrgUnit) emf.createEntityManager().createQuery("SELECT o FROM CoopOrgUnit o WHERE o.ouCode ='" + ouCode + "'").getResultList().get(0);
        //return emf.createEntityManager().createQuery("SELECT c FROM CoopOrgUnit c WHERE c.ouShortName LIKE 'PT%'").getResultList();
    }

    public String fullname(String pprefix, String lastName, String firstName, String middleName, String suffix) {
        String str = "";

        if (pprefix != null) {
            str += pprefix + " ";
        }
        if (firstName != null) {
            str += firstName + " ";
        }
        if (middleName != null) {
            str += middleName + " ";
        }
        if (lastName != null) {
            str += lastName + " ";
        }
        if (suffix != null) {
            str += suffix;
        }

        return str;
    }

    public String liferayUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        RenderRequest renderRequest = (RenderRequest) facesContext.getExternalContext().getRequest();
        Map userInfo = (Map) renderRequest.getAttribute(RenderRequest.USER_INFO);
        return userInfo.get("liferay.user.id").toString();
    }

    public String memberUser(String liferayUserId) {
        try {
            CoopMember member = (CoopMember) emf.createEntityManager().createQuery("SELECT c FROM CoopMember c, CoopMemberUser d "
                    + "WHERE c.memNo = d.memNo and d.userId =" + liferayUserId + "").getResultList().get(0);
            return fullname(member.getPPrefix(), member.getLastName(), member.getFirstName(), member.getMiddleName(), member.getSuffix());
        } catch (Exception e) {
        }
        return "";
    }

    public String getPercentage(float value) {
        return String.format("%.2f", value * 100) + "%";
    }

}
