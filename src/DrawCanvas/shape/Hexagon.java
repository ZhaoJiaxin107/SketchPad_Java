package DrawCanvas.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Hexagon extends Shape{

	private Point begin=null;
	private Point dragPoint=null;
	private Point centerPoint=null;
    boolean isIn=false;
    public Point[] point=new Point[4];
	
	public void setBeginPoint(Point begin){
		this.begin=begin;	
	}
	
	public void setDragPoint(Point dragPoint){
		this.dragPoint=dragPoint;
	}
	
	public Hexagon(Color c) {
		super(c);
	}
	
	public boolean containsPoint(Point p) {
		int xMax=Math.max(begin.x, dragPoint.x);
		int xMin=Math.min(begin.x, dragPoint.x);
		int yMax=Math.max(begin.y, dragPoint.y);
		int yMin=Math.min(begin.y, dragPoint.y);
		if(p.y>=yMin && p.y<=yMax){
			        if((p.x-(xMax-xMin)*(p.y-yMin)/(yMax-yMin))>(2*xMin-xMax)
					&& (p.x-(xMax-xMin)*(p.y-yMin)/(yMax-yMin))<xMax
					&& (p.x+(xMax-xMin)*(yMax-p.y)/(yMax-yMin))>xMin
					&& (p.x+(xMax-xMin)*(yMax-p.y)/(yMax-yMin))<(2*xMax-xMin)){
			        	if((p.x+(xMax-xMin)*(p.y-yMin)/(yMax-yMin))>xMin
						&& (p.x+(xMax-xMin)*(p.y-yMin)/(yMax-yMin))<(2*xMax-xMin)
						&& (p.x-(xMax-xMin)*(yMax-p.y)/(yMax-yMin))>(2*xMin-xMax)
						&& (p.x-(xMax-xMin)*(yMax-p.y)/(yMax-yMin))<xMax
						){
					      isIn=true;
				     }
			        	else isIn=false;
			       }
			        else isIn=false;		
		}
		else
		    isIn=false;
		return isIn;
	}
	
	@Override
	public void drawShape(Graphics g) {
		Polygon polygon=new Polygon();
		polygon.addPoint(begin.x, begin.y);
		polygon.addPoint(dragPoint.x, begin.y);
		polygon.addPoint((dragPoint.x-begin.x)/2+dragPoint.x,(begin.y+dragPoint.y)/2);
		polygon.addPoint(dragPoint.x,dragPoint.y);
		polygon.addPoint(begin.x,dragPoint.y);
		polygon.addPoint(begin.x-(dragPoint.x-begin.x)/2,(begin.y+dragPoint.y)/2);
		g.drawPolygon(polygon);
	}

	@Override
	public void move(int deltaX, int deltaY) {
		begin=new Point(begin.x+deltaX,begin.y+deltaY);
		dragPoint=new Point(dragPoint.x+deltaX,dragPoint.y+deltaY);
	}

	@Override
	public Point getCenter() {
		centerPoint=new Point((begin.x+dragPoint.x)/2,(begin.y+dragPoint.y)/2);
		return centerPoint;
	}

	@Override
	public boolean ableToReshape(Point p) {
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

	@Override
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
