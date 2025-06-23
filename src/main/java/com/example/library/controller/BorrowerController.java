package com.example.library.controller;

import com.example.library.dto.BorrowerDTO;
import com.example.library.service.BorrowerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrowers")
@RequiredArgsConstructor
public class BorrowerController {

    private final BorrowerService borrowerService;

    @GetMapping
    public ResponseEntity<List<BorrowerDTO>> getAll() {
        return ResponseEntity.ok(borrowerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowerDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(borrowerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BorrowerDTO> create(@RequestBody @Valid BorrowerDTO dto) {
        BorrowerDTO created = borrowerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowerDTO> update(@PathVariable Long id, @RequestBody @Valid BorrowerDTO dto) {
        return ResponseEntity.ok(borrowerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        borrowerService.delete(id);
        return ResponseEntity.noContent().build();
    }
} 