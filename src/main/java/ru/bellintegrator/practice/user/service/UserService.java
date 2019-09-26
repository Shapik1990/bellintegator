package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.dto.UserDto;
import ru.bellintegrator.practice.view.DataResponseView;


public interface UserService {
    DataResponseView getUsersListByFilter(UserDto userDto);

    DataResponseView getUserById(int id);

    void update(UserDto userDto);

    void save(UserDto userDto);

}
