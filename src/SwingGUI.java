
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingGUI implements ActionListener, MouseListener {

    private final JFrame frame;

    private final JPanel topColumns;
    private final JPanel columns;
    private final JPanel playArea;

    private Card selectedCard = null;
    private Pile selectedPile = null;

    private Card selectedCard2 = null;
    private Pile selectedPile2 = null;

    private int whichMouseButton;

    public SwingGUI() {
        frame = new JFrame();
        frame.setTitle("Solitaire"); //set the title of the frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //exit out of application
        frame.getContentPane().setBackground(new Color(0, 102, 0)); //set the color of the background
        frame.setMinimumSize(new Dimension(900, 700));
        frame.setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage()); //set the icon image of the application
        frame.setLayout(new BorderLayout()); //create the layout for the frame as a border layout

        playArea = new JPanel();
        playArea.setOpaque(false);
        playArea.setLayout(new BoxLayout(playArea, BoxLayout.PAGE_AXIS));
        playArea.setPreferredSize(new Dimension(1000, 1000));


        FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
        flow.setHgap(20);
        flow.setAlignOnBaseline(false);

        columns = new JPanel();
        columns.setOpaque(false);
        columns.setLayout(flow);
        columns.setPreferredSize(new Dimension(200, 700));

        FlowLayout topFlow = new FlowLayout((FlowLayout.LEFT));
        topFlow.setHgap(20);
        topFlow.setAlignOnBaseline(true);

        topColumns = new JPanel();
        topColumns.setOpaque(false);
        topColumns.setPreferredSize(new Dimension(500, 500));
        topColumns.setLayout(topFlow);

        playArea.add(topColumns);
        playArea.add(columns);

        frame.add(playArea);


        frame.pack();
        frame.setLocationRelativeTo(null);  //center the frame if the window is not max size

        frame.setVisible(true); //make the frame visible

        createGame();
    }

    private void createGame() {
        topColumns.removeAll();
        columns.removeAll();

        Logic.newGame();

        Logic.deck.addMouseListener(this);

        topColumns.add(Logic.deck);
        Logic.deck.setPreferredSize(new Dimension(100, 130));

        Logic.drawPile.addMouseListener(this);
        topColumns.add(Logic.drawPile);
        Logic.drawPile.setPreferredSize(new Dimension(100, 130));

        for (Pile p : Logic.suitPiles) {
            for (int i = 0; i < p.getPileSize(); i++) {
                p.getCard(i).addMouseListener(this);
            }
            p.addMouseListener(this);
            p.setPreferredSize(new Dimension(100, 130));
            topColumns.add(p);
        }

        for (Pile p : Logic.tablePiles) {
            for (int i = 0; i < p.getPileSize(); i++) {
                p.getCard(i).addMouseListener(this);
            }
            p.addMouseListener(this);
            p.setPreferredSize(new Dimension(100, 500));
            columns.add(p);
        }


        frame.validate();
    }

    public static void main(String[] args) {
        new SwingGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component o = e.getComponent().getComponentAt(e.getPoint());
        if (SwingUtilities.isLeftMouseButton(e)) {
            whichMouseButton = 0;
        }
        else if (SwingUtilities.isRightMouseButton(e)){
            whichMouseButton = 1;
        }
        if (o instanceof Pile) {
            if (selectedPile == null) {
                selectedPile = (Pile) o;

                selectedCard = Logic.selectCard(selectedPile, whichMouseButton);
                System.out.println("Selected Card 1: " + selectedCard);
            } else if (selectedPile2 == null) {
                selectedPile2 = (Pile) o;
                selectedCard2 = Logic.selectCard(selectedPile2, whichMouseButton);
                if (selectedCard == selectedCard2) {
                    selectedCard2 = null;
                }
                System.out.println("Selected Card 2: " + selectedCard2);
                if (selectedPile != null && selectedPile2 != null) {
                    Logic.movePile(selectedPile, selectedPile2, whichMouseButton);
                    frame.repaint();
                    if (Logic.gameEnded()){
                        JOptionPane.showMessageDialog(null, "YOU WON CONGRATULATIONS!");
                    }
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
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
