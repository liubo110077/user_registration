package com.pccw.user.registration.domain.user.entity;

import com.pccw.user.registration.domain.user.repository.UserRepositoryInterface;
import com.pccw.user.registration.infrastructure.mq.EmailNotification;
import com.pccw.user.registration.infrastructure.mq.MessageQueueProducer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;


public class UserTest {
    @Before
    public void init()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private UserRepositoryInterface userDao;

    @Mock
    private MessageQueueProducer messageQueueProducer;


    @Test
    public void registerUserWithInvalidEmail(){
        final User user = new UserFactory(userDao,messageQueueProducer).create(".jack@sina.cn","password");

        try
        {
            final User register = user.register();
            fail();
        } catch (Exception e)
        {
            assertThat(e, instanceOf(IllegalArgumentException.class));
        }

    }

    @Test
    public void registerUser(){
        final User user = new UserFactory(userDao,messageQueueProducer).create("jack@sina.cn","password");
        when(userDao.save(user)).thenReturn(new User(1,"jack@sina.cn"));
        when(messageQueueProducer.offer(  EmailNotification.to("jack@sina.cn"))).thenReturn(true);
        final User register = user.register();
        assertThat(register.getEmail().getEmailAddress(), equalTo("jack@sina.cn"));
        assertThat(register.isActive(), equalTo(true));
        assertThat(register.getId(), equalTo(1));
    }

    @Test
    public void registerUserWhenCantSendEmailDoNotBreakProcess(){
        final User user = new UserFactory(userDao,messageQueueProducer).create("jack@sina.cn","password");
        when(userDao.save(user)).thenReturn(new User(1,"jack@sina.cn"));
        when(messageQueueProducer.offer(  EmailNotification.to("jack@sina.cn"))).thenReturn(false);
        final User register = user.register();
        assertThat(register.getEmail().getEmailAddress(), equalTo("jack@sina.cn"));
        assertThat(register.isActive(), equalTo(true));
        assertThat(register.getId(), equalTo(1));
    }


    @Test
    public void deleteUser(){
        int userId=1;
        final User user = new UserFactory(userDao,messageQueueProducer).create(userId,"jack@sina.cn");
        when(userDao.getById(userId)).thenReturn(user);
        user.delete();

        assertThat(user.getEmail().getEmailAddress(), equalTo("jack@sina.cn"));
        assertThat(user.isActive(), equalTo(false));
        assertThat(user.getId(), equalTo(1));
    }

    @Test
    public void changeEmail(){
        int userId=1;
        final User originalUser = new UserFactory(userDao,messageQueueProducer).create(userId,"jack@sina.cn");
        when(userDao.update(originalUser)).thenReturn(originalUser);
        final User user = originalUser.changeEmailAddress("to@sina.cn");

        assertThat(user.getEmail().getEmailAddress(), equalTo("to@sina.cn"));
        assertThat(user.isActive(), equalTo(true));
        assertThat(user.getId(), equalTo(userId));

    }

    @Test
    public void changeEmailWithInvalidEmail(){
        final User user = new UserFactory(userDao,messageQueueProducer).create("jack@sina.cn","password");

        try
        {
            final User register = user.changeEmailAddress(".to@sina.cn");
            fail();
        } catch (Exception e)
        {
            assertThat(e, instanceOf(IllegalArgumentException.class));
        }

    }
}
