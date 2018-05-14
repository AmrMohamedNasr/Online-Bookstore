package utils;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class ParseUtils {
	public static Integer IntegerParse(String t, boolean init, AtomicBoolean error) {
		String x = t != null && !t.trim().isEmpty()? t.trim():null;
		error.set(false);
		if (x == null && init) {
			return 0;
		} else if (x == null) {
			error.set(true);
			return null;
		}
		if (isNumeric(x)) {
			return Integer.parseInt(x);
		} else {
			error.set(true);
			return -1;
		}
	}
	public static Float FloatParse(String t, boolean init,  AtomicBoolean error) {
		String x = t != null && !t.trim().isEmpty()? t.trim():null;
		error.set(false);
		if (x == null && init) {
			return 0f;
		} else if (x == null) {
			error.set(true);
			return null;
		}
		if (isFloat(x)) {
			return Float.parseFloat(x);
		} else {
			error.set(true);
			return -1f;
		}
	}
	public static boolean isNumeric(String x) {
		return x != null && x.matches("^[0-9]+$");
	}
	public static boolean isFloat(String x) {
		return x != null && x.matches("^[0-9]+(.[0-9]+)?$");
	}
	@SuppressWarnings("unchecked")
	public static void doJsonResponse(JSONObject obj, HttpServletResponse resp, int code, String message)
			throws IOException {
		obj.put("code", code);
        obj.put("message", message);
        resp.setContentType("application/json");
        resp.getWriter().write(obj.toJSONString());
        resp.getWriter().close();
	}
}
