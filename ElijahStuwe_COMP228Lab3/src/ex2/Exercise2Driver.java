package ex2;

import javax.swing.*;

public class Exercise2Driver {
    public static void main(String[] args) {
        Exercise2.GameTester tester1 = CreateGameTester();
        tester1.DisplayInfo();

        Exercise2.GameTester tester2 = CreateGameTester();
        tester2.DisplayInfo();
    }

    static public Exercise2.GameTester CreateGameTester() {
        Exercise2.GameTester tester = null;

        int testerType = JOptionPane.showOptionDialog(null, "Full Time?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        switch (testerType) {
            case 0:
                tester = new Exercise2.FullTimeGameTester();
                break;
            case 1:
                tester = new Exercise2.PartTimeGameTester();
                break;
            default:
                break;
        }

        tester.SetTesterName();
        tester.SetTesterSalary();

        return tester;
    }
}
