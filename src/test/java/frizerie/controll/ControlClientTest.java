package frizerie.controll;

import frizerie.model.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlClientTest {
    ControlClient controlClient=new ControlClient();
    @Test
    void adaugareClient() {
        Client client=new Client("Misu","Andrei",34555);
        controlClient.adaugareClient(client);

    }

    @Test
    void deleteClient() {
    }
    @Test
    void verificareClient(){
        Client client=new Client("Misu","Andrei",34555);
        assertEquals(true,controlClient.verficiareClient("Misu","Anddrei"));
    }

}