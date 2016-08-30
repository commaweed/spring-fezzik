package fezzik.web.controller.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents a canned response the controller layer can send to the client.
 */
@JsonInclude(Include.NON_NULL)
public class FezzikResponse {
	
	private boolean success;
	private String message;
	private String exceptionType;
	
	/**
	 * Initialize; default constructor required by jackson.
	 */
	public FezzikResponse() {
		this(null, null);
	}
	
	/**
	 * Initialize using the provided parameters.
	 * @param success
	 * @param message
	 */
	public FezzikResponse(Boolean success, String message) {
		this.setSuccess(success);
		this.setMessage(message);
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the success to the given value.
	 * @param success the success value; if <code>null</code> it will set it to false.
	 */
	public void setSuccess(Boolean success) {
		this.success = success == null ? true : success;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the exceptionType
	 */
	public String getExceptionType() {
		return exceptionType;
	}

	/**
	 * @param exceptionType the exceptionType to set
	 */
	public void setExceptionType(String exceptionType) {
		if (exceptionType != null) {
			this.exceptionType = exceptionType;
		}
	}
	
	/**
	 * Returns a FezzikResponse to indicate a success.  The message is optional.
	 * @param message The message to return to the client, if any.
	 * @return A FezzikResponse representing a success.
	 */
	public static FezzikResponse getSuccessResponse(String message) {
		return new FezzikResponse(true, message);
	}
	
	/**
	 * Returns a FezzikResponse for a failure that occurred due to an exception. 
	 * @param cause The exception that occurred.  
	 * @return A FezzikResponse representing an error.
	 * @throws IllegalArgumentException If the required parameters are missing or invalid.
	 */
	public static FezzikResponse getExceptionResponse(Exception cause) {
		if (cause == null) {
			throw new IllegalArgumentException("Invalid cause; it cannot be null!");
		}
		
		FezzikResponse response = new FezzikResponse(false, cause.getMessage());
		response.setExceptionType(cause.getClass().getSimpleName());
		
		return response;
	}
	
	/**
	 * Returns a FezzikResponse for a failure that occurred.  The message is optional.
	 * @param message The message to return to the client, if any.
	 * @return A FezzikResponse representing a failure.
	 */
	public static FezzikResponse getFailureResponse(String message) {
		return new FezzikResponse(false, message);
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
	
}