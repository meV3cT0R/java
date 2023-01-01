package com.vector;

import java.io.Serializable;
/*
 * 
 * class for exchanging data
 */
public class Message implements Serializable{
    private static final long serialVersionUID = 1L;
   private String message;
   private boolean turn;
   private boolean waiting;
   private String coor;

   
   public Message() {

   } 

   public void setMessage(String message) {
    this.message = message;
   }

   public String getMessage() {
    return message;
   }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }
    public boolean getWaiting() {
        return waiting;
    }
    public String getCoor() {
        return coor;
    }

    public void setCoor(String coor) {
        this.coor = coor;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
   
    
}
