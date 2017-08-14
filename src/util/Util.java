package util;

import java.util.Collection;

public class Util {
	public static <T> boolean listContainsClass(Collection<?> arrayList, Class<T> clazz) {
		for (Object o : arrayList) {
			if (o != null && o.getClass() == clazz) {
				return true;
			}
		}
		return false;
	}
}
