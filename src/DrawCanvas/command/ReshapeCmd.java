package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Shape;

public class ReshapeCmd extends Command{
	private boolean isReshape=false;
	private Shape g=null;
	private Point point=null;

	public void executeDrag(Point p,Drawing dwg){
		if(isReshape){
			g.reshape(point, p);
		}
	}
	
	public void executePress(Point p,Drawing dwg){
		point=p;
		g=dwg.getFrontmostContainer(p);
		if(g!=null && g.ableToReshape(point)){
			isReshape=true;
		}else{
			isReshape=false;
		}
     }

}
