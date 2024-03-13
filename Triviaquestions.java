import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Triviaquestions {
    private Map<String, String> questions;

    public Triviaquestions() {
        this.questions = new HashMap<>();
        this.questions.put("What is the capital of France?", "Paris");
        this.questions.put("What is the largest mammal in the world?", "Blue whale");
        this.questions.put("How many sides does a triangle have?", "3");
        // Add more questions and answers as needed
    }

    public String getQuestion() {
        Random random = new Random();
        Object[] keys = this.questions.keySet().toArray();
        return (String) keys[random.nextInt(keys.length)];
    }

    public boolean checkAnswer(String question, String answer) {
        String correctAnswer = this.questions.get(question);
        return correctAnswer != null && answer.toLowerCase().equals(correctAnswer.toLowerCase());
    }
}

// Trivia questions used when attempting to figure out wumpus location
// could maybe be used to gain additional arrows or even stronger verisions of the arrows
// maybe a way to not die from super bats
