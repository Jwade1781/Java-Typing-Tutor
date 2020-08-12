package WadeJeremyAssignment4;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class Timer {
    private long endTime;
    
    Timer(){
        this.endTime = setTimer(endTime);
    }
    
    private long setTimer(long endTime){
        boolean validInteger = false;
        int interval = 0;
        
        //Asks the user to input how long they'd want the frame to run for
        while (!validInteger){
            try {
                String intervalString = JOptionPane.showInputDialog("How many seconds would you like to practice for? (This will continue until time runs out)");
                interval = Integer.parseInt(intervalString);
                
                if (interval > 0)
                    validInteger = true;
                
                else
                    JOptionPane.showMessageDialog(null, ("Please enter a valid Integer that is greater than 0"));
            }
            
            //User inputted not an integer
            catch(HeadlessException | NumberFormatException InvalidInput){
                JOptionPane.showMessageDialog(null, ("Please enter a valid Integer that is greater than 0"));
            }
        }
        
        //returns the time that the program should end at
        endTime = (System.currentTimeMillis()/1000) + interval;
        return endTime;
    }
    
    public void runTimer(){
        long currentTime = (System.currentTimeMillis()/1000);
        while (currentTime != endTime){
            currentTime = (System.currentTimeMillis()/1000);      
        } 
    }
    
    public void outOfTime(UserInputThread inputThread){   
        //Interrupt the thread that is running the input frame
        inputThread.interrupt();
        
        JOptionPane.showMessageDialog(null, "Out of Time!", "Timer expired", JOptionPane.PLAIN_MESSAGE);  
        
        //Wait until the thread is dead
        while (inputThread.isAlive()){}
    }
    
    public long GetEndTime(){
        return this.endTime;
    }
}
