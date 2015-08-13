package se.jbee.game.common.process;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import se.jbee.game.common.state.Change;

public final class Scene {

	public static final class AreaMapping {
		public final Shape area;
		public final Change[] changeset;
		public AreaMapping(Shape area, Change... changeset) {
			super();
			this.area = area;
			this.changeset = changeset;
		}
	}
	
	public static final class KeyMapping {
		public final int key;
		public final Change[] changeset;
		public KeyMapping(int key, Change... changeset) {
			super();
			this.key = key;
			this.changeset = changeset;
		}
	}
	
	public static final class AreaObject {
		public final Shape area;
		public final List<int[]> objects;
		public AreaObject(Shape area, int[] object) {
			this(area, Collections.singletonList(object));
		}
		public AreaObject(Shape area, List<int[]> objects) {
			super();
			this.area = area;
			this.objects = objects;
		}
	}

	public final List<AreaMapping> onLeftClick = new ArrayList<>();
	public final List<AreaMapping> onRightClick = new ArrayList<>();
	public final List<AreaObject> onMouseOver = new ArrayList<>();
	public final List<KeyMapping>  onKeyPress = new ArrayList<>();
	public final List<KeyMapping>  globalOnKeyPress = new ArrayList<>();
	
	public final AtomicReference<List<int[]>> objects = new AtomicReference<>(Collections.<int[]>emptyList());
	private final AtomicReference<List<int[]>> accents = new AtomicReference<>(Collections.<int[]>emptyList());
	
	private List<int[]> nextObjects;
	private List<int[]> nextAreaObjects;
	private AtomicBoolean ready = new AtomicBoolean(false);
	
	/**
	 * The frame tracks any changes to the stage so that displaying device can
	 * skip repaint as long as the frame has not changed.
	 */
	private int frame = 0;
	
	public int frame() {
		return frame;
	}
	
	public List<int[]> accents() {
		return accents.get();
	}
	
	public void accentuate(List<int[]> objects) {
		List<int[]> old = accents.getAndSet(objects);
		if (!old.isEmpty() || !objects.isEmpty()) {
			frame++;
		}
	}
	
	public void startOver() {
		ready.set(false);
		onLeftClick.clear();
		onRightClick.clear();
		onMouseOver.clear();
		onKeyPress.clear();
		accents.set(Collections.<int[]>emptyList());
		nextObjects = new ArrayList<int[]>();
		nextAreaObjects = new ArrayList<int[]>();
	}
	
	public void ready() {
		objects.set(nextObjects);
		accents.set(nextAreaObjects);
		frame++;
		ready.set(true);
	}
	
	public boolean isReady() {
		return ready.get();
	}
	
	public Scene place(int[] object) {
		nextObjects.add(object);
		return this;
	}
	
	public Scene bindLeftClick(Shape area, Change... changeset) {
		onLeftClick.add(new AreaMapping(area, changeset));
		return this;
	}
	
	public Scene bindRightClick(Shape area, Change... changeset) {
		onRightClick.add(new AreaMapping(area, changeset));
		return this;
	}
	
	public Scene bind(Shape area, int[]...objects) {
		onMouseOver.add(new AreaObject(area, Arrays.asList(objects)));
		return this;
	}

	public Scene bindKey(char key, Change... changeset) {
		onKeyPress.add(new KeyMapping(key, changeset));
		return this;
	}
	
	public Scene bindGlobalKey(char key, Change... changeset) {
		globalOnKeyPress.add(new KeyMapping(key, changeset));
		return this;
	}

}
