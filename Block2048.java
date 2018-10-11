/*
 * Project 4
 * Group member: Jiahao Lu, Linzan Ye
 * We did not copy code from anyone else. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


// Game class
class Block2048 extends JComponent implements KeyListener {
	
	protected int[][] gameBoard; 		// 2D Array as the game board
	private boolean newGame;			// boolean to determine whether generate 2 blocks or 1 block
	private boolean firstMove;			// boolean to check if the user makes a valid move
	private boolean endGame = false;	// boolean to determine the end of the game
	private int quitGame = 0;			// count if the user confirms to quit
	private int resGame = 0;			// count if the user confirms to restart
	private int score = 0;				// record user's score
	private int moved = 0;				// int to check if the user makes a valid move
	private int moveMade = 0;			// int to count user's total valid move
	
	
	// Constructor
	public Block2048() {
		// Initialize listeners
		super();
		this.addKeyListener(this);
		this.setFocusable(true);
		
		// Initialize game board
		gameBoard = new int[4][4];
		
		// Initialize boolean value
		newGame = true;
		endGame = false; 
		
		// Generate 2 block for the game
		generateBlock();
		System.out.println("Hello, this is a simple 2048 game: \nPress w(up), a(left), s(down), d(right) to move the blocks");
		this.printGameBoard();
		
	}
	
	// Method to generate numbers
	private void generateBlock() {
		
		// When it is a new game
		if (newGame) {
			
			// Generate 2 blocks
			for (int i = 0; i < 2; i++) {		// for loop 2 times to generate 2 block
				while (true) {					// while loop to find the space available for new blocks
					int a = randomPos();
					int b = randomPos();
					
					// Until a blank block found
					if (gameBoard[a][b] == 0) {
						gameBoard[a][b] = setNumber();		// Employ setNumber method (get random numbers)
						break;
					}
				}
			}
			newGame = false;			// Make sure only generate 2 blocks when it is a new game.
		} else {
			// Else, generate 1 block
			while (true) {				// while loop 1 time to generate 1 block
				int a = randomPos();
				int b = randomPos();
				
				// Until a blank block found
				if (gameBoard[a][b] == 0) {
					gameBoard[a][b] = setNumber();
					break;
				}
			}
		}
	}
	
	// Method to return a random number between 2 (80%) and 4 (20%)
	private int setNumber() {
		double choose = Math.random();
		if (choose > 0.2) {
			return 2;
		} else {
			return 4;
		}
	}
	
	// Method to generate int from 0 to 3 for the position of the new block
	private int randomPos() {
		int rand = (int) (Math.random() * 4);
		return rand;
	}
	
	// Move all blocks upward. Not summing the blocks in this method
	private void moveUp(int row, int column) {
		
		// Base case 1
		if (row == 0) {
			return;
		} 
		// Base case 2
		else if (gameBoard[row - 1][column] != 0) {
			return;
		} 
		// Recursion: When previous == 0
		else if (gameBoard[row][column] != 0) {
			// If this block is not zero, it means that the user will make a valid move 
			// Make sure only count valid move once instead of many times for all the blocks moved up
			if (firstMove) {
				this.moved++;
				this.moveMade++;
				firstMove = false;
			}
			
			// Shift forward
			gameBoard[row - 1][column] = gameBoard[row][column]; 
			gameBoard[row][column] = 0;
			
			moveUp(row - 1, column);
		}
		return;
	}
	
	// Similar function as moveUp but to a different direction
	private void moveLeft(int row, int column) {		// Move across all zero blocks
		
		// Base case 1
		if (column == 0) {
			return;
		} 
		// Base case 2
		else if (gameBoard[row][column - 1] != 0) {
			return;
		} 
		// Recursion: when previous block == 0
		else if (gameBoard[row][column] != 0) {
			
			if (firstMove) {
				this.moved++;
				this.moveMade++;
				firstMove = false;
			}
			
			gameBoard[row][column - 1] = gameBoard[row][column]; 
			gameBoard[row][column] = 0;
			
			moveLeft(row, column - 1);
		}
		return;
	}
	
	// Similar function as moveUp but to a different direction
	private void moveDown(int row, int column) {		// Move across all zero blocks
		
		// Base case
		if (row == 3) {
			return;	
		} 
		// Base case
		else if (gameBoard[row + 1][column] != 0) {
			return;
		} 
		// Recursion: previous block == 0
		else if (gameBoard[row][column] != 0) {
			
			if (firstMove) {
				this.moved++;
				this.moveMade++;
				firstMove = false;
			}
			
			gameBoard[row + 1][column] = gameBoard[row][column]; 
			gameBoard[row][column] = 0;
			
			moveDown(row + 1, column);
		}
		return;
	}
	
	private void moveRight(int row, int column) {		// Move across all zero blocks
		
		// Base case
		if (column == 3) {
			return;
		} 
		// Base case
		else if (gameBoard[row][column + 1] != 0) {
			return;
		} 
		// Recursion: previous block == 0
		else if (gameBoard[row][column] != 0) {
			
			if (firstMove) {
				this.moved++;
				this.moveMade++;
				firstMove = false;
			}
			
			gameBoard[row][column + 1] = gameBoard[row][column]; 
			gameBoard[row][column] = 0;
			
			moveRight(row, column + 1);
		}
		return;
	}
	
	// Method to sum block up when there is an adjacent same block in the front.
	private void sumBlockUp(int row, int column) {
		
		// If the previous block is not the same, return.
		if (this.gameBoard[row - 1][column] != this.gameBoard[row][column]) {
			return;
		} else if (this.gameBoard[row][column] != 0) {		// When the block checked is not zero
			
			if (firstMove) {		// Valid move made
				this.moved++;
				this.moveMade++;
				firstMove = false;
			}
			
			// increase user's score
			this.score += this.gameBoard[row][column] * 2;
			
			// multiply the previous block by 2
			this.gameBoard[row - 1][column] *= 2;
			// set the original block scanned to be 0
			this.gameBoard[row][column] = 0;
			
			
		}
		return;
	}
	
	// Similar function as sumBlockUp but in a different direction
	private void sumBlockLeft(int row, int column) {
		
		if (this.gameBoard[row][column - 1] != this.gameBoard[row][column]) {
			return;
		} else if (this.gameBoard[row][column] != 0) {
			
			if (firstMove) {
				this.moved++;
				this.moveMade++;
				firstMove = false;
			}
			
			this.score += this.gameBoard[row][column] * 2;
			
			this.gameBoard[row][column - 1] *= 2;
			this.gameBoard[row][column] = 0;
			
			
		}
		return;
	}
	
	// Similar function as sumBlockUp but in a different direction
	private void sumBlockDown(int row, int column) {
		
		if (this.gameBoard[row + 1][column] != this.gameBoard[row][column]) {
			return;
		} else if(this.gameBoard[row][column] != 0) {
			
			if (firstMove) {
				this.moved++;
				this.moveMade++;
				firstMove = false;
			}
			
			this.score += this.gameBoard[row][column] * 2;
			
			this.gameBoard[row + 1][column] *= 2;
			this.gameBoard[row][column] = 0;
			
			
		}
		return;
	}
	
	// Similar function as sumBlockUp but in a different direction
	private void sumBlockRight(int row, int column) {
		
		if (this.gameBoard[row][column + 1] != this.gameBoard[row][column]) {
			return;
		} else if (this.gameBoard[row][column] != 0) {
			
			if (firstMove) {
				this.moved++;
				this.moveMade++;
				firstMove = false;
			}
			
			this.score += this.gameBoard[row][column] * 2;
			this.gameBoard[row][column + 1] *= 2;
			this.gameBoard[row][column] = 0;
			
		}
		return;
	}
	
	// The method that combines moveUp() and sumBlockUp()
	private void moveBlockUp() {
		// Renew the console
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		// boolean value to make sure only count valid move made once
		firstMove = true;
		
		// First for loop to move all the blocks up (not summing them in this for loop)
		for (int row = 1; row < 4; row++) {			// Skipping row = 0 (already in the desired position)
			for (int column = 0; column < 4; column++) {
				moveUp(row, column);
			}
		}
		
		// Second for loop to sum all the blocks that can possibly be summed. (when 2 same blocks are together) 
		for (int row = 1; row < 4; row++) {			// The algorithm counts by gameBoard[row-1][column]
			for (int column = 0; column < 4; column++) {
				sumBlockUp(row, column);
			}
		}
		
		// Last for loop to move all the blocks to their final position
		for (int row = 1; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				moveUp(row, column);
			}
		}
		
		// If moved = 0, means no valid move made. 
		if (this.moved > 0) {
			generateBlock();
			this.moved = 0;
			System.out.println("It's a valid move. " + "Valid move made(cumulative): " + moveMade);
		} else {
			System.out.println("Not a valid move. " + "Valid move made(cumulative): " + moveMade);
		}
		// Print score and maximum number
		System.out.println("Your current score is " + this.score + ". Maximum number is " + findMax());
		
	}
	
	// Similar function as moveBlockUp() but in a different direction
	private void moveBlockLeft() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		firstMove = true;
		
		for (int row = 0; row < 4; row++) {
			for (int column = 1; column < 4; column++) { 			// Skipping first column
				moveLeft(row, column);
			}
		}
		
		for (int row = 0; row < 4; row++) {
			for (int column = 1; column < 4; column++) {
				sumBlockLeft(row, column);
			}
		}
		
		for (int row = 0; row < 4; row++) {
			for (int column = 1; column < 4; column++) {
				moveLeft(row, column);
			}
		}
		
		// If moved = 0, means no move made. 
		if (this.moved > 0) {
			generateBlock();
			this.moved = 0;
			System.out.println("It's a valid move. " + "Valid move made(cumulative): " + moveMade);
		} else {
			System.out.println("Not a valid move. " + "Valid move made(cumulative): " + moveMade);
		}
		
		System.out.println("Your current score is " + this.score + ". Maximum number is " + findMax());
		
	}
	
	// Similar function as moveBlockUp() but in a different direction
	private void moveBlockDown() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		firstMove = true;
		
		for (int row = 2; row >= 0; row--) { 			// Skip row 3 (4th row)
			for (int column = 0; column < 4; column++) { 
				moveDown(row, column);
			}
		}
		
		for (int row = 2; row >= 0; row--) {
			for (int column = 0; column < 4; column++) {
				sumBlockDown(row, column);
			}
		}
		
		for (int row = 2; row >= 0; row--) {
			for (int column = 0; column < 4; column++) {
				moveDown(row, column);
			}
		}
		
		// If moved = 0, means no move made. 
		if (this.moved > 0) {
			generateBlock();
			this.moved = 0;
			System.out.println("It's a valid move. " + "Valid move made(cumulative): " + moveMade);
		} else {
			System.out.println("Not a valid move. " + "Valid move made(cumulative): " + moveMade);
		}
		
		System.out.println("Your current score is " + this.score + ". Maximum number is " + findMax());

	}
	
	// Similar function as moveBlockUp() but in a different direction
	private void moveBlockRight() {
		// Skip the first row
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		firstMove = true;
		
		for (int row = 0; row < 4; row++) {				//Skipping column 3
			for (int column = 2; column >= 0; column--) {		 
				moveRight(row, column);
			}
		}
		
		for (int row = 0; row < 4; row++) {
			for (int column = 2; column >= 0; column--) {
				sumBlockRight(row, column);
			}
		}
		
		for (int row = 0; row < 4; row++) {
			for (int column = 2; column >= 0; column--) {
				moveRight(row, column);
			}
		}
		
		// If moved = 0, means no move made. 
		if (this.moved > 0) {
			generateBlock();
			this.moved = 0;
			System.out.println("It's a valid move. " + "Valid move made(cumulative): " + moveMade);
		} else {
			System.out.println("Not a valid move. " + "Valid move made(cumulative): " + moveMade);
		}
		
		System.out.println("Your current score is " + this.score + ". Maximum number is " + findMax());
		
	}
	
	// Check if the user win: when one block equals to 2048
	public boolean checkWin() {
		
		if (findMax() == 2048) {
			return true;
		}
		return false;
	}
	
	// Check if there is no more valid moved can be made
	public boolean checkLose() {
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameBoard[i][j] == 0) {		// If one block equals to 0, means there is a possible valid move
					return false;
				}
			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameBoard[i][j] == gameBoard[i+1][j]) {		// Else if one block is the same 
					return false;								// as one adjacent block in a row
				}
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == gameBoard[i][j+1]) {		// Else if one block is the same
					return false;								// as one adjacent block in a row
				}
			}
		}

		return true;
		
	}
	
	// Find the maximum block in the game board
	public int findMax() {
		
		// Linear search
		int temp = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameBoard[i][j] > temp) {
					temp = gameBoard[i][j];
				}
			}
		}
		return temp;
	}
	
	@Override
	// Key listeners
	public void keyPressed(KeyEvent e) {
		
		// If !endGame : if game has not ended 
		if (!endGame) {
			
			// Move up
			if (e.getKeyCode() == KeyEvent.VK_W) {
				moveBlockUp();
				if (this.checkWin()) {
					System.out.println("You win.");
				} else if (this.checkLose()) {
					System.out.println("No possible moves can be made. You lose.");
				}
				this.printGameBoard();
				System.out.println("KeyPressed: " + e.getKeyChar());
				
			// Move left
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				moveBlockLeft();
				if (this.checkWin()) {
					System.out.println("You win.");
				} else if (this.checkLose()) {
					System.out.println("No possible moves can be made. You lose.");
				}
				this.printGameBoard();
				System.out.println("KeyPressed: " + e.getKeyChar());
				
			// Move down
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				moveBlockDown();
				if (this.checkWin()) {
					System.out.println("You win.");
				} else if (this.checkLose()) {
					System.out.println("No possible moves can be made. You lose.");
				}
				this.printGameBoard();
				System.out.println("KeyPressed: " + e.getKeyChar());
			
			// Move right
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				moveBlockRight();
				if (this.checkWin()) {
					System.out.println("You win.");
				} else if (this.checkLose()) {
					System.out.println("No possible moves can be made. You lose.");
				}
				this.printGameBoard();
				System.out.println("KeyPressed: " + e.getKeyChar());
			}
		}
		
		// quit game	(when the game has not ended)
		if (e.getKeyCode() == KeyEvent.VK_Q && !this.endGame) {
			
			System.out.println("KeyPressed: " + e.getKeyChar());
			
			// Quit: the user needs to confirm their choice. when quitGame > 2 (confirmed 1 time), quit the game
			this.quitGame++;
			if (this.quitGame == 1) {
				System.out.println("Do you want to quit the game? Press Q again to quit; press w/a/s/d to resume");
			}
			
			// The user confirmed his/her choice
			if (quitGame == 2) {
				endGame = true;
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nEnd Game\n\n\n");
				System.out.println("Valid move made: " + this.moveMade + ", score: " + this.score);
				System.out.println("Maximum score is " + this.findMax());
				this.quitGame = 0;
			}
			
			
		} else { 
			
			// The user cancelled quitting the game
			this.quitGame = 0;
			
		}
		
		// Restart the game (when the game has not ended)
		if (e.getKeyCode() == KeyEvent.VK_R && !this.endGame) {
			
			System.out.println("KeyPressed: " + e.getKeyChar());
			
			this.resGame++;
			
			// Restart: the user needs to confirm their choice. Once resGame > 2 (confirmed once), restart.
			if (this.resGame == 1) {
				System.out.println("Do you want to restart the game? Press R again to restart; press w, a, s, or d to resume");
			}
			
			if (resGame == 2 || e.getKeyCode() != KeyEvent.VK_R) {
				gameBoard = new int[4][4];
				score = 0;
				moveMade = 0;
				this.newGame = true;
				generateBlock();
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nGame restarted. Press w, a, s, or d to start.");
				this.printGameBoard();
				this.resGame = 0;
			}
		} else { 
			// The user cancelled his/her choice. 
			this.resGame = 0;
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// Print the game board
	public void printGameBoard() {
		
		for (int i = 0; i < this.gameBoard.length; i++) {
		
			for (int j = 0; j < this.gameBoard[i].length; j++) {
			
				//Make sure the same spacing for different lengths of numbers
				if (this.gameBoard[i][j] < 10) {
					System.out.print(this.gameBoard[i][j] + "    ");
				} else if (this.gameBoard[i][j] <100) {
					System.out.print(this.gameBoard[i][j] + "   ");
				} else if (this.gameBoard[i][j] <1000) {
					System.out.print(this.gameBoard[i][j] + "  ");
				} else if (this.gameBoard[i][j] <1000) {
					System.out.print(this.gameBoard[i][j] + " ");
				}
			}
		
			// Change line for new row
			System.out.println();
			System.out.println();
		}
	}
}

	
 
