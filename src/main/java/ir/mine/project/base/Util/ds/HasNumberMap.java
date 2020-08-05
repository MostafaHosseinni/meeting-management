package ir.mine.project.base.Util.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HasNumberMap<V> extends HashMap<String, V> {

	private static final long serialVersionUID = 1L;

	public List<V> getValuesByBaseKey(String baseKey) {
		List<V> result = new ArrayList<V>();
		for (String key : keySet()) {
			if (key.startsWith(baseKey)) {
				result.add(get(key));
			}
		}
		return result;
	}
	
	public List<String> getKeysByBaseKey(String baseKey) {
		List<String> result = new ArrayList<String>();
		for (String key : keySet()) {
			if (key.startsWith(baseKey)) {
				result.add(key);
			}
		}
		return result;
	}

	
}
