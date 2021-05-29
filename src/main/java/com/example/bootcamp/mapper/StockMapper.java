package com.example.bootcamp.mapper;

import com.example.bootcamp.model.Stock;
import com.example.bootcamp.model.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    public Stock toEntity(StockDTO dto) {
        return new Stock(dto.getId(), dto.getName(), dto.getPrice(), dto.getDate(), dto.getVariation());
    }

    public StockDTO toDTO(Stock stock) {
        return new StockDTO(stock.getId(), stock.getName(), stock.getPrice(), stock.getDate(), stock.getVariation());
    }

    public List<StockDTO> toDTO(List<Stock> stocks) {
        return stocks.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
