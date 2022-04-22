import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * This class displays the game to the user.
 */
public class SwingGUI extends Component
        implements ActionListener, MouseListener {

    /**
     * Represents the game's frame.
     */
    private JFrame frame;

    /**
     * Represents the top columns of the game.
     */
    private JPanel topColumns;

    /**
     * Represents the bottom columns of the game.
     */
    private JPanel columns;

    /**
     * Represents the JLabel for the number of moves.
     */
    private JLabel label;

    /**
     * Represents the JLabel for the current selected card.
     */
    private JLabel selectedCardLabel;

    /**
     * Represents the current pile selected.
     */
    private Pile selectedPile = null;


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

    /**
     * Represents the starting time for the timer.
     */
    private int time = 0;

    /**
     * Represents the format that the timer is displayed in.
     */
    private String timeDisplay;

    /**
     * Represents the boolean to check if the game has started.
     */
    private Boolean gameStarted = false;

    /**
     * Represents the timer.
     */
    private Timer timer;

    /**
     * Represents the JMenuBar.
     */
    private JMenuBar menuBar;

    /**
     * Represents the current selected color for the back of the cards.
     */
    private String selectedColor = "BackRed.png";

    /**
     * Represents the JRadioButtons.
     */
    private JRadioButton custom;

    /**
     * Represents if a selected file is used for a custom image.
     */
    private File selectedFile;

    /**
     * Represents whether the user clicks right or left mouse button.
     */
    private int whichButton;

    /**
     * Creates the GUI for the game.
     */
    public SwingGUI() {
        createButtonsAndMenus();
        createFrame();
        createGame();
    }

    /**
     * Creates the elements of the GUI (JFrame, JMenuBar, etc...).
     */
    private void createFrame() {
        frame = new JFrame();

        //set the title of the frame
        frame.setTitle("Solitaire");

        //exit out of application
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final int g = 102;

        frame.getContentPane().setBackground(new Color(0, g, 0));

        final int frameWidth = 900;
        final int frameHeight = 700;
        frame.setMinimumSize(new Dimension(frameWidth, frameHeight));

        final String name = "logo.png";
        Image newIcon = new ImageIcon(Objects.requireNonNull(
                getClass().getResource(name))).getImage();

        //set the icon image of the application
        frame.setIconImage(newIcon);

        //create the layout for the frame as a border layout
        frame.setLayout(new BorderLayout());

        JPanel playArea = new JPanel();
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

        //label to display the number of moves,
        // timer, and score
        label = new JLabel(counter + " Moves     "
                + "00:00" + "     Score: "
                + score, JLabel.CENTER);
        label.setPreferredSize(new Dimension(300, 200));
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setForeground(Color.WHITE);

        //label to display the current selected card
        selectedCardLabel = new JLabel("Selected Card:                ");
        selectedCardLabel.setPreferredSize(new Dimension(300, 20));
        selectedCardLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        selectedCardLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        selectedCardLabel.setForeground(Color.WHITE);

        playArea.add(selectedCardLabel);
        playArea.add(label);

        final int timerDelay = 1000;
        timer = new Timer(timerDelay, e -> {
            time++;
            timeDisplay = format(time / 60) + ":" + format(time % 60);
            label.setText(counter + " Moves     "
                    + timeDisplay + "     Score: " + score);
        });


        createButtonsAndMenus();

        frame.setJMenuBar(menuBar);

        frame.add(playArea);

        frame.pack();

        //center the frame if the window is not max size
        frame.setLocationRelativeTo(null);

        //make the frame visible
        frame.setVisible(true);
    }

    /**
     * Creates the buttons and menus for the frame.
     */
    private void createButtonsAndMenus() {
        //JMenuBar
        menuBar = new JMenuBar();

        //JMenus
        JMenu fileMenu = new JMenu("File");
        JMenu optionsMenu = new JMenu("Options");
        JMenu helpMenu = new JMenu("Help");

        //Submenu
        JMenu deck = new JMenu("Deck");
        JMenu background = new JMenu("Background");

        //JMenuItems
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem quit = new JMenuItem("Quit");

        JMenuItem howToPlay = new JMenuItem("How To Play");
        JMenuItem controls = new JMenuItem("Controls");

        //JRadio Buttons
        JRadioButton red = new JRadioButton("Red", true);
        JRadioButton green = new JRadioButton("Green");
        JRadioButton black = new JRadioButton("Black");
        JRadioButton blue = new JRadioButton("Blue");
        JRadioButton orange = new JRadioButton("Orange");
        JRadioButton purple = new JRadioButton("Purple");
        custom = new JRadioButton("Custom...");

        JRadioButton greenBGButton = new JRadioButton("Green Background", true);
        JRadioButton redBG = new JRadioButton("Red Background");
        JRadioButton blackBG = new JRadioButton("Black Background");
        JRadioButton blueBG = new JRadioButton("Blue Background");
        JRadioButton purpleBG = new JRadioButton("Purple Background");

        //add the buttons to a ButtonGroup so that only one can be selected
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(red);
        buttonGroup.add(green);
        buttonGroup.add(black);
        buttonGroup.add(blue);
        buttonGroup.add(orange);
        buttonGroup.add(purple);
        buttonGroup.add(custom);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(greenBGButton);
        buttonGroup1.add(redBG);
        buttonGroup1.add(blackBG);
        buttonGroup1.add(blueBG);
        buttonGroup1.add(purpleBG);

        //Add ActionListeners to radio buttons
        red.addActionListener(this);
        green.addActionListener(this);
        black.addActionListener(this);
        blue.addActionListener(this);
        orange.addActionListener(this);
        purple.addActionListener(this);
        custom.addActionListener(this);

        greenBGButton.addActionListener(this);
        redBG.addActionListener(this);
        blackBG.addActionListener(this);
        blueBG.addActionListener(this);
        purpleBG.addActionListener(this);

        //Add ActionListeners to menus
        restart.addActionListener(this);
        quit.addActionListener(this);
        howToPlay.addActionListener(this);
        controls.addActionListener(this);
        deck.addActionListener(this);
        background.addActionListener(this);

        fileMenu.add(restart);
        fileMenu.add(quit);

        optionsMenu.add(deck);
        optionsMenu.add(background);

        deck.add(red);
        deck.add(black);
        deck.add(blue);
        deck.add(green);
        deck.add(purple);
        deck.add(orange);
        deck.add(custom);

        background.add(greenBGButton);
        background.add(redBG);
        background.add(blackBG);
        background.add(blueBG);
        background.add(purpleBG);

        helpMenu.add(howToPlay);
        helpMenu.add(controls);

        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);

    }

    /**
     * Creates the game.
     */
    private void createGame() {
        Logic.newGame();

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
        try {
            if (custom.isSelected() && selectedFile != null) {
                Logic.changeBackImage(ImageIO.read(selectedFile));
            } else {
                Logic.changeBackImage(ImageIO.read(
                                Objects.requireNonNull(
                                        getClass().getResource(
                                                selectedColor))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        timer.stop();
        time = 0;
        gameStarted = false;
        counter = 0;
        score = 0;
        selectedCardLabel.setText("Selected Card:                ");
        label.setText(counter + " Moves     "
                + "00:00" + "     Score: " + score);
        frame.repaint();
    }

    /**
     * Method to increase the move counter and score.
     * The parameter checks whether to add to the score.
     *
     * @param i If i = 1, the score increments by 100.
     */
    private void increment(final int i) {
        counter++;
        if (i == 1) {
            score += 100;
        }
        label.setText(counter + " Moves     "
                + timeDisplay + "     Score: " + score);
    }

    /**
     * Method to format the timer.
     *
     * @param i the integer you are trying to format
     * @return the new format
     */
    private static String format(final int i) {
        String result = String.valueOf(i);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }


    /**
     * Main class that starts the program.
     *
     * @param args user given arguments
     */
    public static final void main(final String[] args) {
        new SwingGUI();
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        String s = e.getActionCommand();
        switch (s) {
            default:
                break;
            case "Restart":
                reset();
                break;
            case "Quit":
                System.exit(1);
            case "How To Play":
                JOptionPane.showMessageDialog(frame, Logic.howToPlay(),
                        "How to Play", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Controls":
                JOptionPane.showMessageDialog(frame, Logic.controls(),
                        "Controls", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Red":
                try {
                    Logic.changeBackImage(ImageIO.read(
                            Objects.requireNonNull(
                                    getClass().getResource("BackRed.png"))));
                    selectedColor = "BackRed.png";
                    frame.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Black":
                try {
                    Logic.changeBackImage(ImageIO.read(
                            Objects.requireNonNull(
                                    getClass().getResource("BackBlack.png"))));
                    selectedColor = "BackRed.png";
                    frame.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Blue":
                try {
                    Logic.changeBackImage(ImageIO.read(
                            Objects.requireNonNull(
                                    getClass().getResource("BackBlue.png"))));
                    selectedColor = "BackBlue.png";
                    frame.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Green":
                try {
                    Logic.changeBackImage(ImageIO.read(
                            Objects.requireNonNull(
                                    getClass().getResource("BackGreen.png"))));
                    selectedColor = "BackGreen.png";
                    frame.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Purple":
                try {
                    Logic.changeBackImage(ImageIO.read(
                            Objects.requireNonNull(
                                    getClass().getResource("BackPurple.png"))));
                    selectedColor = "BackPurple.png";
                    frame.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Orange":
                try {
                    Logic.changeBackImage(ImageIO.read(
                            Objects.requireNonNull(
                                    getClass().getResource("BackOrange.png"))));
                    selectedColor = "BackOrange.png";
                    frame.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Custom...":
                try {
                    JFileChooser fc = new JFileChooser();
                    fc.setCurrentDirectory(
                            new File(System.getProperty("user.home")));
                    FileFilter type = new FileNameExtensionFilter(
                            "Image Files", "jpg", "png");
                    fc.setFileFilter(type);
                    int r = fc.showOpenDialog(getParent());
                    selectedFile = fc.getSelectedFile();
                    if (r == JFileChooser.APPROVE_OPTION) {
                        Logic.changeBackImage(ImageIO.read(selectedFile));
                        frame.repaint();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Green Background":
                frame.getContentPane().setBackground(new Color(0, 102, 0));
                break;
            case "Red Background":
                frame.getContentPane().setBackground(new Color(128, 0, 0));
                break;
            case "Black Background":
                frame.getContentPane().setBackground(new Color(0, 0, 0));
                break;
            case "Blue Background":
                frame.getContentPane().setBackground(new Color(0, 0, 160));
                break;
            case "Purple Background":
                frame.getContentPane().setBackground(new Color(128, 0, 128));
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
            Card selectedCard;
            if (selectedPile == null) {
                selectedCardLabel.setText("Selected Card:                ");
                selectedPile = (Pile) o;
                selectedCard = Logic.selectCard(selectedPile, whichButton);
                selectedCardLabel.setText("Selected Card: " + selectedCard);
                label.setText(counter + " Moves     "
                        + format(time / 60) + ":" + format(time % 60)
                        + "     Score: " + score);
            } else if (selectedPile2 == null) {
                selectedPile2 = (Pile) o;
                if (!gameStarted) {
                    timer.start();
                    gameStarted = true;
                }
                if (Logic.movePile(selectedPile, selectedPile2, whichButton)
                        == 1) {
                    increment(1);
                }
                frame.repaint();
                if (Logic.gameEnded()) {
                    final String winMessage = "YOU WON CONGRATULATIONS!";
                    timer.stop();
                    JOptionPane.showMessageDialog(
                            frame, winMessage,
                            "YOU WIN!", JOptionPane.INFORMATION_MESSAGE);
                }

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
                if (!gameStarted) {
                    timer.start();
                    gameStarted = true;
                }
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
