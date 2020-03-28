package engine.state;

public class SshProperties {

    private String knownHostsFile;
    private String pubKeyFile;
    private String privKeyFile;

    public String getKnownHostsFile() {
        return knownHostsFile;
    }

    public void setKnownHostsFile(String knownHostsFile) {
        this.knownHostsFile = knownHostsFile;
    }

    public String getPubKeyFile() {
        return pubKeyFile;
    }

    public void setPubKeyFile(String pubKeyFile) {
        this.pubKeyFile = pubKeyFile;
    }

    public String getPrivKeyFile() {
        return privKeyFile;
    }

    public SshProperties setPrivKeyFile(String privKeyFile) {
        this.privKeyFile = privKeyFile;
        return this;
    }
}
