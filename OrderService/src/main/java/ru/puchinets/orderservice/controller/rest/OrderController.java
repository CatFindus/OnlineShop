package ru.puchinets.orderservice.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.puchinets.orderservice.model.dto.request.OrderRequest;
import ru.puchinets.orderservice.model.dto.response.OrderResponse;
import ru.puchinets.orderservice.service.OrderService;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@Tag(name = "Order", description = "Operations related to orders in the Order Service")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Create a order", description = "Create a new order and return the created object")
    @ApiResponse(responseCode = "201", description = "Order created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class)))
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Parameter(description = "Order request object to be created", required = true)
                                                     @RequestBody OrderRequest request) {
        OrderResponse response = orderService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get single order by ID", description = "Returns order by its ID if exists")
    @ApiResponse(responseCode = "200", description = "Found order successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class)))
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<OrderResponse> getOrderById(@Parameter(description = "ID of order")
                                                      @PathVariable("id") Long id) {
        return orderService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order by ID", description = "Update order and return updated order. Require ID of order and order request object for update")
    @ApiResponse(responseCode = "200", description = "Order updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class)))
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<OrderResponse> updateOrder(@Parameter(description = "ID of order")
                                                     @PathVariable("id") Long id,
                                                     @Parameter(description = "Order request object to be updated", required = true)
                                                     @RequestBody OrderRequest request) {
        return orderService.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order by ID", description = "Delete order. Require ID of order delete")
    @ApiResponse(responseCode = "204", description = "Order deleted successfully")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Void> deleteOrder(@Parameter(description = "ID of order")
                                            @PathVariable("id") Long id) {
        boolean isDeleted = orderService.delete(id);
        if (isDeleted) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Find all orders", description = "Find all orders with pagination")
    @ApiResponse(responseCode = "200", description = "Orders found successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class)))
    public ResponseEntity<Page<OrderResponse>> getAllOrders(@Parameter(description = "Pagination parameters")
                                                            @PageableDefault(sort = "id", direction = ASC) Pageable pageable) {
        return ResponseEntity.ok(orderService.getAll(pageable));
    }

    @GetMapping("/users/{userId}")
    @Operation(summary = "Find all orders of user", description = "Find all orders by ID of user with pagination")
    @ApiResponse(responseCode = "200", description = "Orders found successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class)))
    public ResponseEntity<Page<OrderResponse>> getOrderByUserId(@Parameter(description = "ID of order")
                                                                @PathVariable("userId") Long userId,
                                                                @Parameter(description = "Pagination parameters")
                                                                @RequestBody Pageable pageable) {
        return ResponseEntity.ok(orderService.getAllForUser(userId, pageable));
    }
}
