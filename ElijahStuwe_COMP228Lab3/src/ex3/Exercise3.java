package ex3;

import javax.swing.*;
import java.text.NumberFormat;

public class Exercise3 {
    interface MortgageConstants {
        final int shortTerm = 1;
        final int mediumTerm = 3;
        final int longTerm = 5;
    }
    static abstract class Mortgage implements MortgageConstants {
        public int mortgageNumber;
        public String customerName;
        public double mortgageAmount;
        protected double interestRate;
        public int term;

        public void getMortgageInfo() {
            JOptionPane.showMessageDialog(null, "Mortgage Number: "
            + mortgageNumber + " | Name: " + customerName + " | Mortgage Amount: "
            + NumberFormat.getCurrencyInstance().format(mortgageAmount)
            + " | Interest Rate: " + interestRate + "% | Term: " + term);
        }
    }

    static class BusinessMortgage extends Mortgage {
        BusinessMortgage() {
            interestRate = 1;
        }
    }

    static class PersonalMortgage extends Mortgage {
        PersonalMortgage() {
            interestRate = 2;
        }
    }
}
