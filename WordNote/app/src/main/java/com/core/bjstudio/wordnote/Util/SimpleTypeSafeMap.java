package com.core.bjstudio.wordnote.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleTypeSafeMap {
    private Map<Class<?>, List<?>> map = new HashMap<>();

    public <T> void put(Class<T> k, List<T> v) {
        map.put(k, v);
    }

    public <T> List<T> get(Class<T> k) {
        return (List<T>)map.get(k);
    }

    public Set<Map.Entry<Class<?>, List<?>>> entrySet() {
        return map.entrySet();
    }
}
