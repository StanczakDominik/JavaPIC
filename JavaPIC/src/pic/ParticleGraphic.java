package pic;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ParticleGraphic{

	int X, Y;
	Color color;
	int radius=10;
	public ParticleGraphic(int iX, int iY, Color icolor)
	{
		X = iX;
		Y = iY;
		color = icolor;
	}
	public void paint(Graphics g)
	{
		g.fillOval(X-radius, Y-radius, 2*radius, 2*radius);
	}
}
