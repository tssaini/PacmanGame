import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Predator extends Creature{
	private int angleMouth;
	private int angleMouth2;
	private int mouthOpen;
	private int orientation;
	private int speed;
	public Predator (int x, int y, int w, int h){
		super(x,y,w,h);
		orientation = 0;
		speed = 5;
		angleMouth=30;
		angleMouth2=300;
		mouthOpen =0;
	}
	
	
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
	
	
	public void rotateClockwise(){
		if (orientation == 3){
			orientation = 0;
			return;
		}
		orientation++;
	
	}
	public void rotateCountClockwise(){
		if (orientation == 0){
			orientation = 3;
			return;
		}
		orientation--;
	}
	
	public void move(){
		int x = getX();
		int y =getY();
		switch(orientation){
			case 0: x+= speed;  break;
			case 1:	y+= speed; break;
			case 2: x-= speed; break;
			case 3: y-= speed; break;
		}
		setXY(x,y);
		moveBoundingBox();
		
	}
	

}
