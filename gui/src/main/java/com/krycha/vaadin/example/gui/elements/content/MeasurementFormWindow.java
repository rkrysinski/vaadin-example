package com.krycha.vaadin.example.gui.elements.content;

import java.util.ArrayList;
import java.util.List;

import com.krycha.vaadin.example.entity.Measurement;
import com.vaadin.ui.Field;

public class MeasurementFormWindow extends FormWindow<Measurement> {
	private static final long serialVersionUID = -2048754645228523479L;

	public MeasurementFormWindow(Measurement bean, Class<Measurement> type) {
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
