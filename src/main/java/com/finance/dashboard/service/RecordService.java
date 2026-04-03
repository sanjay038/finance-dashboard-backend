package com.finance.dashboard.service;

import com.finance.dashboard.dto.DTO;
import com.finance.dashboard.exception.ResourceNotFoundException;
import com.finance.dashboard.model.FinancialRecord;
import com.finance.dashboard.model.RecordType;
import com.finance.dashboard.repository.RecordRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<FinancialRecord> getAllRecords(RecordType type, String category, LocalDate startDate, LocalDate endDate) {
        Specification<FinancialRecord> spec = Specification.where(null);

        if (type != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("type"), type));
        }
        if (category != null && !category.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(cb.lower(root.get("category")), category.toLowerCase()));
        }
        if (startDate != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("date"), startDate));
        }
        if (endDate != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("date"), endDate));
        }

        return recordRepository.findAll(spec);
    }

    public FinancialRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Financial Record not found with id: " + id));
    }

    public FinancialRecord createRecord(DTO.RecordRequest request) {
        FinancialRecord record = FinancialRecord.builder()
                .type(request.getType())
                .category(request.getCategory())
                .amount(request.getAmount())
                .date(request.getDate())
                .notes(request.getNotes())
                .build();
        return recordRepository.save(record);
    }

    public FinancialRecord updateRecord(Long id, DTO.RecordRequest request) {
        FinancialRecord record = getRecordById(id);
        
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setAmount(request.getAmount());
        record.setDate(request.getDate());
        record.setNotes(request.getNotes());
        
        return recordRepository.save(record);
    }

    public void deleteRecord(Long id) {
        FinancialRecord record = getRecordById(id);
        recordRepository.delete(record);
    }

    public DTO.DashboardSummary getDashboardSummary() {
        BigDecimal totalIncome = recordRepository.getTotalIncome();
        if (totalIncome == null) totalIncome = BigDecimal.ZERO;

        BigDecimal totalExpenses = recordRepository.getTotalExpenses();
        if (totalExpenses == null) totalExpenses = BigDecimal.ZERO;

        BigDecimal netBalance = totalIncome.subtract(totalExpenses);

        DTO.DashboardSummary summary = new DTO.DashboardSummary();
        summary.setTotalIncome(totalIncome);
        summary.setTotalExpenses(totalExpenses);
        summary.setNetBalance(netBalance);

        return summary;
    }
}
