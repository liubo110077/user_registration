package com.pccw.user.registration.infrastructure.mq;

import lombok.Data;

@Data
public class EmailNotification  implements Message {

    private String from = EmailTemplate.DEFAULT_FROM;
    private String to ;
    private String title =EmailTemplate.DEFAULT_TITLE;
    private String content = EmailTemplate.DEFAULT_CONTENT;

    private EmailNotification(String to){
        this.to=to;
    }

    public static  EmailNotification to( String to ){
        return new EmailNotification(to);

    }


}
