/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Andrew
 */
public class ErrorMessageBean {

    private String errorMsg = "";

    /**
     * Creates a new instance of UserInfoBean
     */
    public ErrorMessageBean() {
    }

    public ErrorMessageBean(String s) {
        errorMsg = s;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String s) {
        errorMsg = s;
    }

    public String toString() {
        return errorMsg;
    }
}
