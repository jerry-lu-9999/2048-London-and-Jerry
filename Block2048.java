import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Block2048 implements KeyListener{
	
	private Integer[][] gameBoard; 
	private boolean newGame;
	
	
	private Block2048() {
		// Initialize Board
		gameBoard = new Integer[4][4];
		newGame = true;
		generateBlock();
		
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
			while (true) {
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
	
	private void moveBlockUp() {
		
	}
	private void moveBlockLeft() {
		
	}
	private void moveBlockDown() {
		
	}
	private void moveBlockRight() {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			moveBlockUp();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			moveBlockLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			moveBlockDown();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			moveBlockRight();
		} else if (e.getKeyCode() == KeyEvent.VK_Q) {
			// Quit
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			// Restart
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	// Lab 3 code

	public static void runningSum2DArray(int[][] array, int drct) {	
		
		// switch for 4 directions
		switch(drct) {
		
		case 1: 		// Left to right
			for (int i = 0; i < array.length; i++) {
				for (int j = 1; j < array[i].length; j++) {
					array[i][j] += array[i][j - 1];
				}
			}
			print2Darray(array);
			break;
			
		case 2: 		// Right to left
			for (int i = 0; i < array.length; i++) {
				for (int j = array[i].length - 1; j > 0; j--) {
					array[i][j - 1] += array[i][j];
				}
			}
			print2Darray(array);
			break;
		case 3: 		// Up to down
			
			// Assume square matrix, so setting array.get(0).size()
			for (int j = 0; j < array[0].length; j++) {
				for (int i = 1; i < array.length; i++) {
					array[i][j] += array[i - 1][j];
				}
			}
			print2Darray(array);
			break;
			
		case 4: 		// Down to up
			for (int j = 0; j < array[0].length; j++) {
				for (int i = array[j].length - 1; i > 0; i--) {
					array[i -1][j] += array[i][j];
				}
			}
			print2Darray(array);
			break;
			
		default:
			System.out.println("Error direction");
			break;
		}
	}
	
	public static void print2Darray(int[][] array) {
	
		for (int i = 0; i < array.length; i++) {
		
			for (int j = 0; j < array[i].length; j++) {
			
				//Make sure the same spacing for different lengths of numbers
				if (array[i][j] < 10) {
					System.out.print(array[i][j] + "    ");
				} else if (array[i][j] <100) {
					System.out.print(array[i][j] + "   ");
				} else if (array[i][j] <1000) {
					System.out.print(array[i][j] + "  ");
				} else if (array[i][j] <1000) {
					System.out.print(array[i][j] + " ");
				}
			}
		
			// Change line for new row
			System.out.println();
		}
	}
}

	
