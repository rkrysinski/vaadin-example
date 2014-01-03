package com.krycha.vaadin.example.gui;

import com.krycha.vaadin.example.dao.impl.DbDAOImpl;

import com.krycha.vaadin.example.gui.elements.menu.MenuElement;
import com.krycha.vaadin.example.gui.elements.menu.MenuEvent;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("mytheme")
@StyleSheet("http://fonts.googleapis.com/css?family=Telex")
public class KpiUI extends UI {

	private static final long serialVersionUID = -4025448822821536057L;
	public static final String PERSISTENCE_UNIT = DbDAOImpl.PERSISTENCE_UNIT_NAME;
	public static final String MAIN_NAV = "main";
	private Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {

		navigator = new Navigator(this, this);

		MainView mainView = new MainView();
		mainView.addMenuListener(new MenuEvent() {
			private static final long serialVersionUID = 5413314240280517854L;

			@Override
			public void menuClick(MenuElement selection) {
				navigator.navigateTo(MAIN_NAV + "/" + selection.getItemName());
			}
		});

		navigator.addView("", mainView);
		navigator.addView(MAIN_NAV, mainView);
	}

}
