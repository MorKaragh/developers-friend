package ssh;

import config.local.LocalSshConfig;
import model.RemoteUser;
import org.junit.jupiter.api.Test;
import model.RemoteHost;

class ClientTest {

    @Test
    public void test() {
        Client client = new Client(new LocalSshConfig());
        client.executeString(new RemoteHost(), new RemoteUser(), "touch /tmp/ololo");
    }


}