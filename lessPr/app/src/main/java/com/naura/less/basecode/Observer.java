package com.naura.less.basecode;

public interface Observer {
    public <T> void update(String eventName, T val);
}
