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
public class GiftCardBalanceBean {

    private double balance = 0;

    /**
     * Creates a new instance of UserInfoBean
     */
    public GiftCardBalanceBean() {
    }

    public GiftCardBalanceBean(double d) {
        balance = d;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double d) {
        balance = d;
    }
}
