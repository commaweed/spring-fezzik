package fezzik.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a fezzik user.
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = -2751305641698104649L;
	
	@Id
    private final String uuid;
    
    private String password;
    private final String firstName;
    private final String lastName;
    
    @Version
    private Long version;
    
    @CreatedDate
    private Date createdDate;
    
    @LastModifiedDate
    private Date lastModifiedDate;

    /**
     * Default constructor used by spring-data.  Since spring can use reflection to set members, use alternate 
     * method to validate.
     */
    public User() {
    	this.uuid = null;
    	this.firstName = null;
    	this.lastName = null;
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
	@JsonIgnore
	@JsonProperty(value="encryptedPassword")
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * @param createdDate the createdDate to set
	 */
	@JsonIgnore
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	@JsonIgnore
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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
    	ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE) {
    		@Override
            protected boolean accept(Field field) {
                return !field.getName().equals("password") && !field.getName().equals("serialVersionUID");
            }
    	};
    	
        return builder.toString();
    }
    
    /**
     * Validates this object, which can allow nulls.
     */
    public void validate() {
    	if (StringUtils.trimToNull(this.uuid) == null) {
    		throw new IllegalArgumentException("Invalid uuid; it cannot be null.");
    	}
    	if (StringUtils.trimToNull(this.lastName) == null) {
    		throw new IllegalArgumentException("Invalid lastName; it cannot be null.");
    	}
    	if (StringUtils.trimToNull(this.firstName) == null) {
    		throw new IllegalArgumentException("Invalid firstName; it cannot be null.");
    	}
    	if (StringUtils.trimToNull(this.password) == null) {
    		throw new IllegalArgumentException("Invalid password; it cannot be null.");
    	}
    }
}