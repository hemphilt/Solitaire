
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingGUI implements ActionListener, MouseListener, MouseMotionListener {

    private final JFrame frame;

    private JPanel topColumns;
    private JPanel columns;
    private JPanel playArea;
    private JTextField counter;

    private int numMoves;

    Logic logic;


    public SwingGUI(Logic game){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;


        frame = new JFrame();
        frame.setTitle("Solitaire"); //set the title of the frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //exit out of application
        frame.setSize(screenWidth, screenHeight); //set the dimensions/size of the frame to the computer monitor size
        frame.getContentPane().setBackground(new Color(0,102,0)); //set the color of the background
        frame.setMinimumSize(new Dimension(900, 700));
        frame.setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage()); //set the icon image of the application
        frame.setLocationRelativeTo(null);  //center the frame if the window is not max size
        frame.setLayout(new BorderLayout()); //create the layout for the frame as a border layout

        playArea = new JPanel();
        playArea.setOpaque(false);
        playArea.setLayout(new BoxLayout(playArea, BoxLayout.PAGE_AXIS));
        playArea.setPreferredSize(new Dimension(500, 500));


        FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
        flow.setAlignOnBaseline(false);

        columns = new JPanel();
        columns.setOpaque(false);
        columns.setLayout(flow);
        columns.setMinimumSize(new Dimension(200, 900));

        FlowLayout topFlow = new FlowLayout((FlowLayout.LEFT));
        topFlow.setHgap(20);
        topFlow.setAlignOnBaseline(true);

        topColumns = new JPanel();
        topColumns.setOpaque(false);
        topColumns.setLayout(topFlow);

        playArea.add(topColumns);
        playArea.add(columns);

        frame.add(playArea);

        Point mouseOffset = new Point(0,0);

        frame.setVisible(true); //make the frame visible

        frame.pack();
        createGame();
    }

    private void createGame(){
        topColumns.removeAll();
        columns.removeAll();

        Deck deck = new Deck();
        logic.deck = deck;



        for (Card c: logic.deck.deckOfCards){
            c.setPreferredSize(new Dimension(72, 96));
            c.addMouseListener(this);
            c.addMouseMotionListener(this);
        }

        logic.newGame();

        topColumns.add(logic.deck);
        logic.deck.setPreferredSize(new Dimension(72, 96));
        topColumns.add(logic.drawPile);
        logic.drawPile.setPreferredSize(new Dimension(72, 96));

        for(Pile p: logic.suitPiles){
            p.setPreferredSize(new Dimension(90, 96));
            topColumns.add(p);
        }

        for (Pile p: logic.tablePiles){
            p.setPreferredSize(new Dimension(90, 900));
            columns.add(p);
        }


        frame.validate();;
    }

    public void reset(){
        logic.newGame();
        createGame();
        frame.repaint();
    }


    public static void main(String[] args) {
        Logic game = new Logic();
        new SwingGUI(game);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent() instanceof Card){
            System.out.println("yoyoyoyo");
            Card c = (Card)e.getComponent();
            Pile p = (Pile)c.getParent();

            if (p.getPileType() == PileType.WASTE){
                logic.drawCard();
            }
            else if (p.getPileType() == PileType.FOUNDATION){
                logic.clickPile(p);
            }
            else if (p.getPileType() == PileType.TABLEAU){
                logic.turnPile();
            }

        }
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
