package ir.mine.project.base.excphndl.exceptionClient;

import java.io.Serializable;

public class BaseClientException extends Exception implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String identifier;
	private String[] i18nArgs = new String[]{};

	public BaseClientException() {
	}

	public BaseClientException(String messageKey) {
		super(messageKey);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String[] getI18nArgs() {
		return i18nArgs;
	}

	public void setI18nArgs(String[] i18nArgs) {
		this.i18nArgs = i18nArgs;
	}

}