package controller;

import java.util.Observer;

public abstract class ViewController implements Observer {
	protected Core core;

	public ViewController() {
	}

	public abstract void setCore(Core core);
}
