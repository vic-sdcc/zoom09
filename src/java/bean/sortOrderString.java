/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class sortOrderString extends applicantSortData {

    private ArrayList<String> orderByStr;

    public sortOrderString() {

    }

    public void beanclear2() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sortOrderString", null);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public void alphabetical() {
        if (super.isAlphabetical()) {
            getOrderByStr().add("c.lastName, c.firstName, c.middleName");
        } else {
            getOrderByStr().remove("c.lastName, c.firstName, c.middleName");
        }
    }

    public void applicantDate() {
        if (super.isApplicantDate()) {
            getOrderByStr().add("c.applicationDate");
        } else {
            getOrderByStr().remove("c.applicationDate");
        }
    }

    public void orgUnit() {
        if (super.isOrgUnit()) {
            getOrderByStr().add("c.ouCode.ouCode");
        } else {
            getOrderByStr().remove("c.ouCode.ouCode");
        }
    }

    public ArrayList<String> getOrderByStr() {
        if (orderByStr == null) {
            orderByStr = new ArrayList<>();
        }
        return orderByStr;
    }

    public void setOrderByStr(ArrayList<String> orderByStr) {
        this.orderByStr = orderByStr;
    }

}
