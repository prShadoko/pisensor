package com.polytech.raspberry.usb;

import gnu.io.*;

public class DiscoverPorts {


	public static void listPorts() {
		java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
		while ( portEnum.hasMoreElements() ) {
			CommPortIdentifier portIdentifier = portEnum.nextElement();
			System.out.println(portIdentifier.getName()  +  " - " +  getPortTypeName(portIdentifier.getPortType()) );
		}        
	}
	
	/** run only if one port is available */
	public static CommPortIdentifier getPortAvailable() {
		java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
		if (portEnum.hasMoreElements()) {
			return portEnum.nextElement();	
		}
		return null;
	}

	private static String getPortTypeName ( int portType ) {
		switch ( portType ) {
	        case CommPortIdentifier.PORT_I2C:
	            return "I2C";
	        case CommPortIdentifier.PORT_PARALLEL:
	            return "Parallel";
	        case CommPortIdentifier.PORT_RAW:
	            return "Raw";
	        case CommPortIdentifier.PORT_RS485:
	            return "RS485";
	        case CommPortIdentifier.PORT_SERIAL:
	            return "Serial";
	        default:
	            return "unknown type";
		}
	}

}
