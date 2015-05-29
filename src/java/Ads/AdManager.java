package Ads;

import JPA.Ad;
import JPAController.AdJpaController;
import JPAController.UserJpaController;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Konstantinos Pagonis
 */
public class AdManager {

    public HashMap<Advertisements, String> advertisements;
    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");

    AdJpaController controller = new AdJpaController(emFactory);

    public Advertisements createAdvertisement(String url, String userLocation) {
        Advertisements ad = new Advertisements();
        return ad;
    }

    public String sendLocationToAdServer(String location) {
        return " ";
    }

    public String getAd() {
        EntityManager em = emFactory.createEntityManager();
        int result = controller.getAdCount();
        int rng = 1 + (int) (Math.random() * (result));
        Query query = em.createQuery("Select u.dataPath from Ad u where u.adID= :number");
        query.setParameter("number", rng);
        try {
            String url = (String) query.getSingleResult();
            em.close();
            return url;
        } catch (NoResultException e) {
            em.close();
            return null;
        }
    }

    public void addToAdvertisementmap(Advertisements A) {

    }

    public boolean checkLocationInMap(String location) {
        return false;
    }

}
