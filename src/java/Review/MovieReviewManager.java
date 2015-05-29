package Review;

import JPA.Moviereview;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Konstantinos Pagonis
 */
public class MovieReviewManager {

    private List<Moviereview> flaggedReviews;
    private HashMap<Moviereview, Moviereview> movieReviews;
    private Moviereview movieReview;

    public Moviereview findReview(int ID) {
        return movieReview;
    }

    public void incrementFlagCounter(Moviereview movieReview) {

    }

    public void incrementHelpful(Moviereview movieReview) {

    }

    public void reviewNotHelpful(Moviereview movieReview) {

    }

    public HashMap getAllReviews() {
        return movieReviews;
    }
}
