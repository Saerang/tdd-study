package saerang.tddstudy.user.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String name;

    protected User() {
    }


    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    void changeName(String name) {
        this.name = name;
    }
}
