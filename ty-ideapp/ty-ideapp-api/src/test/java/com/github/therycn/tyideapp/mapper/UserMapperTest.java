package com.github.therycn.tyideapp.mapper;

import com.github.therycn.tyideapp.UserRegistration;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

/**
 * Test class {@link UserMapper}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void whenTo_UserRegistration_thenReturnUser() {
        // Given
        User expectedUser = new User(null, "Thery", "thery@github.com", "ChangeIt#01");

        UserRegistration userRegistration = new UserRegistration("Thery", "thery@github.com", "ChangeIt#01");
        given(userService.encodePassword(userRegistration.getPassword())).willReturn(userRegistration.getPassword());

        // When
        User user = userMapper.to(userRegistration);

        // Then
        Assertions.assertThat(user).isEqualTo(expectedUser);
    }

}
