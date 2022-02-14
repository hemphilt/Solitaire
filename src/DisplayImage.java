import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayImage {

    public static void main(String avg[]) throws IOException
    {
        DisplayImage abc=new DisplayImage();
    }

    public DisplayImage() throws IOException
    {
        Card card = new Card(Suit.DIAMONDS, Rank.TWO);
//        File image = new File(System.getProperty("user.dir") + "\\src\\CardImages\\" + card.getRank() + " " + card.getSuit() + ".png");
//        BufferedImage cardImage = ImageIO.read(image);
//        card.setCardImage(cardImage);
        ImageIcon icon=new ImageIcon(card.getBackImage());
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200,300);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}