package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 
 * @author ccamposa
 *
 */
@ExtendWith(MockitoExtension.class)
public class ClientTest {

    public static final String CLIENT_NAME = "Rafa Perez";
    public static final Long EXISTS_CLIENT_ID = 1L;
    public static final Long NOT_EXISTS_CLIENT_ID = 0L;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void getExistsClientIdShouldReturnClient() {
        Client client = mock(Client.class);
        when(client.getId()).thenReturn(EXISTS_CLIENT_ID);
        when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));

        Client clientResponse = clientService.get(EXISTS_CLIENT_ID);

        assertNotNull(clientResponse);
        assertEquals(EXISTS_CLIENT_ID, client.getId());
    }

    @Test
    public void getNotExistsClientIdShouldReturnNull() {
        when(clientRepository.findById(NOT_EXISTS_CLIENT_ID)).thenReturn(Optional.empty());

        Client client = clientService.get(NOT_EXISTS_CLIENT_ID);

        assertNull(client);
    }

    @Test // that when calling findAll() it returns every client
    public void findAllShouldReturnAllCategories() {
        List<Client> list = new ArrayList<>();
        list.add(mock(Client.class));

        when(clientRepository.findAll()).thenReturn(list);

        List<Client> clients = clientService.findAll();

        assertNotNull(clients);
        assertEquals(1, clients.size());
    }

    @Test // that when saving without ID, a new client is inserted
    public void saveNotExistsClientIdShouldInsert() {
        ClientDto clientDto = new ClientDto();
        clientDto.setName(CLIENT_NAME);

        ArgumentCaptor<Client> client = ArgumentCaptor.forClass(Client.class);

        // Call save with null ID (non-existent client)
        clientService.save(null, clientDto);

        // Simulate save action
        verify(clientRepository).save(client.capture());

        // Check if value is the provided one
        assertEquals(CLIENT_NAME, client.getValue().getName());
    }

    @Test // that when saving with ID, the client is modified
    public void saveExistsClientIdShouldUpdate() {
        ClientDto clientDto = new ClientDto();
        clientDto.setName(CLIENT_NAME);

        // Create mock client
        Client client = mock(Client.class);
        // Find an existing client (id 1)
        when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));

        // Call save with existing ID and mock client
        clientService.save(EXISTS_CLIENT_ID, clientDto);

        // Verify that save was called over the same client
        verify(clientRepository).save(client);
    }

    @Test // that when deleting a client, it deletes the client
    public void deleteExistsClientIdShouldDelete() {
        // Delete the client
        clientService.delete(EXISTS_CLIENT_ID);
        // Verify that the client was deleted
        verify(clientRepository).deleteById(EXISTS_CLIENT_ID);
    }
}
