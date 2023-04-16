package com.kodilla.carrentalfrontend.domain;

import com.kodilla.carrentalfrontend.dto.ClientDto;
import com.kodilla.carrentalfrontend.service.ClientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ClientForm extends FormLayout {
    private final OrderForm orderForm;
    private final ClientService clientService;

    private final TextField firstName = new TextField("Firstname");
    private final TextField lastName = new TextField("Lastname");
    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");

    private final Binder<ClientDto> binder = new Binder<>(ClientDto.class);

    public ClientForm(OrderForm orderForm, ClientService clientService) {
        this.orderForm = orderForm;
        this.clientService = clientService;

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addClickListener(event -> cancel());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY,
                ButtonVariant.LUMO_ERROR);

        HorizontalLayout buttonBar = new HorizontalLayout(save, cancel);

        add(firstName, lastName, buttonBar);
    }

    private void save() {
        ClientDto clientDto = binder.getBean();
        clientService.saveNewClient(clientDto);
    }

    private void cancel() {
        setClient(null);
    }

    public void setClient(ClientDto clientDto) {
        binder.setBean(clientDto);

        if (clientDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            firstName.focus();
        }
    }
}
