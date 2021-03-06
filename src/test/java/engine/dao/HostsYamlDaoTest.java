package engine.dao;

import engine.dao.yaml.HostsYamlDao;
import engine.state.HostStorage;
import org.junit.jupiter.api.Test;
import engine.model.Host;
import engine.model.UserOnHost;

import static org.junit.jupiter.api.Assertions.*;

class HostsYamlDaoTest {

    @Test
    public void testSaveLoad() {

        HostsYamlDao hostsYamlDao = new HostsYamlDao();

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

        HostStorage config = new HostStorage();
        config.getHosts().add(host);
        config.getHosts().add(host1);

        hostsYamlDao.save(config);

        HostsYamlDao dao = new HostsYamlDao();
        HostStorage loadedConfig = dao.load();

        assertEquals(
                config.getHosts().size(),
                loadedConfig.getHosts().size());

        assertEquals(
                config.getHosts().get(0).getUserOnHosts().size(),
                loadedConfig.getHosts().get(0).getUserOnHosts().size());
    }

}