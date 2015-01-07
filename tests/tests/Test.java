package tests;

import org.usb4java.Context;
import org.usb4java.Device;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;
import src.DeviceUtils;

public class Test {

	public static void main(String[] args) {
		Context context = new Context();
		int result = LibUsb.init(context);
		if (result != LibUsb.SUCCESS) {
			throw new LibUsbException("Unable to initialize libusb.", result);
		}
		//36FC9E60-C465-11CF-8056-444553540000
		DeviceUtils deviceUtils = new DeviceUtils();
		Device d = deviceUtils.findDevice((short)0x0424, (short)0x9512);
		System.out.println(d);
	}
}
