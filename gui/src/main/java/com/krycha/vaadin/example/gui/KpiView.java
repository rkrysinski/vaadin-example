package com.krycha.vaadin.example.gui;

import com.krycha.vaadin.example.entity.Customer;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;

public class KpiView extends CustomComponent {

	private AbsoluteLayout mainLayout;
	private Table personTable;

	private JPAContainer<Customer> customers;

	public KpiView() {
		customers = JPAContainerFactory.make(Customer.class, KpiUI.PERSISTENCE_UNIT);

		buildMainLayout();
		setCompositionRoot(mainLayout);

	}

	private AbsoluteLayout buildMainLayout() {
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// table_1
		personTable = new Table(null, customers);
        personTable.setSelectable(true);
        personTable.setImmediate(true);
        personTable.setSizeFull();
		mainLayout.addComponent(personTable, "top:0.0px;");

		return mainLayout;
	}

}
