package com.ccsw.tutorial.lease;

import com.ccsw.tutorial.lease.model.LeaseDto;
import com.ccsw.tutorial.lease.model.LeaseSearchDto;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.game.model.GameDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LeaseIT {
    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/lease/";

    public static final Long DELETE_LEASE_ID = 3L;
    public static final Long MODIFY_LEASE_ID = 1L;
    public static final LocalDate NEW_LEASE_START_DATE = LocalDate.of(2022, 1, 1);
    public static final LocalDate NEW_LEASE_END_DATE = LocalDate.of(2022, 1, 5);

    private static final int TOTAL_LEASES = 8;
    private static final int PAGE_SIZE = 5;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<Page<LeaseDto>> responseTypePage = new ParameterizedTypeReference<Page<LeaseDto>>() {
    };

    @Test
    public void findFirstPageWithFiveSizeShouldReturnFirstFiveResults() {
        LeaseSearchDto searchDto = new LeaseSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LeaseDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_LEASES, response.getBody().getTotalElements());
        assertEquals(PAGE_SIZE, response.getBody().getContent().size());
    }

    @Test
    public void findSecondPageWithFiveSizeShouldReturnLastResults() {
        int elementsCount = TOTAL_LEASES - PAGE_SIZE;

        LeaseSearchDto searchDto = new LeaseSearchDto();
        searchDto.setPageable(PageRequest.of(1, PAGE_SIZE));

        ResponseEntity<Page<LeaseDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_LEASES, response.getBody().getTotalElements());
        assertEquals(elementsCount, response.getBody().getContent().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewLease() {
        long newLeaseId = TOTAL_LEASES + 1;
        long newLeaseSize = TOTAL_LEASES + 1;

        LeaseDto dto = new LeaseDto();
        ClientDto clientDto = new ClientDto();
        GameDto gameDto = new GameDto();
        clientDto.setId(1L);
        gameDto.setId(1L);

        dto.setClient(clientDto);
        dto.setGame(gameDto);
        dto.setStartDate(NEW_LEASE_START_DATE);
        dto.setEndDate(NEW_LEASE_END_DATE);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        LeaseSearchDto searchDto = new LeaseSearchDto();
        searchDto.setPageable(PageRequest.of(0, (int) newLeaseSize));

        ResponseEntity<Page<LeaseDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(newLeaseSize, response.getBody().getTotalElements());

        LeaseDto lease = response.getBody().getContent().stream().filter(item -> item.getId().equals(newLeaseId))
                .findFirst().orElse(null);
        assertNotNull(lease);
        assertEquals(NEW_LEASE_START_DATE, lease.getStartDate());
        assertEquals(NEW_LEASE_END_DATE, lease.getEndDate());
    }

    @Test
    public void modifyWithExistIdShouldModifyLease() {
        LeaseDto dto = new LeaseDto();
        ClientDto clientDto = new ClientDto();
        GameDto gameDto = new GameDto();
        clientDto.setId(2L);
        gameDto.setId(2L);

        dto.setClient(clientDto);
        dto.setGame(gameDto);
        dto.setStartDate(NEW_LEASE_START_DATE);
        dto.setEndDate(NEW_LEASE_END_DATE);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + MODIFY_LEASE_ID, HttpMethod.PUT, new HttpEntity<>(dto),
                Void.class);

        LeaseSearchDto searchDto = new LeaseSearchDto();
        searchDto.setPageable(PageRequest.of(0, PAGE_SIZE));

        ResponseEntity<Page<LeaseDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_LEASES, response.getBody().getTotalElements());

        LeaseDto lease = response.getBody().getContent().stream().filter(item -> item.getId().equals(MODIFY_LEASE_ID))
                .findFirst().orElse(null);
        assertNotNull(lease);
        assertEquals(NEW_LEASE_START_DATE, lease.getStartDate());
        assertEquals(NEW_LEASE_END_DATE, lease.getEndDate());
        assertEquals(clientDto.getId(), lease.getClient().getId());
        assertEquals(gameDto.getId(), lease.getGame().getId());
    }

    @Test
    public void modifyWithNotExistIdShouldThrowException() {
        long leaseId = TOTAL_LEASES + 1;

        LeaseDto dto = new LeaseDto();
        dto.setStartDate(NEW_LEASE_START_DATE);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + leaseId, HttpMethod.PUT,
                new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void deleteWithExistsIdShouldDeleteLease() {
        long newLeasesSize = TOTAL_LEASES - 1;

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + DELETE_LEASE_ID, HttpMethod.DELETE, null, Void.class);

        LeaseSearchDto searchDto = new LeaseSearchDto();
        searchDto.setPageable(PageRequest.of(0, TOTAL_LEASES));

        ResponseEntity<Page<LeaseDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(newLeasesSize, response.getBody().getTotalElements());
    }

    @Test
    public void deleteWithNotExistsIdShouldThrowException() {

        long deleteLeaseId = TOTAL_LEASES + 1;

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + deleteLeaseId,
                HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
