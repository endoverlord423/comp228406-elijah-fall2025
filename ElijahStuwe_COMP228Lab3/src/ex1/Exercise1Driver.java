package ex1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.function.Function;

public class Exercise1Driver {
    public static void main(String[] args) {
        ArrayList<Exercise1.Insurance> insurances = new ArrayList<>();

        Exercise1.Insurance insurance1 = CreateInsurance();
        Exercise1.Insurance insurance2 = CreateInsurance();
        Exercise1.Insurance insurance3 = CreateInsurance();

        insurances.add(insurance1);
        insurances.add(insurance2);
        insurances.add(insurance3);

        insurances.forEach(Exercise1.Insurance::displayInfo);
    }

    static public Exercise1.Insurance CreateInsurance()
    {
        Exercise1.Insurance insurance = null;

        while (insurance == null) {
            String insuranceType = JOptionPane.showInputDialog("Enter Insurance Type (Health, Life)");


            if (insuranceType.equalsIgnoreCase("Health")) {
                insurance = new Exercise1.Health();
            } else if (insuranceType.equalsIgnoreCase("Life")) {
                insurance = new Exercise1.Life();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Insurance Type");
            }
        }

        while (true) {
            try {
                insurance.setInsuranceCost();
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Not a number");
            }
        }

        insurance.displayInfo();
        return  insurance;
    }
}
