package com.example.bootcamp.service;

import com.example.bootcamp.exceptions.BusinessException;
import com.example.bootcamp.exceptions.NotFoundException;
import com.example.bootcamp.mapper.StockMapper;
import com.example.bootcamp.model.Stock;
import com.example.bootcamp.model.dto.StockDTO;
import com.example.bootcamp.repository.StockRepository;
import com.example.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        dto = mapper.toDTO(stock);
        return dto;
    }

    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        dto = mapper.toDTO(stock);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        Optional<Stock> optionalStock = repository.findById(id);
        return optionalStock.map(mapper::toDTO).orElseThrow(NotFoundException::new);
    }

    public StockDTO delete(Long id) {
        StockDTO stockDTO = this.findById(id);
        repository.deleteById(stockDTO.getId());
        return stockDTO;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByCurrentDate() {
        return repository.findByCurrentDate(LocalDate.now()).map(mapper::toDTO).orElseThrow(NotFoundException::new);
    }
}
