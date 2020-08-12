package WadeJeremyAssignment4;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UserInputThread extends Thread {

    private static String phrases;
    private static TypingFrame frame;
    private static long endTime;

    @Override
    public void start() {
        long currentTime;
        long TimeRemaining = -1;

        while (TimeRemaining != 0) {
            currentTime = System.currentTimeMillis() / 1000;
            TimeRemaining = endTime - currentTime;
            frame.SetTimeLeft(TimeRemaining);
        }
    }

    public void run(long endTime) {
        frame = new TypingFrame(phrases, endTime);
        SetUpFrame(frame);
        UserInputThread.endTime = endTime;
    }

    public static void SetPhrases() {
        boolean validInteger = false;
        int iterations = 0;

        while (!validInteger) {
            try {
                String iterationString = JOptionPane.showInputDialog("How many words would you like to type?");
                iterations = Integer.parseInt(iterationString);

                if (iterations > 0) 
                    validInteger = true;
                else 
                    JOptionPane.showMessageDialog(null, ("Please enter a valid Integer that is greater than 0"));
                
            } catch (HeadlessException | NumberFormatException InvalidInput) {
                JOptionPane.showMessageDialog(null, ("Please enter a valid Integer that is greater than 0"));
            }
        }

        //Current number of words 34
        ArrayList<String> wordList = new ArrayList<String>();
        wordList.add("Random");
        wordList.add("abaft");
        wordList.add("shelter");
        wordList.add("Lucky");
        wordList.add("expert");
        wordList.add("Zuccini");
        wordList.add("diplomatic");
        wordList.add("environmental");
        wordList.add("ambiguous");
        wordList.add("Mechanical"); // 10
        wordList.add("literature");
        wordList.add("Rehabilitation");
        wordList.add("drive");
        wordList.add("activity");
        wordList.add("agenda");
        wordList.add("reptile");
        wordList.add("Producer");
        wordList.add("backspace");
        wordList.add("Damage");
        wordList.add("dynamite"); // 20
        wordList.add("compound");
        wordList.add("dramatic");
        wordList.add("Carbon");
        wordList.add("democracy");
        wordList.add("bluff");
        wordList.add("dilemma");
        wordList.add("blacklist");
        wordList.add("leaf");
        wordList.add("healthy");
        wordList.add("knuckle"); // 30
        wordList.add("vinyl");
        wordList.add("lizard");
        wordList.add("Silver");
        wordList.add("grateful");

        String phrases = "";
        int counter = 0;
        int previousPhraseNum = -1;

        while (counter < iterations) {
            Random seed = new Random();
            int pickedPhraseNum = seed.nextInt(wordList.size());

            //Makes sure that the same word cannot be repeated after just being called
            if (previousPhraseNum == pickedPhraseNum) 
                continue;

            phrases += (wordList.get(pickedPhraseNum) + " ");
            counter++;
            previousPhraseNum = pickedPhraseNum;
        }

        //Removes the last space that was added while iterating through the list
        UserInputThread.phrases = phrases.substring(0, phrases.length() - 1);
    }

    //Creates the frame that will be used for the inputs
    private static void SetUpFrame(TypingFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static TypingFrame GetFrame() {
        return frame;
    }

    public static String GetPhrases() {
        return UserInputThread.phrases;
    }

    public static String GetUserPhrase() {
        return frame.GetUserPhrase();
    }

    public static ArrayList<Character> GetWrongCharactersList() {
        return frame.GetWrongCharactersList();
    }

    public static void DisposeFrame() {
        frame.dispose();
    }
}
