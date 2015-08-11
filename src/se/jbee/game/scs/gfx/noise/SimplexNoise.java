package se.jbee.game.scs.gfx.noise;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SimplexNoise {

	private final SimplexOctave[] octaves;
	private final double[] frequencys;
	private final double[] amplitudes;

	public SimplexNoise(int largestFeature, double persistence, int seed) {
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

	public static BufferedImage image(int w, int h, int size, int depth, int seed, float alpha, float limit, int rgbMask) {
		SimplexNoise noise =new SimplexNoise(size,depth/100f, seed);
		BufferedImage image = new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
	
		int[] pixels = new int[w * h];
		int k = 0;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				// the noiseAt results in -1.0 to 1.0; the below does shift it to 0.0 to 1.0
				float rgb = 0.5f*(1f+(float)noise.noiseAt(x, y));
				rgb = min(1f, max(0f, rgb));
				if (rgb < limit) {
					rgb = 0;
				}
				Color c = new Color(rgb, rgb, rgb, alpha);
				pixels[k++] = c.getRGB() & rgbMask;
			}
		}
		image.getRaster().setDataElements(0,  0,  w, h, pixels);
		return image;
	}

}
