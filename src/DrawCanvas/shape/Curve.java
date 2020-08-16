package DrawCanvas.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.QuadCurve2D;

public class Curve extends Shape{
	private Point begin=null;
	private Point dragPoint=null;
	private Point centerPoint=null;
	public Point[] point=new Point[4];
	
	private static boolean pointInCurve(Point p, int left, int top, int width,int height) {
		double a = width / 2.0; // half of the width
		double b = height / 2.0; // half of the height
		double centerx = left + a; // x-axis of the center
		double centery = top + b; // y-axis of the center
		double x = p.x - centerx; // horizontal distance between p and center
		double y = p.y - centery; // vertical distance between p and center

		return Math.pow(x / a, 2) + Math.pow(y / b, 2) <= 1;
	}
	
	public void setBeginPoint(Point begin){
		this.begin=begin;	
	}
	
	public void setDragPoint(Point dragPoint){
		this.dragPoint=dragPoint;
	}

	public Curve(Color c) {
		super(c);
	}

	@Override
	public void drawShape(Graphics g) {
		//g.drawOval(begin.x, begin.y, dragPoint.x-begin.x, dragPoint.y-begin.y);
		g.drawArc(begin.x, begin.y, dragPoint.x-begin.x, dragPoint.y-begin.y,30,60);
		
	}

	@Override
	public boolean containsPoint(Point p) {
		return pointInCurve(p,begin.x, begin.y, dragPoint.x-begin.x, dragPoint.y-begin.y);
	}

	@Override
	public void move(int deltaX, int deltaY) {
		begin=new Point(begin.x+deltaX,begin.y+deltaY);
		dragPoint=new Point(dragPoint.x+deltaX,dragPoint.y+deltaY);
	}

	@Override
	public Point getCenter() {
		centerPoint=new Point(dragPoint.x/2+begin.x/2,dragPoint.y/2+begin.y/2);
		return centerPoint;
	}

	@Override
	public boolean ableToReshape(Point p) {
		point[0]= new Point(begin.x,begin.y);
		point[1]= new Point(begin.x,dragPoint.y);
		point[2]= new Point(dragPoint.x,dragPoint.y);
		point[3]= new Point(dragPoint.x,begin.y);
		for(int i=0;i<=3;i++){
			if(p.x>=point[i].x-100&&p.x<=point[i].x+100&&p.y>=point[i].y-100&&p.y<=point[i].y+100){
			    return true;
			}
		}
		return false;
	}

	@Override
	public void reshape(Point p1, Point p2) {
		for(int i=0;i<=3;i++){
			if(p1.x>=point[i].x-100&&p1.x<=point[i].x+100&&p1.y>=point[i].y-100&&p1.y<=point[i].y+100){
				begin=new Point(point[(i+2)%4].x,point[(i+2)%4].y);
				dragPoint=new Point(p2.x,p2.y);
				break;
			}
		}
		
	}
	
}
