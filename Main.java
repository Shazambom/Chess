import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * @author Ian Armand Moreno
 * @version v1.0
 */
public class Main{
    /* Main Bugs Currently:
     * I think there are still bugs within the king class. Check there to look. Probably something to do with
     * isInCheck(), isInCheckmate(), or checkmateAssist() 
     * 890 effective lines of code in total
     * I need to hire an exterminator ;-;
     */
    private static final int FRAME_WIDTH = 750;
    private static final int FRAME_HEIGHT = 750;
    static Board board = new Board();
    static JButton[][] buttonGrid;
    static JFrame messageScreen = new JFrame();
    static JTextArea text = new JTextArea("good luck; have fun");
    /**
     * The Main function of the program and is what runs all the classes together and initalizes everything.
     * Initalizes: The JFrame object, The array of JButton Objects, assigns all the ClickListeners to
     * their respective JButton objects.
     * @param args The standard String[] for the main function of any Java program. 
     */
    public static void main(String[]args){
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(8,8));
        buttonGrid = new JButton[8][8];
        int vary = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                buttonGrid[j][i] = new JButton();
                final JButton aButton = buttonGrid[j][i];
                final ActionListener a = new ClickListener(j,i);
                aButton.addActionListener(a);
                if(vary%2 == 0)buttonGrid[j][i].setBackground(Color.WHITE);
                else  buttonGrid[j][i].setBackground(Color.gray);
                vary++;
                if(board.getBoard()[j][i] != null){
                    buttonGrid[j][i].setIcon(board.getBoard()[j][i].getImg());
                }
                frame.add(buttonGrid[j][i]);
            }
            vary++;
        }
        frame.pack();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        text.setLineWrap(true);
        text.setTabSize(300000);
        messageScreen.add(text);
        messageScreen.pack();
        messageScreen.setSize(150, 75);
        messageScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        messageScreen.setVisible(true);
    }
}
