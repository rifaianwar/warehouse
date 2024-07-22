package com.r3s.warehouse.controller;

import com.r3s.warehouse.model.request.OutboundRq;
import com.r3s.warehouse.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/outbound")
@SecurityRequirement(name = "bearerAuth")
public class OutboundController {

    @Autowired
    private InventoryService inventoryService;

    @Operation(description = "Endpoint ini membutuhkan Bearer Token dan Role ADMIN/USER",
            summary = "Endpoint untuk menambahkan Produk yang keluar")
    @PostMapping("/add")
    public ResponseEntity<?> addProductOutbound(@RequestBody OutboundRq inMsg) {
        Object object = inventoryService.addProductOutbound(inMsg);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
