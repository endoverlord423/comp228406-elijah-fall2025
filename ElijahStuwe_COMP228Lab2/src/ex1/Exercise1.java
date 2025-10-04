package ex1;

import javax.swing.*;

public class Exercise1 {
    public int correctQuestions = 0, wrongQuestions = 0;
    //I don't see why we need an inputAnswer method when this handles that as well
    public void simulateQuestion() {
        for (int i = 0; i <  5; i++)
        {
            switch (i)
            {
                case 0:
                    String[] options1 =  { "It places the program in memory to execute it",
                            "It examines the bytecodes to ensure that they  are valid and do not violate Java’s security restrictions",
                            "It translates the Java source  code into bytecodes that represent the tasks to execute",
                            "It executes the bytecodes" };

                    var q1 = JOptionPane.showInputDialog(null,
                            "What is the main function of a Java compiler",
                            "Question 1", 0, null, options1, options1[0]);

                    checkAnswer(q1, i);
                    break;
                case 1:
                    String[] options2 =  { "Instance variable",
                            "Local variable",
                            "Non-static Method",
                            "Class" };

                    Object q2 = JOptionPane.showInputDialog(null,
                            "Which of the following components does a Java application need to have at least one of?",
                            "Question 2", 0, null, options2, options2[0]);

                    checkAnswer(q2, i);
                    break;
                case 2:
                    String[] options3 =  { "only constants",
                            "only variables",
                            "constants, variables, or expressions",
                            "only strings" };

                    Object q3 = JOptionPane.showInputDialog(null,
                            "Method arguments may be______________________________.",
                            "Question 3", 0, null, options3, options3[0]);

                    checkAnswer(q3, i);
                    break;
                case 3:
                    String[] options4 =  { "static",
                            "final",
                            "var",
                            "const" };

                    Object q4 = JOptionPane.showInputDialog(null,
                            "Class variables must be declared as___________.",
                            "Question 4", 0, null, options4, options4[0]);

                    checkAnswer(q4, i);
                    break;
                case 4:
                    String[] options5 =  { "local variables of that method and can be used only in that method’s body",
                            "instance variables that are not shared by all objects",
                            "class variables and shared by all the objects",
                            "global variables and can be used from anywhere inside the application source code" };

                    Object q5 = JOptionPane.showInputDialog(null,
                            "A method’s parameters are____________________________.",
                            "Question 5", 0, null, options5, options5[0]);

                    checkAnswer(q5, i);
                    break;
            }

        }
        float questionPercentage = ((float)correctQuestions / (correctQuestions + wrongQuestions) * 100);
        JOptionPane.showMessageDialog(null, correctQuestions +
                " Correct Questions" + ", " + wrongQuestions + " Wrong Questions" + ", "
                + questionPercentage + "% Correct");
    }

    public void checkAnswer(Object answer, int number) {
        boolean answerCheck;
        switch (number)
        {
            case 0:
                answerCheck = answer.toString().equalsIgnoreCase("It translates the Java source  code into bytecodes that represent the tasks to execute");
                generateMessage(answerCheck, number);
                break;
            case 1:
                answerCheck = answer.toString().equalsIgnoreCase("Class");
                generateMessage(answerCheck, number);
                break;
            case 2:
                answerCheck = answer.toString().equalsIgnoreCase("constants, variables, or expressions");
                generateMessage(answerCheck, number);
                break;
            case 3:
                answerCheck = answer.toString().equalsIgnoreCase("static");
                generateMessage(answerCheck, number);
                break;
            case 4:
                answerCheck = answer.toString().equalsIgnoreCase("local variables of that method and can be used only in that method’s body");
                generateMessage(answerCheck, number);
                break;
        }
    }

    public void generateMessage(boolean answerCheck, int number)
    {

        if (answerCheck)
        {
            switch ((int)(Math.random() *  4))
            {
                case 0:
                    JOptionPane.showMessageDialog(null, "Excellent!");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Good!");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Keep up the good work!");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Nice work!");
                    break;
            }
            correctQuestions++;
        }
        else
        {
            String correctAnswer = switch (number) {
                case 0 -> "It translates the Java source code into bytecodes that represent the tasks to execute";
                case 1 -> "Class";
                case 2 -> "constants, variables, or expressions";
                case 3 -> "static";
                case 4 -> "local variables of that method and can be used only in that method’s body";
                default -> "null";
            };
            switch ((int)(Math.random() *  4))
            {
                case 0:
                    JOptionPane.showMessageDialog(null, "No. Please try again." +
                            " Correct Answer: " + correctAnswer);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Wrong. Try once more." +
                            " Correct Answer: " + correctAnswer);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Don't give up!" +
                            " Correct Answer: " + correctAnswer);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "No. Keep trying." +
                            " Correct Answer: " + correctAnswer);
                    break;
            }
            wrongQuestions++;
        }
    }
}