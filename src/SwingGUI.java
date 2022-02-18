
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGUI extends JFrame implements ActionListener {
    private JMenu menus;

    private ImageIcon menuBG;

    private JLabel myLabel;


    public SwingGUI(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        JFrame frame = new JFrame();
        frame.setTitle("Solitaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
        menuBG = new ImageIcon(this.getClass().getResource("main menu background.jpg"));
        myLabel = new JLabel(menuBG);
        myLabel.setSize(650, 650);

        frame.add(myLabel);

        frame.setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage());
    }

    public static void main(String[] args) {
        new SwingGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
