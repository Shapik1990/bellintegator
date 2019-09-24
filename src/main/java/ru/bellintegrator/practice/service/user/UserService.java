package ru.bellintegrator.practice.service.user;

import ru.bellintegrator.practice.dto.UserDto;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.SuccessResponceView;

public interface UserService {
    DataResponseView getUsersListByFilter(UserDto userDto);

    DataResponseView getUserById(int id);

    SuccessResponceView update(UserDto userDto);

    SuccessResponceView save(UserDto userDto);

}
