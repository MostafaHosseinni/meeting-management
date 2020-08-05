package ir.mine.project.base.Util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Utility class which provides lookup services for Spring managed beans from
 * within unmanaged beans.
 */
@Component
public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext sApplicationContext;

	@Override
	public void setApplicationContext(ApplicationContext aApplicationContext)
			throws BeansException {
		setContext(aApplicationContext);
	}

	public static void setContext(ApplicationContext aApplicationContext) {
		sApplicationContext = aApplicationContext;
	}

	protected static ApplicationContext getApplicationContext() {
		return sApplicationContext;
	}

	public static Object getBean(String aName) {
		if (sApplicationContext != null) {
			return sApplicationContext.getBean(aName);
		}
		return null;
	}

	public static <T> T getBean(Class<T> aClass) {
		if (sApplicationContext != null) {
			return sApplicationContext.getBean(aClass);
		}
		return null;
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> aClass) {
		if (sApplicationContext != null) {
			return sApplicationContext.getBeansOfType(aClass);
		}
		return null;
	}
}
