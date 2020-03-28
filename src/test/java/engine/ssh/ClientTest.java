package engine.ssh;

import engine.model.Host;
import engine.model.UserOnHost;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    public void test() {
        Client client = new Client();
        client.executeString(new Host(), new UserOnHost(), "touch /tmp/ololo");
    }


}