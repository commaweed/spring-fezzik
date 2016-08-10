package fezzik.web.controller.model;

import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents a wrapper for a spring rest error.  It will get turned into JSON.
 */
public class WebAttributeError {

    private final int httpStatusCode;
    private final String error;
    private final String message;
    private final String timeStamp;
    private String trace;

    /**
     * Initialize with the given values.
     * @param httpStatusCode
     * @param errorAttributes
     * @param includeStackTrace Indicates whether or not to include the stack trace in the output.
     */
    public WebAttributeError(
    	int httpStatusCode, 
    	Map<String, Object> errorAttributes,
    	boolean includeStackTrace
    ) {
    	if (errorAttributes == null || errorAttributes.size() == 0) {
    		throw new IllegalArgumentException("Invalid map errorAttributes; it cannot be null and must have size > 0!");
    	}
    	
        this.httpStatusCode = httpStatusCode;
        this.error = (String) errorAttributes.get("error");
        this.message = (String) errorAttributes.get("message");
        
        // TODO: maybe ensure this cannot be null; i think it cannot (and provide formatting for it)
        this.timeStamp = errorAttributes.get("timestamp").toString();
        
        if (includeStackTrace) {
        	this.trace = (String) errorAttributes.get("trace");
        }
    }
	
	/**
	 * @return the trace
	 */
	public String getTrace() {
		return trace;
	}

	/**
	 * @param trace the trace to set
	 */
	public void setTrace(String trace) {
		this.trace = trace;
	}

	/**
	 * @return the httpStatusCode
	 */
	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
            return EqualsBuilder.reflectionEquals(this, other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    
}