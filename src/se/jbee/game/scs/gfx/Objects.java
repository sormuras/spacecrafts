package se.jbee.game.scs.gfx;

import java.awt.Rectangle;

/**
 * A utility class to construct GFX objects.
 *
 * This is only to make code more readable and documenting the contract or
 * expectation of each of the {@link Gfx}s.
 *
 * There are two rules:
 * - index 0 holds the object type
 * - index 1 holds the number of successive data elements that belong to the object
 */
public final class Objects implements Gfx {

	//int[] colors = new int[] { 0x006600, 0x82633F, 0xFF5014 };

	public static int[] text(int n, int x, int y, int font, int size, int color) {
		return text(n,x,y,font,size,color, ALIGN_NW, -1, -1);
	}

	//TODO stretch (making size so that the text takes X/Y bounds given)
	public static int[] text(int n, int x, int y, int font, int size, int color, int align, int x2, int y2) {
		return new int[] { OBJ_TEXT, n, x,y, font, size, color, align, x2, y2 };
	}

	public static int[] knob(int n, int x, int y, int d, int piecolor, int textcolor) {
		return new int[] { OBJ_KNOB, n, x, y, d, piecolor, textcolor};
	}

	public static int[] icon(int type, int x, int y, int d, int color) { //TODO add color-effect
		return new int[] { OBJ_ICON, 0, type,x,y,d,color };
	}

	public static int[] techwheel(int xc, int yc, int d, int color) {
		return new int[] { OBJ_TECH_WHEEL, 0, xc,yc,d,color};
	}

	public static int[] ring(int xc, int yc, int d, int thickness, int fg) {
		return new int[] { OBJ_RING, 0, xc,yc,d, thickness, fg};
	}

	public static int[] background(int x, int y, int w, int h, int no, int... seeds) {
		return seeds.length < 2
				? new int[] { OBJ_BACKGROUND, 0, x,y,w,h, no, 0, 0 }
				: new int[] { OBJ_BACKGROUND, 0, x,y,w,h, no, seeds[0], seeds[1] };
	}

	public static int[] border(Rectangle area) {
		return border(area.x, area.y, area.width, area.height);
	}

	public static int[] border(int x, int y, int w, int h) {
		return new int[] { OBJ_RECT, 0, x,y,w,h };
	}

	public static int[] focusBox(int x, int y, int w, int h) {
		return new int[] { OBJ_RECT, 0, x,y,w,h };
	}

	public static int[] path(int type, int color, int stroke, int x1, int y1, int x2, int y2) {
		return new int[] { OBJ_PATH, 0, type, color, stroke, x1, y1, x2, y2 };
	}

	public static int[] path(int type, int color, int stroke, int x1, int y1, int x2, int y2, int x3, int y3) {
		return new int[] { OBJ_PATH, 0, type, color, stroke, x1, y1, x2, y2, x3, y3 };
	}

	public static int[] timeLine(int x1, int y1, int x2, int y2) {
		return path(PATH_EDGY, COLOR_TEXT_NORMAL, 1, x1,y1,x2,y2);
	}

	public static int[] focusLine(int x1, int y1, int x2, int y2) {
		return path(PATH_EDGY, COLOR_TEXT_HIGHLIGHT, 1, x1,y1,x2,y2);
	}

	public static int[] star(int x, int y, int d, int rgba) {
		return new int[] { OBJ_STAR, 0, x,y,d, rgba };
	}

	public static int[] starClip(int x, int y, int d, int rgba) {
		return new int[] { OBJ_STAR_CLIP, 0, x,y, d, rgba };
	}

	public static int[] planet(int x, int y, int d, int type, int rgba) {
		return new int[] { OBJ_PLANET, 0, x,y,d,type,rgba };
	}

	public static int[] planetClip(int x, int y, int d, int c, int rgba) {
		return new int[] { OBJ_PLANET_CLIP, 0, x,y,d,c,rgba };
	}

}
