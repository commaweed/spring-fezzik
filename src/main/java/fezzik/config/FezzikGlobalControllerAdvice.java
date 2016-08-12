package fezzik.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import fezzik.exception.InvalidPasswordException;
import fezzik.exception.UserNotFoundException;
import fezzik.web.controller.model.FezzikResponse;

/**
 * All internal code will be applied to all of the controllers.
 */
@ControllerAdvice
public class FezzikGlobalControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(FezzikGlobalControllerAdvice.class);
	
    /**
     * Indicates whether the given exception is one of the exceptions that can be expected (i.e. we don't want to 
     * log them at all or they have already been logged).
     * @param cause The exception that was thrown.
     * @return <code>true</code> if the given exception is a known type.
     */
    private boolean isKnownExceptionType(Exception cause) {
    	List<Class<?>> knownTypes = new ArrayList<>();
    	
    	knownTypes.add(UserNotFoundException.class);
    	knownTypes.add(InvalidPasswordException.class);
    	knownTypes.add(IllegalArgumentException.class);

    	return knownTypes.contains(cause.getClass());
    }
    
    /**
     * For any controller method that throws UserNotFoundException, cause it to return a 200 status code with the error message.
     * @param response A fezzik response that will get mashalled to the client.
     * @param e The allowable fezzik exception that occurred.
     * @return a success=false, errorMessage=e.getMessage() response.
     * @throws IOException
     * TODO: move to @ControllerAdvice global class or GlobalControllerExceptionHandler.handleError()
     */
//    @ExceptionHandler( { UserNotFoundException.class, IllegalArgumentException.class, InvalidPasswordException.class } )
    @ExceptionHandler( Exception.class )
    @ResponseBody
    private FezzikResponse handleFezzikAllowableExceptions(HttpServletResponse response, Exception e) throws IOException {
    	if (!isKnownExceptionType(e)) {
    		LOGGER.error("Unexpected/Untrapped Exception occurred", e);
    	}

    	return FezzikResponse.getExceptionResponse(e);
    }
}