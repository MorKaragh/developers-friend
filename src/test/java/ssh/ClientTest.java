package ssh;

import org.junit.jupiter.api.Test;
import model.RemoteHost;

class ClientTest {

    @Test
    public void test() {
        Client client = new Client();
        client.executeString(new RemoteHost(),"touch /tmp/ololo");
    }


}