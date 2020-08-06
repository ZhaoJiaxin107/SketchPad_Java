package DrawCanvas.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape implements Cloneable{
	private Color color; // Shape's color

	public abstract void drawShape(Graphics g); // draw the Shape

	public abstract boolean containsPoint(Point p); // whether Shape contain point p
									

	public abstract void move(int deltaX, int deltaY); // move the Shape

	public abstract Point getCenter(); // get the Shape's center
	
    public abstract boolean ableToReshape(Point p);
	
	public abstract void reshape(Point p1,Point p2);

	// Create a Shape, setting its color.
	public Shape(Color c) {
		color = c;
	}

	// Set the Shape's color.
	public void setColor(Color newColor) {
		color = newColor;
	}

	// Draw the Shape.
	public void draw(Graphics g) {
		Color savedColor = g.getColor();
		g.setColor(color);
		drawShape(g);
		g.setColor(savedColor);
	}

	// Move the Shape to be a given center.
	public void setCenter(Point newCenter) {
		Point oldCenter = getCenter();
		move(newCenter.x - oldCenter.x, newCenter.y - oldCenter.y);
	}
	
	public Object clone(){ 
          try{ 
               return super.clone(); 
              }
          catch(CloneNotSupportedException e){ 
                    return null;
               } 
             } 
}
