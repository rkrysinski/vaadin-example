package com.krycha.vaadin.example.gui.elements.content;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.krycha.vaadin.example.entity.Incident;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Field;

public class IncidentFormWindow extends FormWindow<Incident> {
	private static final long serialVersionUID = -6482687887153589537L;
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(Incident.DATE_FORMAT,
			VaadinSession.getCurrent().getLocale());

	public IncidentFormWindow(Incident bean, Class<Incident> type) {
		super(bean, type);
	}

	@Override
	public List<Field<?>> getFields() {
		List<Field<?>> fields = new ArrayList<Field<?>>();
		fields.add(binder.buildAndBind("customer"));
		fields.add(binder.buildAndBind("measurement"));
		fields.add(binder.buildAndBind("date"));
		fields.add(binder.buildAndBind("count"));
		return fields;
	}

	@Override
	public String getCaption() {
		if (bean.getCustomer() == null) {
			return "";
		}
		return bean.getCustomer().getShortName() + "-" + bean.getMeasurement().getShortName()
				+ "-" + DATE_FORMAT.format(bean.getDate().toDate());
	}

}
