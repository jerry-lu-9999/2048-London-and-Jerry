import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



class Block2048 extends JComponent implements KeyListener{
	
	private int[][] gameBoard; 
	private boolean newGame;
	private int moved = 0;
	
	public Block2048() {
		// Initialize Board
		super();
		this.addKeyListener(this);
		this.setFocusable(true);
		
		gameBoard = new int[4][4];
		newGame = true;
		generateBlock();
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
		
		// Base case
		if (row == 0) {
			return;
		} 
		// Base case
		else if (gameBoard[row - 1][column] != 0) {
			return;
		} 
		// Recursion: Prev == 0
		else {
			// We include situations moving 0
			if (gameBoard[row][column] != 0) {
				this.moved++;
			}
			System.out.println(this.moved);
			gameBoard[row - 1][column] = gameBoard[row][column]; 
			gameBoard[row][column] = 0;
			
			moveUp(row - 1, column);
		}
		return;
		
		// Need to reset moved for new move
	}
	
	private void sumBlockUp(int row, int column) {
		
		// No recursion
		if (gameBoard[row - 1][column] != gameBoard[row][column]) {
			return;
		} else {
			
			if (gameBoard[row][column] != 0) {
				System.out.println("Summed");
				this.moved++;
			}
			gameBoard[row - 1][column] *= 2;
			gameBoard[row][column] = 0;
			
			
		}
		return;
	}
	
	private void moveBlockUp() {
		// Skip the first row
		for (int row = 1; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				
				moveUp(row, column);
				sumBlockUp(row, column);
				//moveUp(row, column);
				
			}
		}
		
		// If moved = 0, means no move made. 
		if (this.moved > 0) {
			generateBlock();
		} else {
			// No move made, try again
		}
		
	}
	private void moveBlockLeft() {
		
	}
	private void moveBlockDown() {
		
	}
	private void moveBlockRight() {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key Pressed");
		if (e.getKeyCode() == KeyEvent.VK_W) {
			moveBlockUp();
			this.printGameBoard();
			
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			moveBlockLeft();
			this.printGameBoard();
			
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			moveBlockDown();
			this.printGameBoard();
			
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			moveBlockRight();
			this.printGameBoard();
			
		} else if (e.getKeyCode() == KeyEvent.VK_Q) {
			// Quit
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			// Restart
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
		System.out.println("\n\n\n");
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

	
 
