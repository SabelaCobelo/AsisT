package com.asist.repository;

import com.asist.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    
    // Find reports by user ID
    List<Report> findByUserId(Long userId);
    
    // Find reports by location
    List<Report> findByLocation(String location);
    
    // Find reports by title containing specific text (case insensitive)
    List<Report> findByTitleContainingIgnoreCase(String title);
    
    // Find reports within a date range
    List<Report> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find reports by user ID and date range
    List<Report> findByUserIdAndDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    
    // Find reports by location and date range
    List<Report> findByLocationAndDateBetween(String location, LocalDateTime startDate, LocalDateTime endDate);
}
