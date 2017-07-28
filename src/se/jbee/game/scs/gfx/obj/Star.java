package se.jbee.game.scs.gfx.obj;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.geom.Arc2D;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;

import se.jbee.game.any.gfx.Drawable;
import se.jbee.game.any.gfx.Resources;
import se.jbee.game.any.state.Entity;
import se.jbee.game.any.state.Rnd;
import se.jbee.game.scs.gfx.Gfx;

public final class Star implements Gfx, Drawable {

	private final int x0;;
	private final int y0;
	private final int w;
	private final int rgba;
	private final long seed;
	private final boolean cut;
	
	public Star(int x0, int y0, int w, int rgba, long seed, boolean cut) {
		super();
		this.x0 = x0;
		this.y0 = y0;
		this.w = w;
		this.rgba = rgba;
		this.seed = seed;
		this.cut = cut;
	}

	@Override
	public void draw(Graphics2D gfx, Resources resources) {
		Color color = new Color(rgba);
		if (cut) {
			BufferedImage stex = resources.texture(TEXTURE_STAR_200x2000_SMALL);
			int y = 0;
			TexturePaint ss = new TexturePaint(stex, new Rectangle(x0, y, stex.getWidth(), stex.getHeight()));
			BufferedImage ltex =  color.getGreen() > color.getBlue() && color.getBlue() < color.getRed() 
					|| color.getRed() > color.getBlue()
					? resources.texture(TEXTURE_STAR_200x2000_LARGE_RED)
					: resources.texture(TEXTURE_STAR_200x2000_LARGE_BLUE);
			TexturePaint ls = new TexturePaint(ltex, new Rectangle(x0, y, ltex.getWidth(), ltex.getHeight()));
			starCut(gfx, x0, y0, w, rgba, ls, ss, seed);
		} else {
			starFull(gfx, x0, y0, w, rgba);
		}
	}

	private static void starFull(Graphics2D gfx, int x0, int y0, int dia, int rgba) {
		int r = new Color(rgba).getRed();
		int g = new Color(rgba).getGreen();
		int b = new Color(rgba).getBlue();
		int a = new Color(rgba, true).getAlpha();

		int rad = dia/2;

		Paint paint = new RadialGradientPaint(x0+rad, y0+rad, rad,
				new float[] { 0f, 0.4f, 0.55f, 0.80f, 1f },
				new Color[] { new Color(r,g,b,255), new Color(r,g,b, a-(a/3)), new Color(r,g,b, a/3), new Color(r,g,b, a/5), new Color(r,g,b,20) });
		gfx.setPaint(paint);
		gfx.fillOval(x0, y0, dia, dia);
		gfx.drawLine(x0, y0+rad, x0+dia, y0+rad);
		gfx.drawLine(x0+rad, y0, x0+rad, y0+dia);
	}

	private static void starCut(Graphics2D gfx, int x0, int y0, int d, int rgba, TexturePaint ls, TexturePaint ss, long seed) {
		Color c = new Color(rgba);
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		Arc2D.Float arc = new Arc2D.Float(x0, y0, d, d, 120, 120, Arc2D.CHORD);
		Arc2D.Float arc2 = new Arc2D.Float(x0+4, y0, d, d, 120, 120, Arc2D.CHORD);

		// outer glow (test)
		gfx.setStroke(new WeavyStroke(3f, 1f, -seed));
		gfx.setPaint(ls);
		gfx.draw(arc2);
		
		// basic 3d ball
		Paint paint = new RadialGradientPaint(x0+d/2,
				y0+d/2, d/2,
				new float[] { 0.8f, 0.95f, 1f },
                new Color[] { c, new Color(r-r/4,g-g/4,b-b/4), new Color(r/2,g/2,b/2) });
		gfx.setPaint(paint);
		gfx.fill(arc);
	
		// texture
		gfx.setComposite(AlphaComposite.SrcAtop);		
		gfx.setPaint(ls);
		gfx.fill(arc);
		gfx.setComposite(AlphaComposite.SrcOver);
	}

	public static BufferedImage image(int w, int h, float[] noise) {
		BufferedImage image = new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
		int[] pixels = new int[w * h];
		for (int k = 0; k < pixels.length; k++) {
			float rgb = noise[k];
			rgb = min(1f, max(0f, rgb));
			float a = min(0.9f, max(0.3f, (1.5f-rgb)*rgb));
			Color c = new Color(1f, rgb, rgb/2f, a);
			pixels[k++] = c.getRGB();
		}
		image.getRaster().setDataElements(0,  0,  w, h, pixels);
		return image;
	}
}

final class WeavyStroke implements Stroke {
	private static final float FLATNESS = 1f;

	private final float detail;
	private final float amplitude;
	private final Rnd rnd;

	public WeavyStroke( float detail, float amplitude, long seed ) {
		this.detail	= detail;
		this.amplitude	= amplitude;
		this.rnd = new Rnd(seed);
	}

	@Override
	public Shape createStrokedShape( Shape shape ) {
		GeneralPath result = new GeneralPath();
		shape = new BasicStroke( 10 ).createStrokedShape( shape );
		PathIterator it = new FlatteningPathIterator( shape.getPathIterator( null ), FLATNESS );
		float points[] = new float[6];
		float moveX = 0, moveY = 0;
		float lastX = 0, lastY = 0;
		float thisX = 0, thisY = 0;
		int type = 0;
		float next = 0;

		while ( !it.isDone() ) {
			type = it.currentSegment( points );
			switch( type ){
			case PathIterator.SEG_MOVETO:
				moveX = lastX = randomlyMoved( points[0] );
				moveY = lastY = randomlyMoved( points[1] );
				result.moveTo( moveX, moveY );
				next = 0;
				break;

			case PathIterator.SEG_CLOSE:
				points[0] = moveX;
				points[1] = moveY;
				// Fall into....

			case PathIterator.SEG_LINETO:
				thisX = randomlyMoved( points[0] );
				thisY = randomlyMoved( points[1] );
				float dx = thisX-lastX;
				float dy = thisY-lastY;
				float distance = (float)Math.sqrt( dx*dx + dy*dy );
				if ( distance >= next ) {
					float r = 1.0f/distance;
					while ( distance >= next ) {
						float x = lastX + next*dx*r;
						float y = lastY + next*dy*r;
						result.lineTo( randomlyMoved( x ), randomlyMoved( y ) );
						next += detail;
					}
				}
				next -= distance;
				lastX = thisX;
				lastY = thisY;
				break;
			}
			it.next();
		}

		return result;
	}
    
    private float randomlyMoved( float x ) {
        return x+rnd.nextFloat()*amplitude*2-1;
    }

}

