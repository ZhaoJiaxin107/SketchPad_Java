package DrawCanvas.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import DrawCanvas.shape.Shape;

public class Drawing {
	
	public Color color = null;
	public Shape shape = null;
	public ArrayList<Shape> list = new ArrayList<Shape>();
	
	public Drawing(Color initialColor) {
		this.color = initialColor;
	}
	
	public Shape getFrontmostContainer(Point p) {
		int i = 0;
		for(i = list.size()-1; i>=0;i--) {
			shape = list.get(i);
			if(shape.containsPoint(p)) {
				System.out.println(shape);
				break;
			}
		}
		if(i==-1)
			shape = null;
		return shape;
	}
	
	public void add(Shape g){
		list.add(g);
		System.out.println(list);
	}
	
	public void draw(Graphics g) {
		for(Shape p:list)
			p.draw(g);
	}

}
