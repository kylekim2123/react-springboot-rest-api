package com.programmers.gccoffee.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class EmailTest {

    @Test
    void testInvalidEmail() {
        assertThatThrownBy(() -> new Email("invalid-email-format"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testValidEmail() {
        Email email = new Email("test@naver.com");
        assertThat(email.getAddress()).isEqualTo("test@naver.com");
    }

    @Test
    void testEqualEmail() {
        Email email1 = new Email("test@naver.com");
        Email email2 = new Email("test@naver.com");
        assertThat(email1.getAddress()).isEqualTo(email2.getAddress());
    }


}