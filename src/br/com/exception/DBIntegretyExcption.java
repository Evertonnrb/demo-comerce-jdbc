package br.com.exception;

public class DBIntegretyExcption extends RuntimeException{

    public DBIntegretyExcption(String msg) {
        super(msg);
    }
}
