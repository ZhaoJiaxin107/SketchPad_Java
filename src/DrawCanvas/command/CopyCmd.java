package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Shape;


public class CopyCmd extends Command{
	private int first=0;
	private Shape g,h=null;
	private int x1,x2,y1,y2,i;
	
	public void executeDrag(Point p, Drawing dwg) {
		if(first==0){
			h=dwg.getFrontmostContainer(p);
			if(h!=null){
			i=dwg.list.indexOf(h);
			g=(Shape)dwg.getFrontmostContainer(p).clone();
			dwg.list.add(i+1,g);
			 }
			}
		if(g!=null && h!=null){
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
