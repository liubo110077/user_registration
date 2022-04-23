package com.pccw.user.registration.domain.user.entity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class EmailTest {

    @Test
    public void validEmail(){
        final boolean valid = new Email("jack@sina.cn").isValid();
        assertThat(valid, equalTo(true));
    }

    @Test
    public void invalidEmail(){
        final boolean valid = new Email("@sina.cn").isValid();
        assertThat(valid, equalTo(false));
    }

    @Test
    public void invalidEmailStartWithDot(){
        final boolean valid = new Email(".hello@sina.cn").isValid();
        assertThat(valid, equalTo(false));
    }

    @Test
    public void invalidEmailWithoutSuffix(){
        final boolean valid = new Email("adfasfd@").isValid();
        assertThat(valid, equalTo(false));
    }

    @Test
    public void invalidEmailWithoutAt(){
        final boolean valid = new Email("adfasfd.sina.cn").isValid();
        assertThat(valid, equalTo(false));
    }

    @Test
    public void validEmailLengthIs100(){
        final boolean valid = new Email("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890@sina.comm").isValid();
        assertThat(valid, equalTo(true));
    }
    @Test
    public void invalidEmailLengthIs101(){
        final boolean valid = new Email("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890@sina.com.c").isValid();
        assertThat(valid, equalTo(false));
    }

    @Test
    public void invalidEmailWithChinese(){
        final boolean valid = new Email("你好@sina.com").isValid();
        assertThat(valid, equalTo(false));
    }
}
