package se.jbee.game.scs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import se.jbee.game.uni.state.TestEntity;
import se.jbee.game.uni.state.TestTexts;
import se.jbee.game.uni.state.TestState;

@RunWith(Suite.class)
@SuiteClasses({ TestEntity.class, TestState.class, TestGame.class, TestTexts.class })
public class SuitSCS {
	// just a suite
}
