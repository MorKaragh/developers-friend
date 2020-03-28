package engine.dao;

import engine.state.HostStorage;

public interface HostsDao {
    HostStorage load();
    void save(HostStorage hostStorage);
}
