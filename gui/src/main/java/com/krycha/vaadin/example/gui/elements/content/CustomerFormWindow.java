package com.krycha.vaadin.example.gui.elements.content;

import java.util.ArrayList;
import java.util.List;

import com.krycha.vaadin.example.entity.Customer;
import com.vaadin.ui.Field;

public class CustomerFormWindow extends FormWindow<Customer> {

	private static final long serialVersionUID = -3688980270973043542L;

	public CustomerFormWindow(Customer bean, Class<Customer> type) {
		super(bean, type);
	}

	@Override
	public List<Field<?>> getFields() {
		List<Field<?>> fields = new ArrayList<Field<?>>();
		fields.add(binder.buildAndBind("Short Name", "shortName"));
		fields.add(binder.buildAndBind("Description", "description"));
		return fields;
	}

	@Override
	public String getCaption() {
		return bean.getShortName();
	}

}
