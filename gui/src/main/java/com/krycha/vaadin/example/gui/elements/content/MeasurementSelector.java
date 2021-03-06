package com.krycha.vaadin.example.gui.elements.content;

import com.krycha.vaadin.example.entity.Measurement;
import com.krycha.vaadin.example.gui.KpiUI;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;

public class MeasurementSelector extends CustomField<Measurement> {
	private static final long serialVersionUID = 1588415633929354875L;
	private ComboBox measurement = new ComboBox();
	private JPAContainer<Measurement> container;

	public MeasurementSelector() {
		super();

		container = JPAContainerFactory.make(Measurement.class, KpiUI.PERSISTENCE_UNIT);
		measurement.setContainerDataSource(container);
		measurement.setItemCaptionPropertyId("shortName");
		measurement.setImmediate(true);

		measurement.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 6668702676423757136L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
                if (measurement.getValue() == null) {
                    setValue(null, false);
                } else {
                	Measurement entity = container
                            .getItem(measurement.getValue()).getEntity();
                    setValue(entity, false);
                }
			}
		});
	}

    @SuppressWarnings("rawtypes")
	@Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setMeasurement((Measurement) newDataSource.getValue());
    }

    @Override
    public void setValue(Measurement newValue) throws ReadOnlyException,
            Converter.ConversionException {
        super.setValue(newValue);
        setMeasurement(newValue);
    }

	private void setMeasurement(Measurement newValue) {
		measurement.setValue(newValue != null ? newValue.getId() : null);
	}

	@Override
	protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(measurement);
        return cssLayout;
	}

	@Override
	public Class<? extends Measurement> getType() {
		return Measurement.class;
	}

}
