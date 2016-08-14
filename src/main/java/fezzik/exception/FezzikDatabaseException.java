package fezzik.exception;

/**
 * Represents the type of exception that will be thrown when an error occurs in the database code.
 */
// TODO: determine if passwords should expire after a certain date, and if so, change this to accomodate that type of exception as well.
public class FezzikDatabaseException extends RuntimeException {
	
	private static final long serialVersionUID = -9182990819193054161L;

	/**
	 * Initialize with the given userId; while it does except a <code>null</code> user, ensure it is not.
	 * @param message The error message.
	 */
	public FezzikDatabaseException(String message) {
		super(message);
	}
	
	/**
	 * Initialize with the database exception.
	 * @param cause The database exception.
	 */
    public FezzikDatabaseException(Throwable cause) {
        super(cause);
    }
	
	/**
	 * Initialize with an error message and the database exception.
	 * @param message The error message.
	 * @param cause The database exception.
	 */
    public FezzikDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }



}