package saerang.tddstudy.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import saerang.tddstudy.user.api.UserService;
import saerang.tddstudy.user.domain.User;
import saerang.tddstudy.user.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class UserServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    EntityManager em;

    @Test
    void addUser() {
        //given
        User user = new User("saerang", "tdd-master");
        assertThat(user.getId()).isNull();

        //when
        userService.addUser(user);
        assertThat(user.getId()).isNotNull();

        em.flush();
        em.clear();

        User user1 = userRepository.findById(user.getId()).get();

        //then
        assertThat(user.getId()).isEqualTo(user1.getId());
    }
}
