import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Creature implements MoveableShape{
	private int x;	
	private int y;
	private int width;
	private int height;
	private Random rand;
	private Rectangle boundingBox;

	public Creature(int x, int y, int width, int height){
		this.x = x;
		this.y =y;
		this.width = width;
		this.height = height;
		boundingBox = new Rectangle(x, y, width, height);
	}

	public boolean collide(MoveableShape other){
		Creature otherCreature = (Creature) other;
		return boundingBox.intersects(otherCreature.boundingBox);
		
	}
	
	public void draw(Graphics2D g2){
		Ellipse2D.Double oval = new Ellipse2D.Double(x, y, height, width);
		g2.setColor(Color.yellow);
		g2.fill(oval);
	}

	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
		moveBoundingBox();
	}
	public void moveBoundingBox(){
		boundingBox = new Rectangle(x, y, width, height);
	}
	public void move(){
		x+=2;
		moveBoundingBox();
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}

}
