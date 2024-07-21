package ru.puchinets.productservice.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.puchinets.productservice.model.dto.request.ProductRequest;
import ru.puchinets.productservice.model.dto.response.ProductResponse;
import ru.puchinets.productservice.service.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "Operations related to products in the Product Service")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get product by ID", description = "Find product by ID. Require ID of product")
    @ApiResponse(responseCode = "200", description = "Product found successful",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@Parameter(description = "ID of product")
                                                          @PathVariable("id") Long id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all products", description = "Returns a paginated list of all products] available")
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(@Parameter(description = "Pagination parameters")
                                                                @RequestBody Pageable pageable) {
        return ResponseEntity.ok(productService.getAll(pageable));
    }

    @Operation(summary = "Create a product", description = "Create a new product and return the created object")
    @ApiResponse(responseCode = "201", description = "Product created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)))
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Parameter(description = "Product request object to be created", required = true)
                                                         @RequestBody ProductRequest request) {
        ProductResponse response = productService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a product", description = "Update a product and return the created object")
    @ApiResponse(responseCode = "200", description = "Product updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class)))
    @ApiResponse(responseCode = "404", description = "Product was not found")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@Parameter(description = "ID of product", required = true)
                                                         @PathVariable("id") Long id,
                                                         @Parameter(description = "Product request object", required = true)
                                                         @RequestBody ProductRequest request) {
        return productService.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a product", description = "Delete a product by ID")
    @ApiResponse(responseCode = "204", description = "Product deleted successfully")
    @ApiResponse(responseCode = "404", description = "Product was not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@Parameter(description = "ID of product")
                                              @PathVariable("id") Long id) {
        boolean deleted = productService.delete(id);
        if (deleted) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }
}
