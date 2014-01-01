package com.krycha.vaadin.example.gui.elements.content;

import org.joda.time.DateTime;

import com.krycha.vaadin.example.entity.Incident;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.Converter;
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
		date.setDateFormat(Incident.DATE_FORMAT);
		date.setImmediate(true);
		date.setValidationVisible(false);

		date.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 3498810855361325822L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				setValue(new DateTime(date.getValue()), false);
			}
		});

		addValidator(new Validator() {
			private static final long serialVersionUID = 5852643524016972910L;

			@Override
			public void validate(Object value) throws InvalidValueException {
				date.validate();
			}
		});
	}

	@SuppressWarnings("rawtypes")
	@Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setDate((DateTime) newDataSource.getValue());
    }

    @Override
    public void setValue(DateTime newValue) throws ReadOnlyException,
            Converter.ConversionException {
        super.setValue(newValue);
        setDate(newValue);
    }

	private void setDate(DateTime newValue) {
		date.setValue(newValue != null ? newValue.toDate() : null);
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
