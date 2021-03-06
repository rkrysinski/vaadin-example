package com.krycha.vaadin.example.gui.elements.menu;

import com.krycha.vaadin.example.gui.elements.content.CustomersView;
import com.krycha.vaadin.example.gui.elements.content.IncidentsView;
import com.krycha.vaadin.example.gui.elements.content.MeasurementsView;

public enum MenuElement {
	INCIDENTS("Incidents", IncidentsView.class), //
	CUSTOMERS("Customers", CustomersView.class), //
	MEASUREMENTS("Measurements", MeasurementsView.class); //
//	ADMIN("Admin", AdminView.class); //

	private String itemName;
	private Class<?> itemClass;

	MenuElement(String menuName, Class<?> viewClass) {
		this.setItemName(menuName);
		this.setItemClass(viewClass);
	}

	public Class<?> getItemClass() {
		return itemClass;
	}

	public void setItemClass(Class<?> viewClass) {
		this.itemClass = viewClass;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String menuName) {
		this.itemName = menuName;
	}
}
