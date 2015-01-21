package com.polytech.raspberry.usb;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;

public class PortSerieController {
	private PortSerieReader reader;
	private PortSerieWriter writer;
	
	public PortSerieController() throws Exception {
        CommPortIdentifier portIdentifier = DiscoverPorts.getPortAvailable();
        if (portIdentifier == null) {
        	throw new Exception("Error : no com port available");
        }
        
        if (portIdentifier.isCurrentlyOwned()) {
            throw new Exception("Error: Port is currently in use");
        }
            
        CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
            
        if (commPort instanceof SerialPort) {
        	SerialPort serialPort = (SerialPort) commPort;
        	serialPort.setSerialPortParams(57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

        	InputStream in = serialPort.getInputStream();
        	OutputStream out = serialPort.getOutputStream();

        	this.writer = new PortSerieWriter(out);
        	this.reader = new PortSerieReader(in, this.writer);

        	serialPort.addEventListener(this.reader);
        	serialPort.notifyOnDataAvailable(true);
        } else {
        	throw new Exception("Error: Only serial ports are handled for now");
        }

    }
}
