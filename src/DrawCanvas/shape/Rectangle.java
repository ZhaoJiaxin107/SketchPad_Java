package DrawCanvas.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends Shape{
	
	private Point begin=null;
	private Point dragPoint=null;
	private Point centerPoint=null;
	public Point[] point=new Point[4];
	
	public void setBeginPoint(Point begin){
		this.begin=begin;	
	}
	
	public void setDragPoint(Point dragPoint){
		this.dragPoint=dragPoint;
	}
	
	public Rectangle(Color c) {
		super(c);
	}
	
	public boolean ableToReshape(Point p){
		point[0]= new Point(begin.x,begin.y);
		point[1]= new Point(begin.x,dragPoint.y);
		point[2]= new Point(dragPoint.x,dragPoint.y);
		point[3]= new Point(dragPoint.x,begin.y);
		for(int i=0;i<=3;i++){
			if(p.x>=point[i].x-10&&p.x<=point[i].x+10&&p.y>=point[i].y-10&&p.y<=point[i].y+10){
			    return true;
			}
		}
		return false;
	}

	public boolean containsPoint(Point p) {
		int xMax=Math.max(begin.x, dragPoint.x);
		int yMax=Math.max(begin.y, dragPoint.y);
		int xMin=Math.min(begin.x, dragPoint.x);
		int yMin=Math.min(begin.y, dragPoint.y);
		return ((p.x>=xMin) &&( p.x<=xMax) &&( p.y>=yMin) &&( p.y<=yMax));
	}

	public void drawShape(Graphics g) {
		g.fillRect(begin.x, begin.y, dragPoint.x-begin.x, dragPoint.y-begin.y);
	}
	
	public Point getCenter() {
		centerPoint=new Point((begin.x+dragPoint.x)/2,(begin.y+dragPoint.y)/2);
		return centerPoint;
	}
	
	public void move(int deltaX, int deltaY) {
		begin=new Point(begin.x+deltaX,begin.y+deltaY);
		dragPoint=new Point(dragPoint.x+deltaX,dragPoint.y+deltaY);
	}

	public void reshape(Point p1, Point p2) {
		for(int i=0;i<=3;i++){
			if(p1.x>=point[i].x-10&&p1.x<=point[i].x+10&&p1.y>=point[i].y-10&&p1.y<=point[i].y+10){
				begin=new Point(point[(i+2)%4].x,point[(i+2)%4].y);
				dragPoint=new Point(p2.x,p2.y);
				break;
			}
		}
	  }

}
