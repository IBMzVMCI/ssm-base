package com.xxx.bean;

public enum RightType {

    NONE((byte) 0), READ((byte) 1), ALL((byte) 2);
    private byte id;

    RightType(byte id) {
        this.id = id;
    }

    public byte id() {
        return this.id;
    }

}