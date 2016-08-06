package fezzik.component.domain.user;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A fezzik user.
 */
@Document(collection = "users")
public class FezzikUser {
	
	@Id
	private String userId;
	
	@Field(value="fullname")
	private String name;
	
	/**
	 * Initializer; default constructor required by mongo api.
	 */
	public FezzikUser() {}
	
	/**
	 * Initialize with the given required name.
	 * @param name The name of the user.
	 * @throws IllegalArgumentException if any of the required parameters are invalid.
	 */
	public FezzikUser(String name) {
		if (StringUtils.trimToNull(name) == null) {
			throw new IllegalArgumentException("Invalid name; it cannot be null!");
		}
		
		this.name = name;
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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