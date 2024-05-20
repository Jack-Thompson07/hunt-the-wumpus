import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Trivia {
    private Map<String, String> questions;

    public Trivia() {
        this.questions = new HashMap<>();
        this.questions.put("What is the capital of France?", "Paris");
        this.questions.put("What is the largest mammal in the world?", "Blue whale");
        this.questions.put("How many sides does a triangle have?", "3");
        this.questions.put("What is the chemical symbol for water?", "H2O");
        this.questions.put("Who painted the Mona Lisa?", "Leonardo da Vinci");
        this.questions.put("What year did World War II end?", "1945");
        this.questions.put("What is the capital of Japan?", "Tokyo");
        this.questions.put("What is the tallest mountain in the world?", "Mount Everest");
        this.questions.put("Who wrote 'Hamlet'?", "William Shakespeare");
        this.questions.put("What is the chemical symbol for iron?", "Fe");
        this.questions.put("What is the largest ocean on Earth?", "Pacific Ocean");
        this.questions.put("What is the freezing point of water in Celsius?", "0");
        this.questions.put("Who discovered penicillin?", "Alexander Fleming");
        this.questions.put("What is the main ingredient in hummus?", "Chickpeas");
        this.questions.put("What is the square root of 64?", "8");
        this.questions.put("Who was the first person to step on the moon?", "Neil Armstrong");
        this.questions.put("What is the currency of China?", "Yuan");
        this.questions.put("Who wrote 'Romeo and Juliet'?", "William Shakespeare");
        this.questions.put("What is the chemical symbol for oxygen?", "O");
        this.questions.put("What is the capital of Brazil?", "Brasília");
        this.questions.put("Who was the first female Prime Minister of the United Kingdom?", "Margaret Thatcher");
        this.questions.put("What is the fastest land animal?", "Cheetah");
        this.questions.put("What is the chemical symbol for sodium?", "Na");
        this.questions.put("What is the largest planet in our solar system?", "Jupiter");
        this.questions.put("Who painted 'Starry Night'?", "Vincent van Gogh");
        this.questions.put("What is the capital of Canada?", "Ottawa");
        this.questions.put("Who was the first president of the United States?", "George Washington");
        this.questions.put("What is the chemical symbol for gold?", "Au");
        this.questions.put("What is the melting point of ice in Fahrenheit?", "32");
        this.questions.put("Who wrote '1984'?", "George Orwell");
        this.questions.put("What is the capital of Italy?", "Rome");
        this.questions.put("What is the chemical symbol for carbon?", "C");
        this.questions.put("What is the largest desert in the world?", "Sahara Desert");
        this.questions.put("Who painted 'The Last Supper'?", "Leonardo da Vinci");
        this.questions.put("What is the chemical symbol for helium?", "He");
        this.questions.put("What is the longest river in the world?", "Nile River");
        this.questions.put("Who was the Greek god of thunder?", "Zeus");
        this.questions.put("What is the chemical symbol for silver?", "Ag");
        this.questions.put("What is the boiling point of water in Kelvin?", "373.15");
        this.questions.put("Who wrote 'Pride and Prejudice'?", "Jane Austen");
        this.questions.put("What is the capital of Spain?", "Madrid");
        this.questions.put("What is the chemical symbol for potassium?", "K");
        this.questions.put("What is the smallest country in the world?", "Vatican City");
        this.questions.put("Who painted 'Guernica'?", "Pablo Picasso");
        this.questions.put("What is the chemical symbol for nitrogen?", "N");
        this.questions.put("What is the deepest ocean trench?", "Mariana Trench");
        this.questions.put("Who wrote 'The Great Gatsby'?", "F. Scott Fitzgerald");
        this.questions.put("What is the capital of South Africa?", "Pretoria");
        this.questions.put("What is the chemical symbol for copper?", "Cu");

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
