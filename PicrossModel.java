/*
File name: PicrossModel.java  
Authors: Megan Machkouri 041003409
Course: CST8221 – JAP, Lab Section: 304 
Assignment: UI Programming
Date: 2022/03/20
Professor: Daniel Cormier
Purpose: This Program is the brain of our program, it makes calculations and does all the decision making.
		This Class does not modify any UI components. Contains private class that represents a picross
		gane coordinate.
Class list: Coordinate
 */

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Brain of program, handles calculations and most decisions.
 * 
 * @author Megan Machkouri
 * @version 1.1
 * @since Java 1.8_311
 */
public class PicrossModel {
	/**
	 * Array for Picross Game Coordinate Locations
	 */
	private ArrayList<Coordinate> cords = new ArrayList<Coordinate>();
	/**
	 * String for Row Hint Values
	 */
	private String hintRowText;
	/**
	 * String for Col Hint Values
	 */
	private String hintColText;
	/**
	 * Game Size
	 */
	private final int size = 5;
	/**
	 * Used to determine if user has made a mistake
	 */
	
	private boolean perfectGame = true;
	/**
	 * Game Score
	 */
	private int score = 0;
	/**
	 * Total Number of Correct Squares Played
	 */
	private int squaresPlayed = 0;
	
	/**
	 * Timer control Variable
	 */
	private boolean startTimer = false;



	/**
	 * Default Constructor
	 */
	public PicrossModel() {
		//DO ABSOLUTELY NOTHING

	}
	
	
	/**
	 * This is an inner class intended to represent a coordinate for a game square.
	 * 
	 * @author Megan Machkouri
	 * @version 1.1
	 * @since Java 1.8_311
	 */
	private class Coordinate{
		public int x; // X cord
		public int y; // Y cord

		public Coordinate( int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	
	/**
	 * Calculates column hints for provided index
	 * 
	 * @param board y index
	 */
	public void calculateColHints(int index) {
		// Set empty string
		hintColText = " ";
		// Check if cords array exists
		if(cords.size() > 0) {
			// Initialize cords array
			int[][] cordIndex = new int[cords.size()][2];
			// Initialize column count array
			int[][] colCount = new int[cords.size()+1][2];
			
			// Populate array
			for(int i=0; i < cords.size(); i++) {
				cordIndex[i][1] = cords.get(i).y;
				cordIndex[i][0] = cords.get(i).x;

			}
			// Populate array
			for(int i=0; i < cords.size(); i++) {
				colCount[i][0] = i+1;
				colCount[i][1] = 0;
			}

			for(int i=0; i < cords.size(); i++) {
				for(int b=0; b <= 5; b++) {
					// Count number of squares per column
					if(cordIndex[i][1] == colCount[b][0]) {
						int y = cordIndex[i][1];
						colCount[y-1][1]++;
					}
				}
			}		
			// Initialize array
			ArrayList<Integer> colNumber = new ArrayList<Integer>();
			colNumber.ensureCapacity(10);
			// If more than one full button at col
			if(colCount[index-1][1] > 1) {
				int q = 1;
				for(int x=0; x < cords.size(); x++) {
					if(cords.get(x).y == colCount[index-1][0]) {
						colNumber.add(cords.get(x).x); // Add cords of each button at col
					}
				}
				// Remove duplicates and sort in ascending order
				colNumber.sort(null);
				Set<Integer> colNumberSet = new LinkedHashSet<Integer>(colNumber);
				ArrayList<Integer> colNumber2 = new ArrayList<Integer>(colNumberSet);
			
				int z = 1;
				hintColText = " ";
				// Iterate array
				for(int p = 0; p <= colNumber2.size()-1; p++) {
					// End case
					if(p == colNumber2.size()-1) {
						hintColText += Integer.toString(z);
					}
					// Check if adajacent
					else if((colNumber2.get(p)-colNumber2.get(p+1)) == -1) { 
						++z; // Increment z 
					}
					else {
						// Otherwise add z to hint text
						hintColText += Integer.toString(z);
						z=1;
					}

				}

			}
			// If only one button (no iteration required)
			else if(colCount[index-1][1] == 1) {
				hintColText = "1";
			}
		}	

	}
	
	
	/**
	 * Calculates row hints for provided index
	 * 
	 * @param x index
	 */
	public void calculateRowHints(int index) {
		// Set empty String
		hintRowText = " ";
		// Check if cords array exists
		if(cords.size() > 0) {
			// Initialize cords array
			int[][] cordIndex = new int[cords.size()][2];
			// Initialize row count array
			int[][] rowCount = new int[cords.size()+1][2];
			// Populate array
			for(int i=0; i < cords.size(); i++) {
				cordIndex[i][1] = cords.get(i).y;
				cordIndex[i][0] = cords.get(i).x;
				
			}
			// Populate array
			for(int i=0; i < size; i++) {
				rowCount[i][0] = i+1;
				rowCount[i][1] = 0;
			}

			for(int i=0; i < cords.size(); i++) {
				for(int b=0; b <= 5; b++) {
					// Count number of squares per row
					if(cordIndex[i][0] == rowCount[b][0]) {
						int x = cordIndex[i][0];
						rowCount[x-1][1]++;
					}
				}
			}		
			// Initialize array
			ArrayList<Integer> rowNumber = new ArrayList<Integer>();
			rowNumber.ensureCapacity(30);
			
			// If more than one full button at col
			if(rowCount[index-1][1] > 1) {
				int q = 1;
				for(int x=0; x < cords.size(); x++) {
					if(cords.get(x).x == rowCount[index-1][0]) {
						rowNumber.add(cords.get(x).y); // Add cords of each button at row
					}
				}
				// Remove duplicates and sort in ascending order
				rowNumber.sort(null);
				Set<Integer> rowNumberSet = new LinkedHashSet<Integer>(rowNumber);
				ArrayList<Integer> rowNumber2 = new ArrayList<Integer>(rowNumberSet);

			
				int z = 1;
				// Iterate array
				hintRowText = " ";

				for(int p = 0; p <= rowNumber2.size()-1; p++) {
					// End case
					if(p == rowNumber2.size()-1) {
						hintRowText += Integer.toString(z);
					}
					// Check if adajacent
					else if((rowNumber2.get(p)-rowNumber2.get(p+1)) == -1) { 
						++z; 
					}
					else {
						// Otherwise add z to hint text
						hintRowText += Integer.toString(z);
						z=1;
					}

				}
		

			}
			// If only one button (no iteration required)
			else if(rowCount[index-1][1] == 1) {
				hintRowText = "1";

			}

		}
	}

	/**
	 * Generates a random game of picross
	 */
	public void setRandomGame() {
		// Generate random game
		setPerfectGame(true);
		int fullTiles = (size * size)/2;
		Random random = new Random();
		this.cords = new ArrayList<Coordinate>();
		for(int i = 0; i < fullTiles; i++) {
			int x = random.nextInt(size)+1; // random y
			int y = random.nextInt(size)+1; // random x
			if(cords.size() > 0) {
				for(int p = 0; p < cords.size(); p++) {
					if(cords.get(p).x == x && cords.get(p).y == y) {
						x = random.nextInt(size)+1; // random y
						y = random.nextInt(size)+1; // random x
						p = 0;
					}
				}
			}
			
			this.cords.add(new Coordinate(x,y)); 
		}

		resetScore();
	}
	
	
	
	/**
	 * Removes all values from coordinates array
	 */
	public void resetCords() {
		// Clear Array
		cords.removeAll(cords);
	}
	
	
	/**
	 * Sets first debug sample Scenario 
	 */
	public void setScenario1() {
		// Set Scenario Cords
		resetCords();
		setPerfectGame(true);
		ArrayList<Coordinate> cords2 = new ArrayList<Coordinate>();
		cords2.add(new Coordinate(1,1));
		cords2.add(new Coordinate(2,2));
		cords2.add(new Coordinate(3,3));
		cords2.add(new Coordinate(4,4));
		cords2.add(new Coordinate(5,5));
		cords2.add(new Coordinate(5,2));
		cords2.add(new Coordinate(3,4));
		cords2.add(new Coordinate(4,3));
		cords2.add(new Coordinate(4,4));
		cords2.add(new Coordinate(2,5));
		this.cords = cords2;
	}
	
	
	/**
	 * Sets second debug sample Scenario 
	 */
	public void setScenario2() {
		// Set Scenario Cords
		resetCords();
		setPerfectGame(true);
		ArrayList<Coordinate> cords2 = new ArrayList<Coordinate>();
		cords2.add(new Coordinate(1,1));
		cords2.add(new Coordinate(2,2));
		cords2.add(new Coordinate(3,3));
		cords2.add(new Coordinate(4,4));
		cords2.add(new Coordinate(5,5));
		cords2.add(new Coordinate(1,2));
		cords2.add(new Coordinate(3,4));
		cords2.add(new Coordinate(2,5));
		this.cords = cords2;
	}

	
	/**
	 * Sets third debug sample Scenario 
	 */
	public void setScenario3() {
		// Set Scenario Cords
		resetCords();
		setPerfectGame(true);
		ArrayList<Coordinate> cords2 = new ArrayList<Coordinate>();
		cords2.add(new Coordinate(1,1));
		cords2.add(new Coordinate(2,2));
		cords2.add(new Coordinate(3,3));
		cords2.add(new Coordinate(4,4));
		cords2.add(new Coordinate(5,5));
		cords2.add(new Coordinate(3,2));
		cords2.add(new Coordinate(3,4));
		cords2.add(new Coordinate(3,5));
		this.cords = cords2;
	}
	
	/**
	 * Sets third debug sample Scenario 
	 */
	public void setScenario4() {
		// Set Scenario Cords
		resetCords();
		// R
		setPerfectGame(true);
		ArrayList<Coordinate> cords2 = new ArrayList<Coordinate>();
		cords2.add(new Coordinate(1,3));
		cords2.add(new Coordinate(2,2));
		cords2.add(new Coordinate(2,3));
		cords2.add(new Coordinate(2,4));
		cords2.add(new Coordinate(3,2));
		cords2.add(new Coordinate(3,3));
		cords2.add(new Coordinate(3,4));
		cords2.add(new Coordinate(4,3));
		
		this.cords = cords2;
	}

	
	/**
	 * @param perfectGame the perfectGame to set
	 */
	public void setPerfectGame(boolean perfectGame) {
		this.perfectGame = perfectGame;
	}

	/**
	 * @return the perfectGame
	 */
	public boolean isPerfectGame() {
		return perfectGame;
	}

	public boolean isCorrectSquare(int i, int x, int y) {
		// Get cords
		int yCord = getCordsY(i); 
		int xCord = getCordsX(i);
		// Check for equality
		if(((xCord == (x+1)) && (yCord == (y+1)))) {
			return true;
		}

		return false;

	}

	/**
	 * Decide if game is over.
	 */
	public boolean shoudlEndGame() {
		if(getSquaresPlayed() == getArraySize()) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * @return the squaresPlayed
	 */
	public int getSquaresPlayed() {
		return squaresPlayed;
	}
	/**
	 * @param squaresPlayed the squaresPlayed to set
	 */
	public void updateSquaresPlayed() {
		this.squaresPlayed = squaresPlayed + 1; // increment
	}
	/**
	 * Increments score value by 1
	 */
	public void updateScore() {
		score++;// increment

	}
	/**
	 * Sets score value to 0
	 */
	public void resetScore() {
		score = 0;
	}
	/**
	 * Sets squares played value to 0
	 */
	public void resetSquares() {
		squaresPlayed = 0;
	}

	/**
	 * @return the game score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param Y index
	 * @return the cords Y value at given index
	 */
	public int getCordsY(int index) {
		return cords.get(index).y;
	}
	/**
	 * @param X index
	 * @return the cords X value at given index
	 */
	public int getCordsX(int index) {
		return cords.get(index).x;
	}

	/**
	 * @return the hintRowText
	 */
	public String getHintRowText(int index) {
		calculateRowHints(index);
		return hintRowText;
	}

	/**
	 * @return the hintColText
	 */
	public String getHintColText(int index) {
		calculateColHints(index);
		return hintColText;
	}
	
	
	/**
	 * @return the size
	 */
	public int getArraySize() {
		return cords.size();
	}

	
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * @return the startTimer
	 */
	public boolean isStartTimer() {
		return startTimer;
	}
	
	
	/**
	 * @param startTimer the startTimer to set
	 */
	public void setStartTimer(boolean startTimer) {
		this.startTimer = startTimer;
	}


}
