package fezzik.web.controller.model;

import org.apache.commons.lang3.StringUtils;

public class FezzikResponse {
	
	private boolean success;
	private String message;
	
	/**
	 * Initialize; use this constructor for a response with no content as the body and a success of true.
	 */
	public FezzikResponse() {
		this(null, null);
	}
	
	/**
	 * Initialize with the given error message; success will be <code>false</code>.
	 * @param errorMessage The error message.
	 */
	public FezzikResponse(String errorMessage) {
		this(false, errorMessage);
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
		this.message = StringUtils.trimToNull(message) == null ? "NONE" : message;
	}
	
	
	
}