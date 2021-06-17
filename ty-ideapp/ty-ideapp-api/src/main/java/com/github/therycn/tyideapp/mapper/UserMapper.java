package com.github.therycn.tyideapp.mapper;

import com.github.therycn.tyideapp.UserInfo;
import com.github.therycn.tyideapp.UserRegistration;
import com.github.therycn.tyideapp.entity.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * User Mapper.
 */
@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    UserInfo to(User user);

    User to(UserRegistration userRegistration);

}
