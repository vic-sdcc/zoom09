/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class DataConvert implements Serializable {

    /**
     * Creates a new instance of DataConvert
     */
    public DataConvert() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private String convertedString = "";

    /*
     * getter setter
     */
    public String getConvertedString() {
        return convertedString;
    }

    public void setConvertedString(String convertedString) {
        this.convertedString = convertedString;
    }

    /*
     * methods
     */
    public String genderConv(char value) {
        convertedString = "";
        if (value == 'M') {
            convertedString = "MALE";
        } else if (value == 'F') {
            convertedString = "FEMALE";
        }
        return convertedString;
    }

    public String civilStatusConv(String value) {
        convertedString = "";
        if ("S".equals(value)) {
            convertedString = "SINGLE";
        } else if ("M".equals(value)) {
            convertedString = "MARRIED";
        } else if ("P".equals(value)) {
            convertedString = "SEPARATED";
        } else if ("W".equals(value)) {
            convertedString = "WIDOW(ER)";
        } else if ("N".equals(value)) {
            convertedString = "NO DATA";
        }
        return convertedString;
    }

    public String educConv(char value) {
        convertedString = "";
        if (value == 'P') {
            convertedString = "POST-GRADUATE";
        } else if (value == 'V') {
            convertedString = "VOCATIONAL";
        } else if (value == 'C') {
            convertedString = "COLLEGE";
        } else if (value == 'H') {
            convertedString = "HIGH SCHOOL";
        } else if (value == 'E') {
            convertedString = "ELEMENTARY";
        } else if (value == 'N') {
            convertedString = "NO DATA";
        }
        return convertedString;
    }

}
