import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class SlowCreature extends Creature{
	private int speed;
	private int direction;
	public SlowCreature (int x, int y, int width, int height){
		super(x, y, width, height);
		direction = 1;
		speed =1;
	}
	
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
