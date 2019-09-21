package ru.bellintegrator.practice.service.user;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dto.UserDto;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.SuccessResponceView;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public DataResponseView<UserDto> getUsersListByFilter(UserDto userDto) {
        return null;
    }

    @Override
    public DataResponseView<UserDto> getUserById(int id) {
        return null;
    }

    @Override
    public SuccessResponceView update(UserDto userDto) {
        return null;
    }

    @Override
    public SuccessResponceView save(UserDto userDto) {
        return null;
    }
}
