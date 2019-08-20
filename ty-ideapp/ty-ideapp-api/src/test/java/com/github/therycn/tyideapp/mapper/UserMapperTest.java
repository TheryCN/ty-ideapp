package com.github.therycn.tyideapp.mapper;

import static org.mockito.BDDMockito.given;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.therycn.tyideapp.UserRegistration;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void to_UserRegistration_User() {
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
