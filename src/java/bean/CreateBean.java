/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import service.CoopApplicantFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class CreateBean extends CreateController implements Serializable {

    /**
     * Creates a new instance of CreateBean
     */
    public CreateBean() {
    }

    @EJB
    private CoopApplicantFacadeREST applicantREST;
    @ManagedProperty(value = "#{myMessages}")
    private MyMessages myMessages;

    /*
     * getter setter
     */
    public MyMessages getMyMessages() {
        if (myMessages == null) {
            myMessages = new MyMessages();
        }
        return myMessages;
    }

    public void setMyMessages(MyMessages myMessages) {
        this.myMessages = myMessages;
    }

    /*
     * methods
     */
    public void submit() {
        super.getApplicantData().applicantLoad();
        applicantREST.create(super.getApplicantData().getApplicant());
        getMyMessages().messages("Create " + super.getApplicantData().getApplicant().getApplicantType());
        super.complete();
    }

}
