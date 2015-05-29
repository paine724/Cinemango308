/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import JPA.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Philip
 */
public class FavoriteMovieBean {

    private int userid;
    private List<Movie> movies;

    /**
     * Creates a new instance of UserInfoBean
     */
    public FavoriteMovieBean() {
        this.movies = new ArrayList();
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int s) {
        userid = s;
    }

    public List<Movie> getMovieId() {
        return movies;
    }

    public void setMovieId(List<Movie> s) {
        movies = s;
    }
}
