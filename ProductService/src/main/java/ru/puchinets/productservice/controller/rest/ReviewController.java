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
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.puchinets.productservice.model.dto.request.ReviewRequest;
import ru.puchinets.productservice.model.dto.response.ReviewResponse;
import ru.puchinets.productservice.service.ReviewService;

import static ru.puchinets.productservice.Constants.PAGINATION_EXAMPLE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
@Tag(name = "Review", description = "Operations related to reviews in the Product Service")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Get review by ID", description = "Find review by ID. Require ID of review")
    @ApiResponse(responseCode = "200", description = "Review found successful",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewResponse.class)))
    @ApiResponse(responseCode = "404", description = "Review not found")
    @GetMapping("/{id}")
    ResponseEntity<ReviewResponse> getById(@Parameter(description = "ID of product")
                                           @PathVariable("id") Long id) {
        return reviewService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/products/{id}")
    @Operation(summary = "Get all reviews by product ID", description = "Return paginated list of reviews of product. Require ID of product. ")
    public ResponseEntity<Page<ReviewResponse>> getAllReviewsByProductId(@Parameter(description = "ID of product") @PathVariable("id") Long productId,
                                                                         @Parameter(name = "pagination parameter",
                                                                                 description = "Pagination and sorting parameters", example = PAGINATION_EXAMPLE)
                                                                         @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(reviewService.findAllByProductId(productId, pageable));
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get all reviews by user ID", description = "\"Return paginated list of reviews of user. Require ID of user.")
    public ResponseEntity<Page<ReviewResponse>> getAllReviewsForUser(@Parameter(description = "ID of user") @PathVariable("id") Long userId,
                                                                     @Parameter(name = "pagination parameter",
                                                                             description = "Pagination and sorting parameters", example = PAGINATION_EXAMPLE)
                                                                     @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(reviewService.findAllForUser(userId, pageable));
    }

    @Operation(summary = "Create a review", description = "Create a new review and return the created object")
    @ApiResponse(responseCode = "201", description = "Review created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewResponse.class)))
    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest request) {
        ReviewResponse response = reviewService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update comment in review", description = "Update field comment in review. Require ID of review")
    @ApiResponse(responseCode = "200", description = "Review updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewResponse.class)))
    @ApiResponse(responseCode = "404", description = "Review not found")
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(@Parameter(description = "ID of review")
                                                       @PathVariable("id") Long id,
                                                       @RequestBody ReviewRequest request) {
        return reviewService.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete review", description = "Delete review by id. Require ID of review")
    @ApiResponse(responseCode = "204", description = "Review deleted successfully")
    @ApiResponse(responseCode = "404", description = "Review not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@Parameter(description = "ID of review") @PathVariable("id") Long id) {
        boolean isDeleted = reviewService.delete(id);
        return isDeleted ? ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
