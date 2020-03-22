package ssh;

import com.jcraft.jsch.*;
import model.RemoteHost;

import java.io.IOException;
import java.io.InputStream;

public class Client {


    public String executeString(RemoteHost remoteHost, String command) {
        JSch jsch = new JSch();
        try {
            jsch.setKnownHosts("/home/tookuk/.ssh/known_hosts");
            jsch.addIdentity("/home/tookuk/.ssh/id_rsa");
            Session session = jsch.getSession("root", "68.183.4.164", 22);

            UserInfo ui = new UserInfo() {
                @Override
                public String getPassphrase() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return "qwerty1Q";
                }

                @Override
                public boolean promptPassword(String s) {
                    return true;
                }

                @Override
                public boolean promptPassphrase(String s) {
                    return false;
                }

                @Override
                public boolean promptYesNo(String s) {
                    return true;
                }

                @Override
                public void showMessage(String s) {

                }
            };
            session.setUserInfo(ui);
            session.connect();

            command = "cat /root/logfile.log";

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            // X Forwarding
            // channel.setXForwarding(true);

            //channel.setInputStream(System.in);
            channel.setInputStream(null);

            //channel.setOutputStream(System.out);

            //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
            //((ChannelExec)channel).setErrStream(fos);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();

            StringBuilder stringBuilder = new StringBuilder();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    stringBuilder.append(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    stringBuilder.append("exit-status: ").append(channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }

            channel.disconnect();
            session.disconnect();

            return stringBuilder.toString();
        } catch (JSchException | IOException e) {
            throw new FailedExecution("execution failed", e);
        }
    }

}
