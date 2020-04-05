package com.naura.less.observercode;

public interface Observer {
    public <T> void update(String eventName, T val);
}
