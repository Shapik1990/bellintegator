package ru.bellintegrator.practice.user.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.user.dto.UserDto;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.ErrorResponseView;
import ru.bellintegrator.practice.view.SuccessResponseView;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void getUserByIdTest() {
        Integer id = 1;

        ResponseEntity<DataResponseView<UserDto>> responseEntity = testRestTemplate.exchange("/user/" + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<DataResponseView<UserDto>>(){});

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(responseEntity.getBody().getData().getId(), id);
    }

    @Test
    public void getUserByIdTestError() {
        int id = 10;
        String errorNotEntity = "Не найден пользователь с id " + id;

        ResponseEntity<ErrorResponseView> responseNotEntityById = testRestTemplate.getForEntity("/user/" + id, ErrorResponseView.class);

        Assert.assertNotNull(responseNotEntityById.getBody());
        Assert.assertEquals(responseNotEntityById.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertEquals(responseNotEntityById.getBody().getError(), errorNotEntity);

        ResponseEntity<ErrorResponseView> responseEntityWrongId = testRestTemplate.getForEntity("/user/fdf", ErrorResponseView.class);

        Assert.assertEquals(responseEntityWrongId.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertEquals(responseEntityWrongId.getBody().getError(), "Not Found");
    }

    @Test
    public void getByUsersListTest() {
        UserDto dto = new UserDto();
        dto.setOfficeId(1);

        ResponseEntity<DataResponseView<List<UserDto>>> responseEntity = testRestTemplate.exchange("/user/list",
                HttpMethod.POST, new HttpEntity<>(dto) , new ParameterizedTypeReference<DataResponseView<List<UserDto>>>(){});

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertTrue(!responseEntity.getBody().getData().isEmpty());
    }

    @Test
    public void getByUsersListTestError() {
        UserDto dto = new UserDto();
        String errorValidated = "Ошибка валидации : должно быть задано officeId;";
        String errorNotFound = "Пользователи с указанными параметрами не найдены";

        ResponseEntity<ErrorResponseView> responseEntityValidated = testRestTemplate.postForEntity("/user/list", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntityValidated.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertNotNull(responseEntityValidated.getBody());
        Assert.assertEquals(responseEntityValidated.getBody().getError(), errorValidated);

        dto.setOfficeId(10);

        ResponseEntity<ErrorResponseView> responseNotEntityByFilter = testRestTemplate.postForEntity("/user/list", dto, ErrorResponseView.class);

        Assert.assertEquals(responseNotEntityByFilter.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotNull(responseNotEntityByFilter.getBody());
        Assert.assertEquals(responseNotEntityByFilter.getBody().getError(), errorNotFound);
    }

    @Test
    public void updateUserTest() {
        UserDto dto = new UserDto();
        dto.setId(1);
        dto.setFirstName("Test user");
        dto.setPosition("Директор");

        ResponseEntity<SuccessResponseView> responseEntity = testRestTemplate.postForEntity("/user/update", dto, SuccessResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(responseEntity.getBody().isSuccess());

        ResponseEntity<DataResponseView<UserDto>> responseEntityById = testRestTemplate.exchange("/user/1" ,
                HttpMethod.GET, null, new ParameterizedTypeReference<DataResponseView<UserDto>>(){});

        Assert.assertEquals(responseEntityById.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntityById.getBody());
        Assert.assertEquals(responseEntityById.getBody().getData().getFirstName(), dto.getFirstName());
    }

    @Test
    public void updateUserTestError() {
        UserDto dto = new UserDto();
        String errorValidated = "(?=.*firstName)(?=.*position)(?=.*id).*";
        String errorWrongId = "Не найден пользователь с id ";
        int id = 10;

        ResponseEntity<ErrorResponseView> responseEntity = testRestTemplate.postForEntity("/user/update", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertTrue(responseEntity.getBody().getError().matches(errorValidated));

        dto.setId(id);
        dto.setFirstName("Test user");
        dto.setPosition("Директор");

        ResponseEntity<ErrorResponseView> responseEntityWrongId = testRestTemplate.postForEntity("/user/update", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntityWrongId.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotNull(responseEntityWrongId.getBody());
        Assert.assertEquals(responseEntityWrongId.getBody().getError(), errorWrongId + id);
    }

    @Test
    public void saveUserTest() {
        UserDto dto = new UserDto();
        dto.setOfficeId(1);
        dto.setFirstName("Test user");
        dto.setPosition("Директор");

        ResponseEntity<SuccessResponseView> responseEntity = testRestTemplate.postForEntity("/user/save", dto, SuccessResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(responseEntity.getBody().isSuccess());

        ResponseEntity<DataResponseView<List<UserDto>>> responseEntityByFilter = testRestTemplate.exchange("/user/list",
                HttpMethod.POST, new HttpEntity<>(dto) , new ParameterizedTypeReference<DataResponseView<List<UserDto>>>(){});

        Assert.assertEquals(responseEntityByFilter.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntityByFilter.getBody());
        Assert.assertTrue(!responseEntityByFilter.getBody().getData().isEmpty());
        Assert.assertEquals(responseEntityByFilter.getBody().getData().get(0).getPosition(), dto.getPosition());
        Assert.assertEquals(responseEntityByFilter.getBody().getData().get(0).getFirstName(), dto.getFirstName());
    }

    @Test
    public void saveUserTestError() {
        UserDto dto = new UserDto();
        String errorValidated = "(?=.*firstName)(?=.*position)(?=.*officeId).*";
        String errorOfficeId = "Не найден офис с officeId ";
        String errorTypeDocumentValidated = "Не найден тип документа с docCode 55";
        String errorDocumentValidated = "docCode документа не совпадает с docName";
        String errorCitizenshipCodeValidated = "Не найдена страна с citizenshipCode 0";
        int officeId= 10;

        ResponseEntity<ErrorResponseView> responseEntity = testRestTemplate.postForEntity("/user/save", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertTrue(responseEntity.getBody().getError().matches(errorValidated));

        dto.setOfficeId(officeId);
        dto.setFirstName("Test user");
        dto.setPosition("Директор");

        ResponseEntity<ErrorResponseView> responseEntityWrongOfficeId = testRestTemplate.postForEntity("/user/save", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntityWrongOfficeId.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotNull(responseEntityWrongOfficeId.getBody());
        Assert.assertEquals(responseEntityWrongOfficeId.getBody().getError(), errorOfficeId + officeId);

        dto.setDocCode(55);
        dto.setOfficeId(1);

        ResponseEntity<ErrorResponseView> responseEntityTypeDocumentValidatedError = testRestTemplate.postForEntity("/user/save", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntityTypeDocumentValidatedError.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotNull(responseEntityTypeDocumentValidatedError.getBody());
        Assert.assertEquals(responseEntityTypeDocumentValidatedError.getBody().getError(), errorTypeDocumentValidated);

        dto.setDocCode(21);
        dto.setDocName("Свидетельство");

        ResponseEntity<ErrorResponseView> responseEntityDocumentValidatedError = testRestTemplate.postForEntity("/user/save", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntityDocumentValidatedError.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotNull(responseEntityDocumentValidatedError.getBody());
        Assert.assertEquals(responseEntityDocumentValidatedError.getBody().getError(), errorDocumentValidated);

        dto.setDocName(null);
        dto.setCitizenshipCode(0);
        ResponseEntity<ErrorResponseView> responseEntityCitizenshipCodeValidatedError = testRestTemplate.postForEntity("/user/save", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntityCitizenshipCodeValidatedError.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotNull(responseEntityCitizenshipCodeValidatedError.getBody());
        Assert.assertEquals(responseEntityCitizenshipCodeValidatedError.getBody().getError(), errorCitizenshipCodeValidated);
    }
}
