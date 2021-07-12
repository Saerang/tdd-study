package saerang.tddstudy.user.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import saerang.tddstudy.user.domain.User;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestEntityManager em;

    @Test
    void testAddUser() {
        //given
        User user = new User("saerang", "tdd-master");
        assertThat(user.getId()).isNull();

        //when
        userRepository.save(user);
        assertThat(user.getId()).isGreaterThan(0);

        userRepository.flush();
        em.clear();

        User user2 = userRepository.findById(user.getId()).get();

        //then
        assertThat(user.getId()).isEqualTo(user2.getId());
    }
}
