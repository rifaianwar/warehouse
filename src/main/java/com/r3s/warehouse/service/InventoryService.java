package com.r3s.warehouse.service;

import com.r3s.warehouse.entity.InboundEntity;
import com.r3s.warehouse.entity.OutboundEntity;
import com.r3s.warehouse.entity.ProductEntity;
import com.r3s.warehouse.entity.ProductTypeEntity;
import com.r3s.warehouse.model.request.InboundRq;
import com.r3s.warehouse.model.request.OutboundRq;
import com.r3s.warehouse.model.request.ProductRq;
import com.r3s.warehouse.model.request.ProductTypeRq;
import com.r3s.warehouse.model.response.GeneralRs;
import com.r3s.warehouse.repository.InboundRepository;
import com.r3s.warehouse.repository.OutboundRepository;
import com.r3s.warehouse.repository.ProductRepository;
import com.r3s.warehouse.repository.ProductTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InboundRepository inboundRepository;
    @Autowired
    private OutboundRepository outboundRepository;

    public GeneralRs addProductType(ProductTypeRq inMsg) {
        GeneralRs outMsg = new GeneralRs();
        try {
            ProductTypeEntity productTypeEntity = new ProductTypeEntity();
            productTypeEntity.setName(inMsg.getName());
            productTypeRepository.save(productTypeEntity);

            outMsg.setMessage("Success");
            outMsg.setData(productTypeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            outMsg.setMessage("Error : " + e.getMessage());
            outMsg.setData(e);
        }
        return outMsg;
    }

    public GeneralRs updateProductType(long productTypeId, ProductTypeRq inMsg) {
        GeneralRs outMsg = new GeneralRs();
        try {
            Optional<ProductTypeEntity> productType = productTypeRepository.findById(productTypeId);
            if (productType.isEmpty()) {
                outMsg.setMessage("Product type not found");
                outMsg.setData(productType);
                return outMsg;
            }
            productType.get().setName(inMsg.getName());
            productTypeRepository.save(productType.get());

            outMsg.setMessage("Success");
            outMsg.setData(productType);
        } catch (Exception e) {
            e.printStackTrace();
            outMsg.setMessage("Error : " + e.getMessage());
            outMsg.setData(e);
        }
        return outMsg;
    }

    public GeneralRs deleteProductType(long productTypeId) {
        GeneralRs outMsg = new GeneralRs();
        try {
            Optional<ProductTypeEntity> productType = productTypeRepository.findById(productTypeId);
            if (productType.isEmpty()) {
                outMsg.setMessage("Product type not found");
                outMsg.setData(productType);
                return outMsg;
            }
            productTypeRepository.deleteById(productType.get().getId());

            outMsg.setMessage("Success");
            outMsg.setData(productType);
        } catch (Exception e) {
            e.printStackTrace();
            outMsg.setMessage("Error : " + e.getMessage());
            outMsg.setData(e);
        }
        return outMsg;
    }

    public GeneralRs getProductType(ProductTypeRq inMsg) {
        GeneralRs outMsg = new GeneralRs();
        try {
            ProductTypeEntity productTypeEntity = productTypeRepository.findByName(inMsg.getName());
            if (productTypeEntity == null) {
                outMsg.setMessage("Product type not found");
                outMsg.setData(productTypeEntity);
                return outMsg;
            }

            outMsg.setMessage("Success");
            outMsg.setData(productTypeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            outMsg.setMessage("Error : " + e.getMessage());
            outMsg.setData(e);
        }
        return outMsg;
    }

    public GeneralRs getAllProductType() {
        GeneralRs outMsg = new GeneralRs();
        try {
            List<ProductTypeEntity> productTypeEntity = productTypeRepository.getAllByOrderByNameAsc();
            if (productTypeEntity == null) {
                outMsg.setMessage("Product type not found");
                outMsg.setData(productTypeEntity);
                return outMsg;
            }

            outMsg.setMessage("Success");
            outMsg.setData(productTypeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            outMsg.setMessage("Error : " + e.getMessage());
            outMsg.setData(e);
        }
        return outMsg;
    }

    public GeneralRs addProduct(ProductRq inMsg) {
        GeneralRs outMsg = new GeneralRs();
        try {
            Optional<ProductTypeEntity> productType = productTypeRepository.findById(inMsg.getProductTypeId().getId());
            if (productType.isEmpty()) {
                outMsg.setMessage("Product type not found");
                outMsg.setData(productType);
                return outMsg;
            }
            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductTypeEntity(productType.get());
            productEntity.setName(inMsg.getName());
            productEntity.setDescription(inMsg.getDescription());
            productEntity.setQuantity(inMsg.getQuantity());
           productRepository.save(productEntity);

            outMsg.setMessage("Success");
            outMsg.setData(productEntity);
        } catch (Exception e) {
            e.printStackTrace();
            outMsg.setMessage("Error : " + e.getMessage());
            outMsg.setData(e);
        }
        return outMsg;
    }

    public GeneralRs updateProduct(int productId, ProductRq inMsg) {
        GeneralRs outMsg = new GeneralRs();
        try {
            Optional<ProductTypeEntity> productType = productTypeRepository.findById(inMsg.getProductTypeId().getId());
            if (productType.isEmpty()) {
                outMsg.setMessage("Product type not found");
                outMsg.setData(productType);
                return outMsg;
            }
            Optional<ProductEntity> product = productRepository.findById(productId);
            if (product.isEmpty()) {
                outMsg.setMessage("Product not found");
                outMsg.setData(product);
                return outMsg;
            }
            product.get().setName(inMsg.getName());
            product.get().setDescription(inMsg.getDescription());
            product.get().setQuantity(inMsg.getQuantity());
            product.get().setProductTypeEntity(productType.get());
            productRepository.save(product.get());

            outMsg.setMessage("Success");
            outMsg.setData(product);
        } catch (Exception e) {
            e.printStackTrace();
            outMsg.setMessage("Error : " + e.getMessage());
            outMsg.setData(e);
        }
        return outMsg;
    }

    public GeneralRs getProductById(Long productId) {
        GeneralRs outMsg = new GeneralRs();
        try {

            Optional<ProductEntity> product = productRepository.findById(Integer.parseInt(String.valueOf(productId)));
            if (product.isEmpty()) {
                outMsg.setMessage("Product not found");
                outMsg.setData(product);
                return outMsg;
            }

            outMsg.setMessage("Success");
            outMsg.setData(product.get());
        } catch (Exception e) {
            e.printStackTrace();
            outMsg.setMessage("Error : " + e.getMessage());
            outMsg.setData(e);
        }
        return outMsg;
    }
    public GeneralRs getAllProduct() {
        GeneralRs outMsg = new GeneralRs();
        try {
            List<ProductEntity> productEntityList = productRepository.getAllByOrderByNameAsc();
            if (productEntityList == null) {
                outMsg.setMessage("Product type not found");
                outMsg.setData(productEntityList);
                return outMsg;
            }
            outMsg.setMessage("Success");
            outMsg.setData(productEntityList);
        } catch (Exception e) {
            e.printStackTrace();
            outMsg.setMessage("Error : " + e.getMessage());
            outMsg.setData(e);
        }
        return outMsg;
    }


    public GeneralRs addProductInbound(InboundRq inMsg) {
        GeneralRs outMsg = new GeneralRs();
        try {
            Optional<ProductEntity> product = productRepository.findById(Integer.parseInt(String.valueOf(inMsg.getProduct().getId())));
            if (product.isEmpty()) {
                outMsg.setMessage("Product not found");
                outMsg.setData(product);
                return outMsg;
            }
            InboundEntity productInboundEntity = new InboundEntity();
            productInboundEntity.setProductEntity(product.get());
            productInboundEntity.setQuantity(inMsg.getQuantity());
            productInboundEntity.setSource(inMsg.getSource());
            productInboundEntity.setDate(new Date());
            inboundRepository.save(productInboundEntity);

            product.get().setQuantity(product.get().getQuantity() + productInboundEntity.getQuantity());
            productRepository.save(product.get());

            outMsg.setMessage("Success");
            outMsg.setData(product);


        }catch (Exception e) {
            e.printStackTrace();
        }
        return outMsg;
    }

    public GeneralRs addProductOutbound(OutboundRq inMsg) {
        GeneralRs outMsg = new GeneralRs();
        try {
            Optional<ProductEntity> product = productRepository.findById(Integer.parseInt(String.valueOf(inMsg.getProduct().getId())));
            if (product.isEmpty()) {
                outMsg.setMessage("Product not found");
                outMsg.setData(product);
                return outMsg;
            }
            OutboundEntity productOutboundEntity = new OutboundEntity();
            productOutboundEntity.setProductEntity(product.get());
            productOutboundEntity.setQuantity(inMsg.getQuantity());
            productOutboundEntity.setDestination(inMsg.getDestination());
            productOutboundEntity.setDate(new Date());
            outboundRepository.save(productOutboundEntity);

            product.get().setQuantity(product.get().getQuantity() - productOutboundEntity.getQuantity());
            productRepository.save(product.get());

            outMsg.setMessage("Success");
            outMsg.setData(product);


        }catch (Exception e) {
            e.printStackTrace();
        }
        return outMsg;
    }
}
