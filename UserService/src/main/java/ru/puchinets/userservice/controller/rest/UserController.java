package ru.puchinets.userservice.controller.rest;

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
import ru.puchinets.userservice.model.dto.request.UserRequest;
import ru.puchinets.userservice.model.dto.response.UserResponse;
import ru.puchinets.userservice.model.entity.User;
import ru.puchinets.userservice.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
@Tag(name = "User", description = "Operations related to users in User Service")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve user by their ID")
    @ApiResponse(responseCode = "200", description = "User found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserResponse> getUserById(@Parameter(description = "ID of the user to retrieve") @PathVariable("id") Long id) {
        Optional<UserResponse> mayBeUser = userService.getUserById(id);
        return mayBeUser
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Retrieve a paginated list of all users")
    public ResponseEntity<Page<UserResponse>> getAllUsers(@Parameter(description = "Pagination and sorting parameters") Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticate a user's credentials and return a success message")
    @ApiResponse(responseCode = "401", description = "Invalid username or password")
    @ApiResponse(responseCode = "200", description = "Authenticated")
    public ResponseEntity<String> login(@Parameter(description = "username and passwordHash of user") @RequestBody UserRequest request) {
        boolean isAuthenticated = userService.authenticateUser(request.getUsername(), request.getPasswordHash());
        if (isAuthenticated) return ResponseEntity.ok("Authenticated");
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    @PostMapping
    @Operation(summary = "Register new user", description = "Register a new user and return the user data")
    @ApiResponse(responseCode = "201", description = "User created",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    public ResponseEntity<UserResponse> register(@Parameter(description = "Fields of new user") @RequestBody UserRequest request) {
        UserResponse savedUser = userService.registerUser(request);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user by ID", description = "Update user data by ID and return updated user data")
    @ApiResponse(responseCode = "200", description = "User updated successfully",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserResponse> updateUser(@Parameter(description = "ID of the user to update") @PathVariable("id") Long id,
                                                   @Parameter(description = "User data for update") @RequestBody UserRequest request) {
        var updatedUser = userService.update(id, request);
        return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by ID", description = "Delete user by ID and return response code 204")
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "ID of the user to delete") @PathVariable("id") Long id) {
        boolean deleted = userService.delete(id);
        if (deleted) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }
}
