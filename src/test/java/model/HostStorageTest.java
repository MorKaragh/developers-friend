package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HostStorageTest {

    @Test
    void add() {

        HostStorage list = new HostStorage();

        Host host = new Host();
        host.setHostname("hostname 1");
        host.getUserOnHosts().add(new UserOnHost().setUsername("username 1 on host 1"));
        host.getUserOnHosts().add(new UserOnHost().setUsername("username 2 on host 1"));

        list.add(host);

        assertEquals(1, list.getHosts().size());
        assertEquals(2, list.getHosts().get(0).getUserOnHosts().size());

        list.add(host);

        assertEquals(1, list.getHosts().size());
        assertEquals(2, list.getHosts().get(0).getUserOnHosts().size());

        host.getUserOnHosts().add(new UserOnHost().setUsername("username 3 on host 1"));

        list.add(host);

        assertEquals(1, list.getHosts().size());
        assertEquals(3, list.getHosts().get(0).getUserOnHosts().size());

        host.setHostname("hostname 2");

        list.add(host);

        assertEquals(2, list.getHosts().size());

    }
}