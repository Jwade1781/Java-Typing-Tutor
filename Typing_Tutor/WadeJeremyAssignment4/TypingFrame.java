package WadeJeremyAssignment4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class TypingFrame extends JFrame {

    private final JPanel panel = new JPanel();
    private String userPhrase = "";
    private final String phrase;
    private Label timeLeftLabel;
    private JTextArea phraseTextArea;
    private final long endTime;
    private int charNumb = 0;
    private boolean correctChars = true;
    private Highlighter highlighter;
    private final ArrayList<Character> wrongCharacters = new ArrayList<>();
    private final buttonActions keyPressActions = new buttonActions();

    TypingFrame(String phrase, long endTime) {
        super("Typing Tutor");
        this.phrase = phrase;
        this.endTime = endTime;
        setSize(1080, 720);

        add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.gray);

        SetEnterBelowLabel();
        SetTimeLeftLabel();
        phraseTextArea = SetPhraseTextArea();
        SetUserTextArea();
        CreateButtons buttonsCreate = new CreateButtons(panel, keyPressActions);
    }

    private void SetEnterBelowLabel() {
        Label enterBelowLabel = new Label();
        enterBelowLabel.setText("Enter The Text below this line **This will Continue Until the Timer has run out**");

        Font font = new Font(null, Font.BOLD, 14);
        enterBelowLabel.setFont(font);
        enterBelowLabel.setForeground(Color.WHITE);
        enterBelowLabel.setBounds(186, 69, 560, 26);
        panel.add(enterBelowLabel);
    }

    private void SetTimeLeftLabel() {
        Label secondsRemaining = new Label();
        timeLeftLabel = new Label();
        secondsRemaining.setText("Seconds Remaining:");

        Font font = new Font(null, Font.BOLD, 16);
        secondsRemaining.setFont(font);
        secondsRemaining.setForeground(Color.GREEN);

        timeLeftLabel.setFont(font);
        timeLeftLabel.setForeground(Color.GREEN);

        secondsRemaining.setBounds(866, 199, 156, 26);
        timeLeftLabel.setBounds(930, 225, 100, 26);

        panel.add(secondsRemaining);
        panel.add(timeLeftLabel);
    }

    private JTextArea SetPhraseTextArea() {
        this.phraseTextArea = new JTextArea();
        JScrollPane phraseScrollPane = new JScrollPane(phraseTextArea);

        phraseTextArea.setText(phrase);

        Font font = new Font(null, Font.BOLD, 16);
        phraseTextArea.setFont(font);
        phraseTextArea.setLineWrap(true);
        phraseTextArea.setWrapStyleWord(true);
        phraseTextArea.setEditable(false);
        phraseTextArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                                                                    BorderFactory.createLoweredBevelBorder()));
        phraseScrollPane.setBounds(86, 105, 751, 92);

        panel.add(phraseScrollPane);
        return phraseTextArea;
    }

    private void SetUserTextArea() {
        JTextArea userTextArea = new JTextArea();
        JScrollPane userScrollPane = new JScrollPane(userTextArea);
        userScrollPane.setBounds(86, 210, 751, 200);
        Font font = new Font(null, Font.BOLD, 16);
        userTextArea.setFont(font);

        userTextArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                                                                  BorderFactory.createLoweredBevelBorder()));
        userTextArea.setLineWrap(true);
        userTextArea.setWrapStyleWord(true);
        
        userTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char typedKey = e.getKeyChar();

                //Checks if backspace is pressed.. Correcting the user's phrase if so
                try {
                    if (typedKey == '\b' && charNumb >= 1) {
                        userPhrase = userPhrase.substring(0, --charNumb);
                        //Checks to see if the new current userPhrase after the backspace matches the substring of the phrase
                        if (userPhrase.equals(phrase.substring(0, charNumb))) {
                            Highlighter.HighlightPainter painter;
                            highlighter.removeAllHighlights();
                            painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
                            highlighter.addHighlight(0, userPhrase.length(), painter);
                            SetCorrectColor();
                        }
                    }
                } 
                
                //Catches if the user enters a backspace with an empty String causing the program not to go out of bounds
                catch (BadLocationException BackSpaceAtChar0Error) {
                    userPhrase = "";
                    charNumb = 0;
                }

                //Adds the new key to the User's phrase and formating the input JtextArea depending
                if (typedKey != '\b') {
                    userPhrase += (typedKey);
                    //The user's typed character does not match the character within the random phrase
                    if (userPhrase.charAt(charNumb) != phrase.charAt(charNumb) && correctChars == true) {
                        wrongCharacters.add(phrase.charAt(charNumb));
                        try {
                            SetIncorrectColor();
                        } catch (BadLocationException ex) {
                            Logger.getLogger(TypingFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                    
                    //Sets the correct formatted input textarea if the user phrase matches the random phrase
                    else if (userPhrase.substring(0, charNumb).equals(phrase.substring(0, charNumb))) {
                        try {
                            SetCorrectColor();
                        } catch (BadLocationException ex) {
                            Logger.getLogger(TypingFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    charNumb++;
                }
            }               

            @Override
            public void keyPressed(KeyEvent e) {
                //Changes the buttons border by the HashMap(VK, JButton)
                keyPressActions.KeyPressed(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Changes the buttons border by the HashMap(VK, JButton)
                keyPressActions.KeyReleased(e.getKeyCode());
            }

        });
        panel.add(userScrollPane);
    }
    

    private void HighLightPhrase(String color) throws BadLocationException {
        this.highlighter = phraseTextArea.getHighlighter();
        Highlighter.HighlightPainter painter;

        switch (color) {
            case "GREEN":
                painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
                this.highlighter.addHighlight(0, userPhrase.length(), painter);
                break;

            case "RED":
                painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
                this.highlighter.addHighlight(userPhrase.length() - 1, userPhrase.length(), painter);
                break;

            default:
                throw new IllegalArgumentException("String color was not defined for TypingFrame.HighLightPhrase");
        }
    }

    private void SetIncorrectColor() throws BadLocationException {
        HighLightPhrase("RED");
        Font font = new Font(null, Font.BOLD, 18);
        phraseTextArea.setFont(font);
        phraseTextArea.setForeground(Color.BLUE);
        correctChars = false;
    }

    private void SetCorrectColor() throws BadLocationException {
        HighLightPhrase("GREEN");
        Font font = new Font(null, Font.BOLD, 16);
        phraseTextArea.setFont(font);
        phraseTextArea.setForeground(Color.BLACK);
        correctChars = true;
    }

    public ArrayList<Character> GetWrongCharactersList() {
        return wrongCharacters;
    }

    public String GetUserPhrase() {
        return userPhrase;
    }

    public void SetTimeLeft(long timeRemaining) {
        timeLeftLabel.setText(Long.toString(timeRemaining));
    }
}
