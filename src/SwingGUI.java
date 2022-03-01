
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingGUI implements ActionListener, MouseListener, MouseMotionListener {

    private final JFrame frame;

    private JPanel topColumns;
    private JPanel columns;
    private JPanel playArea;
    private JTextField counter;
    private JButton btn;

    private int numMoves;

    Logic logic;


    public SwingGUI(Logic game){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;


        frame = new JFrame();
        frame.setTitle("Solitaire"); //set the title of the frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //exit out of application
//        frame.setSize(screenWidth, screenHeight); //set the dimensions/size of the frame to the computer monitor size
        frame.getContentPane().setBackground(new Color(0,102,0)); //set the color of the background
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
        topColumns.setPreferredSize(new Dimension(500,500));
        topColumns.setLayout(topFlow);

        btn = new JButton("Press me");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.drawCard();
                frame.repaint();
            }
        });

        JButton btn2 = new JButton("SHuflfe waste");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.shuffleWaste();
                frame.repaint();
            }
        });

        playArea.add(btn);
        playArea.add(btn2);

        playArea.add(topColumns);
        playArea.add(columns);

        frame.add(playArea);

        Point mouseOffset = new Point(0,0);

        frame.pack();
        frame.setLocationRelativeTo(null);  //center the frame if the window is not max size

        frame.setVisible(true); //make the frame visible

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
        logic.deck.setPreferredSize(new Dimension(100, 130));
        topColumns.add(logic.drawPile);
        logic.drawPile.setPreferredSize(new Dimension(100, 130));

        for(Pile p: logic.suitPiles){
            p.setPreferredSize(new Dimension(100, 130));
            topColumns.add(p);
        }

        for (Pile p: logic.tablePiles){
            p.setPreferredSize(new Dimension(100, 500));
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
                logic.shuffleWaste();
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
