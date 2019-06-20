package com.github.therycn.tyideapp.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.github.therycn.tyideapp.UserInfo;
import com.github.therycn.tyideapp.UserSave;
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

	User to(UserSave userSave);

	User updateUser(UserSave userSave, @MappingTarget User existingUser);

}
