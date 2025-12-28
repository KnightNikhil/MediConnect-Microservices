package com.mediconnect.service.BookingService.controller;

import com.mediconnect.service.BookingService.service.InventoryService;
import com.mediconnect.service.common_entities.dto.BookingInventoryRequestDto;
import com.mediconnect.service.common_entities.entity.Enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/booking")
public class BookingInventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/addInventory")
    @PreAuthorize("hasRole('DOCTOR')")
    public void addInventory(@RequestBody BookingInventoryRequestDto bookingInventoryRequestDto){
        inventoryService.addInventory(bookingInventoryRequestDto);
    }

}
