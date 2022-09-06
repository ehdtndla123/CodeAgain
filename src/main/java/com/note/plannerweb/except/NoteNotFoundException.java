package com.note.plannerweb.except;

public class NoteNotFoundException extends RuntimeException{
    public NoteNotFoundException(){
        super();
    }
    public NoteNotFoundException(String msg){
        super(msg);
    }
}
