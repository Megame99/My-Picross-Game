/*
File name: PicrossView.java  
Authors: Megan Machkouri 041003409
Course: CST8221 – JAP, Lab Section: 304 
Assignment: UI Programming
Date: 2022/03/20
Professor: Daniel Cormier
Purpose: This Program creates the UI of our Picross game. It contains all required game components
	along with a Controller class to manage user interaction. 
Class list: Controller Class
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
/**
 * Contains all UI elements of Program
 * 
 * @author Megan Machkouri
 * @version 1.1
 * @since Java 1.8_311
 */
public class PicrossView extends JWindow{
	
	/**
	 * Mark mode checkbox
	 */
	private JCheckBox mark = new JCheckBox("MARK");
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Duration of splash screen
	 */
	private final int duration;
	
	
	/**
	 * Timer Object
	 */
	private ControllableTimer time = new ControllableTimer(this);

	
	/**
	 * Handles calculations and decisions.
	 */
	private PicrossModel Model = new PicrossModel();
	
	/**
	 * Size of Game Board
	 */
	private int size = Model.getSize();
	
	
	/**
	 * Handles user interaction and events.
	 */
	private Controller buttonHandler = new Controller();
	
	
	/**
	 * Text Area where component interaction is recorded/outputted
	 */
	private TextArea actionLog;
	
	
	/**
	 * Hint Area Color
	 */
	private Color darkBlue = new Color(0, 0, 153);
	
	
	/**
	 * Console Log and Menu Button Color
	 */
	private Color lightBlue = new Color(164, 203, 255);
	/**
	 * Grid Button Color
	 */
	
	
	private Color gold = new Color(255, 201, 21);
	/**
	 * Game Board Panel
	 */
	
	
	public JPanel gameBoard;
	/**
	 * Menu Panel
	 */
	
	
	private JPanel topMenu;
	/**
	 * Side Panel
	 */
	
	
	private JPanel sidePanel;
	/**
	 * Hint Columns
	 */
	
	
	private JPanel hintZone = new JPanel();
	/**
	 * Game UI Frame
	 */
	
	
	private JFrame frame = new JFrame();
	/**
	 * Game Menu Bar
	 */
	
	
	private JMenuBar menuBar = new JMenuBar();
	/**
	 * Time Text Field
	 */
	
	
	private JTextField timeField = new JTextField(10);
	/**
	 * Score Text Field
	 */
	
	
	private JTextField scoreField = new JTextField(10);
	/**
	 * Game Board Squares
	 */
	
	
	private JButton[][] board = new JButton[Model.getSize()][Model.getSize()];
	/**
	 * Dialog Window
	 */
	
	
	private JWindow w;

	/**
	 * Default Constructor, launches game.
	 */
	public PicrossView() {
		this.duration = 7000;
		launchSplashScreen();
		createGUI();
		time.run();
	}

	/**
	 * Launches and Displays Splash Screen window for seven seconds.
	 * 
	 */
	public void launchSplashScreen() {
		//create content pane
		JPanel content = new JPanel(new BorderLayout());
		// or use the window content pane
		//  JPanel content = (JPanel)getContentPane();
		content.setBackground(Color.GRAY);

		// Set the window's bounds, position the window in the center of the screen
		int width =  534+10;
		int height = 263+10;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		//set the location and the size of the window
		setBounds(x,y,width,height);


		JLabel label = new JLabel(new ImageIcon("Picture1.jpg")); 
		label.setBackground(darkBlue);
		JLabel demo = new JLabel("Megan Machkouri's App Splash Window", JLabel.CENTER);
		demo.setBackground(darkBlue);
		demo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		demo.setForeground(darkBlue);
		content.add(label, BorderLayout.CENTER);
		content.add(demo, BorderLayout.SOUTH);
		// create custom RGB color
		Color customColor = new Color(44, 197, 211);
		content.setBorder(BorderFactory.createLineBorder(gold, 10));
		content.setBackground(gold);

		//replace the window content pane with the content JPanel
		setContentPane(content);

		//make the splash window visible
		setVisible(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		int duration = 7000;

		// Snooze for awhile, pretending the code is loading something awesome while
		// our splashscreen is entertaining the user.
		try {
			Thread.sleep(duration);

		}
		catch (InterruptedException e) {/*log an error here?*//*e.printStackTrace();*/}
		//destroy the window and release all resources
		setCursor(Cursor.getDefaultCursor());
		dispose(); 
		//You can hide the splash window. The resources will not be released.
		//setVisible(false);
	}

	/**
	 * Creates GUI by bundling other methods and calling each in correct error.
	 * 
	 */
	public void createGUI() {
		// Frame for our GUI application


		// Set proprieties and operations for the Frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frame will be divided into three distinct sections
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(1000, 1000));
		frame.setVisible(true);
		// Allow resize
		frame.setResizable(true);
		frame.setBackground(Color.LIGHT_GRAY);
		createMenu();
		frame.setJMenuBar(menuBar);

		// Call other class methods sequentially to build GUI
		setLayout();
		setBoard();
		setTopMenu();
		setSidePanel();

		// Add Main Panels to seperate areas
		frame.getContentPane().add(topMenu, BorderLayout.NORTH);
		frame.getContentPane().add(sidePanel, BorderLayout.WEST);
		frame.getContentPane().add(gameBoard); // Fill/Overflow Center 

		frame.pack();
	}

	
	/**
	 * Creates GUI menu containing information and useful functionalties.
	 * 
	 */
	public void createMenu() {
		
		
		/* Create Game Menu Options */
		JMenu gameMenu = new JMenu("Game");
		JMenu helpMenu = new JMenu("Help"); 
		JMenu debugMenu = new JMenu("Debug");
		
		/* Add image icons */
		ImageIcon newOption = new ImageIcon("piciconnew.gif");
		ImageIcon exitOption = new ImageIcon("piciconext.gif");
		
		/* Config New Button */
		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		newMenuItem.addActionListener(buttonHandler.newGame);
		newMenuItem.setActionCommand("New Game Generated... Good Luck!");
		newMenuItem.addActionListener(buttonHandler);
		newMenuItem.setIcon(newOption);	   
		


		/* Config Scenario Items (add seperators + listeners) */
		JMenuItem Scenario = new JMenuItem("Scenario1");
		Scenario.addActionListener(buttonHandler.Scenario1);
		Scenario.setActionCommand("Scenario 1 Generated... Good Luck!");
		Scenario.addActionListener(buttonHandler);
		JMenuItem Scenario1 = new JMenuItem("Scenario2");
		Scenario1.addActionListener(buttonHandler.Scenario2);
		Scenario1.setActionCommand("Scenario 2 Generated... Good Luck!");
		Scenario1.addActionListener(buttonHandler);
		JMenuItem Scenario2 = new JMenuItem("Scenario3");
		Scenario2.addActionListener(buttonHandler.Scenario3);
		Scenario2.setActionCommand("Scenario 3 Generated... Good Luck!");
		Scenario2.addActionListener(buttonHandler);
		JMenuItem Scenario3 = new JMenuItem("Scenario4");
		Scenario3.addActionListener(buttonHandler.Scenario4);
		Scenario3.setActionCommand("Scenario 4 Generated... Good Luck!");
		Scenario3.addActionListener(buttonHandler);
		
		
		/* Create Debug SubMenu */
		
		debugMenu.addSeparator();
		debugMenu.add(Scenario);
		debugMenu.addSeparator();
		debugMenu.add(Scenario1);
		debugMenu.addSeparator();
		debugMenu.add(Scenario2);
		debugMenu.addSeparator();
		debugMenu.add(Scenario3);
		
	
		
	

		
		/* Config Exit Button */
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(buttonHandler.exit);
		exitMenuItem.setIcon(exitOption);
		
		/* Create Help Menu Options */
		JMenuItem solutionMenuItem = new JMenuItem("Solution");
		solutionMenuItem.addActionListener(buttonHandler.displaySolution);
		solutionMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(buttonHandler.aboutDialog);
		
		
		
		/* Add Game Menu Options */
		gameMenu.add(newMenuItem);
		gameMenu.add(debugMenu);
		gameMenu.add(exitMenuItem);
		
		/* Add Help Menu Options */
		helpMenu.add(solutionMenuItem);
		helpMenu.add(aboutMenuItem);
		/* Add Menu's to Bar */
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(gameMenu);
		menuBar.add(helpMenu);
		menuBar.setOpaque(true);
	

	}



	/**
	 * Sets and initializes the three primary areas of our Picross Game Board.
	 */
	public void setLayout() {
		// Main areas of Picross Game
		gameBoard = new JPanel();
		topMenu = new JPanel();
		sidePanel = new JPanel();

		// Set Background Color
		topMenu.setBackground(Color.LIGHT_GRAY);
		sidePanel.setBackground(Color.LIGHT_GRAY);
		gameBoard.setBackground(Color.LIGHT_GRAY);

		// Set Dimensions
		gameBoard.setPreferredSize(new Dimension(700, 700));
		topMenu.setPreferredSize(new Dimension(250, 300));
		sidePanel.setPreferredSize(new Dimension(300, 200));
	}

	
	/**
	 * Sets and initializes the components of the top menu panel of our Picross Game.
	 * 
	 * @param none
	 */
	public void setTopMenu() {
		// Panel that contains both menu and hint panel (divided in two sections)
		JPanel upperPane = new JPanel();
		// Panel for Hint Columns

		// Panel for menu components
		JPanel menuPanel = new JPanel();
		// Reset Button
		JButton reset = new JButton();
		// Mark Panel 
		JPanel markBox = new JPanel();
		// Mark Checkbox
		JCheckBox check = new JCheckBox("MARK");
		// Panel for score and time info
		JPanel infoPanel = new JPanel();

		// Create Layout so objects are added to the left
		FlowLayout flow = new FlowLayout(FlowLayout.TRAILING, 5, 5);

		// Divide topMenu (North/South)
		topMenu.setLayout(new BorderLayout());

		// Customize panel proprieties
		upperPane.setBackground(Color.LIGHT_GRAY);
		upperPane.setLayout(new BorderLayout());

		// Customize/Initialize Mark checkbox proprieties and operations
		check.setBackground(lightBlue);
		// Record Action upon user click and describe event
		check.setActionCommand("You pressed Mark");

		check.addActionListener(buttonHandler.mark);
		// Custom Dimension
		markBox.setPreferredSize(new Dimension(120, 50));
		// Add padding 
		markBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5, false));
		markBox.setBackground(lightBlue);
		markBox.add(check);

		// Add mark component to panel
		menuPanel.add(markBox);
		// Customize panel proprieties
		menuPanel.setOpaque(false);

		// Initialize/Customize info panel
		infoPanel.setPreferredSize(new Dimension(390, 50));
		// Add padding
		infoPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5, false));
		infoPanel.setBackground(lightBlue);

		// Create Text String to label time field
		final String timeString = "Time Elapsed";
		// Field that displays time
		timeField = new JTextField(10);
		// Record Action upon user click and describe event
		timeField.setActionCommand(timeString);
		timeField.setBackground(darkBlue);
		timeField.setText("00:00");
		timeField.setForeground(gold);
		// Enclose field and descriptor within label 
		JLabel timeFieldLabel = new JLabel(timeString + ": ");
		// Add time textField/label to info panel
		infoPanel.add(timeFieldLabel);
		infoPanel.add(timeField);

		// Create Text String to label score field
		final String scoreString = "Score";
		// Field that displays score

		// Record Action upon user click and describe event
		scoreField.setActionCommand(scoreString);
		scoreField.setBackground(darkBlue);
		scoreField.setText("0");

//		scoreField.addActionListener(buttonHandler.updateScore);

		scoreField.setForeground(gold);
		// Enclose field and string descriptor within label
		JLabel scoreFieldLabel = new JLabel(scoreString + ": ");
		// Add score textField/label to info panel
		infoPanel.add(scoreFieldLabel);
		infoPanel.add(scoreField);
		// Add info component to panel
		menuPanel.add(infoPanel);

		//Customize reset button
		reset.setPreferredSize(new Dimension(120, 50));
		reset.setText("RESET");
		// Add padding
		reset.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5, false));
		reset.setBackground(lightBlue);
		// Record Action upon user click and describe event
		reset.setActionCommand("Game Reset!");
		reset.addActionListener(buttonHandler.reset);
		reset.addActionListener(buttonHandler);
		// Add reset button to panel
		menuPanel.add(reset);

		// Initialize Column Hint Zones
		setHintZone();
		// Iterate Grid Columns


		// Panel for menu buttons/components added to TOP
		upperPane.add(menuPanel, BorderLayout.NORTH);
		// Panel for menu buttons/components added to BOTTOM
		upperPane.add(hintZone, BorderLayout.SOUTH);

		// Logo/Game Log Area Panel
		JPanel console = new JPanel();
		// Panel for user input
		JPanel inputBox = new JPanel();
		console.setBackground(darkBlue);
		console.setLayout(new BorderLayout());

		// Create Game Log Area for displaying messages and recording game actions
		JPanel gameLog = new JPanel();

		// Intialize/Customize text input box where users may type messages
		JTextField userInput = new JTextField(14);
		userInput.setBackground(lightBlue);
		userInput.setText("Start Typing..."); // Prompt
		userInput.setEditable(true); // Make Editable for user
		userInput.setForeground(Color.BLACK);
		userInput.addActionListener(buttonHandler);
		// Send Button that displays text in field to console log upon user click
		JButton send = new JButton("Send");
		// Record Action upon user click and describe event 
		send.setActionCommand(userInput.getText());
		send.addActionListener(buttonHandler);
		send.setBackground(gold);
		// Customize area surronding input field
		inputBox.setPreferredSize(new Dimension(250, 50));
		inputBox.setBackground(darkBlue);
		// Add components
		inputBox.add(userInput);
		inputBox.add(send);

		// Create Text Area for displaying messages and user actions(clicking buttons)
		actionLog = new TextArea("I'm listening... \n", 8,5);
		actionLog.setBackground(lightBlue);
		actionLog.setForeground(Color.black);
		
		// Add text box and text field to game log panel (top/bottom) 
		gameLog.setLayout(new BorderLayout());
		gameLog.add(actionLog, BorderLayout.NORTH);
		gameLog.add(inputBox, BorderLayout.SOUTH);

		// Create Logo Area to display Picross 
		JPanel topLogo = new JPanel();
		topLogo.setBackground(darkBlue);
		// Use picross logo image
		ImageIcon picrossImage = new ImageIcon("Picture1.jpg");
		// Customize size and appearance 
		JLabel logo = new JLabel();
		logo.setIcon(picrossImage);
		logo.setOpaque(true);
		logo.setPreferredSize(new Dimension(200, 50));
		logo.setBorder(BorderFactory.createLineBorder(gold, 5, false));
		logo.setBackground(Color.black);
		// Center logo
		topLogo.setLayout(new FlowLayout(FlowLayout.LEADING, 35, 10));
		// Add to panel
		topLogo.add(logo);
		// Define border to pad surronding area 
		console.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 17, false));
		// Add Logo at top and gamelog to bottom 
		console.add(topLogo, BorderLayout.NORTH);
		console.add(gameLog, BorderLayout.SOUTH);

		// Add Console Area and menu options to Top menu Panel (Left and Right)
		topMenu.add(upperPane); // Add left
		topMenu.add(console, BorderLayout.WEST); // Add right
	}

	
	/**
	 * Sets and initializes the components of our Picross Board Panel.
	 * 
	 * @param none
	 */
	public void setBoard() {
		int cordsSize = Model.getArraySize();
		// Set Layout of Board to Grid(equal to size of game)
		gameBoard.setLayout(new GridLayout(size, size));
		// Create 2D array of Grid buttons (equal to size of game)
		board = new JButton[size][size];
		// Iterate through Grid
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				// Create button and set custom proprieties and operations
				board[y][x] = new JButton();
				board[y][x].setBackground(gold);
				// add padding
				board[y][x].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10, false));
				board[y][x].addActionListener(buttonHandler.incorrectSquare);
				board[y][x].setActionCommand("Square at Index, " + x + "," + y + " clicked.");
				board[y][x].addActionListener(buttonHandler);
				// Record Action upon user click and describe event
				for (int i = 0; i < cordsSize; i++) {
					if(Model.isCorrectSquare(i, x, y)) {
						board[y][x].removeActionListener(buttonHandler.incorrectSquare);
						board[y][x].addActionListener(buttonHandler.correctSquare);
					}
				}

				// Add button to game board
				gameBoard.add(board[y][x]);
			}
		}
	}
	

	/**
	 * Sets the columns hint zones according to game size.
	 * 
	 */
	public void setHintZone() {
		hintZone.removeAll();
		hintZone.setLayout(new GridLayout(1, 0));
		for (int y = 0; y < size; y++) {
			// Create Hints 

			// Initialize/Customize Hint Zones
			JPanel hintCol = new JPanel();
			hintCol.setPreferredSize(new Dimension(140, 250));
			// Add Padding around hint cols 
			hintCol.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 17, false));
			hintCol.setBackground(darkBlue);
			hintCol.setOpaque(true);
			hintCol.setForeground(gold);
			//			Model.calculateRowHints(y+1);
			String text = Model.getHintRowText(y+1);
			// Add to Side Panel
			JTextArea hintText = new JTextArea();
			hintText.setBackground(darkBlue);
			hintText.setForeground(gold);
			hintText.setText(text);
			hintCol.add(hintText);

			// Add to panel
			hintZone.add(hintCol);


		}
	}

	
	/**
	 * Sets and intializes components contained within the side panel of our Picross
	 * Game.
	 * 
	 */
	public void setSidePanel() {
		sidePanel.removeAll();
		// Initialize One D Grid with single Column for Hint Zones
		sidePanel.setLayout(new GridLayout(0, 1));
		// Iterate Hint Cols
		for (int y = 0; y < size; y++) {
			// Create 3 Hint Values for each Zone

			// Make Hints more Visible
			//			hintText.setFont(new Font(" 1  2  3", Font.BOLD,50));
			// Create and Customize Hint Panel 
			JPanel hintRow = new JPanel();
			hintRow.setPreferredSize(new Dimension(140, 250));
			// Add padding
			hintRow.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 17, false));
			hintRow.setBackground(darkBlue);
			hintRow.setOpaque(true);
			hintRow.setForeground(gold);
			// Add Hints to Panel
			String text = Model.getHintColText(y+1);
			// Add to Side Panel
			JTextArea hintText = new JTextArea();
			hintText.setBackground(darkBlue);
			hintText.setForeground(gold);
			hintText.setText(text);
			hintRow.add(hintText);

			sidePanel.add(hintRow);
		}

	}


	/**
	 * Resets and initializes the components of our Picross Board Panel. Without modifying game.
	 * 
	 */
	public void resetBoard() {
		// Iterate Board
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				// Reset all Buttons
				board[x][y].setIcon(null);
				board[x][y].setBackground(gold);
				board[x][y].setEnabled(true);
			}
		}
		
	}
	
	
	/**
	 * Sets score text field to timer value.
	 * 
	 */
	public void setTime(int time) {
		if(Model.isStartTimer() == true) {
			// edit text time field
			timeField.setText(Integer.toString(time));
		}

	}
	

	/**
	 * Increments Score by 1 and updates Score Field Text to Reflect those Changes  
	 **/
	public void updateScoreField() {
		scoreField.setText(Integer.toString(Model.getScore()));

	}
	
	
	/**
	 * Resets score text field to 0.
	 * 
	 */
	public void resetScoreField() {
		
		// Update UI element
		scoreField.setText(Integer.toString(Model.getScore()));
	}
	
	/**
	 * Closes Game OVer Window
	 */
	public void closeWindow() {
		w.dispose();
	}
	

	/**
	 * Displays Game Info Dialog.
	 */
	public void displayAboutDialog() {
		// Create Info Dialog
		JOptionPane.showMessageDialog(frame,
				"A simple and ugly game of Picross \n" +
						"By: Megan Machkouri \n"
						+ "\nWinter Term 2022 \n");
	}
	

	/**
	 * Displays Game Over window.
	 */
	@SuppressWarnings("deprecation")
	public void perfectGameOver() {

		w = new JWindow();

		// set background of window transparent
		w.setBackground(darkBlue);
		w.setVisible(true);


		// create a new button
		JButton b = new JButton("OK");

		// add action listener
		b.addActionListener(buttonHandler.closeWindow);
		BufferedImage bufferedImage = null;
		
		// Check if user made mistakes
		if (Model.isPerfectGame() == false) {
			try {
				bufferedImage = ImageIO.read(new File("gamepicend.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else {
			try {
				bufferedImage = ImageIO.read(new File("gamepicwinner.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Scale Image 
		Image image = bufferedImage.getScaledInstance(600, 400, Image.SCALE_DEFAULT);

		ImageIcon picrossImage = new ImageIcon(image);
		// Customize size and appearance 
		JLabel logo = new JLabel();
		logo.setIcon(picrossImage);
		logo.setOpaque(true);
		logo.setPreferredSize(new Dimension(600,350));

		// Add Image and button to Panel with proper layout
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(logo,BorderLayout.NORTH);
		p.add(b,BorderLayout.SOUTH);

		// Add to Window
		w.add(p);

		// Configure Window Size and Position on Screen
		int width =  554+10;
		int height = 263+10;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int z = (screen.width-width)/2;
		int h = (screen.height-height)/2;
		//set the location and the size of the window
		w.setBounds(z,h,width,height);
		w.setSize(new Dimension(600,400));
		w.pack();
		w.show();
		// Disable Buttons upon gaeme termination
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {			
				board[x][y].setEnabled(false);	
			}
		}

	}

	
	/**
	 * Called when user selects blank square in mark mode, alters elements of button.
	 * @param Action Event e 
	 */
	public void markCorrectSquare(ActionEvent e) {
		// Get button that user clicked
		JButton button = (JButton) e.getSource();
		// Alter appearance of UI and disable button
		button.setBackground(Color.LIGHT_GRAY);
		button.setEnabled(true);
	}
	
	
	/**
	 * Called when user selects full square in mark mode, alters elements of button.
	 * @param Action Event e 
	 */
	public void markIncorrectSquare(ActionEvent e) {
		// Get button that user clicked
		JButton button = (JButton) e.getSource();
		// Alter appearance of UI and disable button
		ImageIcon error = new ImageIcon("xmark.png");
		button.setIcon(error);
		button.setBackground(darkBlue);
		button.setOpaque(true);
		button.setEnabled(false);

	}
	
	/**
	 * Called when user selects full square in normal mode, alters elements of button.
	 * @param Action Event e 
	 */
	public void correctSquare(ActionEvent e) {
		// Get button that user clicked
		JButton button = (JButton) e.getSource();
		// Alter appearance of UI and disable button
		button.setBackground(darkBlue);
		button.setEnabled(false);

	}
	
	
	/**
	 * Called when user selects empty square in normal mode, alters elements of button.
	 * @param Action Event e 
	 */
	public void incorrectSquare(ActionEvent e) {
		// Get button that user clicked
		JButton button = (JButton) e.getSource();
		// Alter appearance of UI and disable button
		ImageIcon error = new ImageIcon("xmark.png");
		button.setIcon(error);
		button.setBackground(Color.RED);
		button.setOpaque(true);
		button.setEnabled(false);
		// Set perfect game to false 
		
	}

	/**
	 * Clears Game Board Components and Resets
	 */
	public void clearBoard() {
		// Clean Board Area
		gameBoard.removeAll();
		gameBoard.revalidate();
		gameBoard.repaint();
	}
	
	
	/**
	 * Changes game board square color to light blue
	 * @param x index, y index
	 */
	public void displaySolutionSquare(int y, int x) {
		// make visible to user
		if(!(board[y][x].getBackground() == darkBlue)) { // check if already guessed
			board[y][x].setBackground(lightBlue);
		}
		
	}
	
	/**
	 * Adds an ActionListener to gameboard square
	 * @param x index, y index, specific listener
	 */
	public void addActionListener(int y, int x, ActionListener control) {
		// add action listener specified
		board[y][x].addActionListener(control);
	}
	
	
	/**
	 * Removes an ActionListener from gameboard square
	 * @param x index, y index, specific listener
	 */
	public void removeActionListener(int y, int x, ActionListener control) {
		// remove action listener specified
		board[y][x].removeActionListener(control);
	}
	

	/**
	 * This is an inner class intended to manage all Picross button events. Sort of like 
	 * a traffic manager.
	 * 
	 * @author Megan Machkouri
	 * @version 1.1
	 * @since Java 1.8_311
	 * @see java.awt.event.ActionEvent, java.awt.event.ActionListener
	 */
	private class Controller implements ActionListener {


		/**
		 * This method is called when any UI component's "action" is triggered by user interaction.
		 * 
		 * @param e The generated "event" object. It contains info about what was
		 *          clicked.
		 */
		
		/**
		 * Reallocates actionlisteners according to mark mode
		 * @param ActionEvent e
		 */
		public void markMode(ActionEvent e) {
			// Get button that user clicked
			JCheckBox check = (JCheckBox) e.getSource();
			// Get number of playable squares
			int cordsSize = Model.getArraySize();
			// if mark mode enabled
			if(check.isSelected()) {
				for (int y = 0; y < size; y++) {
					for (int x = 0; x < size; x++) {
						// Iterate array removing existing listeners and adding new ones
						removeActionListener(y, x, buttonHandler.incorrectSquare);
						for (int i = 0; i < cordsSize; i++) {	
							if(Model.isCorrectSquare(i, x, y)) { // Check if full square
								removeActionListener(y, x, buttonHandler.correctSquare);

							}

						}
					}
				}

				for (int y = 0; y < size; y++) {
					for (int x = 0; x < size; x++) {
						// Iterate array removing existing listeners and adding new ones
						addActionListener(y, x, buttonHandler.markCorrectSquare);
						// Record Action upon user click and describe event
						for (int i = 0; i < cordsSize; i++) {	
							if(Model.isCorrectSquare(i, x, y)) { // Check if full square
								removeActionListener(y, x, buttonHandler.markCorrectSquare);
								addActionListener(y, x, buttonHandler.markIncorrectSquare);
								
							}

						}
					}
				}
			}
			else {
				for (int y = 0; y < size; y++) {
					for (int x = 0; x < size; x++) {
						// Iterate array removing existing listeners 
						removeActionListener(y, x, buttonHandler.markCorrectSquare);
						// Record Action upon user click and describe event
						for (int i = 0; i < cordsSize; i++) {

							if(Model.isCorrectSquare(i, x, y)) { // Check if full square
								removeActionListener(y, x, buttonHandler.markIncorrectSquare);
							}

						}
					}
				}

				for (int y = 0; y < size; y++) {
					for (int x = 0; x < size; x++) {
						// Add normal listeners
						addActionListener(y, x, buttonHandler.incorrectSquare);
						// Record Action upon user click and describe event
						for (int i = 0; i < cordsSize; i++) {	

							if(Model.isCorrectSquare(i, x, y)) { // Check if full square
								removeActionListener(y, x, buttonHandler.incorrectSquare);
								addActionListener(y, x, buttonHandler.correctSquare);
							}
						}
					}
				}

			}
		}

		
		/**
		 * Update View if Game is Over.
		 */
		public void endGame() {
			// Check model method
			if(Model.shoudlEndGame()) {
				// if true update ui
				perfectGameOver();
				time.setStatus(ControllableTimer.STOP);
			}
		}
		
		/**
		 * Resets and initializes the components of our Picross Game. Allows for new game creation. 
		 */
		public void resetAll() {
			// Reset all UI elements and game characteristcs
			Model.resetScore();
			Model.resetSquares();
			resetScoreField();
			clearBoard();
			setSidePanel();
			setHintZone();
			setBoard();
			// Reset/Restart timer
			time.setStatus(ControllableTimer.RESET);
			time.setStatus(ControllableTimer.START);
			Model.setStartTimer(true);
		}
		
		/**
		 * Updates Board UI to display game solution to user.
		 */
		public void displaySolution() {
			int cordsSize = Model.getArraySize();
			// Create 2D array of Grid buttons (equal to size of game)
			// Iterate through Grid
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					// Create button and set custom proprieties and operations
					// Record Action upon user click and describe event
					for (int i = 0; i < cordsSize; i++) {	
						if(Model.isCorrectSquare(i, x, y)) { // check if index matches game cords
							// make visible to user
							displaySolutionSquare(y, x);
						}
					}
				}
			}
		}

		/**
		 * Exit Program.
		 */
		ActionListener exit = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		/**
		 * Display info dialog
		 */
		ActionListener aboutDialog = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayAboutDialog();
			}
		};
		/**
		 * New game 
		 */
		ActionListener newGame = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Model.setRandomGame();
				resetAll();
				
			}
		};
		
		
		/**
		 * Reset Game
		 */
		ActionListener reset = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetBoard();
				// Reset Timer Status
				time.setStatus(ControllableTimer.RESET);
				time.setStatus(ControllableTimer.START);
				// Reset Score and Square Field
				Model.resetSquares();
				Model.resetScore();
				Model.setPerfectGame(true);
				resetScoreField();
			}
		};
		
		
		/**
		 * Mark mode toggle
		 */
		ActionListener mark = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				markMode(e);
			}
		};
		
		
		/**
		 * Mark correct blank square
		 */
		ActionListener markCorrectSquare = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				markCorrectSquare(e);
			}
		};
		
		/**
		 * Mark incorrect full square
		 */
		ActionListener markIncorrectSquare = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				markIncorrectSquare(e);
				Model.setPerfectGame(false); // User made a mistake
				// Increment squares played
				Model.updateSquaresPlayed();
				// Check if all squares have been played
				endGame();
			}
		};
		
		
		/**
		 * Close dialog window
		 */
		ActionListener closeWindow = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		};
		
		
		/**
		 * Display game solution 
		 */
		ActionListener displaySolution = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displaySolution();
			}
		}; 
 
		/**
		 * Correct Square
		 */
		ActionListener correctSquare = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				correctSquare(e);
				// Increment score and squares played
				Model.updateScore();
				Model.updateSquaresPlayed();
				updateScoreField();
				// Check if all squares have been played
				endGame();
			}
		};
		
		/**
		 * Incorrect square
		 */
		ActionListener incorrectSquare = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				incorrectSquare(e);
				Model.setPerfectGame(false);
			}
		};
		
		/**
		 * Scenario 1
		 */
		ActionListener Scenario1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Model.setScenario1();
				resetAll();
			}
		};
		
		
		/**
		 * Scenario 2
		 */
		ActionListener Scenario2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Model.setScenario2();
				resetAll();
			}
		};
		
		
		/**
		 * Scenario 3
		 */
		ActionListener Scenario3 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Model.setScenario3();
				resetAll();
			}
		};
		
		
		/**
		 * Scenario 4
		 */
		ActionListener Scenario4 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Model.setScenario4();
				resetAll();
			}
		};
		
		
		
		/**
		 * Append to Game Log Console
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Display action command to console log when user interacts with interactable component
			actionLog.append(e.getActionCommand() + "\n");

		}





	}
	


}
