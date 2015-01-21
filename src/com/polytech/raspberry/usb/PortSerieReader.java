package com.polytech.raspberry.usb;

import java.io.IOException;
import java.io.InputStream;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class PortSerieReader implements SerialPortEventListener {
	private InputStream in;
	private PortSerieWriter writer;
    private byte[] buffer = new byte[1024];
    
    public PortSerieReader(InputStream in) {
        this(in, null);
    }
    
    public PortSerieReader(InputStream in, PortSerieWriter writer) {
        this.in = in;
        this.writer = writer;
    } 
    
    public void serialEvent(SerialPortEvent arg0) {
    	Trame t = this.getTrame();
        if (t.getBitAction() == ActionBit.RECEPTION && t.getDataAction() == DataBit.ALLUME) {
//        	ConnexionBD.getInstance().envoi();
        	this.writer.envoiTrame(new Trame("11"));
        }
    }
    
    private Trame getTrame() {
    	int data;
        String trame = "";
        try {
            int len = 0;
            while ((data = in.read()) > -1 ) {
                if (data == '\n') {
                    break;
                }
                buffer[len++] = (byte) data;
            }
            trame += new String(buffer, 0, len);
            
            return new Trame(trame);
            
        } catch ( IOException e ) {
            e.printStackTrace();
            System.exit(-1);
        }
        
        return null;
    }
}
