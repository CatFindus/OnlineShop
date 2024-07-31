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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.puchinets.orderservice.model.dto.request.OrderItemRequest;
import ru.puchinets.orderservice.model.dto.response.OrderItemResponse;
import ru.puchinets.orderservice.service.OrderItemService;

@RestController
@RequestMapping("/api/orders/{orderId}/items")
@RequiredArgsConstructor
@Tag(name = "OrderItem", description = "Operations related to items of order in the Order Service")
public class OrderItemController {

    private final OrderItemService service;

    @Operation(summary = "Create item of order", description = "Create a new item of order and return the created object")
    @ApiResponse(responseCode = "201", description = "Item of order created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemResponse.class)))
    @ApiResponse(responseCode = "422", description = "Item cant be created because have not product in stock")
    @PostMapping
    public ResponseEntity<OrderItemResponse> addOrderItem(@Parameter(description = "ID of order")
                                                          @PathVariable("orderId") Long orderId,
                                                          @Parameter(description = "OrderItem request object to be created", required = true)
                                                          @RequestBody OrderItemRequest request) {
        request.setOrderId(orderId);
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{itemId}")
    @Operation(summary = "Get single item by ID", description = "Returns order item by its ID if exists")
    @ApiResponse(responseCode = "200", description = "Found item successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemResponse.class)))
    @ApiResponse(responseCode = "404", description = "Item not found")
    public ResponseEntity<OrderItemResponse> getOrderItemById(@Parameter(description = "ID of item")
                                                              @PathVariable("itemId") Long itemId) {
        return service.getById(itemId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Find all order items", description = "Find all order items with pagination")
    @ApiResponse(responseCode = "200", description = "Items found successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemResponse.class)))
    public ResponseEntity<Page<OrderItemResponse>> getAllItemsByOrderId(@Parameter(description = "Pagination parameters")
                                                                        @PathVariable("orderId") Long orderId, @RequestBody Pageable pageable) {
        return ResponseEntity.ok(service.findAllByOrderId(orderId, pageable));
    }

    @DeleteMapping("/{itemId}")
    @Operation(summary = "Delete order item by ID", description = "Delete order item. Require ID of item delete")
    @ApiResponse(responseCode = "204", description = "Item deleted successfully")
    @ApiResponse(responseCode = "404", description = "Item not found")
    public ResponseEntity<OrderItemResponse> deleteOrderItemById(@Parameter(description = "ID of item")
                                                                 @PathVariable("itemId") Long itemId) {
        boolean isDeleted = service.delete(itemId);
        if (isDeleted) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/{itemId}")
    @Operation(summary = "Update item of order", description = "Update item of order and return the updated object")
    @ApiResponse(responseCode = "200", description = "Item of order updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemResponse.class)))
    @ApiResponse(responseCode = "422", description = "Item cant be updated because have not product in stock")
    public ResponseEntity<OrderItemResponse> updateOrderItem(@Parameter(description = "ID of item")
                                                             @PathVariable("itemId") Long itemId,
                                                             @Parameter(description = "OrderItem request object to be updated", required = true)
                                                             @RequestBody OrderItemRequest request) {
        return service.update(itemId, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
