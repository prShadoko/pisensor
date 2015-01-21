package com.polytech.raspberry;

public class CheckBdd implements Runnable {
	
	private boolean value;
	
	private DataBase db;

	public CheckBdd(){
		db = new DataBase();
	}

	public void run() {
		while(true){
			value = db.getLastConnector().isPushed();
			if(value){
				App.writeData("d");
			}else{
				App.writeData("c");
			}
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
