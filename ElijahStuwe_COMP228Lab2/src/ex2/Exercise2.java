package ex2;

import javax.swing.*;

public class Exercise2 {
    public void simulateLotto()
    {
        int lottoSum;
        for (int i = 0; i < 5; i++)
        {
            lottoSum = 0;
            Lotto lotto = new Lotto();
            lotto.printArray();

            String lottoGuess = JOptionPane.showInputDialog(null, "Guess a number between 3 and 27", "LottoMin", 0);

            for (int number : lotto.randArray)
            {
                lottoSum += number;
            }

            if (lottoGuess.equals(String.valueOf(lottoSum)))
            {
                JOptionPane.showMessageDialog(null, "Congrats, You won!");
                break;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Sorry, wrong guess");
                if (i == 4)
                {
                    JOptionPane.showMessageDialog(null, "Sorry, all out of guesses");
                }
            }
        }
    }

    public class Lotto
    {
        int[] randArray = { 0, 0, 0 };

        public Lotto()
        {
            for (int i = 0; i < 3; i++)
            {
                randArray[i] = (int)(Math.random() * 9) + 1;
            }
        }

        public void printArray()
        {
            for (int number : randArray)
            {
                System.out.println(number);
            }
        }
    }
}
