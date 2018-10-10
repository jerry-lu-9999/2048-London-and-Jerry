import javax.swing.*;

public class Game {
	
	static Block2048 canvas;
	
	public static void main(String[]args) {
		JFrame frame = new JFrame();
		canvas = new Block2048();
		
		frame.add(canvas);
		frame.setSize(150,1);
		frame.setLocation(400,100);
		frame.setTitle("2048");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(canvas);
		frame.setVisible(true);
		
	}
}
