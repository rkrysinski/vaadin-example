package com.krycha.vaadin.example.gui.elements.content;

import java.util.ArrayList;
import java.util.List;

import com.krycha.vaadin.example.entity.Incident;
import com.vaadin.ui.Field;

public class IncidentFormWindow extends FormWindow<Incident> {
	private static final long serialVersionUID = -6482687887153589537L;

	public IncidentFormWindow(Incident bean, Class<Incident> type) {
		super(bean, type);
	}

	@Override
	public List<Field<?>> getFields() {
		List<Field<?>> fields = new ArrayList<Field<?>>();
		fields.add(binder.buildAndBind("Customer", "customer"));
		fields.add(binder.buildAndBind("Measurement", "measurement"));
		return fields;
	}

	@Override
	public String getCaption() {
		if (bean.getCustomer() == null) {
			return "";
		}
		return bean.getCustomer().getShortName() + "-" + bean.getMeasurement().getShortName()
				+ "-" + bean.getDate();
	}

}
