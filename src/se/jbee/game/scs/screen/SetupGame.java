package se.jbee.game.scs.screen;

import static se.jbee.game.common.state.Change.replace;
import static se.jbee.game.common.state.Entity.codePoints;
import static se.jbee.game.scs.gfx.Objects.text;

import java.awt.Rectangle;

import se.jbee.game.common.gfx.Dimension;
import se.jbee.game.common.gfx.Stage;
import se.jbee.game.common.screen.Screen;
import se.jbee.game.common.screen.ScreenNo;
import se.jbee.game.common.state.Entity;
import se.jbee.game.common.state.State;
import se.jbee.game.scs.gfx.Gfx;
import se.jbee.game.scs.gfx.Objects;
import se.jbee.game.scs.state.GameComponent;

/**
 * Used in turn zero to setup overall game properties.
 */
@ScreenNo(GameScreen.SCREEN_SETUP_GAME)
public class SetupGame implements Screen, GameComponent, Gfx {

	@Override
	public void show(State user, State game, Dimension screen, Stage stage) {

		stage.enter(Objects.background(0, 0, screen.width, screen.height, BG_BLACK));
		
		// # players
		// # AI
		// # stars in a cluster
		// # clusters in galaxy
		// (things about events, independent parties, etc.)
		
		// new entities should only be created by the game
		// especially as entities cannot be removed the player should first be created at the end of the zero'th turn.

		
		int x0 = screen.width/2-200;
		int y0 = screen.height/3;
		
		setup(stage, game, x0, y0, "Players", SETUP_NUMBER_OF_PLAYERS);
		y0 += 60;
		setup(stage, game, x0, y0, "AIs", SETUP_NUMBER_OF_AIS);
		y0 += 60;
		setup(stage, game, x0, y0, "Galaxy", SETUP_GALAXY_SIZE, "none", "small", "medium", "large");
	}

	private void setup(Stage stage, State game, int x0, int y0, String text, int setupIndex, String...names) {
		Entity gamE = game.single(GAME);
		int[] setup = gamE.list(SETUP);		
		stage.enter(text(x0, y0+40, FONT_THIN, 48, COLOR_TEXT_NORMAL, 1));
		stage.enter(codePoints(text));
		stage.enter(new int[] {OBJ_BUTTON_LESS, x0+200, y0, 50, 50});
		stage.enter(new int[] {OBJ_BUTTON_LESS, x0+350, y0, 50, 50});
		int val = setup[setupIndex];
		if (names.length == 0) {
			stage.enter(text(x0+290, y0+40, FONT_LIGHT, 48, COLOR_TEXT_HIGHLIGHT, 1));
			stage.enter(codePoints(String.valueOf(val)));
		} else {
			stage.enter(text(x0+270, y0+40, FONT_LIGHT, 24, COLOR_TEXT_HIGHLIGHT, 1));
			stage.enter(codePoints(names[val]));
		}
		if (val > 1) {
			stage.onLeftClickIn(new Rectangle(x0+200, y0, 50,50), replace(gamE.id(), SETUP, setupIndex, val-1));
		}
		if (names.length == 0 || val < names.length-1) {
			stage.onLeftClickIn(new Rectangle(x0+350, y0, 50,50), replace(gamE.id(), SETUP, setupIndex, val+1));
		}
	}

}
