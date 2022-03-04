import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class displays the game to the user.
 */
public class SwingGUI implements ActionListener, MouseListener {

    /** Represents the game's frame.*/
    private final JFrame frame;

    /** Represents the top columns of the game.*/
    private final JPanel topColumns;

    /** Represents the bottom columns of the game.*/
    private final JPanel columns;

    /** Represents the playable area of the game.*/
    private final JPanel playArea;

    /** Represents the current card selected.*/
    private Card selectedCard = null;

    /** Represents the current pile selected.*/
    private Pile selectedPile = null;

    /** Represents the destination card selected.*/
    private Card selectedCard2 = null;

    /** Represents the destination pile selected.*/
    private Pile selectedPile2 = null;

    /** Represents whether the user clicks right or left mouse button.*/
    private int whichButton;

    /** Creates the GUI for the game.*/
    public SwingGUI() {
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
        Image newIcon = new ImageIcon(getClass().getResource(name)).getImage();

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

        frame.add(playArea);

        frame.pack();

        //center the frame if the window is not max size
        frame.setLocationRelativeTo(null);

        //make the frame visible
        frame.setVisible(true);

        createGame();
    }

    /**
     * Creates the game.
     */
    private void createGame() {
        topColumns.removeAll();
        columns.removeAll();

        Logic.newGame();

        Logic.deck.addMouseListener(this);

        topColumns.add(Logic.deck);

        final int width = 100;
        final int height = 130;

        Logic.deck.setPreferredSize(new Dimension(width , height));

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
        Logic.newGame();
        createGame();
        frame.repaint();
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
                selectedPile = (Pile) o;
                selectedCard = Logic.selectCard(selectedPile, whichButton);
                System.out.println("Selected Card 1: " + selectedCard);
            } else if (selectedPile2 == null) {
                selectedPile2 = (Pile) o;
                selectedCard2 = Logic.selectCard(selectedPile2, whichButton);
                if (selectedCard == selectedCard2) {
                    selectedCard2 = null;
                }
                System.out.println("Selected Card 2: " + selectedCard2);
                Logic.movePile(selectedPile, selectedPile2, whichButton);
                frame.repaint();
                    if (Logic.gameEnded()) {
                        final String winMessage = "YOU WON CONGRATULATIONS!";
                        JOptionPane.showMessageDialog(null, winMessage);
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
