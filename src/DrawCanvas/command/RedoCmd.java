package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Shape;

public class RedoCmd extends Command{
	UndoCmd undo = new UndoCmd();
	Shape g;
	public void executeClick(Point p, Drawing dwg) {
		//System.out.println(undo.undolist);
		g = undo.undolist.get(0);
       if(g!=null) {
			dwg.list.add(g);
			//System.out.println(dwg.list);
			undo.undolist.remove(g);	
		}
		
	}
	
	
}
	
