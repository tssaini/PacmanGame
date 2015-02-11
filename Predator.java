import java.util.*;
import java.awt.*;
import java.awt.geom.*;
/**
The predator class extends the creature class to add more features, such as rotation and moving in different directions.

*/
public class Predator extends Creature{
	private int angleMouth;
	private int angleMouth2;
	private int mouthOpen;
	private int orientation;
	private int speed;
	/**
	sets the x, y, width and height of the predator
	@pram x the x coordinate of the predator
	@pram y the y coordinate of the predator
	@pram w the width of the the predator
	*/
	public Predator (int x, int y, int w, int h){
		super(x,y,w,h);
		orientation = 0;
		speed = 5;
		angleMouth=30;
		angleMouth2=300;
		mouthOpen =0;
	}
	/**
	Draws the predator according to its orientation
	@pram g2 the Graphics2D to draw the Predator on
	*/
	public void draw(Graphics2D g2){
		if(mouthOpen % 2 ==0 ){
			switch(orientation){
			case 0: angleMouth = 30; break;
			case 1:	angleMouth = 300; break;
			case 2: angleMouth = 210; break;
			case 3: angleMouth = 120; break;
			}
			angleMouth2 = 300;
		} else {
			angleMouth2 = 360;
		}
		g2.setColor(Color.yellow);		
		mouthOpen++;
		g2.fillArc(getX(), getY(), getWidth(), getHeight(), angleMouth, angleMouth2);
	}
	
	/**
	Rotate the predator clock wise 
	*/
	public void rotateClockwise(){
		if (orientation == 3){
			orientation = 0;
			return;
		}
		orientation++;
	
	}
	/**
	Rotate the predator counter clock wise
	*/
	public void rotateCountClockwise(){
		if (orientation == 0){
			orientation = 3;
			return;
		}
		orientation--;
	}
	/**
	Moves the Predator according to its orientation
	*/
	public void move(){
		int x = getX();
		int y =getY();
		switch(orientation){
			case 0: x+= speed;  break;
			case 1:	y+= speed; break;
			case 2: x-= speed; break;
			case 3: y-= speed; break;
		}
		if(getX() >= PacmanViewer.FRAME_WIDTH-getWidth()){
			x-= speed;
		}else if(getX() <= 0){
			x+=speed;
		}else if(getY() >= PacmanViewer.FRAME_HEIGHT-getHeight()){
			y-=speed;
		}else if(getY() <= 0){
			y+=speed;
		}
		setXY(x,y);
		moveBoundingBox();
		
	}
	

}
