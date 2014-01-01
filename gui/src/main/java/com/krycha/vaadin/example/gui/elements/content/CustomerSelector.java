package com.krycha.vaadin.example.gui.elements.content;

import com.krycha.vaadin.example.entity.Customer;
import com.krycha.vaadin.example.gui.KpiUI;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;

public class CustomerSelector extends CustomField<Customer> {
	private static final long serialVersionUID = -399862732517983430L;
	private ComboBox customer = new ComboBox();
	private JPAContainer<Customer> container;

	public CustomerSelector() {
		super();

		container = JPAContainerFactory.make(Customer.class, KpiUI.PERSISTENCE_UNIT);
		customer.setContainerDataSource(container);
		customer.setItemCaptionPropertyId("shortName");
		customer.setImmediate(true);

		customer.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 6668702676423757136L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
                if (customer.getValue() == null) {
                    setValue(null, false);
                } else {
                	Customer entity = container.getItem(customer.getValue()).getEntity();
                    setValue(entity, false);
                }
			}
		});
	}


    @SuppressWarnings("rawtypes")
	@Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setCustomer((Customer) newDataSource.getValue());
    }

    @Override
    public void setValue(Customer newValue) throws ReadOnlyException,
            Converter.ConversionException {
        super.setValue(newValue);
        setCustomer(newValue);
    }

	private void setCustomer(Customer newValue) {
		customer.setValue(newValue != null ? newValue.getId() : null);
	}


	@Override
	protected Component initContent() {
        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(customer);
        return cssLayout;
	}

	@Override
	public Class<? extends Customer> getType() {
		return Customer.class;
	}

}
