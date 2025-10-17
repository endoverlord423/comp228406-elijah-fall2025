package ex2;

import javax.swing.*;
import java.text.Normalizer;
import java.text.NumberFormat;

public class Exercise2 {
    static abstract class GameTester {
        private String testerName;
        protected boolean isFullTime;
        protected double testerSalary;

        void SetTesterName() {
            testerName = JOptionPane.showInputDialog("Enter Name");
        }

        void DisplayInfo() {
            JOptionPane.showMessageDialog(null, "Tester Name: " + testerName
            + " | Tester Type: " + (isFullTime ? "Full Time" : "Part Time")
            + " | Tester Salary: " + NumberFormat.getCurrencyInstance().format(testerSalary) + (isFullTime ? "" : " Per Hour"));
        }

        abstract void SetTesterSalary();
    }

    static class FullTimeGameTester extends GameTester {
        @Override
        void SetTesterSalary() {
            testerSalary = 3000;
        }

        FullTimeGameTester() {
            isFullTime = true;
        }
    }

    static class PartTimeGameTester extends GameTester {
        @Override
        void SetTesterSalary() {
            testerSalary = 20;
            JOptionPane.showInputDialog("Enter Hours");
        }

        PartTimeGameTester() {
            isFullTime = false;
        }
    }
}
