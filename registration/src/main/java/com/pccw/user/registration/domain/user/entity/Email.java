package com.pccw.user.registration.domain.user.entity;

import lombok.Data;
import lombok.Setter;
import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.GenericValidator;

@Setter
public class Email {

    public static final int MAX_LENGTH_OF_EMAIL = 100;

    public Email(String email){
        this.email=email;
    }

    public String getEmailAddress() {
        return email;
    }

    private String email;

    /**
     * length of email cannot over 100 character
     * EmailValidator provides email address validation according to RFC 822 standards.
     *
     * Uppercase and lowercase letters
     * The digits 0 through 9
     * The characters, !#$%&'*+-/=?^_`{|}~
     * The character "." provided that it is not the first or last character in the local-part.
     *
     * Matches:
     *
     * a&d@somedomain.com
     * a*d@somedomain.com
     * a/d@somedomain.com
     * Non-Matches:
     *
     * .abc@somedomain.com
     * abc.@somedomain.com
     * a>b@somedomain.com
     *
     * @return
     */
    public boolean isValid(){

        return isValid(this.email);
    }

    public static boolean isValid(String email) {

        return  EmailValidator.getInstance().isValid(email)
                &&  GenericValidator.maxLength(email, MAX_LENGTH_OF_EMAIL)
                ;
    }
}
