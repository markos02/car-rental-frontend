package com.kodilla.carrentalfrontend.domain;

import com.kodilla.carrentalfrontend.MainView;
import com.kodilla.carrentalfrontend.domain.enums.OrderStatus;
import com.kodilla.carrentalfrontend.dto.ClientDto;
import com.kodilla.carrentalfrontend.dto.OrderDto;
import com.kodilla.carrentalfrontend.service.CarService;
import com.kodilla.carrentalfrontend.service.ClientService;
import com.kodilla.carrentalfrontend.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.component.checkbox.CheckboxGroup;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderForm extends FormLayout {

    private final MainView mainView;
    private final OrderService orderService;
    private final ClientService clientService;
    private final CarService carService;

    private final ComboBox<Integer> clientId = new ComboBox<>("Client");
    private final Map<Integer, String> clientList;
    private final ComboBox<Integer> carId = new ComboBox<>("Car");
    private final DatePicker dateFrom = new DatePicker("Start date");
    private final DatePicker dateTo = new DatePicker("End date");
    private final CheckboxGroup<String> options = new CheckboxGroup<>();
    private final Button checkAvailable = new Button("Find available cars");
    private final Button makeReservation = new Button("Make reservation");
    private final Button addNewClient = new Button("New client");
    private final ClientForm clientForm;

    private final Binder<OrderDto> binder = new Binder<>(OrderDto.class);

    public OrderForm(MainView mainView, OrderService orderService, ClientService clientService, CarService carService) {
        this.mainView = mainView;
        this.orderService = orderService;
        this.clientService = clientService;
        this.carService = carService;
        this.clientForm = new ClientForm(this, clientService);
        this.clientList = new HashMap<>();

        refreshClientList();
        refreshCarList();

        dateFrom.setInitialPosition(LocalDate.now());
        dateTo.setInitialPosition(LocalDate.now());

        options.setLabel("Extra Options");
        options.setItems("Child Seat", "GPS", "Extra driver");

        binder.bindInstanceFields(this);

        addNewClient.addClickListener(event -> clientForm.setClient(new ClientDto()));
        addNewClient.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        checkAvailable.addClickListener(event -> check(dateFrom.getValue(), dateTo.getValue()));
        checkAvailable.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        makeReservation.addClickListener(event -> makeReservation());
        makeReservation.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout buttonBar = new VerticalLayout(addNewClient, checkAvailable, makeReservation);
        VerticalLayout column1 = new VerticalLayout(clientId, dateFrom, options);
        VerticalLayout column2 = new VerticalLayout(carId, dateTo, buttonBar);

        HorizontalLayout orderLayout = new HorizontalLayout(column1, column2, clientForm);

        add(orderLayout);

        clientForm.setClient(null);
    }

    private void check(LocalDate dateFrom, LocalDate dateTo) {
        mainView.showCars(carService.getAvailableCars(dateFrom, dateTo));
    }

    private void makeReservation() {
        //OrderDto orderDto = binder.getBean();
        Set<String> selectedOptions = options.getSelectedItems();
        OrderDto orderDto = new OrderDto.OrderDtoBuilder()
                .orderId(0)
                .clientId(clientId.getValue())
                .rentalId(0)
                .carId(carId.getValue())
                .dateFrom(dateFrom.getValue())
                .dateTo(dateTo.getValue())
                .status(OrderStatus.IN_PROCESS.toString())
                .childSeat(selectedOptions.contains("Child Seat"))
                .gps(selectedOptions.contains("GPS"))
                .extraDriver(selectedOptions.contains("Extra driver"))
                .fuelLevel(1.0)
                .build();

        System.out.println(orderDto);
        orderService.makeReservation(orderDto);
        mainView.showAllCars();
    }

    public void refreshClientList() {
        Map<Integer, String> newClientList = new HashMap<>();
        clientService.getAllClients().stream()
                .map(c -> newClientList.put(c.getClientId(), c.getFirstname() + " " + c.getLastname()))
                .collect(Collectors.toList());
        clientId.setItems(newClientList.keySet());
        clientId.setItemLabelGenerator(newClientList::get);
        this.clientList.clear();
        this.clientList.putAll(newClientList);
    }

    public void refreshCarList() {
        Map<Integer, String> carList = new HashMap<>();
        carService.getCars().stream()
                .map(c -> carList.put(c.getCarId(), c.getModel() + c.getLicensePlate()))
                .collect(Collectors.toList());
        carId.setItems(carList.keySet());
        carId.setItemLabelGenerator(c -> carList.get(c));
    }
}