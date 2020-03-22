package dao;

import config.local.LocalHostsConfig;
import org.junit.jupiter.api.Test;
import model.RemoteHost;
import model.RemoteUser;

import static org.junit.jupiter.api.Assertions.*;

class RemotesDaoTest {

    @Test
    public void testSaveLoad() {

        RemotesDao remotesDao = new RemotesDao();

        RemoteHost host = new RemoteHost();
        host.setHostname("localhost");
        host.setKnown(false);
        RemoteUser user = new RemoteUser();
        user.setUsername("username");
        RemoteUser user2 = new RemoteUser();
        user2.setUsername("another username");
        host.getRemoteUsers().add(user);
        host.getRemoteUsers().add(user2);

        RemoteHost host1 = new RemoteHost();
        host1.setHostname("localhost1");
        host1.setKnown(false);
        RemoteUser user3 = new RemoteUser();
        user3.setUsername("username");
        RemoteUser user4 = new RemoteUser();
        user4.setUsername("another username");
        host1.getRemoteUsers().add(user3);
        host1.getRemoteUsers().add(user4);

        LocalHostsConfig config = new LocalHostsConfig();
        config.getHosts().add(host);
        config.getHosts().add(host1);

        remotesDao.saveLocalHostConfig(config);

        RemotesDao dao = new RemotesDao();
        LocalHostsConfig loadedConfig = dao.loadHostsConfig();

        assertEquals(
                config.getHosts().size(),
                loadedConfig.getHosts().size());

        assertEquals(
                config.getHosts().get(0).getRemoteUsers().size(),
                loadedConfig.getHosts().get(0).getRemoteUsers().size());
    }

}