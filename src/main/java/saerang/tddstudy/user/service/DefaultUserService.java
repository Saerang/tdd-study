package saerang.tddstudy.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import saerang.tddstudy.user.domain.User;
import saerang.tddstudy.user.repository.UserRepository;
import saerang.tddstudy.user.api.UserService;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
