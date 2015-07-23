package com.mojokarma.mojokarma;

/**
 * Created by kundan on 6/23/2015.
 */
public class Globals {


    private static Globals instance = new Globals();

    // Getter-Setters
    public static Globals getInstance() {
        return instance;
    }

    public static void setInstance(Globals instance) {
        Globals.instance = instance;
    }

    private String notification_index;
    private String desig;


    protected Globals() {

    }


    public String getValue() {
        return notification_index;
    }


    public void setValue(String notification_index) {
        this.notification_index = notification_index;
    }

    public void setDesignation(String desig) {
        this.desig = desig;
    }
    public String getDesignation() {
        return desig;
    }


}