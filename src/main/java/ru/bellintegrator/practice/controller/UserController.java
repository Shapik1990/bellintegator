package ru.bellintegrator.practice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dto.UserDto;
import ru.bellintegrator.practice.service.user.UserService;
import ru.bellintegrator.practice.validation.DtoByFilter;
import ru.bellintegrator.practice.validation.DtoSave;
import ru.bellintegrator.practice.validation.DtoUpdate;
import ru.bellintegrator.practice.validation.ShowDto;
import ru.bellintegrator.practice.validation.ShowDtoFull;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.SuccessResponceView;


@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @JsonView(ShowDto.class)
    @PostMapping(value = "/list" )
    public DataResponseView<UserDto> getUsersList(@Validated(DtoByFilter.class) @RequestBody UserDto userDto){
        return userService.getUsersListByFilter(userDto);
    }

    @JsonView(ShowDtoFull.class)
    @GetMapping(value = "/{id:\\d+}")
    public DataResponseView<UserDto> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/update")
    public SuccessResponceView updateUser(@Validated (DtoUpdate.class) @RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    @PostMapping(value = "/save")
    public SuccessResponceView saveUser(@Validated (DtoSave.class) @RequestBody UserDto userDto){
        return userService.save(userDto);
    }
}
