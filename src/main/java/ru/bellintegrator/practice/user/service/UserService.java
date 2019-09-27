package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.dto.UserDto;

import java.util.List;


public interface UserService {
    /**
     * Получить список всех пользователей по фильтру
     *
     * @param userDto данные фильтров обернутые в {@link UserDto}
     * @return список {@link UserDto}
     */
    List<UserDto> getUsersListByFilter(UserDto userDto);

    /**
     * Получить пользователя по идентификатору
     *
     * @param id идентификатор пользователя
     * @return {@link UserDto}
     */
    UserDto getUserById(int id);

    /**
     * Обновить данные пользователя
     *
     * @param userDto данные для обновления обернутые в {@link UserDto}
     */
    void update(UserDto userDto);

    /**
     * Сохранить нового пользователя
     *
     * @param userDto данные для сохранения обернутые в {@link UserDto}
     */
    void save(UserDto userDto);

}
