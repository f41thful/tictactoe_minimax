package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.WindowConstants;

import Game.Board.Message;
import Game.Board.MessageType;
import Game.Board.SquareState;
import Game.Board.Winner;

public class BoardGUI implements Observer{
	
	public static class Icons{
		public ImageIcon getNotch(){return null;};
		public ImageIcon getCross(){return null;};
		public ImageIcon getEmpty(){return null;};
	}
	
	static final int SIZE = 3;
	JButton[][] buttons;
	GridLayout layout;
	JPanel gamePanel;
	JFrame frame;
	Icons icons;
	
	JLabel winnerName;
	JPanel winnerPanel;
	

	static ActionListener defaultAl = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}};

	public BoardGUI(){
		this(new Icons(), defaultAl, defaultAl);
	}
	
	public BoardGUI(Icons icons, ActionListener al, ActionListener restart){
		this.icons = icons;
		
		
		gamePanel = getGamePanel( al );
		winnerPanel = getGameOverPanel(restart);
		

		frame = new JFrame();
		JPanel topPane = new JPanel();
		topPane.setLayout( new BoxLayout(topPane, BoxLayout.Y_AXIS) );
		topPane.add(gamePanel);
		topPane.add(winnerPanel);
		frame.add(topPane);
		
		
		frame.pack();
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
	}
	
	private JPanel getGamePanel(ActionListener al){
		layout = new GridLayout( SIZE, SIZE );
		gamePanel = new JPanel();
		gamePanel.setLayout( layout );
		buttons = new JButton[SIZE][SIZE];

		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				buttons[i][j] = getButton();
				buttons[i][j].addActionListener( al );
				buttons[i][j].putClientProperty( "row", i);
				buttons[i][j].putClientProperty( "col", j);
				gamePanel.add( buttons[i][j] );
			}
		}
		
		gamePanel.setPreferredSize( new Dimension(500, 500));
		return gamePanel;
	}
	
	private JPanel getGameOverPanel(ActionListener restart){
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
	
	private JButton getButton(){
		JButton button = new JButton();
		button.setBackground( Color.WHITE );
		button.setIcon( icons.getEmpty() );
		return button;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println(arg1);
		Message m = (Message) arg1;
		if(m.type == MessageType.SET)
		{
			JButton b = buttons[m.row][m.col];
			if(m.value == SquareState.CROSS) b.setIcon( icons.getCross() );
			else if(m.value == SquareState.NOTCH) b.setIcon( icons.getNotch() );
			b.setEnabled(false);
		}else if(m.type == MessageType.WINNER && m.value != Winner.NONE){
			winnerName.setText(((Winner)m.value).toString());
			winnerPanel.setVisible(true);
			frame.pack();
		}
	}
}
