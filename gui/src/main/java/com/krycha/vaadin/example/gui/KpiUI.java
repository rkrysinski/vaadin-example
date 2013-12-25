package com.krycha.vaadin.example.gui;

import javax.servlet.annotation.WebServlet;

import com.krycha.vaadin.example.entity.Customer;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@StyleSheet("http://fonts.googleapis.com/css?family=Telex")
@SuppressWarnings("serial")
public class KpiUI extends UI {

	public static final String PERSISTENCE_UNIT = "derbydao";

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = KpiUI.class, widgetset = "com.krycha.vaadin.example.gui.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		setContent(new MainView(this));
	}

}
