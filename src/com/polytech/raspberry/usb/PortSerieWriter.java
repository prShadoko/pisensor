package com.polytech.raspberry.usb;

import java.io.IOException;
import java.io.OutputStream;

public class PortSerieWriter {
	private OutputStream out;
    
    public PortSerieWriter(OutputStream out) {
        this.out = out;
    }
    
    public void envoiTrame(Trame t) {
    	try {
			this.out.write(t.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
