package ir.mine.project.web.rest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ir.mine.project.web.rest.util.HeaderUtil;

@ControllerAdvice
@RestController
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Void> handleException(Exception ex) {

		String exceptionType = "un";
		String errorType = ex.getMessage();

		if (ex instanceof DataIntegrityViolationException) {
			exceptionType = "db";
			DataIntegrityViolationException e = (DataIntegrityViolationException) ex;
			if (e.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
				String message = cve.getSQLException().getMessage();

				if (message.contains("NULL"))
					errorType = "notNull";
				else if (message.contains("UNIQUE") || message.contains("Duplicate"))
					errorType = "uniq";
				else
					errorType = "un";
			}

		}

		if (ex instanceof AccessDeniedException) {
			exceptionType = "ac";
			errorType = "ac";
		}

		ex.printStackTrace();
		return ResponseEntity.status(500).headers(HeaderUtil.createAlert(exceptionType, errorType, null, null))
				.body(null);

	}
}
