package se.jbee.game.common.gfx;

import java.util.Random;

public class Noise {

	private final SimplexOctave[] octaves;
	private final double[] frequencys;
	private final double[] amplitudes;

	public Noise(int largestFeature, double persistence, int seed) {
		// recieves a number (eg 128) and calculates what power of 2 it is (e.g. 2^7)
		int nOctaves = (int) Math.ceil(Math.log10(largestFeature) / Math.log10(2));

		octaves = new SimplexOctave[nOctaves];
		frequencys = new double[nOctaves];
		amplitudes = new double[nOctaves];

		Random rnd = new Random(seed);

		for (int i = 0; i < nOctaves; i++) {
			octaves[i] = new SimplexOctave(rnd.nextInt());
			frequencys[i] = Math.pow(2, i);
			amplitudes[i] = Math.pow(persistence, octaves.length - i);
		}
	}

	public double noiseAt(int x, int y) {
		double res = 0d;
		for (int i = 0; i < octaves.length; i++) {
			res = res + octaves[i].noise(x / frequencys[i], y / frequencys[i]) * amplitudes[i];
		}
		return res;
	}

}
