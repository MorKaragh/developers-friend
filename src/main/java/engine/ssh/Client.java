package engine.ssh;

import com.jcraft.jsch.*;
import engine.state.SshProperties;
import engine.model.Host;
import engine.model.UserOnHost;

import java.io.IOException;
import java.io.InputStream;

public class Client {

    private SshProperties sshConfig;

    public Client() {
    }

    public String executeString(Host host, UserOnHost user, String command) {
        JSch jsch = new JSch();
        try {
            jsch.setKnownHosts(sshConfig.getKnownHostsFile());
            jsch.addIdentity(sshConfig.getPrivKeyFile());
            Session session = jsch.getSession(user.getUsername(), host.getHostname(), 22);

            UserInfo ui = new UserInfo() {
                @Override
                public String getPassphrase() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return null;
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
            throw new SshExecutionException("execution failed", e);
        }
    }

    public Client setSshConfig(SshProperties sshConfig) {
        this.sshConfig = sshConfig;
        return this;
    }
}
