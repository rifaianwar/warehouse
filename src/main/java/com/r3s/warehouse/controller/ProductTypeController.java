package com.r3s.warehouse.controller;

import com.r3s.warehouse.model.request.ProductTypeRq;
import com.r3s.warehouse.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product-type")
@SecurityRequirement(name = "bearerAuth")
public class ProductTypeController {
    @Autowired
    private InventoryService inventoryService;

    @Operation(description = "Endpoint ini membutuhkan Bearer Token dan Role ADMIN/USER",
            summary = "Endpoint untuk menambahkan Tipe Produk")
    @PostMapping("/add")
    public ResponseEntity<?> addProductType(@RequestBody ProductTypeRq inMsg) {
        Object object = inventoryService.addProductType(inMsg);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(description = "Endpoint ini membutuhkan Bearer Token dan Role ADMIN/USER",
            summary = "Endpoint untuk update Tipe Produk")
    @PutMapping("/update")
    public ResponseEntity<?> updateProductType(@RequestParam long productTypeId,
                                            @RequestBody ProductTypeRq inMsg) {
        Object object = inventoryService.updateProductType(productTypeId,inMsg);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(description = "Endpoint ini membutuhkan Bearer Token dan Role ADMIN/USER",
            summary = "Endpoint untuk delete Tipe Produk")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProductType(@RequestParam long productTypeId) {
        Object object = inventoryService.deleteProductType(productTypeId);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(description = "Endpoint ini membutuhkan Bearer Token dan Role ADMIN/USER",
            summary = "Endpoint untuk menampilkan Tipe Produk by name")
    @GetMapping("/get")
    public ResponseEntity<?> getProductType(ProductTypeRq inMsg) {
        Object object = inventoryService.getProductType(inMsg);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(description = "Endpoint ini membutuhkan Bearer Token dan Role ADMIN/USER",
            summary = "Endpoint untuk menampilkan list Tipe Produk")
    @GetMapping("/list")
    public ResponseEntity<?> getAllProductType() {
        Object object = inventoryService.getAllProductType();
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
