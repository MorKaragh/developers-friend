package engine.ssh;

import model.SshProperties;
import model.UserOnHost;
import org.junit.jupiter.api.Test;
import model.Host;

class ClientTest {

    @Test
    public void test() {
        Client client = new Client(new SshProperties());
        client.executeString(new Host(), new UserOnHost(), "touch /tmp/ololo");
    }


}