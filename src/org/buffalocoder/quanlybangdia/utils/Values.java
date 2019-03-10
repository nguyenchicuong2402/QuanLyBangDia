package org.buffalocoder.quanlybangdia.utils;

public class Values {
    private static Values _instance;

    private Values(){

    }

    public static Values getInstance(){
        if (_instance == null){
            synchronized (Values.class){
                if (null == _instance){
                    _instance = new Values();
                }
            }
        }

        return _instance;
    }
}
