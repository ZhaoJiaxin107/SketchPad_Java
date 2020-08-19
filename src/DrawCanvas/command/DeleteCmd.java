package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Shape;


public class DeleteCmd extends Command{
	public static Shape g1;
	public void executeClick(Point p, Drawing dwg) {
		Shape g=dwg.getFrontmostContainer(p);
		//System.out.println(g);
		if(g!=null) {
		g1 = g;
		dwg.list.remove(g);
		}
	}
}
