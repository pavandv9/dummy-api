package in.test.dummy.api.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class NoDataFoundException.
 */
public class NoDataFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new no data found exception.
	 *
	 * @param throwable the throwable
	 */
	public NoDataFoundException(Throwable throwable) {
		super(throwable);
	}
	
	/**
	 * Instantiates a new no data found exception.
	 *
	 * @param message the message
	 */
	public NoDataFoundException(String message) {
		super(message);
	}
}
