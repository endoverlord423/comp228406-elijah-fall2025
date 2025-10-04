package ex3;

import java.awt.*;

public class Exercise3 {
    public void classCaller()
    {
        Test test1 = new Test(false, "George");
        System.out.println(test1);

        Test test2 = new Test(-1000, 500.01f);
        System.out.println(test2);

        Test test3 = new Test(true, 1500, "Alex", 5.004f);
        System.out.println(test3);
    }

    public class Test
    {
        boolean isFrench = false;
        int timeKidnapped = 0;
        String nameOfChair = "Tony";
        float money = 0.5f;

        public Test(boolean isFrench, String nameOfChair)
        {
            this.isFrench = isFrench;
            this.nameOfChair = nameOfChair;
        }

        public Test(int timeKidnapped, float money)
        {
            this.timeKidnapped = timeKidnapped;
            this.money = money;
        }

        public Test(boolean isFrench, int timeKidnapped, String nameOfChair, float money)
        {
            this.isFrench = isFrench;
            this.timeKidnapped = timeKidnapped;
            this.nameOfChair = nameOfChair;
            this.money = money;
        }

        @Override
        public String toString() {
            return (isFrench ? "You are French." : "You are not French.") +
                    " You have been kidnapped for " + timeKidnapped + " Light Hours. " +
                    "Your chairs name is " + nameOfChair + ". You have $" + String.format("%.2f", money);
        }
    }
}
