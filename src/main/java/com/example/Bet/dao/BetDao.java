package com.example.Bet.dao;

import com.example.Bet.model.BetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BetDao extends JpaRepository<BetModel, Integer> {
}
