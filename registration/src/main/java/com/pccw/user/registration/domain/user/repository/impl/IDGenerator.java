package com.pccw.user.registration.domain.user.repository.impl;

public class IDGenerator {

    private  int maxId=0;

    public  int generateID(){
        return ++maxId;
    }
}
