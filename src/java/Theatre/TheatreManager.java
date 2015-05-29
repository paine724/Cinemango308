package Theatre;

import JPAController.TheatreJpaController;
import JPA.Movie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import JPA.Theatre;
import static java.lang.Integer.parseInt;
import javax.persistence.NoResultException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.ReadURL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Konstantinos Pagonis
 */
public class TheatreManager {

    private HashMap<Theatre, String> theatres;
    private HashMap<Theatre, String> theatresNearby;
    private String location;
    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");

    TheatreJpaController controller = new TheatreJpaController(emFactory);

    public void addTheatres(Theatre theatre) {

    }

    public void removeTheatre(Theatre theatre) {

    }

    public void removeMovieFromMoviesShowing(Movie m) {

    }

    public void addMovieToMovieShowing(Movie m) {

    }

    public void addMovieToMoviesUpcoming(Movie m) {

    }

    public HashMap<Theatre, String> findNearby(String location, Theatre T) {
        HashMap<Theatre, String> h = new HashMap();
        return h;
    }

    public HashMap<Theatre, String> findNearby(String location) {
        return null;
    }

    public ArrayList search(String s) {
        EntityManager em = emFactory.createEntityManager();
        ArrayList<Theatre> theatres = new ArrayList();
        Query query = em.createQuery("Select t.id, t.name from Theatre t where t.name like :name");
        query.setParameter("name", "%" + s + "%");
        try {
            List<Object[]> result = query.getResultList();
            for (Object[] q : result) {
                Theatre t = getTheatre((int) q[0]);
                theatres.add(t);

            }
            em.close();
            return theatres;
        } catch (NoResultException e) {
            em.close();
            return null;
        }
    }

    public List searchByZipcode(String s) throws Exception {
        EntityManager em = emFactory.createEntityManager();
        String apikey = "AIzaSyDu9NzJpmv0Wk4dxJQbucgh8Kew4i9zgIQ";
        String uriS = ReadURL.encodeURIComponent(s);
        JSONObject obj = new JSONObject(org.json.ReadURL.readUrl("https://maps.googleapis.com/maps/api/geocode/json?address=" + uriS + "&key=" + apikey));
        double lat = obj.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
        double lon = obj.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
        List<Object[]> theatres = getTheatreByLocation((float) lat, (float) lon, 10);
        return theatres;
    }

    public List searchByCityState(String s) throws Exception {
        return searchByZipcode(s);
    }

    public Theatre createTheatre() {
        Theatre T = new Theatre();
        return T;
    }

    public Map getAllTheatres() {
        return null;
    }

    public String getInfo(Theatre T) {
        return null;
    }

    public String getInfo(int ID) {
        return null;
    }

    public Theatre getTheatre(int ID) {
        JPA.Theatre theatre = controller.findTheatre(ID);
        return theatre;
    }

    public List getTheatreByLocation(float latitude, float longitude, int radius) {
        EntityManager em = emFactory.createEntityManager();
        Query query = em.createNativeQuery("Select u.id, u.name, u.address, u.city, u.state, u.zipcode,(ACOS(SIN(RADIANS(u.latitude))*SIN(RADIANS(" + latitude + ")) + COS(RADIANS(u.latitude))*COS(RADIANS(" + latitude + "))*COS(RADIANS(u.longitude)-RADIANS(" + longitude + "))))*3959 as distance from Theatre u where (ACOS(SIN(RADIANS(u.latitude))*SIN(RADIANS(" + latitude + ")) + COS(RADIANS(u.latitude))*COS(RADIANS(" + latitude + "))*COS(RADIANS(u.longitude)-RADIANS(" + longitude + "))))*3959<" + radius + " order by distance");
        List<Object[]> result = query.getResultList();
        em.close();
        return result;
    }
}
