package com.finance.dashboard.controller;

import com.finance.dashboard.dto.DTO;
import com.finance.dashboard.model.FinancialRecord;
import com.finance.dashboard.model.RecordType;
import com.finance.dashboard.service.RecordService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    // Unified dynamic filtering endpoint
    @GetMapping("/records")
    public ResponseEntity<List<FinancialRecord>> getRecords(
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        
        List<FinancialRecord> records = recordService.getAllRecords(type, category, startDate, endDate);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<FinancialRecord> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(recordService.getRecordById(id));
    }

    @PostMapping("/records")
    public ResponseEntity<FinancialRecord> createRecord(@Valid @RequestBody DTO.RecordRequest request) {
        FinancialRecord createdRecord = recordService.createRecord(request);
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }

    @PutMapping("/records/{id}")
    public ResponseEntity<FinancialRecord> updateRecord(
            @PathVariable Long id, 
            @Valid @RequestBody DTO.RecordRequest request) {
        FinancialRecord updatedRecord = recordService.updateRecord(id, request);
        return ResponseEntity.ok(updatedRecord);
    }

    @DeleteMapping("/records/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }

    // Summary endpoint
    @GetMapping("/summary/dashboard")
    public ResponseEntity<DTO.DashboardSummary> getDashboardSummary() {
        return ResponseEntity.ok(recordService.getDashboardSummary());
    }
}
