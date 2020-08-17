package DrawCanvas.command;

import java.awt.Point;
import java.util.ArrayList;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Shape;

public class UndoCmd extends Command{
	public static ArrayList<Shape> undolist = new ArrayList<Shape>();
	Shape g;
	public void executeClick(Point p, Drawing dwg) {
		if(dwg.list.size() == 0) {
			  g = null;
		}else {
			g=dwg.list.get(dwg.list.size()-1);
		}
		if( g!=null ) {
			dwg.list.remove(g);
			undolist.add(g);
			}
			System.out.println(undolist);
	}
}
