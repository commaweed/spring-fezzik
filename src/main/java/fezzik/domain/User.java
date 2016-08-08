package fezzik.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements Serializable {
	
	private static final long serialVersionUID = -2751305641698104649L;
	
	@Id
    private final String uuid;
    
    @JsonIgnore
    private final String password;
    
    private final String firstName;
    private final String lastName;

    /**
     * Default constructor used by spring-data.
     */
    public User() {
    	this.uuid = null;
    	this.firstName = null;
    	this.lastName = null;
    	this.password = null;
    }

    /**
     * Initialize with the required fields.
     * @param 
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param password The encrypted password of the user.
     */
    public User(
    	String uuid,
		String firstName, 
		String lastName,
		String password
    ) {
    	this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
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