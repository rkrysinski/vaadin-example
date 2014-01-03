package com.krycha.vaadin.example.gui.elements.menu;

import java.io.Serializable;

public interface MenuEvent extends Serializable {
	void menuClick(MenuElement selection);
}
