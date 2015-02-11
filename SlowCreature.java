import java.util.*;
import java.awt.*;
import java.awt.geom.*;
/**
The SlowCreature class extends the Creature class and adds more functionality such as different movement and the appearence of the creature is also changed

*/
public class SlowCreature extends Creature{
	private int speed;
	private int direction;
	/**
	sets the x, y, width and height values of the slow creature
	@pram x the x coordinate of the slow creature 
	@pram y the y coordinate of the slow creatures
	@pram width the width of the slow creature
	@pram height the height of the slow creature
	*/
	public SlowCreature (int x, int y, int width, int height){
		super(x, y, width, height);
		direction = 1;
		speed =2;
	}
	/**
	Draws the slow creature using polygons and arcs
	@pram g2 the Graphics2D to draw the shapes on 
	*/
	public void draw(Graphics2D g2){
		g2.setColor(Color.red);
		int arcStart = 0;
		int arcExtent = 180;
		Arc2D.Double head = new Arc2D.Double(getX(), getY(), getWidth(), getHeight()+15, arcStart, arcExtent, Arc2D.CHORD);
		g2.fill(head);
		//legs
		int[] xArray = {getX()+1, getX()+getWidth()/6+1 ,getX()+getWidth()/3+1};
		int[] yArray = {getY()+getHeight()-5,getY()+getHeight()+getHeight()/3-5, getY()+getHeight()-5};
		Polygon leg1 = new Polygon(xArray, yArray,3);
		g2.fill(leg1);
		
		int[] xArray2 = {xArray[2], xArray[2]+getWidth()/6 , xArray[2]+getWidth()/3};//getX()+getWidth()/2 + getWidth()/6};
		int[] yArray2 = {yArray[0],yArray[1], yArray[2]};
		Polygon leg2 = new Polygon(xArray2, yArray2,3);
		g2.fill(leg2);
		
		int[] xArray3 = {xArray2[2], xArray2[2]+getWidth()/6 ,xArray2[2]+getWidth()/3};
		int[] yArray3 = {yArray[0],yArray[1], yArray[2]};
		Polygon leg3 = new Polygon(xArray3, yArray3,3);
		g2.fill(leg3);
		
		
	}
	/**
	Moves the slow creature in the x direction
	*/
	public void move(){
		if(getX() >= PacmanViewer.FRAME_WIDTH-getWidth()){
			direction = -1;
		}else if(getX() <= 0){
			direction =1;
		}
		setXY(getX()+direction*speed, getY());
		moveBoundingBox();
	}
}
