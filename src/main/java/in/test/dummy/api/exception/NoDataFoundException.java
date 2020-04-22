package in.test.dummy.api.exception;

public class NoDataFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoDataFoundException(Throwable throwable) {
		super(throwable);
	}
	
	public NoDataFoundException(String message) {
		super(message);
	}
}
