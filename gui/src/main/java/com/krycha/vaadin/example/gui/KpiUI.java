package com.krycha.vaadin.example.gui;

import javax.servlet.annotation.WebServlet;

import com.krycha.vaadin.example.gui.elements.menu.MenuElement;
import com.krycha.vaadin.example.gui.elements.menu.MenuEvent;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
@StyleSheet("http://fonts.googleapis.com/css?family=Telex")
@SuppressWarnings("serial")
public class KpiUI extends UI {

	public static final String PERSISTENCE_UNIT = "derbydao";
	public static final String MAIN_NAV = "main";
	private Navigator navigator;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = KpiUI.class, widgetset = "com.krycha.vaadin.example.gui.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		navigator = new Navigator(this, this);

		MainView mainView = new MainView();
		mainView.getMenu().addMenuListener(new MenuEvent() {
			@Override
			public void menuClick(MenuElement selection) {
				navigator.navigateTo(MAIN_NAV + "/" + selection.getItemName());
			}
		});

		navigator.addView("", mainView);
		navigator.addView(MAIN_NAV, mainView);
	}

}
