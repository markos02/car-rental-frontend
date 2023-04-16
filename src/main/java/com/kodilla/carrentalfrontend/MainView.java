package com.kodilla.carrentalfrontend;

import com.kodilla.carrentalfrontend.domain.CarForm;
import com.kodilla.carrentalfrontend.domain.CarGroupForm;
import com.kodilla.carrentalfrontend.domain.OrderForm;
import com.kodilla.carrentalfrontend.dto.CarDto;
import com.kodilla.carrentalfrontend.dto.CarGroupDto;
import com.kodilla.carrentalfrontend.dto.CreateCarDto;
import com.kodilla.carrentalfrontend.service.CarGroupService;
import com.kodilla.carrentalfrontend.service.CarService;
import com.kodilla.carrentalfrontend.service.ClientService;
import com.kodilla.carrentalfrontend.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route
public class MainView extends VerticalLayout {

    private final CarService carService;
    private final CarGroupService carGroupService;
    private final OrderService orderService;
    private final ClientService clientService;
    private final Grid<CarDto> grid = new Grid<>(CarDto.class);
    private final CarForm carForm;
    private final CarGroupForm carGroupForm;
    private final OrderForm orderForm;
    private Button addNewCarGroup = new Button("Add new car group");
    private Button addNewCar = new Button("Add new car");

    public MainView(CarGroupService carGroupService, CarService carService, OrderService orderService, ClientService clientService) {
        this.carService = carService;
        this.carGroupService = carGroupService;
        this.orderService = orderService;
        this.clientService = clientService;
        this.carForm = new CarForm(this, carService, carGroupService);
        this.carGroupForm = new CarGroupForm(this, carGroupService);
        this.orderForm = new OrderForm(this, orderService, clientService, carService);
        grid.setColumns("carId", "carGroupName", "licensePlate", "fuelType", "transmission", "model");

        addNewCar.addClickListener(e -> {
            grid.asSingleSelect().clear();
            carForm.setCar(new CreateCarDto());
        });

        addNewCarGroup.addClickListener(e -> {
            grid.asSingleSelect().clear();
            carGroupForm.setCarGroup(new CarGroupDto());
        });

        HorizontalLayout toolbar = new HorizontalLayout(addNewCarGroup, addNewCar);

        HorizontalLayout mainContentRow1 = new HorizontalLayout(grid, carForm, carGroupForm);
        mainContentRow1.setSizeFull();
        grid.setSizeFull();

        HorizontalLayout mainContentRow2 = new HorizontalLayout(orderForm);

        HorizontalLayout mainContentRow3 = new HorizontalLayout(orderForm);

        add(toolbar, mainContentRow1, mainContentRow2, mainContentRow3);
        carForm.setCar(null);
        carGroupForm.setCarGroup(null);
        setSizeFull();
        showAllCars();
    }

    public void showAllCars() {
        grid.setItems(carService.getCars());
    }

    public void showCars(List<CarDto> carList) {
        grid.setItems(carList);
    }
}
