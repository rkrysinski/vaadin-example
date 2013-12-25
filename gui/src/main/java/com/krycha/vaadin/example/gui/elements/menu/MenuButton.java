package com.krycha.vaadin.example.gui.elements.menu;

import com.vaadin.ui.NativeButton;

public class MenuButton extends NativeButton {
	private static final long serialVersionUID = -5179176461892206768L;

	MenuElement menuElement;

	public MenuButton(MenuElement menuElement) {
		super();
		this.menuElement = menuElement;
	}

	public MenuElement getMenuElement() {
		return menuElement;
	}

}
