package br.com.controller.excpetions;

public class BDException extends RuntimeException{

    public BDException(String msg){
        super(msg);
    }
}
