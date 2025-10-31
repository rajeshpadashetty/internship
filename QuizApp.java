import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    String questionText;
    String[] options;
    char correctAnswer;

    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = Character.toUpperCase(correctAnswer);
    }

    public boolean isCorrect(char answer) {
        return Character.toUpperCase(answer) == correctAnswer;
    }

    public void displayQuestion() {
        System.out.println("\n" + questionText);
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Enter your answer (A/B/C/D): ");
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("      🧠 WELCOME TO QUIZ APP 🧠");
        System.out.println("==================================");

        // Create questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "1. What is the capital of France?",
                new String[]{"A. Berlin", "B. Madrid", "C. Paris", "D. Rome"},
                'C'
        ));
        questions.add(new Question(
                "2. Which programming language is known as platform-independent?",
                new String[]{"A. Python", "B. C++", "C. Java", "D. C"},
                'C'
        ));
        questions.add(new Question(
                "3. What is 5 + 3 * 2?",
                new String[]{"A. 16", "B. 11", "C. 10", "D. 13"},
                'B'
        ));
        questions.add(new Question(
                "4. Who wrote 'Romeo and Juliet'?",
                new String[]{"A. Charles Dickens", "B. J.K. Rowling", "C. William Shakespeare", "D. Mark Twain"},
                'C'
        ));
        questions.add(new Question(
                "5. Which planet is known as the Red Planet?",
                new String[]{"A. Mars", "B. Venus", "C. Jupiter", "D. Saturn"},
                'A'
        ));

        int score = 0;

        // Loop through questions
        for (Question q : questions) {
            q.displayQuestion();
            char answer = sc.next().toUpperCase().charAt(0);

            if (q.isCorrect(answer)) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct answer: " + q.correctAnswer);
            }
        }

        // Final result
        System.out.println("\n==================================");
        System.out.println("           QUIZ OVER!             ");
        System.out.println("==================================");
        System.out.println("Your Score: " + score + " / " + questions.size());

        if (score == questions.size()) {
            System.out.println("🏆 Excellent! Perfect Score!");
        } else if (score >= 3) {
            System.out.println("👍 Good job! Keep practicing!");
        } else {
            System.out.println("📘 You can do better next time!");
        }

        sc.close();
    }
}
