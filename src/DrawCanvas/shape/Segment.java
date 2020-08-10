package DrawCanvas.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Segment extends Shape{

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
	
	public Segment(Color c) {
		super(c);
	}
	
	private static boolean almostContainsPoint(Point p, int left, int top,
			int right, int bottom, double tolerance) {
		return p.x >= left - tolerance && p.y >= top - tolerance
				&& p.x <= right + tolerance && p.y <= bottom + tolerance;
	}

	private static double distanceToPoint(Point p, int x1, int y1, int x2, int y2) {
		if (x1 == x2) // vertical segment
			return (double) (Math.abs(p.x - x1)); // yes, use horizontal
		// distance
		else if (y1 == y2) // horizontal segment
			return (double) (Math.abs(p.y - y1)); // yes, use vertical distance
		else {

			// Compute m, the slope of the line containing the segment.
			double m = ((double) (y1 - y2)) / ((double) (x1 - x2));

			//The slope of the line perpendicular to the
			// segment.
			double mperp = -1.0 / m;

			// Compute the (x, y) intersection of the line containing the
			// segment and the line that is perpendicular to the segment and
			// that
			// contains Point p.
			double x = (((double) y1) - ((double) p.y) - (m * x1) + (mperp * p.x))
					/ (mperp - m);
			double y = m * (x - x1) + y1;

			// Return the distance between Point p and (x, y).
			return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
		}
	}
	@Override
	public void drawShape(Graphics g) {
		g.drawLine(begin.x, begin.y, dragPoint.x, dragPoint.y);
		
	}

	@Override
	public boolean containsPoint(Point p) {
		int xMax=Math.max(begin.x,dragPoint.x);
		int yMax=Math.max(begin.y,dragPoint.y);
		int xMin=Math.min(begin.x,dragPoint.x);
		int yMin=Math.min(begin.y,dragPoint.y);
		return (distanceToPoint(p,begin.x, begin.y, dragPoint.x, dragPoint.y)<5)
		&& almostContainsPoint(p,xMin,yMin,xMax,yMax,3);
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
