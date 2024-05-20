import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Trivia {
    private Map<String, String> questions;

    public Trivia(String csvFilePath) {
        this.questions = new HashMap<>();
        loadQuestionsFromCSV(csvFilePath);
    }

    private void loadQuestionsFromCSV(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String question = parts[0].trim();
                    String answer = parts[1].trim();
                    this.questions.put(question, answer);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    public String getQuestion() {
        Random random = new Random();
        Object[] keys = this.questions.keySet().toArray();
        return (String) keys[random.nextInt(keys.length)];
    }

    public boolean checkAnswer(String question, String answer) {
        String correctAnswer = this.questions.get(question);
        return correctAnswer != null && correctAnswer.equalsIgnoreCase(answer);
    }

    public static void main(String[] args) {
        Trivia trivia = new Trivia("trivia_questions.csv");
        String question = trivia.getQuestion();
        System.out.println("Question: " + question);
        // Example usage
        boolean correct = trivia.checkAnswer(question, "Your Answer");
        System.out.println("Correct? " + correct);
    }
}
