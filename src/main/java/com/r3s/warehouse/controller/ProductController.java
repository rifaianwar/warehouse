package com.r3s.warehouse.controller;

import com.r3s.warehouse.model.request.ProductRq;
import com.r3s.warehouse.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {
    @Autowired
    private InventoryService inventoryService;

    @Operation(description = "Endpoint ini membutuhkan Bearer Token dan Role ADMIN/USER",
            summary = "Endpoint untuk menambahkan Produk")
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductRq inMsg) {
        Object object = inventoryService.addProduct(inMsg);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(description = "Endpoint ini membutuhkan Bearer Token dan Role ADMIN/USER",
            summary = "Endpoint untuk update Produk")
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestParam int productId, @RequestBody ProductRq inMsg) {
        Object object = inventoryService.updateProduct(productId, inMsg);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(description = "Endpoint ini membutuhkan Bearer Token dan Role ADMIN/USER",
            summary = "Endpoint untuk menampilkan Produk by id produk")
    @GetMapping("/getById")
    public ResponseEntity<?> getProduct(@RequestParam Long productId) {
        Object object = inventoryService.getProductById(productId);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(description = "Endpoint ini membutuhkan Bearer Token dan Role ADMIN/USER",
            summary = "Endpoint untuk menampilkan list Produk")
    @GetMapping("/list")
    public ResponseEntity<?> getProductList() {
        Object object = inventoryService.getAllProduct();
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
