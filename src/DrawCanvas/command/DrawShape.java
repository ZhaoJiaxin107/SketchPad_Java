package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Circle;
import DrawCanvas.shape.Curve;
import DrawCanvas.shape.Hexagon;
import DrawCanvas.shape.Oval;
import DrawCanvas.shape.Rectangle;
import DrawCanvas.shape.Segment;
import DrawCanvas.shape.Square;


public class DrawShape extends Command{
	private Point beginPoint=null;
	private int first=0;
	private String choose=null;
    
    public DrawShape(String choose){
    	this.choose=choose;
    }
    
	public void executeDrag(Point p, Drawing dwg) {
		
		if((choose.equals("Rectangle"))){
		first++;
		if(first==1){
			Rectangle rect=new Rectangle(dwg.color);
			beginPoint=p;
			dwg.add(rect);
		}
		((Rectangle)(dwg.list.get(dwg.list.size()-1))).setBeginPoint(beginPoint);
		((Rectangle)(dwg.list.get(dwg.list.size()-1))).setDragPoint(p);
	   }
		
		if((choose.equals("Oval"))){
			first++;
			if(first==1){
				Oval oval=new Oval(dwg.color);
				beginPoint=p;
				dwg.add(oval);
			}
			((Oval)(dwg.list.get(dwg.list.size()-1))).setBeginPoint(beginPoint);
			((Oval)(dwg.list.get(dwg.list.size()-1))).setDragPoint(p);
		   }
		
		if((choose.equals("Line"))){
			first++;
			if(first==1){
				Segment segment=new Segment(dwg.color);
				beginPoint=p;
				dwg.add(segment);
			}
			((Segment)(dwg.list.get(dwg.list.size()-1))).setBeginPoint(beginPoint);
			((Segment)(dwg.list.get(dwg.list.size()-1))).setDragPoint(p);
		   }
		
		if((choose.equals("Hexagon"))){
			first++;
			if(first==1){
				Hexagon hexagon=new Hexagon(dwg.color);
				beginPoint=p;
				dwg.add(hexagon);
			}
			((Hexagon)(dwg.list.get(dwg.list.size()-1))).setBeginPoint(beginPoint);
			((Hexagon)(dwg.list.get(dwg.list.size()-1))).setDragPoint(p);
		   }
		
		if((choose.equals("Circle"))){
			first++;
			if(first==1){
				Circle circle=new Circle(dwg.color);
				beginPoint=p;
				dwg.add(circle);
			}
			((Circle)(dwg.list.get(dwg.list.size()-1))).setBeginPoint(beginPoint);
			((Circle)(dwg.list.get(dwg.list.size()-1))).setDragPoint(p);
		   }
		
		if((choose.equals("Square"))){
			first++;
			if(first==1){
				Square square=new Square(dwg.color);
				beginPoint=p;
				dwg.add(square);
			}
			((Square)(dwg.list.get(dwg.list.size()-1))).setBeginPoint(beginPoint);
			((Square)(dwg.list.get(dwg.list.size()-1))).setDragPoint(p);
		   }
		
		if((choose.equals("Curve"))){
			first++;
			//System.out.println(p);
			if(first==1){
				Curve curve=new Curve(dwg.color);
				beginPoint=p;   
				dwg.add(curve);
			}
			((Curve)(dwg.list.get(dwg.list.size()-1))).setBeginPoint(beginPoint);
			((Curve)(dwg.list.get(dwg.list.size()-1))).setDragPoint(p);
		   }
	}
	
	public void executePress(Point p, Drawing dwg) {
		first=0;
	}

}
