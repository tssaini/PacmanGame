import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class FastCreature extends Creature{
	
	private int direction;
	private int speed;
	private int orientation;
	private Rectangle boundingBox2;
	private Random rand;
	private long time;
	private long time2;
	public FastCreature(int x , int y, int w, int h){
		super(x, y, w, h);
		direction =1;
		rand = new Random();
		speed=3;
		time = System.currentTimeMillis();
		time2 = 0;
		orientation =0;
		boundingBox2 = new Rectangle(x-w*2,y-h*2,w*5,h*5);		
	}
	
	public void draw(Graphics2D g2){
		Color limeGreen = new Color(153,255,51);
		g2.setColor(limeGreen);
		int arcStart = 0;
		int arcExtent = 180;
		Arc2D.Double head = new Arc2D.Double(getX(), getY(), getWidth(), getHeight(), arcStart, arcExtent, Arc2D.CHORD);
		g2.fill(head);
		Rectangle upperBody = new Rectangle(getX(), getY()+getWidth()/2+4, getWidth(), getHeight()/2);
		g2.fill(upperBody);
		RoundRectangle2D.Double lowerBody= new RoundRectangle2D.Double(getX(), upperBody.getY()+upperBody.getHeight()-3, getWidth(), getHeight()/4, 10, 10);
		g2.fill(lowerBody);
		//arms
		RoundRectangle2D.Double arm1= new RoundRectangle2D.Double(getX()- getWidth()/4.0 -1, getY()+getHeight()/2.0, getWidth()/4.0, getHeight()/2.0, 10, 10);
		g2.fill(arm1);
		RoundRectangle2D.Double arm2= new RoundRectangle2D.Double(getX()+getWidth()+1, getY()+getHeight()/2.0, getWidth()/4.0, getHeight()/2, 10, 10);
		g2.fill(arm2);
		//legs
		RoundRectangle2D.Double leg1= new RoundRectangle2D.Double(getX()+getWidth()*(3/4.0)-arm1.getWidth()/2.0, lowerBody.getY()+lowerBody.getHeight()-4, arm1.getWidth(), arm1.getHeight(), 10, 10);
		g2.fill(leg1);
		RoundRectangle2D.Double leg2= new RoundRectangle2D.Double(getX()+getWidth()/4.0-arm1.getWidth()/2.0, lowerBody.getY()+lowerBody.getHeight()-4, arm1.getWidth(), arm1.getHeight(), 10, 10);
		g2.fill(leg2);
		//eyes
		g2.setColor(Color.white);
		Ellipse2D.Double eye1 = new Ellipse2D.Double(getX()+getWidth()/4.0 - getWidth()/10, getY()+5, getWidth()/5.0, getHeight()/5.0);
		g2.fill(eye1);
		Ellipse2D.Double eye2 = new Ellipse2D.Double(getX()+getWidth()*(3/4.0) - getWidth()/10, getY()+5, getWidth()/5.0, getHeight()/5.0);
		g2.fill(eye2);
				
	}
	public boolean collide(MoveableShape other){
		if(super.collide(other))
			return true;
		
		Creature otherC = (Creature) other;
		if((new Rectangle(otherC.getX(), otherC.getY(), otherC.getWidth(), otherC.getHeight())).intersects(boundingBox2)){
			evade();
		}else{
			time2 =0;
			
		}
		return false;
	}
	
	private void evade (){
		if((time2 == 0) || (System.currentTimeMillis() - time2)/1000.0 >= 2.5){
			time2 = System.currentTimeMillis();
			rotateRandom();
		}
	}
	
	private void rotateClockwise(){
		if (orientation == 3){
			orientation = 0;
			return;
		}
		orientation++;
	
	}
	private void rotateCountClockwise(){
		if (orientation == 0){
			orientation = 3;
			return;
		}
		orientation--;
	}
	private void rotateRandom(){
		
		//orientation = rand.nextInt(4);
		if(rand.nextInt(20) % 2 == 0){
			rotateClockwise();
		}else 
			rotateCountClockwise();
	}
	
	public void move(){
		int x = getX();
		int y = getY();
		switch(orientation){
			case 0: x+= speed;  break;
			case 1:	y+= speed; break;
			case 2: x-= speed; break;
			case 3: y-= speed; break;
		}
		/*if((System.currentTimeMillis() - time)/1000 >= 5 && time2 == 0){
			rotateRandom();
			time = System.currentTimeMillis();
		}*/
		if(getX() >= PacmanViewer.FRAME_WIDTH-getWidth()){
			x-= speed+2;
			rotateRandom();
		}else if(getX() <= 0){
			x+=speed+2;
			rotateRandom();
		}else if(getY() >= PacmanViewer.FRAME_HEIGHT-getHeight()*2){
			y-=speed+2;
			rotateRandom();
		}else if(getY() <= 0){
			y+=speed+2;
			rotateRandom();
		}
		
		setXY(x,y);
		moveBoundingBox();
		boundingBox2 = new Rectangle(getX()-getWidth()*2,getY()-getHeight()*2,getWidth()*5,getHeight()*5);
	}


}
