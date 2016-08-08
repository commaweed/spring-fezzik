package fezzik.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;

public class User implements Serializable {
	
	private static final long serialVersionUID = -2751305641698104649L;
	
	@Id
    private String id;
    private String firstName;
    private String lastName;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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