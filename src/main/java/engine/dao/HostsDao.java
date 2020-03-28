package engine.dao;

import model.HostStorage;

public interface HostsDao {
    HostStorage load();
    void save(HostStorage hostStorage);
}
