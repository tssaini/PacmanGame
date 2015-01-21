import javax.swing.Timer;
import javax.swing.JComponent;
import java.util.*;
import java.util.Date;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;
public class PacmanComponent extends JComponent {
	
	private ArrayList<Creature> creatures;
	private Predator p;
	boolean firstClick;
	private long timeElapsed;
	private String timeFormat;
	
	public PacmanComponent(){
		timeElapsed = 0;
		creatures = new ArrayList<Creature>();
		Random rand = new Random();
		firstClick =true;
		//Predator
		p = new Predator(20, 20 , 40 ,40);
		
		//slow creatures
		for(int i =0; i < 7; i++){
			creatures.add(new SlowCreature(rand.nextInt(PacmanViewer.FRAME_WIDTH-30), rand.nextInt(PacmanViewer.FRAME_HEIGHT-30), 20, 30));
		}
		//fast creatures
		for(int i = 0; i < 4; i++){
			creatures.add(new FastCreature(rand.nextInt(PacmanViewer.FRAME_WIDTH-50), rand.nextInt(PacmanViewer.FRAME_HEIGHT-50), 30, 35));
		}
		class TimerListener implements ActionListener{
			public void actionPerformed(ActionEvent e){				
				p.move();
				for(Creature c : creatures){
					c.move();
					repaint();
				}
				for(Creature c : creatures){
					if(c.collide(p)){
						creatures.remove(c);
						repaint();
						break;
					}			
				}
				
				
				
			}

		}
		TimerListener listener =  new TimerListener();
		final Timer timer = new Timer(70, listener);
		
		
		class MyMouseListener extends MouseAdapter{
			public void mousePressed(MouseEvent e){
				if (e.getButton() == MouseEvent.BUTTON1) {
					if(firstClick){
						timeElapsed = System.currentTimeMillis();
						timer.start();
						firstClick=false;
					}else					
						p.rotateCountClockwise();
					
				}
				if (e.getButton() == MouseEvent.BUTTON3){
					p.rotateClockwise();
				}
			}
		}
		MyMouseListener listener2 = new MyMouseListener();
		addMouseListener(listener2);
	}

	public void paintComponent(Graphics g){	
		Graphics2D g2 = (Graphics2D) g;
		for(Creature c : creatures){
			c.draw(g2);
		}
		p.draw(g2);
		g2.setColor(Color.black);
		
		long time = 0;
		if(timeElapsed != 0)
			time = System.currentTimeMillis()-timeElapsed;
		String text = String.format("%02d:%02d", time/60000, time/1000%60);
		g2.drawString(text,20,20);
		
	}

}
