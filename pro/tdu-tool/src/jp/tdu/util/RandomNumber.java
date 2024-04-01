package jp.tdu.util;

import java.util.Random;

public final class RandomNumber{

	private static final Random random = new Random();

	public static int nextInt(int number){
		return random.nextInt(number);
	}
}
