package com.domain;

public enum StationCode {

    LEEDS("LDS"), Nottingham("NOT"), Sheffield("SHF");

    private final String code;

    StationCode(String code) {
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
