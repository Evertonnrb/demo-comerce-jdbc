package br.com.controller.excpetions;

public class DBIntegretyExcption extends RuntimeException{

    public DBIntegretyExcption(String msg) {
        super(msg);
    }
}
