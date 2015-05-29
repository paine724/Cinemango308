package Payment;

import java.util.Map;
import JPA.Giftcard;
import JPA.Payment;
import JPAController.PaymentJpaController;
import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Konstantinos Pagonis
 */
public class PaymentManager {
    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");
    PaymentJpaController controller = new PaymentJpaController(emFactory);

    private Giftcard giftcard;
    private String promo;
    private TheatreRewards rewards;
    private Payment payment;
    public HashMap<Payment, String> allPayments;


    public Map getAllPayments() {
        return allPayments;
    }
    public void addPayment(Payment p){
        controller.create(p);
    }
}
