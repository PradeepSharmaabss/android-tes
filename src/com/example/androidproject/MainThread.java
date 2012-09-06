package com.example.androidproject;

public class MainThread extends Thread {

 // flag to hold game state
	private boolean running;
	public void setRunning(boolean running) 
	{
		this.running = running;
	}
	
	@Override
	public void run() 
	{
		while (running) 
		{
			System.out.println("update");
		   // update game state
		   // render state to the screen
		}
	}
}

