import java.awt.*;
public interface MoveableShape{
	public void move();
	public boolean collide(MoveableShape other);
	public void draw(Graphics2D g2);

}
