package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Shape;


public class MoveCmd extends Command{
	private Shape g=null;
	private int x1,x2,y1,y2;
	private int first=0; 
	
	public void executeDrag(Point p, Drawing dwg) {
		if(first==0){
			g=dwg.getFrontmostContainer(p);
			}
		if(g!=null){
			first++;
			if(first==1){
				x1=p.x;
				y1=p.y;
			}
			x2=p.x;
			y2=p.y;
	        g.move(x2-x1, y2-y1);
	        x1=p.x;
	        y1=p.y;
		}
		
	}

	public void executePress(Point p, Drawing dwg) {
		first=0;
	}

}
