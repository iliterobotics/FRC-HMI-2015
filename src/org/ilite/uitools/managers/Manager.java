package org.ilite.uitools.managers;

import javafx.scene.Node;

public abstract class Manager {
	public abstract Node[] buildWidgets(int size);
	public abstract String getName();
	public abstract String getDesc();
}

