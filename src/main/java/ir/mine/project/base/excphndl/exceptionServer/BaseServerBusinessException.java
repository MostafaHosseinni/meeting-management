package ir.mine.project.base.excphndl.exceptionServer;

public class BaseServerBusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private String messageKey;

	public BaseServerBusinessException() {
	}

	public BaseServerBusinessException(String msg) {
		super(msg);
		messageKey = msg;
	}

	public BaseServerBusinessException(String msg, Throwable e) {
		super(msg, e);
		messageKey = msg;
	}
	
	public BaseServerBusinessException(Throwable e) {
		super(e);
		messageKey = "DEFAULT_MSG";
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

}
