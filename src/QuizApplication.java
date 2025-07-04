import java.util.*;

class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswer;

    public Question(String questionText, List<String> options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctAnswer;
    }
}

class OnlineQuizApp {
    private List<Question> questions;
    private Scanner scanner;
    private int score;
    private int totalQuestions;

    public OnlineQuizApp() {
        questions = new ArrayList<>();
        scanner = new Scanner(System.in);
        score = 0;
        initializeQuestions();
        totalQuestions = questions.size();
    }

    private void initializeQuestions() {
        // Java programming questions
        questions.add(new Question(
                "What is the main method signature in Java?",
                Arrays.asList(
                        "1. public static void main(String args[])",
                        "2. public void main(String args[])",
                        "3. static void main(String args[])",
                        "4. public static main(String args[])"
                ),
                1
        ));

        questions.add(new Question(
                "Which of the following is NOT a primitive data type in Java?",
                Arrays.asList(
                        "1. int",
                        "2. float",
                        "3. String",
                        "4. boolean"
                ),
                3
        ));

        questions.add(new Question(
                "What does JVM stand for?",
                Arrays.asList(
                        "1. Java Virtual Machine",
                        "2. Java Variable Method",
                        "3. Java Verified Method",
                        "4. Java Visual Machine"
                ),
                1
        ));

        questions.add(new Question(
                "Which keyword is used to inherit a class in Java?",
                Arrays.asList(
                        "1. implements",
                        "2. extends",
                        "3. inherits",
                        "4. super"
                ),
                2
        ));

        questions.add(new Question(
                "What is the default value of an int variable in Java?",
                Arrays.asList(
                        "1. null",
                        "2. 0",
                        "3. -1",
                        "4. undefined"
                ),
                2
        ));
    }

    public void startQuiz() {
        System.out.println("=================================");
        System.out.println("    WELCOME TO ONLINE QUIZ APP");
        System.out.println("=================================");
        System.out.println("Instructions:");
        System.out.println("- Answer each question by entering the option number (1-4)");
        System.out.println("- Press Enter after each answer");
        System.out.println("- Total Questions: " + totalQuestions);
        System.out.println("=================================\n");

        System.out.print("Do you want to shuffle questions? (y/n): ");
        String shuffleChoice = scanner.nextLine().trim().toLowerCase();
        if (shuffleChoice.equals("y") || shuffleChoice.equals("yes")) {
            Collections.shuffle(questions);
            System.out.println("Questions shuffled!\n");
        }

        int questionNumber = 1;
        for (Question question : questions) {
            askQuestion(question, questionNumber);
            questionNumber++;
        }

        displayResults();
    }

    private void askQuestion(Question question, int questionNumber) {
        System.out.println("Question " + questionNumber + ":");
        System.out.println(question.getQuestionText());
        System.out.println();

        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println(options.get(i));
        }

        int userAnswer = getUserAnswer();

        switch (userAnswer) {
            case 1:
            case 2:
            case 3:
            case 4:
                if (question.isCorrect(userAnswer)) {
                    System.out.println("✓ Correct!\n");
                    score++;
                } else {
                    System.out.println("✗ Incorrect! The correct answer was option " +
                            question.getCorrectAnswer() + "\n");
                }
                break;
            default:
                System.out.println("✗ Invalid option! Marked as incorrect.\n");
                break;
        }

        System.out.println("-----------------------------------\n");
    }

    private int getUserAnswer() {
        int answer = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Your answer (1-4): ");
            try {
                String input = scanner.nextLine().trim();
                answer = Integer.parseInt(input);
                if (answer >= 1 && answer <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        return answer;
    }

    private void displayResults() {
        System.out.println("=================================");
        System.out.println("         QUIZ COMPLETED!");
        System.out.println("=================================");
        System.out.println("Your Score: " + score + "/" + totalQuestions);

        double percentage = (double) score / totalQuestions * 100;
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");

        String grade;
        if (percentage >= 90) {
            grade = "A+ (Excellent!)";
        } else if (percentage >= 80) {
            grade = "A (Great job!)";
        } else if (percentage >= 70) {
            grade = "B (Good work!)";
        } else if (percentage >= 60) {
            grade = "C (Fair)";
        } else {
            grade = "F (Need improvement)";
        }

        System.out.println("Grade: " + grade);
        System.out.println("=================================");

        System.out.print("\nDo you want to see detailed results? (y/n): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        if (choice.equals("y") || choice.equals("yes")) {
            showDetailedResults();
        }

        System.out.println("Thank you for taking the quiz!");
    }

    private void showDetailedResults() {
        System.out.println("\n=== DETAILED RESULTS ===");
        Iterator<Question> iterator = questions.iterator();
        int questionNum = 1;

        while (iterator.hasNext()) {
            Question question = iterator.next();
            System.out.println(questionNum + ". " + question.getQuestionText());
            System.out.println("   Correct Answer: Option " + question.getCorrectAnswer());
            questionNum++;
        }
    }

    public void closeResources() {
        scanner.close();
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        OnlineQuizApp quiz = new OnlineQuizApp();

        try {
            quiz.startQuiz();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            quiz.closeResources();
        }
    }
}