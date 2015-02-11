import javax.swing.*;
/**
Creates the frame and sets up the PacmanComponent

*/
public class PacmanViewer {
	static final int FRAME_WIDTH = 500;	
	static final int FRAME_HEIGHT = 600;
	/**
	Makes the frame a certain size and adds the component to it 
	*/
	public static void main(String[] args){
		
		JFrame frame = new JFrame("PacMan");
		PacmanComponent component = new PacmanComponent();
		frame.add(component);
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		frame.setVisible(true) ;

	}
}

