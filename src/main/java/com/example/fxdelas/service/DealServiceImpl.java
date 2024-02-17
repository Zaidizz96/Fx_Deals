package com.example.fxdelas.service;

import com.example.fxdelas.dao.DealRepository;
import com.example.fxdelas.entity.Deal;
import com.example.fxdelas.exceptions.DealNotFoundException;
import com.example.fxdelas.exceptions.DealValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;

    private static final Logger LOGGER = Logger.getLogger(DealServiceImpl.class.getName());


    @Autowired
    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    @Transactional
    public String addDeal(Deal deal) {
        String message = "";
        try {
            validateDeal(deal);

            Deal existDeal = getDealByUniqueId(deal.getDealUniqueId());

            if (existDeal == null) {
                dealRepository.save(deal);
                message = "deal with id" +" "+deal.getDealUniqueId()+" "+"saved successfully";
            } else {
                LOGGER.log(Level.WARNING, "this deal with Id:" + " " + deal.getDealUniqueId() + " " + "is already exists ");
                message = "deal with id" +" "+deal.getDealUniqueId()+" "+"is already existed";
            }
        } catch (Exception e){
            LOGGER.log(Level.SEVERE , "Error occur while trying to save a deal" + e.getMessage());
            throw e;
        }
        return message;
    }

    @Override
    public void validateDeal(Deal deal) {
        validateMissingFields(deal);
        validateFieldsFormat(deal);
    }

    @Override
    public Deal findById(Long id) {
        try {
            return dealRepository.findById(id).get();
        }catch (NoSuchElementException e){
            LOGGER.log(Level.SEVERE , "Error occur while trying to find a deal" + e.getMessage());
            throw new DealNotFoundException("deal with id:" + " " + id + " " + "dose not exist");
        }
    }

    @Override
    public Deal getDealByUniqueId(Long dealUniqueId) {
        return dealRepository.findByDealUniqueId(dealUniqueId);
    }

    private void validateMissingFields(Deal deal) {
        if (deal.getDealUniqueId() == null){
            throw new DealValidationException("the Id is required");
        }
        if (deal.getFromCurrencyISOCode().isEmpty() || deal.getToCurrencyISOCode().isEmpty()){
            throw new DealValidationException("from currency ISOCode and to currency ISOCode both are required");
        }
        if (deal.getDealAmount() == null){
            throw new DealValidationException("the deal amount is required");
        }
    }

    private void validateFieldsFormat(Deal deal){
        Pattern pattern = Pattern.compile("[A-Z]{3}");

        if (!pattern.matcher(deal.getFromCurrencyISOCode()).matches()){
            throw new  DealValidationException("this field must be 3 uppercase letters");
        }

        if (!pattern.matcher(deal.getToCurrencyISOCode()).matches()){
            throw new  DealValidationException("this field must be 3 uppercase letters");
        }
    }

}
