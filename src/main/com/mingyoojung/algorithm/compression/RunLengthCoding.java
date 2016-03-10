package com.mingyoojung.algorithm.compression;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class RunLengthCoding {

	private static byte[] compress(byte[] raw) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int p = 0;
		byte run = 0;

		for (byte r : raw) {
			for (int i = 7; i >= 0; i--) {
				if (((r >> i) & 1) != p) {
					out.write(run);
					run = 1;
					p = Math.abs(p - 1);
				} else {
					run++;
				}
			}
		}

		out.write(run);

		return out.toByteArray();
	}

	private static byte[] uncompress(byte[] compressed) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int bit = 0;

		int buf = 0;
		int pos = 7;

		for(byte b : compressed) {
			for(int i = 0; i < b; i++) {
				buf = buf | (bit << pos--);

				if(pos < 0) {
					out.write(buf);
					buf = 0;
					pos = 7;
				}
			}
			bit = Math.abs(bit - 1);
		}

		if(pos != 7) {
			out.write(buf);
		}

		return out.toByteArray();
	}

	public static void main(String[] args) throws Exception {

		byte[] raw = new byte[]{
				0, 0, -128, 127
		};

		System.out.println("raw : " + Arrays.toString(raw));

		byte[] compressed = RunLengthCoding.compress(raw);

		System.out.println("cmp : " + Arrays.toString(compressed));

		byte[] uncompressed = RunLengthCoding.uncompress(compressed);

		System.out.println("ucp : " + Arrays.toString(uncompressed));
	}
}
