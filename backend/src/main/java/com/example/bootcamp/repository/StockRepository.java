package com.example.bootcamp.repository;

import com.example.bootcamp.model.Stock;
import com.example.bootcamp.model.dto.StockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByNameAndDate(String name, LocalDate date);

    @Query("SELECT s FROM Stock s WHERE s.name = :name AND s.date = :date AND s.id <> :id")
    Optional<Stock> findByStockUpdate(String name, LocalDate date, Long id);

    @Query("SELECT s FROM Stock s WHERE s.date = :date ")
    Optional<List<Stock>> findByCurrentDate(LocalDate date);
}
