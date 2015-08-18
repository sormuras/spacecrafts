package se.jbee.game.scs.screen;

import static se.jbee.game.common.state.Change.put;
import static se.jbee.game.scs.gfx.Objects.background;
import static se.jbee.game.scs.gfx.Objects.star;

import java.awt.Rectangle;

import se.jbee.game.common.gfx.Dimension;
import se.jbee.game.common.gfx.Rnd;
import se.jbee.game.common.gfx.Stage;
import se.jbee.game.common.screen.Screen;
import se.jbee.game.common.screen.ScreenNo;
import se.jbee.game.common.state.Entity;
import se.jbee.game.common.state.State;
import se.jbee.game.scs.gfx.Gfx;
import se.jbee.game.scs.gfx.Objects;
import se.jbee.game.scs.state.GameComponent;

@ScreenNo(GameScreen.SCREEN_GALAXY)
public class Galaxy implements Screen, Gfx, GameComponent, GameScreen {

	@Override
	public void show(State user, State game, Dimension screen, Stage stage) {
		
		int w = screen.width;
		int h = screen.height;
		stage.enter(background(0, 0, w, h, BG_SPACE));
		
		Entity gamE = game.single(GAME);
		
		Rnd rnd = new Rnd(56);
		
		int concentration = 100;
		int cr = 200-concentration;
		int systems = (w/cr)*(h/cr);
		
		for (int i = 0; i < systems; i++) {
			int x = rnd.nextInt(w);
			if (x -50 < 0) {
				x += 50;
			} else if (x + 50 > w) {
				x-=50;
			}
			int y = rnd.nextInt(h);
			if (y -50 < 0) {
				y += 50;
			} else if (y + 50 > h) {
				y-=50;
			}
			int d = rnd.nextInt(12,22);
			stage.enter(star(x, y, d, rnd.nextInt(255), 0));
			int box = 2*d;
			Rectangle area = new Rectangle(x-d/2,y-d/2,box,box);
			stage.in(area, Objects.focusBox(x-d/2, y-d/2, box, box));
			stage.onLeftClickIn(area, put(gamE.id(), SCREEN, SCREEN_SOLAR_SYSTEM));
			
			// draw straight lines for all systems that can be reached for the currently selected fleet
			// draw dashed/dotted lines for all systems that can be reached given the current technology
		}
	}

}