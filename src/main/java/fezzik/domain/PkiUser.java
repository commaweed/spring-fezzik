package fezzik.domain;

import java.io.Serializable;
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
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a fezzik user.
 * Note: This file was gutted because we changed from basic auth to pki.  Could put roles in here, etc.
 */
@Document
public class PkiUser implements Serializable {
	
	private static final long serialVersionUID = 3720252696715612849L;

	@Id
    private final String uuid;
    
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
    public PkiUser() {
    	this.uuid = null;
    }

    /**
     * Initialize with the required fields.
     * @param uuid The ID of the user.
     */
    public PkiUser(
    	String uuid
    ) {
    	this.uuid = uuid;
    }

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
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
    	return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    /**
     * Validates this object, which can allow nulls.
     */
    public void validate() {
    	if (StringUtils.trimToNull(this.uuid) == null) {
    		throw new IllegalArgumentException("Invalid uuid; it cannot be null.");
    	}
    }
}