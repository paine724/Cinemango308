package Giftcard;

import java.util.ArrayList;
import java.util.HashMap;
import JPA.Giftcard;
import JPAController.GiftcardJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GiftCardManager {

    private Giftcard card;
    private ArrayList<Giftcard> cards;
    private int quanity;
    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");

    GiftcardJpaController controller = new GiftcardJpaController(emFactory);

    public Giftcard createGiftCard(double d, String s, String m) {
        return card;
    }

    public Giftcard createMailedGiftCard(double d, String s, String m) {
        return card;
    }

    public double checkBalance(int giftCardCode) {
        EntityManager em = emFactory.createEntityManager();

        try {
            Query query = em.createQuery("Select u.balance from Giftcard u where u.number= :number");
            query.setParameter("number", giftCardCode);
            double result = (double) query.getSingleResult();
            em.close();
            return result;
        } catch (NoResultException e) {
            em.close();
            return -1;
        }

    }

    public HashMap<Giftcard, String> getAllGiftCards() {
        HashMap<Giftcard, String> giftcards = new HashMap();
        return giftcards;
    }

    public Giftcard getGiftCardByCode(int giftCardCode) {
        card=controller.findGiftcard(giftCardCode);
        return card;
    }

    public boolean checkNumberExists(int number) {
        EntityManager em = emFactory.createEntityManager();
        try {
            Query query = em.createQuery("Select u.id from Giftcard u where u.number= :number");
            query.setParameter("number", number);
            Integer result = (Integer) query.getSingleResult();
            em.close();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            em.close();
            return false;
        }

    }

    public Giftcard addGiftCard(int number, double balance) {
        Giftcard card = new Giftcard();
        card.setNumber(number);
        card.setBalance(balance);
        controller.create(card);
        return card;

    }
    
    public boolean editGiftcard(Giftcard g){
        try {
            controller.edit(g);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(GiftCardManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
}
