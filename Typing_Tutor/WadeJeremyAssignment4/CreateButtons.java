package WadeJeremyAssignment4;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CreateButtons {

    private final JPanel panel;
    private final buttonActions keyPressActions;
    public static HashMap<Integer, JButton> buttonMap = new HashMap<Integer, JButton>();
    
    private final String [] rowOne = {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "Backspace" };
    private final int [] rowOneKeys =  {KeyEvent.VK_BACK_QUOTE,KeyEvent.VK_1,KeyEvent.VK_2, KeyEvent.VK_3, 
                                  KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8,
                                  KeyEvent.VK_9, KeyEvent.VK_0, KeyEvent.VK_MINUS, KeyEvent.VK_EQUALS, KeyEvent.VK_BACK_SPACE};
    
    private final String [] rowTwo = {"Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\" };
    private final int [] rowTwoKeys = {KeyEvent.VK_TAB, KeyEvent.VK_Q, KeyEvent.VK_W, KeyEvent.VK_E, KeyEvent.VK_R,
                                 KeyEvent.VK_T, KeyEvent.VK_Y, KeyEvent.VK_U, KeyEvent.VK_I, KeyEvent.VK_O, KeyEvent.VK_P,
                                 KeyEvent.VK_OPEN_BRACKET, KeyEvent.VK_CLOSE_BRACKET, KeyEvent.VK_BACK_SLASH};
    
    private final String [] rowThree = {"Caps", "A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "'", "Enter"};
    private final int [] rowThreeKeys = {KeyEvent.VK_CAPS_LOCK, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_F,
                                  KeyEvent.VK_G, KeyEvent.VK_H, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L,
                                  KeyEvent.VK_SEMICOLON, KeyEvent.VK_QUOTE, KeyEvent.VK_ENTER};

    private final String [] rowFour = {"Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/", "Shift"};
    private final int [] rowFourKeys = {KeyEvent.VK_SHIFT, KeyEvent.VK_Z, KeyEvent.VK_X, KeyEvent.VK_C,KeyEvent.VK_V,
                                  KeyEvent.VK_B, KeyEvent.VK_N, KeyEvent.VK_M, KeyEvent.VK_COMMA, KeyEvent.VK_PERIOD,
                                  KeyEvent.VK_SLASH, KeyEvent.VK_SHIFT};
    
    //private final String [] rowFive = {"Ctrl", "Alt", "--", "Alt", "Ctrl"};
    //private final int [] rowFiveKeys = {KeyEvent.VK_CONTROL, KeyEvent.VK_ALT, KeyEvent.VK_SPACE, KeyEvent.VK_ALT, KeyEvent.VK_CONTROL};
    
    //private final String [] arrowKeys = {"<", "^", ">", "v"};
    //private final int [] arrowKeysKeys = {KeyEvent.VK_LEFT, KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN};
            
    public CreateButtons(JPanel panel, buttonActions keyPressActions) {
        this.panel = panel;
        this.keyPressActions = keyPressActions;
        SetButtons(panel);
        buttonActions.buttonMap = CreateButtons.buttonMap;
    }

    private void SetButtons(JPanel panel) {
        SetFirstRowButtons(panel);
        SetSecondRowButtons(panel);
        SetThirdRowButtons(panel);
        SetFourthRowButtons(panel);
        SetFifthRowButtons(panel);
        SetArrowButtons(panel);
    }

    private void SetFirstRowButtons(JPanel panel) {
        int xValue = 86;
        final int yValue = 421;
        int width = 50;
        final int height = 45;
        
        int counter = 0;
        for (counter = 0; counter < rowOne.length-1; counter++){
            JButton button = new JButton(rowOne[counter]);
            CreateButtons(button, xValue, yValue, width, height, panel);
            buttonMap.put(rowOneKeys[counter], button);
            xValue += width;
        }
        
        //BackSpace
        width = 100;
        JButton button = new JButton("Backspace");
        CreateButtons(button,xValue , yValue, width, height, panel);
        buttonMap.put((rowOneKeys[counter]), button);
    }

    private void SetSecondRowButtons(JPanel panel) {
        int xValue = 86;
        final int yValue = 466;
        int width = 75;
        final int height = 45;
        
        JButton buttonTab = new JButton("Tab");
        CreateButtons(buttonTab, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_TAB, buttonTab);
        
        xValue += width;
        width = 50;
        int counter;
        for (counter = 1; counter < rowTwo.length-1; counter++){
            JButton button = new JButton(rowTwo[counter]);
            CreateButtons(button, xValue, yValue, width, height, panel);
            buttonMap.put(rowTwoKeys[counter], button);
            xValue += width;
        }
        
        width = 75;
        JButton buttonBackSlash = new JButton("\\");
        CreateButtons(buttonBackSlash, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_BACK_SLASH, buttonBackSlash);
    }

    private void SetThirdRowButtons(JPanel panel) {
        int xValue = 86;
        final int yValue = 511;
        int width = 100;
        final int height = 45;
       
        JButton buttonCaps = new JButton("Caps");
        CreateButtons(buttonCaps, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_CAPS_LOCK, buttonCaps);
        
        xValue += width;
        width = 50;
        
        int counter;
        for (counter = 1; counter < rowThree.length-1; counter++){
            JButton button = new JButton(rowThree[counter]);
            CreateButtons(button, xValue, yValue, width, height, panel);
            buttonMap.put(rowThreeKeys[counter], button);
            xValue += width;
        }

        width += 50;
        JButton buttonEnter = new JButton("Enter");
        CreateButtons(buttonEnter, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_ENTER, buttonEnter);
    }

    private void SetFourthRowButtons(JPanel panel) {
        int xValue = 86;
        final int yValue = 556;
        int width = 115;
        final int height = 45;
        
        JButton buttonLeftShift = new JButton("Shift");
        CreateButtons(buttonLeftShift, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_SHIFT, buttonLeftShift);

        xValue += width;
        width = 50;
        
        
        int counter;
        for (counter = 1; counter < rowFour.length-1; counter++){
            JButton button = new JButton(rowFour[counter]);
            CreateButtons(button, xValue, yValue, width, height, panel);
            buttonMap.put(rowFourKeys[counter], button);
            xValue += width;
        }

        width = 135;
        JButton buttonRightShift = new JButton("Shift");
        CreateButtons(buttonRightShift, xValue, yValue, width, height, panel);
        //buttonMap.put(KeyEvent.KEY_LOCATION_LEFT , buttonRightShift);
    }
    
    private void SetFifthRowButtons(JPanel panel){
        int xValue = 86;
        final int yValue = 601;
        int width = 100;
        final int height = 45;
        
        JButton buttonLeftCtrl = new JButton("Ctrl");
        CreateButtons(buttonLeftCtrl, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_CONTROL, buttonLeftCtrl);

        xValue += width;
        width = 75;
        JButton buttonLeftAlt = new JButton("Alt");
        CreateButtons(buttonLeftAlt, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_ALT, buttonLeftAlt);

        xValue += width;
        width = 390;
        JButton buttonSpace = new JButton("--");
        CreateButtons(buttonSpace, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_SPACE, buttonSpace);

        xValue += width;
        width = 80;
        JButton buttonRightAlt = new JButton("Alt");
        CreateButtons(buttonRightAlt, xValue, yValue, width, height, panel);
        //buttonMap.put(KeyEvent.VK_ALT, buttonRightAlt);

        xValue += width;
        width = 105;
        JButton buttonRightCtrl = new JButton("Ctrl");
        CreateButtons(buttonRightCtrl, xValue, yValue, width, height, panel);
        //buttonMap.put(KeyEvent.VK_CONTROL, buttonRightCtrl);
    }

    private void SetArrowButtons(JPanel panel) {
        int xValue = 913;
        int yValue = 556;
        final int width = 50;
        final int height = 45;
        
        JButton buttonUpArrow = new JButton("^");
        CreateButtons(buttonUpArrow, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_UP, buttonUpArrow);

        yValue += height;
        JButton buttonDownArrow = new JButton("v");
        CreateButtons(buttonDownArrow, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_DOWN, buttonDownArrow);
        
        xValue -= width;
        JButton buttonLeftArrow = new JButton("<");
        CreateButtons(buttonLeftArrow, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_LEFT, buttonLeftArrow);
        
        xValue += 2*width;
        JButton buttonRightArrow = new JButton(">");
        CreateButtons(buttonRightArrow, xValue, yValue, width, height, panel);
        buttonMap.put(KeyEvent.VK_RIGHT, buttonRightArrow);
    }

    private void CreateButtons(JButton button, int xValue, int yValue, int width, int height, JPanel panel) {
        button.setBounds(xValue, yValue, width, height);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.add(button);
    }
}
