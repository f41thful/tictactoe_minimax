package GUI;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Game.Board.Message;
import Game.Board.MessageType;
import Game.Board.Winner;

public class GUI implements Observer{
	
	public static class Icons{
		public ImageIcon getNotch(){return null;};
		public ImageIcon getCross(){return null;};
		public ImageIcon getEmpty(){return null;};
	}
	
	public static class NormalIcons extends Icons{
		public NormalIcons(){}
		
		@Override
		public ImageIcon getNotch() {
			return new ImageIcon("icons/notch.png");
		}

		@Override
		public ImageIcon getCross() {
			return new ImageIcon("icons/cross.png");
		}
		
	}
	
	public static NormalIcons defaultIcons = new NormalIcons();
	
	public static ActionListener defaultAl = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
	}};
	
	ActionListener restart;
	Observer observer;
	BoardGUI board;
	
	JFrame frame;
	
	JLabel winnerName;
	JPanel winnerPanel;

	public GUI(){
		this(new Icons(), defaultAl, defaultAl);
	}
	
	public GUI(Icons icons, ActionListener al, ActionListener restart){
		this.restart = restart;
		board = new BoardGUI(icons, al);
		observer = board;
		
		winnerPanel = getWinnerPanel(restart);
		

		frame = new JFrame();
		JPanel topPane = new JPanel();
		topPane.setLayout( new BoxLayout(topPane, BoxLayout.Y_AXIS) );
		topPane.add(board.getPanel());
		topPane.add(winnerPanel);
		frame.add(topPane);
		
		
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		observer.update( arg0, arg1 );
		Message m = (Message) arg1;
		if(m.type == MessageType.WINNER && m.value != Winner.NONE){
			winnerName.setText(((Winner)m.value).toString());
			winnerPanel.setVisible(true);
			frame.pack();
		}
	}
	
	private JPanel getWinnerPanel(ActionListener restart){
		JLabel winnerText = new JLabel("The winner is ");
		winnerName = new JLabel("");
		Font font = new Font("Courier", Font.BOLD,30);

		JPanel textPane = new JPanel();

		winnerPanel = new JPanel();
		BoxLayout layout = new BoxLayout(winnerPanel, BoxLayout.Y_AXIS);
		winnerPanel.setLayout( layout );

		winnerText.setOpaque(false);
		winnerText.setFont( font );
		
		winnerName.setOpaque(false);
		winnerName.setFont( font );
		
		
		JButton restartButton = new JButton("Restart Game");
		restartButton.addActionListener( restart );
		
		textPane.add( winnerText);
		textPane.add( winnerName);
		winnerPanel.add(textPane);
		winnerPanel.add(restartButton);
		
		winnerPanel.setVisible( false );
		return winnerPanel;
	}
	
	


	
}
