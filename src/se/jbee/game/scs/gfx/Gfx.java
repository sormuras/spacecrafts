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
	FONT_THIN = 2,
	FONT_DOTS = 3;

	/**
	 * Alignment
	 */
	int
	ALIGN_N = 0,
	ALIGN_NE = 1,
	ALIGN_E = 2,
	ALIGN_SE = 3,
	ALIGN_S = 4,
	ALIGN_SW = 5,
	ALIGN_W = 6,
	ALIGN_NW = 7,
	ALIGN_EYE = 8;

	/**
	 * Colors
	 */
	int
	COLOR_TRANSPARENT = 0,
	COLOR_WHITE = 1,
	COLOR_BLACK = 2,
	COLOR_DEFAULT = 3,
	COLOR_TEXT_NORMAL = 7,
	COLOR_TEXT_HIGHLIGHT = 8,
	COLOR_TEXT_SPECIAL = 9,
	// component colors
	COLOR_SLOT = 10, // the border of an empty slot
	COLOR_SHIELD = 11,
	COLOR_ENERGY = 12,
	COLOR_WEAPON = 13,
	COLOR_CONTROL = 14,
	COLOR_DRIVE = 15,
	COLOR_SPECIAL = 16,
	COLOR_SCANNER = 17,
	// components worked at
	COLOR_YARD = 20,  // building
	COLOR_BIOSPHERE = 21, // housing and food
	COLOR_FARM = 22, // food
	COLOR_LAB = 23, // research
	COLOR_ACADEMY = 24 // leaders
	;

	/**
	 * Backgrounds
	 */
	int
	BG_BLACK = 0,
	BG_SPACE = 1;

	/**
	 * Noises
	 */
	int
	NOISE_STAR_LARGE = 0,
	NOISE_STAR_SMALL = 1,
	NOISE_PLANET_LARGE = 2,
	NOISE_PLANET_SMALL = 3;

	/**
	 * Textures
	 */
	int
	TEXTURE_STAR_200x2000_LARGE = 0,
	TEXTURE_STAR_200x2000_SMALL = 1,
	TEXTURE_PLANET_200x2000_LARGE = 2,
	TEXTURE_PLANET_200x2000_SMALL = 3,
	TEXTURE_PLANET_600x600_LARGE = 4,
	TEXTURE_PLANET_600x600_SMALL = 5;

	/**
	 * Objects
	 * (this might be changed at any time as they do not occur in the game data - just in rendering)
	 */
	int
	// backs
	OBJ_BACKGROUND = 1,
	OBJ_STAR = 2,
	OBJ_PLANET = 3,
	OBJ_STAR_CLIP = 4,
	OBJ_PLANET_CLIP = 5,
	OBJ_TECH_WHEEL = 6,

	// lines
	OBJ_BORDER = 10,
	OBJ_FOCUS_LINE = 11,
	OBJ_FOCUS_BOX = 12,
	OBJ_ROUTE = 13,
	OBJ_TIME_LINE = 14,

	// information
	OBJ_TEXT = 30,
	OBJ_ICON = 31,
	OBJ_KNOB = 32,
	OBJ_RING = 33
	;

	/**
	 * Icons
	 *
	 * (Icons do match most of the actual technologies/components but
	 * conceptually a component refers to its icon and color)
	 */
	int
	ICON_DAMPING_FIELD = 1,
	ICON_FORCE_FIELD = 2,
	ICON_CLOAKING_FIELD = 3,
	ICON_PARTICLE_FIELD = 4,
	ICON_DEFLECTOR_FIELD = 5,
	ICON_FIELD_CAPACITOR = 6,
	ICON_IMPULSE_GENERATOR = 7,

	ICON_STASIS_FIELD = 10,
	ICON_LASER = 11,
	ICON_MASS_GUN = 12,
	ICON_ROCKET = 13,
	ICON_PLASMA_TORPEDO = 14,
	ICON_DISRUPTOR = 15,
	ICON_TURBO = 16,
	ICON_TRACTOR = 17,
	ICON_PHASER = 18,
	ICON_PLASMA_CANNON = 19,
	ICON_PARTICLE_CANNON = 20,

	ICON_IMPULSE_DRIVE = 21,
	ICON_HYPER_DRIVE = 22,
	ICON_WARP_DRIVE = 23,
	ICON_ORBITAL_DRIVE = 24,

	ICON_REACTOR1 = 31,
	ICON_REACTOR2 = 32,
	ICON_REACTOR3 = 33,
	ICON_REACTOR4 = 34,
	ICON_REACTOR5 = 35,
	ICON_ENERGY = 36,
	ICON_ENERGY_OFF = 37,

	ICON_SELF_DESTRUCT = 40,
	ICON_REPAIR_DRONE = 41,
	ICON_TARGET_COMPUTER = 42,
	ICON_JAMMER = 43,

	ICON_SLOT = 50,
	ICON_BUILDING = 51,
	ICON_RESOURCE = 52,
	ICON_ROCK = 53

	;
}
