package com.krycha.vaadin.example.gui.elements.content;

import org.joda.time.DateTime;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.PopupDateField;

public class DateSelector extends CustomField<DateTime> {
	private static final long serialVersionUID = -2616294344681326671L;

	private PopupDateField date = new PopupDateField();

	public DateSelector() {
		super();
		date.setValue((new DateTime()).toDate());
		date.setResolution(Resolution.MONTH);

		date.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 3498810855361325822L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				setValue(new DateTime(date.getValue()), false);
			}
		});
	}

	@Override
	protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(date);
        return cssLayout;
	}

	@Override
	public Class<? extends DateTime> getType() {
		return DateTime.class;
	}

}
