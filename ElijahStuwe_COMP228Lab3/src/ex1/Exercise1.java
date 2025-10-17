package ex1;

import javax.swing.*;
import java.text.NumberFormat;

public class Exercise1 {
    static abstract class Insurance {
        protected String typeOfInsurance;
        protected double monthlyCost;

        public String GetInsuranceType() {
            return  typeOfInsurance;
        }
        public double GetMonthlyCost() {
            return monthlyCost;
        }

        public abstract void setInsuranceCost();
        public abstract void displayInfo();
    }

    static class Health extends Insurance {
        @Override
        public void setInsuranceCost() {
            monthlyCost = Double.parseDouble(JOptionPane.showInputDialog("Enter Insurance Cost"));
        }

        public void displayInfo() {
            JOptionPane.showMessageDialog(null, "Insurance type: " + typeOfInsurance
            + " | Monthly Cost: " + NumberFormat.getCurrencyInstance().format(monthlyCost));
        }

        public Health() {
            typeOfInsurance = "Health";
        }

    }

    static class Life extends Insurance {
        @Override
        public void setInsuranceCost() {
            monthlyCost = Double.parseDouble(JOptionPane.showInputDialog("Enter Insurance Cost"));
        }

        public void displayInfo() {
            JOptionPane.showMessageDialog(null, "Insurance type: " + typeOfInsurance
            + " | Monthly Cost: " + NumberFormat.getCurrencyInstance().format(monthlyCost));
        }

        public Life() {
            typeOfInsurance = "Life";
        }
    }
}
