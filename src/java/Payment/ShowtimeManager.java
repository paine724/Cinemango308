/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;

import JPAController.ShowtimeJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Andrew
 */
public class ShowtimeManager {

    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");
    ShowtimeJpaController controller = new ShowtimeJpaController(emFactory);

    public JPA.Showtime find(int id) {
        return controller.findShowtime(id);

    }
}
