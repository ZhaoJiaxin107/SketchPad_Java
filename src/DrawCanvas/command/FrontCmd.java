package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Shape;

public class FrontCmd extends Command{

	public void executeClick(Point p, Drawing dwg) {
			Shape g=dwg.getFrontmostContainer(p);
			if(g!=null){
			dwg.list.remove(g);
			dwg.list.add(g);
		 }
		}
	
}
