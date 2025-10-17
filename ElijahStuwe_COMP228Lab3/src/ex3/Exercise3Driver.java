package ex3;

import javax.swing.*;
import java.util.ArrayList;

public class Exercise3Driver {
    public static void main(String[] args) {
        ArrayList<Exercise3.Mortgage> mortgages = ProcessMortgage();

        mortgages.forEach(Exercise3.Mortgage::getMortgageInfo);
    }

    public static ArrayList<Exercise3.Mortgage> ProcessMortgage() {
        ArrayList<Exercise3.Mortgage> mortgages = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            boolean flag = false;
            Exercise3.Mortgage mortgage = null;

            while (mortgage == null) {
                String mortgageType = JOptionPane.showInputDialog("Enter Mortgage Type (Business, Personal)");
                if (mortgageType.equalsIgnoreCase("business")) {
                    mortgage = new Exercise3.BusinessMortgage();
                } else if (mortgageType.equalsIgnoreCase("personal")) {
                    mortgage = new Exercise3.PersonalMortgage();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Type");
                }
            }

            mortgage.mortgageNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Mortgage Number"));
            mortgage.customerName = JOptionPane.showInputDialog("Enter Name");

            while (flag == false) {
                mortgage.mortgageAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter Mortgage Amount at or below $300k"));

                if (mortgage.mortgageAmount > 300000) {
                    JOptionPane.showMessageDialog(null, "Mortgage Amount too High");
                } else {
                    flag = true;
                }
            }

            mortgage.term = Integer.parseInt(JOptionPane.showInputDialog("Enter Term Length (1, 3, 5)"));

            switch (mortgage.term) {
                case(1):
                    break;
                case(3):
                    break;
                case(5):
                    break;
                default:
                    mortgage.term = Exercise3.MortgageConstants.shortTerm;
            }

            mortgages.add(mortgage);
        }

        return mortgages;
    }
}
