package com.programmers.gccoffee.model;

import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@Getter
@EqualsAndHashCode
@ToString
public class Email {

    private final String address;

    public Email(String address) {
        Assert.notNull(address, "Address should not be Null.");
        Assert.isTrue(address.length() >= 4 && address.length() <= 50, "Address length must be between 4 and 50 characters.");
        Assert.isTrue(isValidAddress(address), "Invalid email address.");

        this.address = address;
    }

    private static boolean isValidAddress(String address) {
        return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", address);
    }
}
