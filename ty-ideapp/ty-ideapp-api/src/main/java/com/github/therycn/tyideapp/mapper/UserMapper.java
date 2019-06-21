package com.github.therycn.tyideapp.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.github.therycn.tyideapp.UserInfo;
import com.github.therycn.tyideapp.UserRegistration;
import com.github.therycn.tyideapp.UserUpdate;
import com.github.therycn.tyideapp.entity.User;

/**
 * User Mapper.
 * 
 * @author tcharass
 *
 */
@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

	UserInfo to(User user);

	User to(UserRegistration userRegistration);

	User updateUser(UserUpdate userUpdate, @MappingTarget User existingUser);

}
