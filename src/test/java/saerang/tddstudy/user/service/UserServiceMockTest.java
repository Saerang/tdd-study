package saerang.tddstudy.user.service;

import org.junit.jupiter.api.Test;
import saerang.tddstudy.user.api.UserService;
import saerang.tddstudy.user.domain.User;
import saerang.tddstudy.user.repository.UserRepository;

import static org.mockito.Mockito.*;

public class UserServiceMockTest {

    @Test
    void addUser() {
        //given
        User user = new User("saerang", "tdd-master");

        //when
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new DefaultUserService(userRepository);

        when(userRepository.save(user)).thenReturn(user);

        userService.addUser(user);

        //then
        verify(userRepository).save(any());
    }
}
