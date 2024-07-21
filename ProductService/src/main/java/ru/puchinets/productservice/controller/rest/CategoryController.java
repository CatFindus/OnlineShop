package ru.puchinets.productservice.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.puchinets.productservice.model.dto.request.CategoryRequest;
import ru.puchinets.productservice.model.dto.response.CategoryResponse;
import ru.puchinets.productservice.service.CategoryService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
@Tag(name = "Category", description = "Operations related to categories in the Product Service")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Get single category by ID", description = "Returns category by its ID if exists")
    @ApiResponse(responseCode = "200", description = "Found category",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = CategoryResponse.class),
                        examples = {@ExampleObject(value =  "{\"id\": 1, \"name\": \"Electronics\", \"description\": \"Electronic gadgets and devices\"}")}))
    @ApiResponse(responseCode = "404", description = "Category not found")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @Parameter(description = "The ID of the category to retrieve") @PathVariable("id") Long id) {
        return categoryService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Get all categories", description = "Returns a paginated list of all categories available")
    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getAllCategories(
            @Parameter(description = "Pagination parameters") @RequestBody Pageable pageable) {
        return ResponseEntity.ok(categoryService.getAll(pageable));
    }

    @PostMapping
    @Operation(summary = "Create a category", description = "Create a new category and return the created object")
    @ApiResponse(responseCode = "201", description = "Category created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation =CategoryResponse.class)))
    public ResponseEntity<CategoryResponse> createCategory(
            @Parameter(description = "Category request object to be created", required = true) @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category", description = "Update category and return updated category. Require ID of category and category request object for update")
    @ApiResponse(responseCode = "200", description = "Category updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class)))
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<CategoryResponse> updateCategory(@Parameter(description = "ID of category", required = true) @PathVariable Long id,
                                                           @Parameter(description = "Category request object to be updated", required = true) @RequestBody CategoryRequest request) {
        return categoryService.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a category", description = "Delete category by ID")
    @ApiResponse(responseCode = "204", description = "Category deleted successfully")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@Parameter(description = "ID of category", required = true)
                                                   @PathVariable("id") Long id) {
        boolean deleted = categoryService.delete(id);
        if(deleted) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }
}
