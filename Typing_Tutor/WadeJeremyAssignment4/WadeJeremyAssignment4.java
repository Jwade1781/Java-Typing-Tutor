package WadeJeremyAssignment4;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class WadeJeremyAssignment4 {

    private static String phrase;
    private static String userTypedPhrase;
    private static TypingFrame frame;
    private static ArrayList<Character> wrongCharacters;
    private static UserInputThread inputThread;
    private static long endTime;
    private static Timer newTimer;

    public static void main(String[] args) {
        boolean playing = true;
        while (playing) {
            SetUpThread();
            SetUpTimer();

            inputThread.run(endTime);

            //Starts the thread and the timer
            inputThread.start();
            newTimer.runTimer();

            //Interrupts the thread
            EndThread(newTimer, inputThread);             
            
            FindCorrectPercent(newTimer);

            if (!wrongCharacters.isEmpty()) 
                JOptionPane.showMessageDialog(null, ("The characters that you messed up on were: " + wrongCharacters));
            else 
                JOptionPane.showMessageDialog(null, ("Congrats! No characters attempted to type were missed!"));
                

            playing = AskToTryAgain();
            UserInputThread.DisposeFrame();
        }
    }
    
    private static void SetUpThread(){
        //Creates a thread that will be used to create the frame and gather input from the user
        WadeJeremyAssignment4.inputThread = new UserInputThread();
        UserInputThread.SetPhrases();
    }
    
    private static void SetUpTimer(){
        //Timer that will be used to interrupt the thread when time runs out
        WadeJeremyAssignment4.newTimer = new Timer();
        WadeJeremyAssignment4.endTime = newTimer.GetEndTime();
    }

    private static void EndThread(Timer newTimer, UserInputThread inputThread) {
        //Gets the phrases that were generated randomly
        WadeJeremyAssignment4.phrase = UserInputThread.GetPhrases();

        //Gets the inputted User Phrase
        WadeJeremyAssignment4.userTypedPhrase = UserInputThread.GetUserPhrase();

        //Gets all the wrong Characters that were inputted in the thread
        WadeJeremyAssignment4.wrongCharacters = UserInputThread.GetWrongCharactersList();

        //Timer interrupts the thread
        newTimer.outOfTime(inputThread);
    }

    //Finds the percantage of the correct characters that were matched from what
    //the user typed and the predefined phrases
    private static void FindCorrectPercent(Timer newTimer) {
        double totalMatched = 0;

        //Finds the total number of matched characters between the user entered phrase and the 
        for (int counter = 0; counter < phrase.length() && counter < userTypedPhrase.length(); counter++) {
            if (phrase.charAt(counter) == userTypedPhrase.charAt(counter)) 
                totalMatched++;
        }

        String percentCorrect = Math.round(100 * (totalMatched / phrase.length())) + "%";
        JOptionPane.showMessageDialog(null, String.format("You managed to type %s of the characters within the time", percentCorrect));
    }

    private static boolean AskToTryAgain() {
        int tryAgain = JOptionPane.showConfirmDialog(null, "Would you like to try again?", "Try Again?", JOptionPane.YES_NO_OPTION);
        return tryAgain == JOptionPane.YES_OPTION;
    }
}
