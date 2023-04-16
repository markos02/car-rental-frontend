package com.kodilla.carrentalfrontend.domain;

import com.kodilla.carrentalfrontend.MainView;
import com.kodilla.carrentalfrontend.dto.CreateCarDto;
import com.kodilla.carrentalfrontend.service.CarGroupService;
import com.kodilla.carrentalfrontend.service.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CarForm extends FormLayout {

    private final MainView mainView;
    private final CarService carService;

    private final TextField model = new TextField("Model");
    private final ComboBox<Integer> carGroupId = new ComboBox<>("Car group");
    private final TextField licensePlate = new TextField("License Plate");
    private final ComboBox<String> fuelType = new ComboBox<>("Fuel Type");
    private final ComboBox<String> transmission = new ComboBox<>("Transmission");
    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");

    private final Binder<CreateCarDto> binder = new Binder<>(CreateCarDto.class);

    public CarForm(MainView mainView, CarService carService, CarGroupService carGroupService) {
        this.mainView = mainView;
        this.carService = carService;

        Map<Integer, String> carGroupsList = new HashMap<>();
        carGroupService.getAllCarGroups().stream()
                .map(group -> carGroupsList.put(group.getCarGroupId(), group.getName()))
                .collect(Collectors.toList());

        carGroupId.setItems(carGroupsList.keySet());
        carGroupId.setItemLabelGenerator(group -> carGroupsList.get(group));

        fuelType.setItems("GASOLINE", "DIESEL", "ELECTRIC", "HYBRID");
        transmission.setItems("MANUAL", "AUTOMATIC");
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addClickListener(event -> cancel());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY,
                ButtonVariant.LUMO_ERROR);

        HorizontalLayout buttonBar = new HorizontalLayout(save, cancel);

        add(model, carGroupId, licensePlate, fuelType, transmission, buttonBar);
    }

    private void save() {
        CreateCarDto createCarDto = binder.getBean();
        carService.saveNewCar(createCarDto);
        mainView.showAllCars();
    }

    private void cancel() {
        setCar(null);
    }

    public void setCar(CreateCarDto createCarDto) {
        binder.setBean(createCarDto);

        if (createCarDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            model.focus();
        }
    }
}
