package Actor;
import JPA.Actor;
import java.util.ArrayList;
import java.util.HashMap;
import Search.*;
import JPA.Theatre;
import JPAController.ActorJpaController;
import java.util.List;
import java.util.regex.Pattern;
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
public class ActorManager {
    private Actor actor;
    private HashMap<Actor, String> allActors;
    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");
    ActorJpaController controller= new ActorJpaController(emFactory);
    public void createActor(){
        
    }
    public HashMap getAllActors(){
        HashMap <Actor, String> actors= new HashMap();
        return actors;
    }
    
        public ArrayList search(String s) {
        EntityManager em = emFactory.createEntityManager();
        ArrayList<Actor> actors = new ArrayList();
        Query query = em.createQuery("Select a.actorid from Actor a where a.lastName like :name OR a.firstname like :fname");
        if(Pattern.matches("^[A-Za-z]{2,}[ ]{1,}[A-Za-z]{2,}$", s)){
            String a[]=s.split("\\s+");
            query.setParameter("fname", "%" + a[0] + "%");
            query.setParameter("name", "%" + a[1] + "%");
        }else{
            query.setParameter("name", "%" + s + "%");
            query.setParameter("fname", "%" + s + "%");
        }
        try {
            List<Integer> result = query.getResultList();
            for (Integer q : result) {
                Actor a = getActor(q);
                actors.add(a);

            }
            em.close();
            return actors;
        } catch (NoResultException e) {
            em.close();
            return null;
        }
    }
        
        public Actor getActor(int id){
            Actor actor= controller.findActor(id);
            return actor;
        }
    
}
