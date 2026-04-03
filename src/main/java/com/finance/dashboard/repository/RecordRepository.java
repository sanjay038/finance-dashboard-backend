package com.finance.dashboard.repository;

import com.finance.dashboard.model.FinancialRecord;
import com.finance.dashboard.model.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<FinancialRecord, Long>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<FinancialRecord> {

    // Filtering queries
    List<FinancialRecord> findByType(RecordType type);
    List<FinancialRecord> findByCategory(String category);
    List<FinancialRecord> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<FinancialRecord> findByTypeAndCategory(RecordType type, String category);

    // Dashboard aggregate queries
    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = 'INCOME'")
    BigDecimal getTotalIncome();

    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = 'EXPENSE'")
    BigDecimal getTotalExpenses();

    // Fetch top recent records
    List<FinancialRecord> findTop5ByOrderByDateDesc();

}
