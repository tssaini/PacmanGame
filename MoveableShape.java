import java.awt.*;
/**
Interface provides a class with methods that are important for a moving object
*/
public interface MoveableShape{
	/**
	Move the shape in a certain pattern or direction
	*/
	public void move();
	/**
	Checks if this MoveableShape has collided with the other MoveableShape
	@pram other the other MoveableShape
	@return returns a boolean value which determines if this MoveableShape has collided with the other MoveableShape
	*/
	public boolean collide(MoveableShape other);
	/**
	Draws the moveable shape using different shapes
	@pram g2 the Graphics2D object to draw the shapes on 
	*/
	public void draw(Graphics2D g2);

}
