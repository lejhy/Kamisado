package controller;

import java.util.Observer;

public abstract class ViewController implements Observer {
	protected Core core;

	public ViewController() {
	}

	public void setCore(Core core) {
		this.core = core;
	}
}
