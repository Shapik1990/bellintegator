package ru.bellintegrator.practice.organization.controller;

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
import ru.bellintegrator.practice.organization.dto.OrganizationDto;
import ru.bellintegrator.practice.view.DataResponseView;
import ru.bellintegrator.practice.view.ErrorResponseView;
import ru.bellintegrator.practice.view.SuccessResponseView;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void getOrganizationByIdTest() {
        Integer id = 1;

        ResponseEntity<DataResponseView<OrganizationDto>> responseEntity = testRestTemplate.exchange("/organization/" + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<DataResponseView<OrganizationDto>>(){});

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(responseEntity.getBody().getData().getId(), id);
    }

    @Test
    public void getOrganizationByIdTestError() {
        int id = 10;
        String errorNotEntity = "Не найдена организация с id " + id;

        ResponseEntity<ErrorResponseView> responseNotEntityById = testRestTemplate.getForEntity("/organization/" + id, ErrorResponseView.class);

        Assert.assertNotNull(responseNotEntityById.getBody());
        Assert.assertEquals(responseNotEntityById.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertEquals(responseNotEntityById.getBody().getError(), errorNotEntity);

        ResponseEntity<ErrorResponseView> responseEntityWrongId = testRestTemplate.getForEntity("/organization/fdf", ErrorResponseView.class);

        Assert.assertEquals(responseEntityWrongId.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertEquals(responseEntityWrongId.getBody().getError(), "Not Found");
    }

    @Test
    public void getByOrganizationsListTest() {
        OrganizationDto dto = new OrganizationDto();
        dto.setName("РЖД");

        ResponseEntity<DataResponseView<List<OrganizationDto>>> responseEntity = testRestTemplate.exchange("/organization/list",
                HttpMethod.POST, new HttpEntity<>(dto) , new ParameterizedTypeReference<DataResponseView<List<OrganizationDto>>>(){});

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertTrue(!responseEntity.getBody().getData().isEmpty());
        Assert.assertNotNull(responseEntity.getBody().getData().get(0).getName());
        Assert.assertNotNull(responseEntity.getBody().getData().get(0).getId());
        Assert.assertNotNull(responseEntity.getBody().getData().get(0).isActive());
    }

    @Test
    public void getByOrganizationsListTestError() {
        OrganizationDto dto = new OrganizationDto();
        String errorValidated = "Ошибка валидации : должно быть задано name;";
        String errorNotFound = "Не найдены организации с указанными параметрами";

        ResponseEntity<ErrorResponseView> responseEntityValidated = testRestTemplate.postForEntity("/organization/list", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntityValidated.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertNotNull(responseEntityValidated.getBody());
        Assert.assertEquals(responseEntityValidated.getBody().getError(), errorValidated);

        dto.setName("Газпро");

        ResponseEntity<ErrorResponseView> responseNotEntityByFilter = testRestTemplate.postForEntity("/organization/list", dto, ErrorResponseView.class);

        Assert.assertEquals(responseNotEntityByFilter.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotNull(responseNotEntityByFilter.getBody());
        Assert.assertEquals(responseNotEntityByFilter.getBody().getError(), errorNotFound);
    }

    @Test
    public void updateOrganizationTest() {
        OrganizationDto dto = new OrganizationDto();
        dto.setId(1);
        dto.setName("Gazprom");
        dto.setFullName("OOO Gazprom");
        dto.setInn("1234567890");
        dto.setKpp("123456789");
        dto.setAddress("Moscow");

        ResponseEntity<SuccessResponseView> responseEntity = testRestTemplate.postForEntity("/organization/update", dto, SuccessResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(responseEntity.getBody().isSuccess());

        ResponseEntity<DataResponseView<OrganizationDto>> responseEntityById = testRestTemplate.exchange("/organization/1" ,
                HttpMethod.GET, null, new ParameterizedTypeReference<DataResponseView<OrganizationDto>>(){});

        Assert.assertEquals(responseEntityById.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntityById.getBody());
        Assert.assertEquals(responseEntityById.getBody().getData().getName(), dto.getName());
    }

    @Test
    public void updateOrganizationTestError() {
        OrganizationDto dto = new OrganizationDto();
        String errorValidated = "(?=.*kpp)(?=.*inn)(?=.*address)(?=.*name)(?=.*fullName)(?=.*id).*";
        String errorWrongId = "Не найдена организация с id ";
        int id = 10;

        ResponseEntity<ErrorResponseView> responseEntity = testRestTemplate.postForEntity("/organization/update", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertTrue(responseEntity.getBody().getError().matches(errorValidated));

        dto.setId(id);
        dto.setName("Gazprom");
        dto.setFullName("OOO Gazprom");
        dto.setInn("1234567890");
        dto.setKpp("123456789");
        dto.setAddress("Moscow");

        ResponseEntity<ErrorResponseView> responseEntityWrongId = testRestTemplate.postForEntity("/organization/update", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntityWrongId.getStatusCode(), HttpStatus.NOT_FOUND);
        Assert.assertNotNull(responseEntityWrongId.getBody());
        Assert.assertEquals(responseEntityWrongId.getBody().getError(), errorWrongId + id);
    }

    @Test
    public void saveOrganizationTest() {
        OrganizationDto dto = new OrganizationDto();
        dto.setName("BellIntegrator");
        dto.setFullName("OOO BellIntegrator");
        dto.setInn("1234567890");
        dto.setKpp("123456789");
        dto.setAddress("Moscow");
        dto.setActive(true);

        ResponseEntity<SuccessResponseView> responseEntity = testRestTemplate.postForEntity("/organization/save", dto, SuccessResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertTrue(responseEntity.getBody().isSuccess());

        ResponseEntity<DataResponseView<List<OrganizationDto>>> responseEntityByFilter = testRestTemplate.exchange("/organization/list",
                HttpMethod.POST, new HttpEntity<>(dto) , new ParameterizedTypeReference<DataResponseView<List<OrganizationDto>>>(){});

        Assert.assertEquals(responseEntityByFilter.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntityByFilter.getBody());
        Assert.assertTrue(!responseEntityByFilter.getBody().getData().isEmpty());
        Assert.assertTrue(responseEntityByFilter.getBody().getData().get(0).isActive());
        Assert.assertEquals(responseEntityByFilter.getBody().getData().get(0).getName(), dto.getName());
    }

    @Test
    public void saveOrganizationTestError() {
        OrganizationDto dto = new OrganizationDto();
        String errorValidated = "(?=.*kpp)(?=.*inn)(?=.*address)(?=.*name)(?=.*fullName).*";

        ResponseEntity<ErrorResponseView> responseEntity = testRestTemplate.postForEntity("/organization/save", dto, ErrorResponseView.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertTrue(responseEntity.getBody().getError().matches(errorValidated));
    }
}
