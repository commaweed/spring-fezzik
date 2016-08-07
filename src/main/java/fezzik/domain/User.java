package fezzik.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@Slf4j
public class User implements Serializable {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName) {
        log.info("Creating user object...");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, firstName='%s', lastName='%s']", id,
                firstName, lastName);
    }

}