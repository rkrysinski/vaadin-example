package com.krycha.vaadin.example.gui;

import com.krycha.vaadin.example.gui.elements.Content;
import com.krycha.vaadin.example.gui.elements.Footer;
import com.krycha.vaadin.example.gui.elements.Header;
import com.krycha.vaadin.example.gui.elements.menu.MenuEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout implements View {

	private static final long serialVersionUID = -2382953812809122378L;
	private Header header;
	private Content content;
	private Footer footer;

	public MainView() {
		super();
		buildMainArea();
	}

	private void buildMainArea() {
		setSizeFull();
		header = new Header();
		content = new Content();
		footer = new Footer();

		addComponent(header);
		addComponent(content);
		addComponent(footer);

		setExpandRatio(content, 1);
		content.setSizeFull();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		content.enter(event);
	}

	public void addMenuListener(MenuEvent menuEvent) {
		content.getMenu().addMenuListener(menuEvent);
	}
}
