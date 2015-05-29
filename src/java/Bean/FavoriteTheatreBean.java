/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import JPA.Theatre;
import java.util.List;

/**
 *
 * @author Philip
 */
public class FavoriteTheatreBean {

    private int userid;
    private List<Theatre> theatres;

    /**
     * Creates a new instance of UserInfoBean
     */
    public FavoriteTheatreBean() {
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int s) {
        userid = s;
    }

    public List<Theatre> getTheatreList() {
        return theatres;
    }

    public void setTheatreList(List<Theatre> s) {
        theatres = s;
    }
}
