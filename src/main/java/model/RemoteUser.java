package model;

public class RemoteUser implements MainTreeItem{

    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        if (username != null) {
            return username;
        }
        return "- not specified -";
    }

    public String getPassword() {
        return null;
    }

}
