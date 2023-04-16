package com.kodilla.carrentalfrontend.domain;

import com.kodilla.carrentalfrontend.MainView;
import com.kodilla.carrentalfrontend.dto.CarGroupDto;
import com.kodilla.carrentalfrontend.service.CarGroupService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class CarGroupForm extends FormLayout {

    private final MainView mainView;
    private final CarGroupService carGroupService;

    private final Grid<CarGroupDto> grid = new Grid<>(CarGroupDto.class);
    private final TextField name = new TextField("Group Name");
    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");

    private final Binder<CarGroupDto> binder = new Binder<>(CarGroupDto.class);

    public CarGroupForm(MainView mainView, CarGroupService carGroupService) {
        this.mainView = mainView;
        this.carGroupService = carGroupService;

        grid.setColumns("name");
        grid.getColumnByKey("name").setHeader("Car Groups");
        grid.setItems(carGroupService.getAllCarGroups());
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addClickListener(event -> cancel());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY,
                ButtonVariant.LUMO_ERROR);

        add(grid, name, save, cancel);
    }

    private void save() {
        CarGroupDto carGroupDto = binder.getBean();
        carGroupService.addNewGroup(carGroupDto);
        grid.setItems(carGroupService.getAllCarGroups());
    }

    private void cancel() {
        setCarGroup(null);
    }

    public void setCarGroup(CarGroupDto carGroupDto) {
        binder.setBean(carGroupDto);

        if (carGroupDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }
}
