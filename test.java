import java.awt.*;

public class test{
	public static void main(String[] args){
		Rectangle r = new Rectangle(200, 300, 40, 40);
		Rectangle r2 = new Rectangle(210, 330, 40, 40);
		System.out.println(r.intersects(r2));		

	}
}
