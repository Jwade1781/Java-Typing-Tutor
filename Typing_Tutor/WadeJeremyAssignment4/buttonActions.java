package WadeJeremyAssignment4;

import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class buttonActions {
    public static HashMap<Integer, JButton> buttonMap = new HashMap<Integer, JButton>();

    public void KeyPressed(int keyCode) {
        //Takes the VK KeyCode and matches it with the corresponding JButton value in the hashMap
        if (buttonMap.containsKey(keyCode)){
            Object button = buttonMap.get(keyCode);
            SetKeyPressedBorder((JButton) button);
        }
    }
    
    public void KeyReleased(int keyCode) {
        //Takes the VK KeyCode and matches it with the corresponding JButton value in the hashMap
        if (buttonMap.containsKey(keyCode)){
            Object button = buttonMap.get(keyCode);
            SetKeyReleasedBorder((JButton) button);
        }
    }
    
    private void SetKeyPressedBorder(JButton button) {
        button.setBorder(BorderFactory.createLoweredBevelBorder());
    }

    private void SetKeyReleasedBorder(JButton button) {
        button.setBorder(BorderFactory.createRaisedBevelBorder());
    }
}
