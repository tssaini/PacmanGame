import java.util.*;
import java.awt.*;
import java.awt.geom.*;

/**
The Creature class describes the properties and functions that a creature should have 

*/
public class Creature implements MoveableShape{
	private int x;	
	private int y;
	private int width;
	private int height;
	private Random rand;
	private Rectangle boundingBox;
	/**
	Sets the x, y, height and width of the creature according to the arguements
	@pram x the x coordinate of the creature
	@pram y the y coordinate of the creature 
	@pram width the width of the creature
	@pram height the height of the creature
	*/
	public Creature(int x, int y, int width, int height){
		this.x = x;
		this.y =y;
		this.width = width;
		this.height = height;
		boundingBox = new Rectangle(x, y, width, height);
	}
	/**
	Checks if another creature has collided with this creature
	@pram other the other creature
	@return boolean value that tells if this creature has collided with another creature
	*/
	public boolean collide(MoveableShape other){
		Creature otherCreature = (Creature) other;
		return boundingBox.intersects(otherCreature.boundingBox);
		
	}
	/**
	Draws and fills the shape of the creature
	@pram g2 the Graphics2D object to draw on
	*/
	public void draw(Graphics2D g2){
		Ellipse2D.Double oval = new Ellipse2D.Double(x, y, height, width);
		g2.setColor(Color.yellow);
		g2.fill(oval);
	}
	/**
	Sets the value of x and y according to the arguements
	@pram x the value to change the x to
	@pram y the value to change the y to
	*/
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
		moveBoundingBox();
	}
	/**
	Moves the bounding box of the creature to its x and y
	*/
	public void moveBoundingBox(){
		boundingBox = new Rectangle(x, y, width, height);
	}
	/**
	Moves the creature in the x direction by adding to the x value
	*/
	public void move(){
		x+=2;
		moveBoundingBox();
	}
	/**
	Returns the x value of the creature
	@return value of x
	*/
	public int getX(){
		return x;
	}
	/**
	Returns the y value of the creature
	@return the y value
	*/
	public int getY(){
		return y;
	}
	/**
	Returns the height of the creature
	@return the height value
	*/
	public int getHeight(){
		return height;
	}
	/**
	Returns the width of the creature
	@return the width of the creature
	*/
	public int getWidth(){
		return width;
	}

}
