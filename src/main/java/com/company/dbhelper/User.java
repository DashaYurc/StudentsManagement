package com.company.dbhelper;

public enum User {
    USER("root"),
    PASSWORD("Pugipi69");

    private final String str;

    User(String str){
        this.str = str;
    }

    public String getStr(){
        return str;
    }
}
