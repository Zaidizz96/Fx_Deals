package com.example.fxdelas.service;

import com.example.fxdelas.entity.Deal;

import java.util.List;

public interface DealService {
    String addDeal(Deal deal);

    void validateDeal(Deal deal) ;

    Deal findById(Long id);

    Deal getDealByUniqueId(Long dealUniqueId);

}
