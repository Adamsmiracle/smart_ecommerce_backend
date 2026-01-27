package com.amalitech.smartEcommerce.domain.product.mapper;

import com.amalitech.smartEcommerce.domain.product.dto.ProductResponse;
import com.amalitech.smartEcommerce.domain.product.dto.ProductItemRequest;
import com.amalitech.smartEcommerce.domain.product.dto.ProductItemResponse;
import com.amalitech.smartEcommerce.domain.product.dto.ProductRequest;
import com.amalitech.smartEcommerce.domain.product.entity.Product;
import com.amalitech.smartEcommerce.domain.product.entity.ProductItem;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductResponse toProductResponse(Product p) {
        if (p == null) return null;
        ProductResponse r = new ProductResponse();
        r.setId(p.getId());
        r.setName(p.getName());
        r.setDescription(p.getDescription());
        r.setProductImage(p.getProductImage());
        return r;
    }

    public static ProductItemResponse toItemResponse(ProductItem i) {
        if (i == null) return null;
        ProductItemResponse r = new ProductItemResponse();
        r.setId(i.getId());
        r.setSku(i.getSku());
        r.setImage(i.getImage());
        r.setPrice(i.getPrice() == null ? null : i.getPrice().toPlainString());
        r.setQtyInStock(i.getQtyInStock());
        return r;
    }

    public static ProductResponse toDetailResponse(Product p, List<ProductItem> items) {
        if (p == null) return null;
        ProductResponse d = new ProductResponse();
        d.setId(p.getId());
        d.setName(p.getName());
        d.setDescription(p.getDescription());
        d.setProductImage(p.getProductImage());
        if (p.getCategory() != null) {
            d.setCategoryId(p.getCategory().getId());
            d.setCategoryName(p.getCategory().getName());
        }
        d.setItems(items == null ? null : items.stream().map(ProductMapper::toItemResponse).collect(Collectors.toList()));
        return d;
    }

    public static ProductItem fromItemRequest(ProductItemRequest req, Product product) {
        if (req == null) return null;
        ProductItem item = new ProductItem();
        item.setProduct(product);
        item.setSku(req.getSku());
        item.setPrice(req.getPrice());
        item.setQtyInStock(req.getQtyInStock());
        item.setImage(req.getImage());
        return item;
    }

    public static Product fromProductRequest(ProductRequest req) {
        if (req == null) return null;
        Product p = new Product();
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setProductImage(req.getProductImage());
        return p;
    }
}
