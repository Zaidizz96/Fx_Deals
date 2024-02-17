package com.example.fxdelas.dao;

import com.example.fxdelas.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal , Long> {

    Deal findByDealUniqueId(Long dealUniqueId);
}
