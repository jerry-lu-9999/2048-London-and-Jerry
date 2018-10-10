import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



class Block2048 extends JComponent implements KeyListener{
	
	protected int[][] gameBoard; 
	private boolean newGame;
	private boolean firstMove;
	private boolean endGame = false;
	private int quitGame = 0;
	private int resGame = 0;
	private int score = 0;
	private int moved = 0;
	private int moveMade = 0;
	
	public Block2048() {
		// Initialize Board
		super();
		this.addKeyListener(this);
		this.setFocusable(true);
		
		gameBoard = new int[4][4];
		newGame = true;
		endGame = false; 
		
		generateBlock();
		System.out.println("Hello, this is a simple 2048 game: \nPress w(up), a(left), s(down), d(right) to move the blocks");
		this.printGameBoard();
		
	}
	
	private void generateBlock() {
		
		if (newGame) {
			// Generate 2 blocks
			for (int i = 0; i < 2; i++) {		// while loop 2 times to generate block
				while (true) {
					int a = randomPos();
					int b = randomPos();
					
					if (gameBoard[a][b] == 0) {
						gameBoard[a][b] = setNumber();
						break;
					}
				}
			}
			newGame = false;
		} else {
			// Generate 1 block
			while (true) {				// while loop 1 time to generate 1 block
				int a = randomPos();
				int b = randomPos();
				if (gameBoard[a][b] == 0) {
					gameBoard[a][b] = setNumber();
					break;
				}
			}
		}
	}
	
	private int setNumber() {
		double choose = Math.random();
		if (choose > 0.2) {
			return 2;
		} else {
			return 4;
		}
	}
	
	private int randomPos() {
		int rand = (int) (Math.random() * 4);
		return rand;
	}
	
	private void moveUp(int row, int column) {		// Move across all zero blocks
		
		// Base case 1
		if (row == 0) {
			return;
		} 
		// Base case 2
		else if (gameBoard[row - 1][column] != 0) {
			return;
		} 
		// Recursion: Prev == 0
		else if (gameBoard[row][column] != 0) {
			// If the block is not zero, it means that it will make a valid move 
			// In this else 
			
			if (firstMove) {
				this.moved++;
				this.moveMade++;
				firstMove = false;
			}
			
			gameBoard[row - 1][column] = gameBoard[row][column]; 
			gameBoard[row][column] = 0;
			
			moveUp(row - 1, column);
		}
		return;
		
		// Need to reset moved for new move
	}
	
	private void moveLeft(int row, int column) {		// Move across all zero blocks
		
		// Base case 1
		if (column == 0) {
			return;
		} 
		// Base case 2
		else if (gameBoard[row][column - 1] != 0) {
			return;
		} 
		// Recursion: Prev == 0
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
		
		// Need to reset moved for new move
	}
	
	private void moveDown(int row, int column) {		// Move across all zero blocks
		
		// Base case
		if (row == 3) {
			return;		// The method ends here.
		} 
		// Base case
		else if (gameBoard[row + 1][column] != 0) {
			return;
		} 
		// Recursion: Prev == 0
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
		
		// Need to reset moved for new move
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
		// Recursion: Prev == 0
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
		
		// Need to reset moved for new move
	}
	
	private void sumBlockUp(int row, int column) {
		
		if (this.gameBoard[row - 1][column] != this.gameBoard[row][column]) {
			return;
		} else if (this.gameBoard[row][column] != 0) {
			
			if (firstMove) {
				this.moved++;
				this.moveMade++;
				firstMove = false;
			}
			
			this.score += this.gameBoard[row][column] * 2;
			
			this.gameBoard[row - 1][column] *= 2;
			this.gameBoard[row][column] = 0;
			
			
		}
		return;
	}
	
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
	
	private void moveBlockUp() {
		// Skip the first row
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		firstMove = true;
		
		for (int row = 1; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				// Elements are only all successfully moved up 
				// when this for loop is finished
				// Therefore, the loop is repeated triply. 
				moveUp(row, column);
			}
		}
		
		for (int row = 1; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				sumBlockUp(row, column);
			}
		}
		
		for (int row = 1; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				moveUp(row, column);
			}
		}
		
		// If moved = 0, means no move made. 
		if (this.moved > 0) {
			generateBlock();
			this.moved = 0;
			System.out.println("It's a valid move. " + "Valid move made: " + moveMade);
		} else {
			System.out.println("Not a valid move. " + "Valid move made: " + moveMade);
		}
		
		System.out.println("Your current score is " + this.score + ". Maximum number is " + findMax());
		
	}
	
	private void moveBlockLeft() {
		// Skip the first row
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		firstMove = true;
		
		for (int row = 0; row < 4; row++) {
			for (int column = 1; column < 4; column++) { 
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
			System.out.println("It's a valid move. " + "Valid move made: " + moveMade);
		} else {
			System.out.println("Not a valid move. " + "Valid move made: " + moveMade);
		}
		
		System.out.println("Your current score is " + this.score + ". Maximum number is " + findMax());
		
	}
	
	private void moveBlockDown() {
		// Skip the first row
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		firstMove = true;
		
		for (int row = 2; row >= 0; row--) { 		// Skip row 3 (4th row)
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
			System.out.println("It's a valid move. " + "Valid move made: " + moveMade);
		} else {
			System.out.println("Not a valid move. " + "Valid move made: " + moveMade);
		}
		
		System.out.println("Your current score is " + this.score + ". Maximum number is " + findMax());
		
		
	}
	
	private void moveBlockRight() {
		// Skip the first row
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		firstMove = true;
		
		for (int row = 0; row < 4; row++) {
			for (int column = 2; column >= 0; column--) {		//Skip column 3 (no 4) 
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
			System.out.println("It's a valid move. " + "Valid move made: " + moveMade);
		} else {
			System.out.println("Not a valid move. " + "Valid move made: " + moveMade);
		}
		
		System.out.println("Your current score is " + this.score + ". Maximum number is " + findMax());
		
	}
	
	public boolean checkWin() {
		
		if (findMax() == 2048) {
			return true;
		}
		return false;
	}
	
	public boolean checkLose() {
		
		boolean noZero = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameBoard[i][j] == 0) {
					return false;
				}
			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameBoard[i][j] == gameBoard[i+1][j]) {
					return false;
				}
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == gameBoard[i][j+1]) {
					return false;
				}
			}
		}

		return true;
		
	}
	
	public int findMax() {
		
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
	public void keyPressed(KeyEvent e) {
		
		if (!endGame) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				moveBlockUp();
				if (this.checkWin()) {
					System.out.println("You win.");
				} else if (this.checkLose()) {
					System.out.println("No possible moves can be made. You lose.");
				}
				this.printGameBoard();
				
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				moveBlockLeft();
				if (this.checkWin()) {
					System.out.println("You win.");
				} else if (this.checkLose()) {
					System.out.println("No possible moves can be made. You lose.");
				}
				this.printGameBoard();
				
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				moveBlockDown();
				if (this.checkWin()) {
					System.out.println("You win.");
				} else if (this.checkLose()) {
					System.out.println("No possible moves can be made. You lose.");
				}
				this.printGameBoard();
				
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				moveBlockRight();
				if (this.checkWin()) {
					System.out.println("You win.");
				} else if (this.checkLose()) {
					System.out.println("No possible moves can be made. You lose.");
				}
				this.printGameBoard();
			}
			System.out.println("KeyPressed: " + e.getKeyChar());
		}
		
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			// Quit
			this.quitGame++;
			if (this.quitGame == 1) {
				System.out.println("Do you want to quit the game? Press Q again to quit; press any other key to resume");
			}
			
			if (quitGame == 2) {
				endGame = true;
				System.out.println("End Game");
				this.quitGame = 0;
			}
			
			
		} else { 
			
			this.quitGame = 0;
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_R) {
			this.resGame++;
			if (this.resGame == 1) {
				System.out.println("Do you want to restart the game? Press R again to restart; press any other key to resume");
			}
			
			if (resGame == 2 || e.getKeyCode() != KeyEvent.VK_R) {
				gameBoard = new int[4][4];
				score = 0;
				moveMade = 0;
				this.newGame = true;
				generateBlock();
				this.printGameBoard();
				System.out.println("Restart game. Press w, a, s, or d to start.");
				this.resGame = 0;
			}
		} else { 
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
	
	
	
	
	
	
	
	// Lab 3 code

	public void runningSum2DArray(int[][] array, int drct) {	
		
		// switch for 4 directions
		switch(drct) {
		
		case 1: 		// Left to right
			for (int i = 0; i < array.length; i++) {
				for (int j = 1; j < array[i].length; j++) {
					array[i][j] += array[i][j - 1];
				}
			}
			printGameBoard();
			break;
			
		case 2: 		// Right to left
			for (int i = 0; i < array.length; i++) {
				for (int j = array[i].length - 1; j > 0; j--) {
					array[i][j - 1] += array[i][j];
				}
			}
			printGameBoard();
			break;
		case 3: 		// Up to down
			
			// Assume square matrix, so setting array.get(0).size()
			for (int j = 0; j < array[0].length; j++) {
				for (int i = 1; i < array.length; i++) {
					array[i][j] += array[i - 1][j];
				}
			}
			printGameBoard();
			break;
			
		case 4: 		// Down to up
			for (int j = 0; j < array[0].length; j++) {
				for (int i = array[j].length - 1; i > 0; i--) {
					array[i -1][j] += array[i][j];
				}
			}
			printGameBoard();
			break;
			
		default:
			System.out.println("Error direction");
			break;
		}
	}
	
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

	
 
