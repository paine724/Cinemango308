package Photos;

import JPA.Photo;
import JPAController.PhotoJpaController;
import java.util.List;
import javax.persistence.EntityManager;
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
public class PhotoManager {

    private List<String> photos;
    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Cinemango308PU");

    PhotoJpaController controller = new PhotoJpaController(emFactory);

    public void addPhoto(String s) {

    }

    public List getAllPhotos() {
        return photos;
    }

    public int getNumPhotos() {
        int photoNum = 0;
        EntityManager em = emFactory.createEntityManager();
        photoNum = controller.getPhotoCount();
        em.close();
        return photoNum;
    }

    public String getPhotoURL(int id) {
        EntityManager em = emFactory.createEntityManager();
        Photo photo = controller.findPhoto(id);

        return photo.getDataPath();

    }
}
