package saerang.tddstudy.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    void createUser() {
        //given
        String userId = "saerang";

        //when
        User user = new User(userId, "name");

        //then
        assertThat(user.getUserId()).isEqualTo(userId);
    }

    @Test
    void changeUserName() {
        //given
        String beforeName = "before";
        String afterName = "after";
        User user = new User("userId", beforeName);
        assertThat(user.getName()).isEqualTo(beforeName);

        //when
        user.changeName(afterName);

        //then
        assertThat(user.getName()).isEqualTo(afterName);
    }

}
