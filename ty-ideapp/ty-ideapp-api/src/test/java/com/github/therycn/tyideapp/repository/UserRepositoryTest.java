package com.github.therycn.tyideapp.repository;

import com.github.therycn.tyideapp.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepo;

    private User user;

    @Before
    public void setUp() {
        user = new User(null, "Thery", "thery@github.com", "ChangeIt#01Encoded");
        user = entityManager.persist(user);
    }

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // Given
        // When
        Optional<User> requestedUser = userRepo.findByUsername(user.getUsername());

        // Then
        Assertions.assertThat(requestedUser.isPresent()).isTrue();
        Assertions.assertThat(requestedUser.get()).isEqualTo(user);
    }

    @Test
    public void whenUpdatePassword_thenReturnOne() {
        // Given
        String newPassword = "ChangeIt#02Encoded";

        // When
        int result = userRepo.updatePassword(user.getId(), newPassword);

        // Then
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void whenUpdatePassword_thenReturnZero() {
        // Given
        String newPassword = "ChangeIt#02Encoded";

        // When
        int result = userRepo.updatePassword(-1L, newPassword);

        // Then
        Assertions.assertThat(result).isEqualTo(0);
    }

}
