package com.lauder.app.ecommapp.controller;



import com.lauder.app.ecommapp.dto.request.product.ProductRequest;
import com.lauder.app.ecommapp.dto.response.product.ProductResponse;
import com.lauder.app.ecommapp.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;



    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> products() {
        logger.info("Get all products");
        try {
            logger.info("Successfully get all products ");
            return ResponseEntity.ok(productService.getAllProducts());
        } catch (Exception e) {
            logger.info("error in fetching all products");
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/products/{id}")
    ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        logger.info("Fetching product by id: {} ",  id);
        try {
            logger.info("Successfully found product");
            ProductResponse product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            logger.info("error in fetching product by id: {}  message: {}", id , e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/products/add")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        logger.info("Request to create a new product: {}", productRequest);

        try {
            logger.info("Successful add Product to collection" );

            ProductResponse results = productService.createProduct(productRequest);
            return ResponseEntity.created(new URI("api/new/product" + results.getProductId()))
                    .body(results);
        } catch (Exception e) {

            logger.info("failure to add to the product: {}", productRequest);
            return ResponseEntity.badRequest().build();
        }


    }

    @PutMapping("/products/update/{id}")
    public ResponseEntity<ProductResponse> updatedProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long id) {
        logger.info("Request to update new product: {}", productRequest);
        if (productRequest.getProductId() == null) {
            productRequest.setProductId(id);
        } else if (!productRequest.getProductId().equals(id)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(productService.updateProduct(id,productRequest));
    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        logger.info("Request to delete product id: {}", id);

        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

           logger.info("failing to fetch product to delete by id: {}, message: {}", id, e.getMessage());
           return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/products/price-range")
    public ResponseEntity<Page<ProductResponse>> priceRangeBetweenProduct(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice, Pageable pageable) {
        logger.info("filtering Product by min price: {} and max price: {} ", minPrice, maxPrice);

        try {

            logger.info("Successful find price range between {} and {}", minPrice, maxPrice);
            Page<ProductResponse> products = productService.findProductsByPriceRange(minPrice, maxPrice, pageable);
            return ResponseEntity.ok(products);
        } catch (Exception e) {

            logger.info("Cannot find products between {} and {}, message: {}", minPrice, maxPrice, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
