package se.jbee.game.scs.gfx;

/**
 * Contains all the type-like constants used in the context of game specific
 * graphics.
 */
public interface Gfx {

	/**
	 * Fonts
	 */
	int
	FONT_REGULAR = 0,
	FONT_LIGHT = 1,
	FONT_DOTS = 2;
	
	/**
	 * Colors
	 */
	int
	COLOR_TEXT_HIGHLIGHT = 1,
	COLOR_TEXT_NORMAL = 2;
	
	/**
	 * Backgrounds
	 */
	int
	BACK_STAR_WIDE = 1,
	BACK_STAR_NARROW = 2,
	BACK_PLANET_WIDE = 3,
	BACK_PLANET_NARROW = 4;
	
	/**
	 * Objects
	 */
	int
	OBJ_BACKGROUND = 1,
	
	OBJ_BORDER = 20,
	OBJ_FOCUS_LINE = 21,
	OBJ_FOCUS_BOX = 22,
	
	OBJ_TEXT = 30,
	
	OBJ_STAR = 40,
	OBJ_STAR_ARC = 41,
	OBJ_PLANET = 42,
	OBJ_PLANET_ARC = 43,
	OBJ_ORBIT_ARC = 44,
	OBJ_ROUTE = 45,
	
	OBJ_SLOT = 50,
	OBJ_STRUCTURE = 51,
	OBJ_RESOURCE = 52,
	OBJ_ROCK = 53
	
	;
}