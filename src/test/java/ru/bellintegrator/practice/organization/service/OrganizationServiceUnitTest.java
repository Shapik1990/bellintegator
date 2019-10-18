package ru.bellintegrator.practice.organization.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.dto.OrganizationDto;
import ru.bellintegrator.practice.organization.model.Organization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationServiceUnitTest {

    @Mock
    OrganizationDao organizationDao;

    @InjectMocks
    OrganizationServiceImpl service;

    @Test
    public void update() {
        List<Organization> organizations = Arrays.asList(new Organization(), new Organization(), new Organization());

        when(organizationDao.loadById(any())).thenAnswer(new Answer<Organization>() {
            @Override
            public Organization answer(InvocationOnMock invocationOnMock) throws Throwable {
                Integer id = invocationOnMock.getArgument(0);
                if (id > organizations.size() || id < 0) {
                    return null;
                }
                else
                    return organizations.get(id - 1);
            }
        });

        OrganizationDto dto = new OrganizationDto();
        dto.setId(1);
        dto.setName("Bell");

        service.update(dto);

        verify(organizationDao).loadById(any());
        assertEquals(organizations.get(0).getName(), dto.getName());
    }

    @Test
    public void getOrganizationById() {
        Organization organization = new Organization();
        organization.setName("Газпром");

        when(organizationDao.loadById(1)).thenReturn(organization);
        assertEquals(service.getOrganizationById(1).getClass(), OrganizationDto.class);
        verify(organizationDao).loadById(1);
        assertEquals(service.getOrganizationById(1).getName(), "Газпром");
    }

    @Test
    public void getOrganizationsListByFilter() {
        List<Organization> organizations = Arrays.asList(new Organization(), new Organization(), new Organization());
        when(organizationDao.listByFilter(any(), any(), any())).thenReturn(organizations);

        assertTrue(service.getOrganizationsListByFilter(new OrganizationDto()).stream().
                allMatch(x -> x.getClass().equals(OrganizationDto.class)));

        verify(organizationDao).listByFilter(null, null, null);
    }

    @Test
    public void save() {
        List<Organization> organizations = new ArrayList<>();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Organization org = (Organization)invocationOnMock.getArguments()[0];
                organizations.add(org);
                return null;
            }
        }).when(organizationDao).save(any());

        OrganizationDto dto = new OrganizationDto();
        dto.setName("Bell");
        service.save(dto);

        verify(organizationDao).save(any());
        assertFalse(organizations.isEmpty());
        assertEquals(dto.getName(), organizations.get(0).getName());
    }
}