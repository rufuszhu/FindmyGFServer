package com.rufus.rest.resource;

@SuppressWarnings("serial")
public class PersonDoesNotExistException extends Exception{
	public PersonDoesNotExistException(){
		
	}
	public PersonDoesNotExistException(String msg){
		super(msg);
	}

}

