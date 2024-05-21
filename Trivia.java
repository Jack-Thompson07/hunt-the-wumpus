import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Trivia {
    private List<Question> triviaQuestions;
    private Random random;

    public Trivia() {
        triviaQuestions = new ArrayList<>();
        random = new Random();
        loadQuestionsFromCSV("trivia_questions.csv");
    }

    // Load trivia questions and answers from a CSV file
    private void loadQuestionsFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6); // Split into six parts
                if (parts.length == 6) {
                    String questionText = parts[0].trim();
                    String[] options = new String[] { parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim() };
                    String correctAnswer = parts[5].trim();
                    triviaQuestions.add(new Question(questionText, options, correctAnswer));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get a random trivia question and ensure it is not asked again
    public String[] getNextQuestion() {
        if (triviaQuestions.isEmpty()) {
            return null;
        }

        int index = random.nextInt(triviaQuestions.size());
        Question question = triviaQuestions.remove(index); // Remove the question to ensure it is not asked again

        String[] result = new String[5];
        result[0] = question.getQuestion();

        List<String> answers = new ArrayList<>();
        Collections.addAll(answers, question.getOptions());
        Collections.shuffle(answers); // Shuffle the answer options

        for (int i = 0; i < answers.size(); i++) {
            result[i + 1] = answers.get(i);
        }

        return result;
    }

    // Check if the given answer is correct for the specified question
    public boolean isCorrectAnswer(String questionText, String answer) {
        for (Question question : triviaQuestions) {
            if (question.getQuestionText().equalsIgnoreCase(questionText.trim())) {
                return question.isCorrect(answer);
            }
        }
        return false;
    }

    // Inner class to represent a trivia question
    public static class Question {
        private String questionText;
        private String[] options;
        private String correctAnswer;

        public Question(String questionText, String[] options, String correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return questionText;
        }

        public String[] getOptions() {
            return options;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public boolean isCorrect(String answer) {
            return correctAnswer.equalsIgnoreCase(answer.trim());
        }
    }
}