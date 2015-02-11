import java.util.*;
import java.awt.*;
import java.awt.geom.*;
/**
The FastCreature class extends the Creature class and adds more functionality to it by making it move faster and in a tactical way. It also redraws the Creature.

*/
public class FastCreature extends Creature{
	
	private int direction;
	private int speed;
	private int orientation;
	private Rectangle boundingBox2;
	private Random rand;
	private long time;
	private long time2;
	/**
	Sets the x, y, width and height values of the fast creature
	@pram x the x coordinate of the fast creature
	@pram y the y coordinate of the fast creature
	@pram w the width of the fast creature
	@pram h the height of the fast creature
	*/
	public FastCreature(int x , int y, int w, int h){
		super(x, y, w, h);
		direction =1;
		rand = new Random();
		speed=4;
		time = System.currentTimeMillis();
		time2 = 0;
		orientation =0;
		boundingBox2 = new Rectangle(x-w*2,y-h*2,w*5,h*5);		
	}
	/**
	Draws and colors the fast creature by using arcs, rectangles and ellipses
	@pram g2 the Graphics2D to draw and fill the shapes on
	*/
	public void draw(Graphics2D g2){
		Color limeGreen = new Color(153,255,51);
		g2.setColor(limeGreen);
		int arcStart = 0;
		int arcExtent = 180;
		//draw body
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
	/**
	Checks if another creature has collided with this creature or if another creature is near by
	@pram other the other creature 
	@return a boolean value that tells if another creature has collided 
	*/
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
	/**
	used to evade an the enemy by turning in a random direction
	
	*/
	private void evade (){
		if((time2 == 0) || (System.currentTimeMillis() - time2)/1000.0 >= 2.5){
			time2 = System.currentTimeMillis();
			rotateRandom();
		}
	}
	/**
	rotates the creature clock wise and moves it in the correct direction
	
	*/
	private void rotateClockwise(){
		if (orientation == 3){
			orientation = 0;
			return;
		}
		orientation++;
	}
	/**
	rotates the creature counter clock wise and moves it in the correct direction
	
	*/
	private void rotateCountClockwise(){
		if (orientation == 0){
			orientation = 3;
			return;
		}
		orientation--;
	}
	/**
	Rotates the creature either clock wise or counter clock wise
	
	*/
	private void rotateRandom(){
		if(rand.nextInt(20) % 2 == 0){
			rotateClockwise();
		}else 
			rotateCountClockwise();
	}
	/**
	Moves the creature according to its orrientaton and doesn't allow it to go off the screen
	*/
	public void move(){
		int x = getX();
		int y = getY();
		switch(orientation){
			case 0: x+= speed;  break;
			case 1:	y+= speed; break;
			case 2: x-= speed; break;
			case 3: y-= speed; break;
		}
		if((System.currentTimeMillis() - time)/1000 >= 5 && time2 == 0){
			rotateRandom();
			time = System.currentTimeMillis();
		}
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
