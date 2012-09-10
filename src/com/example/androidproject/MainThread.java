package com.example.androidproject;

import android.view.SurfaceHolder;

public class MainThread extends Thread {
private boolean running;
private SurfaceHolder surfaceHolder;
private Screen gamePanel;
public MainThread(SurfaceHolder surfaceHolder,Screen gamePanel)
{
	super();
	this.surfaceHolder=surfaceHolder;
	this.gamePanel=gamePanel;
}
public void setRunning(boolean running)
{
	this.running= running;
}
@Override
public void run(){
while(running)
{
	
}
}
}
