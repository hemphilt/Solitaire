import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * This class displays the game to the user.
 */
public class SwingGUI extends Component implements ActionListener, MouseListener {

    /**
     * Represents the game's frame.
     */
    private JFrame frame, deckFrame;

    /**
     * Represents the top columns of the game.
     */
    private JPanel topColumns;

    /**
     * Represents the bottom columns of the game.
     */
    private JPanel columns;

    /**
     * Represents the playable area of the game.
     */
    private JPanel playArea;

    /**
     * Represents the JLabel for the number of moves.
     */
    private JLabel label, selectedCardLabel;

    /**
     * Represents the JMenuBar for the frame.
     */
    private JMenuBar menuBar;

    /**
     * Represents the JMenus for the JMenuBar.
     */
    private JMenu fileMenu, optionsMenu, helpMenu;

    /**
     * Represents the JMenuItems for the JMenus.
     */
    private JMenuItem load, save, restart, quit, howToPlay, controls, deck, background, settings;

    /**
     * Represents the current card selected.
     */
    private Card selectedCard = null;

    /**
     * Represents the current pile selected.
     */
    private Pile selectedPile = null;

    /**
     * Represents the destination card selected.
     */
    private Card selectedCard2 = null;

    /**
     * Represents the destination pile selected.
     */
    private Pile selectedPile2 = null;

    /**
     * Represents the counter for the number of moves.
     */
    private int counter = 0;

    /**
     * Represents the score.
     */
    private int score = 0;

    private Timer timer;

    /**
     * Represents whether the user clicks right or left mouse button.
     */
    private int whichButton;

    /**
     * Creates the GUI for the game.
     */
    public SwingGUI() {
        createFrame();
        createGame();
    }

    /**
     * Creates the elements of the GUI (JFrame, JMenuBar, etc...)
     */
    private void createFrame(){
        frame = new JFrame();

        //set the title of the frame
        frame.setTitle("Solitaire");

        //exit out of application
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final int g = 102;

        //set the color of the background
        frame.getContentPane().setBackground(new Color(0, g, 0));

        final int frameWidth = 900;
        final int frameHeight = 700;
        frame.setMinimumSize(new Dimension(frameWidth, frameHeight));

        final String name = "logo.png";
        Image newIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(name))).getImage();

        //set the icon image of the application
        frame.setIconImage(newIcon);

        //create the layout for the frame as a border layout
        frame.setLayout(new BorderLayout());

        playArea = new JPanel();
        playArea.setOpaque(false);
        playArea.setLayout(new BoxLayout(playArea, BoxLayout.PAGE_AXIS));

        final int panelWidth = 1000;
        final int panelHeight = 1000;
        playArea.setPreferredSize(new Dimension(panelWidth, panelHeight));

        FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
        final int hGap = 20;
        flow.setHgap(hGap);
        flow.setAlignOnBaseline(false);

        columns = new JPanel();
        columns.setOpaque(false);
        columns.setLayout(flow);
        final int colWidth = 200;
        final int colHeight = 700;
        columns.setPreferredSize(new Dimension(colWidth, colHeight));

        FlowLayout topFlow = new FlowLayout((FlowLayout.LEFT));
        topFlow.setHgap(hGap);
        topFlow.setAlignOnBaseline(true);

        topColumns = new JPanel();
        topColumns.setOpaque(false);
        final int topColWidth = 500;
        final int topColHeight = 500;
        topColumns.setPreferredSize(new Dimension(topColWidth, topColHeight));
        topColumns.setLayout(topFlow);

        playArea.add(topColumns);
        playArea.add(columns);

        //label to display the number of moves, timer, and score
        label = new JLabel(counter + " Moves     " + timer + "     Score: " + score, JLabel.CENTER);
        label.setPreferredSize(new Dimension(300, 200));
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setForeground(Color.WHITE);

        selectedCardLabel = new JLabel("Selected Card:                ");
        selectedCardLabel.setPreferredSize(new Dimension(300,20));
        selectedCardLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        selectedCardLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        selectedCardLabel.setForeground(Color.WHITE);

        playArea.add(selectedCardLabel);
        playArea.add(label);

        //New Frame
        deckFrame = new JFrame("Yo");
        deckFrame.setPreferredSize(new Dimension(500,500));
        deckFrame.setMinimumSize(new Dimension(500,500));

        ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir") + "\\images\\doge.png");
        Image img = imageIcon.getImage();
        Image newImg = img.getScaledInstance(90,120, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);

        JLabel backred = new JLabel(imageIcon);

        deckFrame.add(backred);

        deckFrame.pack();
        deckFrame.setLocationRelativeTo(null);

        //JMenuBar, JMenus, and JMenuBarItems
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        optionsMenu = new JMenu("Options");
        helpMenu = new JMenu("Help");

        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        restart = new JMenuItem("Restart");
        quit = new JMenuItem("Quit");

        deck = new JMenuItem("Deck");
        background = new JMenuItem("Background");
        settings = new JMenuItem("Settings");

        howToPlay = new JMenuItem("How To Play");
        controls = new JMenuItem("Controls");

        load.addActionListener(this);
        save.addActionListener(this);
        restart.addActionListener(this);
        quit.addActionListener(this);
        howToPlay.addActionListener(this);
        controls.addActionListener(this);
        deck.addActionListener(this);
        background.addActionListener(this);
        settings.addActionListener(this);

        fileMenu.add(load);
        fileMenu.add(save);
        fileMenu.add(restart);
        fileMenu.add(quit);

        optionsMenu.add(deck);
        optionsMenu.add(background);
        optionsMenu.add(settings);

        helpMenu.add(howToPlay);
        helpMenu.add(controls);

        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);

        frame.add(playArea);

        frame.pack();

        //center the frame if the window is not max size
        frame.setLocationRelativeTo(null);

        //make the frame visible
        frame.setVisible(true);
    }

    /**
     * Creates the game.
     */
    private void createGame() {
        Logic.newGame();

        createTimer();

        Logic.deck.addMouseListener(this);

        topColumns.add(Logic.deck);

        final int width = 100;
        final int height = 130;

        Logic.deck.setPreferredSize(new Dimension(width, height));

        Logic.drawPile.addMouseListener(this);
        topColumns.add(Logic.drawPile);
        Logic.drawPile.setPreferredSize(new Dimension(width, height));

        for (Pile p : Logic.getSuitPiles()) {
            for (int i = 0; i < p.getPileSize(); i++) {
                p.getCard(i).addMouseListener(this);
            }
            p.addMouseListener(this);
            p.setPreferredSize(new Dimension(width, height));
            topColumns.add(p);
        }

        for (Pile p : Logic.getTablePiles()) {
            for (int i = 0; i < p.getPileSize(); i++) {
                p.getCard(i).addMouseListener(this);
            }
            p.addMouseListener(this);
            final int height2 = 500;
            p.setPreferredSize(new Dimension(width, height2));
            columns.add(p);
        }

        frame.validate();
    }

    /**
     * Resets the game.
     */
    public void reset() {
        topColumns.removeAll();
        columns.removeAll();
        createGame();
        counter = 0;
        score = 0;
        selectedCardLabel.setText("Selected Card:                ");
        label.setText(counter + " Moves     " + timer + "     Score: " + score);
        frame.repaint();
    }

    /**
     * Method to increase and update the counter for each move.
     */
    private void increment(int i) {
        counter++;
        if (i == 1){
            score+= 100;
        }
        label.setText(counter + " Moves     " + timer + "     Score: " + score);
    }

    private void createTimer(){
        final int[] time = {0};
        JLabel timerLabel = new JLabel("00:00");
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time[0] += 1;
                timerLabel.setText(format(time[0] /60) + ":" + format(time[0] % 60));
            }
        });
        timer.start();
    }

    private static String format(int i){
        String result = String.valueOf(i);
        if (result.length() == 1){
            result = "0" + result;
        }
        return result;
    }

    /**
     * Main class that starts the program.
     *
     * @param args user given arguments
     */
    public static void main(final String[] args) {
        new SwingGUI();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        String s = e.getActionCommand();

        switch (s) {
            default:
                break;
            case "Load":
                System.out.println(s);
                try {
                    Logic.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Save":
                System.out.println(s);
                try {
                    Logic.save();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Restart":
                reset();
                break;
            case "Quit":
                System.exit(0);
            case "How To Play":
                JOptionPane.showMessageDialog(frame, Logic.howToPlay(), "How to Play", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Controls":
                JOptionPane.showMessageDialog(frame, Logic.controls(), "Controls", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Deck":
                System.out.println("Deck");
                try {
                    Logic.deck.setDeckImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("card back black.png"))));
                    for(int i = 0; i < Logic.deck.deckSize(); i++){
                        Logic.deck.getCard(i).setBackImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("card back black.png"))));
                    }
                    frame.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                deckFrame.setVisible(true);
                break;
            case "Background":
                System.out.println("Background");
                break;
            case "Settings":
                System.out.println("Settings");
                break;
        }
    }

    /**
     * Selects card based on which mouse button is clicked.
     *
     * @param e the mouse event that occurred
     */
    @Override
    public void mouseClicked(final MouseEvent e) {
        Component o = e.getComponent().getComponentAt(e.getPoint());
        if (SwingUtilities.isLeftMouseButton(e)) {
            whichButton = 0;
        } else if (SwingUtilities.isRightMouseButton(e)) {
            whichButton = 1;
        }
        if (o instanceof Pile) {
            if (selectedPile == null) {
                selectedCardLabel.setText("Selected Card:                ");
                selectedPile = (Pile) o;
                selectedCard = Logic.selectCard(selectedPile, whichButton);
                selectedCardLabel.setText("Selected Card: " + selectedCard);
                label.setText(counter + " Moves     " + timer + "     Score: " + score);
            } else if (selectedPile2 == null) {
                selectedPile2 = (Pile) o;
                selectedCard2 = Logic.selectCard(selectedPile2, whichButton);
                if (Logic.movePile(selectedPile, selectedPile2, whichButton) == 1) {
                    increment(1);
                }
                frame.repaint();
                if (Logic.gameEnded()) {
                    final String winMessage = "YOU WON CONGRATULATIONS!";
                    JOptionPane.showMessageDialog(frame, winMessage, "YOU WIN!", JOptionPane.INFORMATION_MESSAGE);
                }

                selectedCard = null;
                selectedCard2 = null;
                selectedPile = null;
                selectedPile2 = null;
            }
        }

        if (o instanceof Deck) {
            if (Logic.deck.isEmpty()) {
                Logic.shuffleWaste();
            } else {
                Logic.drawCard();
                increment(0);
            }
        }

        frame.repaint();
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }
}
