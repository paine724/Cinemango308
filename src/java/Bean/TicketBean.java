/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author zeb
 */
public class TicketBean {

    int adults;
    int children;
    int seniors;
    int showtimeid;

    public int getShowtimeid() {
        return showtimeid;
    }

    public void setShowtimeid(int showtimeid) {
        this.showtimeid = showtimeid;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getSeniors() {
        return seniors;
    }

    public void setSeniors(int seniors) {
        this.seniors = seniors;
    }

}
