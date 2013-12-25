package com.krycha.vaadin.example.gui;

import com.krycha.vaadin.example.gui.elements.Content;
import com.krycha.vaadin.example.gui.elements.Footer;
import com.krycha.vaadin.example.gui.elements.Header;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout {

	private static final long serialVersionUID = -2382953812809122378L;
	private Header header;
	private Content content;
	private Footer footer;
	private UI ui;

	public MainView(UI ui) {
		super();
		this.ui = ui;
		buildMainArea();
	}

	private void buildMainArea() {
		header = new Header();
		content = new Content(ui);
		footer = new Footer();

		addComponent(header);
		addComponent(content);
		addComponent(footer);
	}
}
