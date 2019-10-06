package ru.bellintegrator.practice.office.controller;

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
import ru.bellintegrator.practice.office.dto.OfficeDto;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.ErrorResponseView;
import ru.bellintegrator.practice.view.SuccessResponseView;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfficeControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void getOfficeByIdTest() {
        Integer id = 1;

        ResponseEntity<DataResponseView<OfficeDto>> responseEntity = testRestTemplate.exchange("/office/" + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<DataResponseView<OfficeDto>>(){});

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(responseEntity.getBody().getData().getId(), id);
    }

    @Test
    public void getOfficeByIdTestError() {
        int id = 10;
        String errorNotEntity = "Не найден офис с id " + id;

        ResponseEntity<ErrorResponseView> responseNotEntityById = testRestTemplate.getForEntity("/office/" + id, ErrorResponseView.class);

        Assert.assertNotNull(responseNotEntityById.getBody());
        Assert.assertEquals(responseNotEntityById.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertEquals(responseNotEntityById.getBody().getError(), errorNotEntity);

        ResponseEntity<ErrorResponseView> responseEntityWrongId = testRestTemplate.getForEntity("/office/fdf", ErrorResponseView.class);

        Assert.assertEquals(responseEntityWrongId.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertEquals(responseEntityWrongId.getBody().getError(), "Not Found");
    }

    @Test
    public void getByOfficesListTest() {
        OfficeDto dto = new OfficeDto();
        dto.setOrgId(1);

        ResponseEntity<DataResponseView<List<OfficeDto>>> responseEntity = testRestTemplate.exchange("/office/list",
                HttpMethod.POST, new HttpEntity<>(dto) , new ParameterizedTypeReference<DataResponseView<List<OfficeDto>>>(){});

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertTrue(!responseEntity.getBody().getData().isEmpty());
    }

    @Test
    public void getByOfficesListTestError() {
        OfficeDto dto = new OfficeDto();
        String errorValidated = "Ошибка валидации : должно быть задано orgId;";
        String errorNotFound = "Не найдены офисы с указанными параметрами";

        ResponseEntity<ErrorResponseView> responseEntityValidated = testRestTemplate.postForEntity("/office/list", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntityValidated.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertNotNull(responseEntityValidated.getBody());
        Assert.assertEquals(responseEntityValidated.getBody().getError(), errorValidated);

        dto.setOrgId(10);

        ResponseEntity<ErrorResponseView> responseNotEntityByFilter = testRestTemplate.postForEntity("/office/list", dto, ErrorResponseView.class);

        Assert.assertEquals(responseNotEntityByFilter.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotNull(responseNotEntityByFilter.getBody());
        Assert.assertEquals(responseNotEntityByFilter.getBody().getError(), errorNotFound);
    }

    @Test
    public void updateOfficeTest() {
        OfficeDto dto = new OfficeDto();
        dto.setId(1);
        dto.setName("Gazprom Office");
        dto.setAddress("Moscow");

        ResponseEntity<SuccessResponseView> responseEntity = testRestTemplate.postForEntity("/office/update", dto, SuccessResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(responseEntity.getBody().isSuccess());

        ResponseEntity<DataResponseView<OfficeDto>> responseEntityById = testRestTemplate.exchange("/office/1" ,
                HttpMethod.GET, null, new ParameterizedTypeReference<DataResponseView<OfficeDto>>(){});

        Assert.assertEquals(responseEntityById.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntityById.getBody());
        Assert.assertEquals(responseEntityById.getBody().getData().getName(), dto.getName());
    }

    @Test
    public void updateOfficeTestError() {
        OfficeDto dto = new OfficeDto();
        String errorValidated = "(?=.*address)(?=.*name)(?=.*id).*";
        String errorWrongId = "Не найден офис с id ";
        int id = 10;

        ResponseEntity<ErrorResponseView> responseEntity = testRestTemplate.postForEntity("/office/update", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertTrue(responseEntity.getBody().getError().matches(errorValidated));

        dto.setId(id);
        dto.setName("Gazprom Office");
        dto.setAddress("Moscow");

        ResponseEntity<ErrorResponseView> responseEntityWrongId = testRestTemplate.postForEntity("/office/update", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntityWrongId.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotNull(responseEntityWrongId.getBody());
        Assert.assertEquals(responseEntityWrongId.getBody().getError(), errorWrongId + id);
    }

    @Test
    public void saveOfficeTest() {
        OfficeDto dto = new OfficeDto();
        dto.setOrgId(1);
        dto.setName("Test office");
        dto.setActive(true);

        ResponseEntity<SuccessResponseView> responseEntity = testRestTemplate.postForEntity("/office/save", dto, SuccessResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(responseEntity.getBody().isSuccess());

        ResponseEntity<DataResponseView<List<OfficeDto>>> responseEntityByFilter = testRestTemplate.exchange("/office/list",
                HttpMethod.POST, new HttpEntity<>(dto) , new ParameterizedTypeReference<DataResponseView<List<OfficeDto>>>(){});

        Assert.assertEquals(responseEntityByFilter.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntityByFilter.getBody());
        Assert.assertTrue(!responseEntityByFilter.getBody().getData().isEmpty());
        Assert.assertTrue(responseEntityByFilter.getBody().getData().get(0).isActive());
        Assert.assertEquals(responseEntityByFilter.getBody().getData().get(0).getName(), dto.getName());
    }

    @Test
    public void saveOfficeTestError() {
        OfficeDto dto = new OfficeDto();
        String errorValidated = "Ошибка валидации : должно быть задано orgId;";
        String errorOrgId = "Не найдена организация с orgId ";
        int orgId= 10;

        ResponseEntity<ErrorResponseView> responseEntity = testRestTemplate.postForEntity("/office/save", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(responseEntity.getBody().getError(), errorValidated);

        dto.setOrgId(orgId);

        ResponseEntity<ErrorResponseView> responseEntityWrongOrgId = testRestTemplate.postForEntity("/office/save", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntityWrongOrgId.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotNull(responseEntityWrongOrgId.getBody());
        Assert.assertEquals(responseEntityWrongOrgId.getBody().getError(), errorOrgId + orgId);
    }
}
