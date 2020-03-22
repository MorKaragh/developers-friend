package config;

import engine.dao.HostsYamlDao;
import model.Host;
import model.HostsList;
import model.UserOnHost;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HostsConfigurationTest {

    @Test
    public void testSaveLoad() {

        Host host = new Host();
        host.setHostname("localhost");
        host.setKnown(false);
        UserOnHost user = new UserOnHost();
        user.setUsername("username");
        UserOnHost user2 = new UserOnHost();
        user2.setUsername("another username");
        host.getUserOnHosts().add(user);
        host.getUserOnHosts().add(user2);

        Host host1 = new Host();
        host1.setHostname("localhost1");
        host1.setKnown(false);
        UserOnHost user3 = new UserOnHost();
        user3.setUsername("username");
        UserOnHost user4 = new UserOnHost();
        user4.setUsername("another username");
        host1.getUserOnHosts().add(user3);
        host1.getUserOnHosts().add(user4);

        HostsList config = HostsConfiguration.getCurrent().getHosts();
        config.getHosts().add(host);
        config.getHosts().add(host1);

        HostsConfiguration.getCurrent().save();

        HostsConfiguration.getCurrent().reload();

        HostsList loadedConfig = HostsConfiguration.getCurrent().getHosts();

        assertEquals(
                config.getHosts().size(),
                loadedConfig.getHosts().size());

        assertEquals(
                config.getHosts().get(0).getUserOnHosts().size(),
                loadedConfig.getHosts().get(0).getUserOnHosts().size());
    }
}