package com.houoy.game.saigou.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CacheService {
    void expire(String key, long expire);

    String get(String key);

    void set(String key, String value);

    void set(String key, String value, long expire);

    public List<String> mget(Set<String> keys);

    public void mset(Map<String, String> map);

    void remove(String key);

    String getHash(String key, String hashKey);

    void setHash(String key, String hashKey, String value);

    void removeHash(String key, String hashKey);

    List<String> getHashValues(String key);

    Map getHashEntries(String key);

    Boolean incrementHashLong(String hashname, String itemkey, Long delta);

    List<String> mgetHash(String key, Set<String> keys);

    void msetHash(String key, Map<String, String> map);
}
