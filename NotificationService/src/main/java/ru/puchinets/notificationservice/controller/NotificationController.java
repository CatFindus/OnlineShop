package ru.puchinets.notificationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.puchinets.notificationservice.model.entity.Notification;
import ru.puchinets.notificationservice.service.NotificationService;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    @GetMapping("/users/{id}")
    @Operation(summary = "Find all notifications for user", description = "Find all notifications by userId with pagination")
    @ApiResponse(responseCode = "200", description = "Notifications found successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notification.class)))
    public ResponseEntity<Page<Notification>> getByUserId(@PathVariable("id") Long userId,
                                                          @PageableDefault(sort = "id", direction = ASC) Pageable pageable) {
            return ResponseEntity.ok(service.getNotificationByUserId(userId, pageable));
    }

    @GetMapping("/orders/{id}")
    @Operation(summary = "Find all notifications for order", description = "Find all notifications by orderId with pagination")
    @ApiResponse(responseCode = "200", description = "Notifications found successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notification.class)))
    public ResponseEntity<Page<Notification>> getByOrderId(@PathVariable("id") Long orderId,
                                                          @PageableDefault(sort = "id", direction = ASC) Pageable pageable) {
        return ResponseEntity.ok(service.getNotificationByOrderId(orderId, pageable));
    }
}
