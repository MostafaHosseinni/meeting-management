package ir.mine.project.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-eApp-alert", message);
        headers.add("X-eApp-params", param);
        return headers;
    }
    
    public static HttpHeaders createAlert(String exceptionType, String erorrType, String entityName, String param) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-eApp-exceptionType", exceptionType);
		headers.add("X-eApp-erorrType", erorrType);
		headers.add("X-eApp-entityName", entityName);
		headers.add("X-eApp-param", param);
		return headers;
	}

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert("A new " + entityName + " is created with identifier " + param, param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert("A " + entityName + " is updated with identifier " + param, param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert("A " + entityName + " is deleted with identifier " + param, param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-eApp-error", defaultMessage);
        headers.add("X-eApp-params", entityName);
        return headers;
    }
}
