package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Shape;


public class ColorCmd extends Command{
	public Shape g=null;
	public void executeClick(Point p, Drawing dwg) {
		 g=dwg.getFrontmostContainer(p);
		if(g!=null)
		g.setColor(dwg.color);	
	}
}
