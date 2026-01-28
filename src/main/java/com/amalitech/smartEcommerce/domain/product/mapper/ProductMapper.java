package com.amalitech.smartEcommerce.domain.product.mapper;

import com.amalitech.smartEcommerce.domain.product.dto.ProductResponse;
import com.amalitech.smartEcommerce.domain.product.dto.ProductItemRequest;
import com.amalitech.smartEcommerce.domain.product.dto.ProductItemResponse;
import com.amalitech.smartEcommerce.domain.product.dto.ProductRequest;
import com.amalitech.smartEcommerce.domain.product.entity.Product;
import com.amalitech.smartEcommerce.domain.product.entity.ProductItem;

import java.util.List;

public class ProductMapper {

    public static ProductResponse toProductResponse(Product p) {
        if (p == null) return null;
        ProductResponse r = new ProductResponse();
        r.setId(p.getId());
        r.setName(p.getName());
        r.setDescription(p.getDescription());
        r.setProductImage(p.getProductImage());
        if (p.getCategory() != null) {
            r.setCategoryName(p.getCategory().getName());
        }


        r.setPrice(p.getItems().getFirst().getPrice().toString());

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
            d.setCategoryName(p.getCategory().getName());
        }
        java.util.Optional<java.math.BigDecimal> minPrice2 = (items == null) ? java.util.Optional.empty()
                : items.stream().map(ProductItem::getPrice).filter(java.util.Objects::nonNull).min(java.math.BigDecimal::compareTo);
        minPrice2.ifPresent(mp -> d.setPrice(mp.toPlainString()));
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
