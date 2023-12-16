package Final_Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Feedback {
    private static final List<Feedback> allFeedback = new ArrayList<>();
    private static final Random random = new Random();

    private final int feedbackId;
    private final String feedbackText;

    public Feedback(String feedbackText) {
        this.feedbackText = feedbackText;
        this.feedbackId = random.nextInt(10000); // Random ID, max value 9999
        allFeedback.add(this); // Add this feedback to the list
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public static List<Feedback> getAllFeedback() {
        return new ArrayList<>(allFeedback); // Return a copy to preserve encapsulation
    }

    // Optional: a method to display feedback information
    @Override
    public String toString() {
        return "Feedback ID: " + feedbackId + ", Text: " + feedbackText;
    }

  
}
