package se.jbee.game.scs.gfx.obj;

import java.awt.Graphics2D;

import se.jbee.game.any.gfx.ObjClass;
import se.jbee.game.any.gfx.Resources;
import se.jbee.game.scs.gfx.Gfx;

public class Rect implements Gfx, ObjClass {

	@Override
	public void draw(Graphics2D gfx, Resources resources, int[] obj) {
		gfx.setColor(resources.color(COLOR_TEXT_HIGHLIGHT)); gfx.drawRect(obj[1], obj[2], obj[3], obj[4]);		
	}

}
