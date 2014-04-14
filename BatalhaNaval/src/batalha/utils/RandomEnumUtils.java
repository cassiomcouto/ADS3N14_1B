package batalha.utils;

import java.util.Random;

public class RandomEnumUtils {
	
	public static <T extends Enum<?>> T random(Class<T> clazz){
        final int x = new Random().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
	
}
