package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Game.Board.Message;
import Game.Board.MessageType;
import Game.Board.Winner;
import Launcher.Main;

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
	
	static class Restart implements ActionListener{

		GUI gui;
		JFrame frame;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(frame != null)
				frame.dispose();
			Main.main( new String[]{});
		}
		
		public void setGui(GUI gui, JFrame frame){
			this.gui = gui;
			this.frame = frame;
		}
		
	}
	
	public static NormalIcons defaultIcons = new NormalIcons();
	
	public static ActionListener defaultAl = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
	}};
	
	public static Restart defaultRestart = new Restart();
	
	Restart restart;
	Observer observer;
	BoardGUI board;
	
	JFrame frame;
	
	JLabel winnerName;
	JPanel winnerPanel;
	
	JPanel gamePanel;
	JComponent sideBoard;
	JPanel topPanel;

	public GUI(){
		this(new Icons(), defaultAl, defaultRestart);
	}
	
	public GUI(Icons ic, ActionListener al){
		this(ic, al, defaultRestart);
	}
	
	public GUI(Icons icons, ActionListener al, Restart restart){
		this.restart = restart;
		gamePanel = new JPanel();
		BoxLayout gamePanelLayout = new BoxLayout(gamePanel, BoxLayout.Y_AXIS);
		gamePanel.setLayout( gamePanelLayout );
		board = new BoardGUI(icons, al);
		observer = board;
		
		winnerPanel = getWinnerPanel(restart);
		gamePanel.add( board.getPanel() );
		gamePanel.add( winnerPanel );

		frame = new JFrame();
		restart.setGui( this, frame );
		topPanel = new JPanel();
		topPanel.setLayout( new BoxLayout(topPanel, BoxLayout.X_AXIS) );
		topPanel.add(gamePanel);
		addSideBoard(sideBoard);
		frame.add(topPanel);
		
		
		//frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
			//frame.pack();
		}
	}
	
	private JPanel getWinnerPanel(ActionListener restart){
		JLabel winnerText = new JLabel("The winner is ");
		winnerName = new JLabel("");
		Font font = new Font("Courier", Font.BOLD,30);

		JPanel textPane = new JPanel();

		winnerPanel = new JPanel();
		BoxLayout layout = new BoxLayout(winnerPanel, BoxLayout.X_AXIS);
		BoxLayout textLayout = new BoxLayout(textPane, BoxLayout.X_AXIS);
		winnerPanel.setLayout( layout );
		textPane.setLayout( textLayout );

		winnerText.setOpaque(false);
		winnerText.setFont( font );
		
		winnerName.setOpaque(false);
		winnerName.setFont( font );
		
		
		JButton restartButton = new JButton("Restart Game");
		restartButton.addActionListener( restart );
		
		textPane.add( winnerText);
		textPane.add( winnerName);
		winnerPanel.add(textPane);
		winnerPanel.add(Box.createRigidArea( new Dimension(20, 0) ));
		winnerPanel.add(restartButton);
		
		winnerPanel.setVisible( false );
		return winnerPanel;
	}
	
	public void addSideBoard(JComponent panel){
		if(sideBoard != null){
			topPanel.remove( sideBoard );
		}
		
		sideBoard = panel;
		if(sideBoard != null)
			topPanel.add( sideBoard );
	}


	public void pack(){
		//frame.pack();
	}
	
	public void dispose(){
		frame.dispose();
	}
}
