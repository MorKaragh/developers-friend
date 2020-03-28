package engine.ssh;

import engine.state.SshProperties;
import engine.model.UserOnHost;
import org.junit.jupiter.api.Test;
import engine.model.Host;

class ClientTest {

    @Test
    public void test() {
        Client client = new Client(new SshProperties());
        client.executeString(new Host(), new UserOnHost(), "touch /tmp/ololo");
    }


}