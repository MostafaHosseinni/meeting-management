package ir.mine.project.base.Util;

import java.util.Collection;

public class CoreUtility {

	public static boolean isTrue(Boolean value) {
		if (value != null && Boolean.TRUE.equals(value)) {
			return true;
		}
		return false;
	}

	public static boolean isFalse(Boolean value) {
		if (value != null && Boolean.FALSE.equals(value)) {
			return true;
		}
		return false;
	}

	public static boolean isTrue(String value) {
		if (value == null || "".equals(value.trim())) {
			return false;
		}
		if ("TRUE".equals(value.toUpperCase())) {
			return true;
		}
		return false;
	}

	public static boolean isFalse(String value) {
		if (value == null || "".equals(value.trim())) {
			return false;
		}
		if ("FALSE".equals(value.toUpperCase())) {
			return true;
		}
		return false;
	}

	public static boolean isNotNull(Collection<?> list) {
		if (list == null || list.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean isNull(Collection<?> list) {
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isNotNull(String value) {
		if (value == null || "".equals(value.trim())) {
			return false;
		}
		return true;
	}

}
