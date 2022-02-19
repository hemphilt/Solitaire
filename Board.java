import java.awt.*;
import javax.swing.*;

public class Board {

	Board() {

		JFrame frame = new JFrame("Solitaire");

		JPanel game = new JPanel();
		game.setBounds(0, 0, 1000, 600);
		game.setBackground(new Color(3, 80, 0));
		 
		JLayeredPane board = new JLayeredPane();
	    board.setPreferredSize(new Dimension(300, 310));
	    board.setBackground(new Color(3, 80, 0));
	    board.setOpaque(true);

			// TO DO : CHANGE TO FOR LOOPS //
		JLabel deck = new JLabel();
	        deck.setOpaque(true);
	        deck.setBackground(Color.gray); //eventually change to image
	        deck.setBorder(BorderFactory.createLineBorder(Color.black));
	        deck.setBounds(10, 10, 100, 150);
	        board.add(deck);

        JLabel drawn = new JLabel();
	        drawn.setOpaque(true);
	        drawn.setBackground(Color.lightGray); //eventually change to image
	        drawn.setBorder(BorderFactory.createLineBorder(Color.black));
	        drawn.setBounds(120, 10, 100, 150);
	        board.add(drawn);
	        
        JLabel stack1 = new JLabel();
	        stack1.setOpaque(true);
	        stack1.setBackground(Color.red); //eventually change to image
	        stack1.setBorder(BorderFactory.createLineBorder(Color.black));
	        stack1.setBounds(10, 170, 100, 150);
	        board.add(stack1);

        JLabel stack2 = new JLabel();
	        stack2.setOpaque(true);
	        stack2.setBackground(Color.orange); //eventually change to image
	        stack2.setBorder(BorderFactory.createLineBorder(Color.black));
	        stack2.setBounds(120, 170, 100, 150);
	        board.add(stack2);
	        
	        JLabel stack2Card2 = new JLabel();
	        	stack2Card2.setOpaque(true);
	        	stack2Card2.setBackground(Color.orange); //eventually change to image
	        	stack2Card2.setBorder(BorderFactory.createLineBorder(Color.black));
		        stack2Card2.setBounds(120, 210, 100, 150);
		        board.add(stack2Card2);
		        board.moveToFront(stack2Card2);

        JLabel stack3 = new JLabel();
	        stack3.setOpaque(true);
	        stack3.setBackground(Color.yellow); //eventually change to image
	        stack3.setBorder(BorderFactory.createLineBorder(Color.black));
	        stack3.setBounds(230, 170, 100, 150);
	        board.add(stack3);
	        
	        JLabel stack3Card2 = new JLabel();
		        stack3Card2.setOpaque(true);
		        stack3Card2.setBackground(Color.yellow); //eventually change to image
		        stack3Card2.setBorder(BorderFactory.createLineBorder(Color.black));
		        stack3Card2.setBounds(230, 210, 100, 150);
		        board.add(stack3Card2);
		        board.moveToFront(stack3Card2);
	        JLabel stack3Card3 = new JLabel();
		        stack3Card3.setOpaque(true);
		        stack3Card3.setBackground(Color.yellow); //eventually change to image
		        stack3Card3.setBorder(BorderFactory.createLineBorder(Color.black));
		        stack3Card3.setBounds(230, 250, 100, 150);
		        board.add(stack3Card3);
		        board.moveToFront(stack3Card3);

        JLabel stack4 = new JLabel();
	        stack4.setOpaque(true);
	        stack4.setBackground(Color.green); //eventually change to image
	        stack4.setBorder(BorderFactory.createLineBorder(Color.black));
	        stack4.setBounds(340, 170, 100, 150);
	        board.add(stack4);

	        JLabel stack4Card2 = new JLabel();
		        stack4Card2.setOpaque(true);
		        stack4Card2.setBackground(Color.green); //eventually change to image
		        stack4Card2.setBorder(BorderFactory.createLineBorder(Color.black));
		        stack4Card2.setBounds(340, 210, 100, 150);
		        board.add(stack4Card2);
		        board.moveToFront(stack4Card2);
	        JLabel stack4Card3 = new JLabel();
		        stack4Card3.setOpaque(true);
		        stack4Card3.setBackground(Color.green); //eventually change to image
		        stack4Card3.setBorder(BorderFactory.createLineBorder(Color.black));
		        stack4Card3.setBounds(340, 250, 100, 150);
		        board.add(stack4Card3);
		        board.moveToFront(stack4Card3);
	        JLabel stack4Card4 = new JLabel();
	        	stack4Card4.setOpaque(true);
	        	stack4Card4.setBackground(Color.green); //eventually change to image
		        stack4Card4.setBorder(BorderFactory.createLineBorder(Color.black));
		        stack4Card4.setBounds(340, 290, 100, 150);
		        board.add(stack4Card4);
		        board.moveToFront(stack4Card4);
	        
        JLabel stack5 = new JLabel();
	        stack5.setOpaque(true);
	        stack5.setBackground(Color.cyan); //eventually change to image
	        stack5.setBorder(BorderFactory.createLineBorder(Color.black));
	        stack5.setBounds(450, 170, 100, 150);
	        board.add(stack5);

        JLabel stack6 = new JLabel();
	        stack6.setOpaque(true);
	        stack6.setBackground(Color.blue); //eventually change to image
	        stack6.setBorder(BorderFactory.createLineBorder(Color.black));
	        stack6.setBounds(560, 170, 100, 150);
	        board.add(stack6);

        JLabel stack7 = new JLabel();
	        stack7.setOpaque(true);
	        stack7.setBackground(Color.magenta); //eventually change to image
	        stack7.setBorder(BorderFactory.createLineBorder(Color.black));
	        stack7.setBounds(670, 170, 100, 150);
	        board.add(stack7);
	        
        JLabel clubs = new JLabel();
	        clubs.setOpaque(true);
	        clubs.setBackground(Color.white); //eventually change to image
	        clubs.setBorder(BorderFactory.createLineBorder(Color.black));
	        clubs.setBounds(340, 10, 100, 150);
	        board.add(clubs);

        JLabel diamonds = new JLabel();
		    diamonds.setOpaque(true);
		    diamonds.setBackground(Color.lightGray); //eventually change to image
		    diamonds.setBorder(BorderFactory.createLineBorder(Color.black));
		    diamonds.setBounds(450, 10, 100, 150);
	        board.add(diamonds);

	    JLabel hearts = new JLabel();
		    hearts.setOpaque(true);
		    hearts.setBackground(Color.gray); //eventually change to image
		    hearts.setBorder(BorderFactory.createLineBorder(Color.black));
		    hearts.setBounds(560, 10, 100, 150);
	        board.add(hearts);

        JLabel spades = new JLabel();
	        spades.setOpaque(true);
	        spades.setBackground(Color.darkGray); //eventually change to image
	        spades.setBorder(BorderFactory.createLineBorder(Color.black));
	        spades.setBounds(670, 10, 100, 150);
	        board.add(spades);
	        
	      	
		
		
		frame.add(board);
		frame.setSize(1013, 636);
		frame.setVisible(true);
	}

	public static void main(String args[]) {
		new Board();
	}

}
