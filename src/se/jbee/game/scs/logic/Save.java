package se.jbee.game.scs.logic;

import java.io.File;
import java.io.IOException;

import se.jbee.game.any.logic.Progress;
import se.jbee.game.any.state.Entity;
import se.jbee.game.any.state.State;
import se.jbee.game.scs.state.GameComponent;
import se.jbee.game.scs.state.UserComponent;

public class Save implements Progress, GameComponent, UserComponent {

	public static final Progress INSTANCE = new Save();
	
	@Override
	public void progress(State user, State game) {
		Entity gamE = game.single(GAME);
		File file = new File(user.single(USER).text(SAVEGAME_DIR), gamE.text(SAVEGAME)+".game");
		gamE.erase(ACTION);
		gamE.erase(SAVEGAME);
		try {
			game.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO write a error journal that is shown in the error screen
			gamE.put(ACTION, ACTION_ERROR);
		}		
	}

}
