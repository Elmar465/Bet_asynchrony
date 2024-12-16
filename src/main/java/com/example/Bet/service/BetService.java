package com.example.Bet.service;


import com.example.Bet.dao.BetDao;
import com.example.Bet.model.BetModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class BetService {

    @Autowired
    private BetDao betDao;


    //Create a Bet
    @Async
    public CompletableFuture<List<BetModel>> createBet(MultipartFile file) throws Exception{
        List<BetModel> betModels = parseCSVFile(file);
        betModels =  betDao.saveAll(betModels);
        return CompletableFuture.completedFuture(betModels);
    }

    //Get All Bet Details
    @Async
    public CompletableFuture<List<BetModel>> getAllBetDetails(){
        List<BetModel> betModels = betDao.findAll();
        return CompletableFuture.completedFuture(betModels);
     }

     //Get SIngle Bet Detail
    @Async
    public CompletableFuture<List<BetModel>> geetSingleBetModel(MultipartFile file) throws  Exception{
        List<BetModel> betModels = parseCSVFile(file);
        betModels = Collections.singletonList(betDao.findById(betModels.hashCode()).orElse(new BetModel()));
        return  CompletableFuture.completedFuture(betModels);
    }

    @Async
    public CompletableFuture<BetModel> updateABetDetails(Integer bet_id, String new_bet_name){
        Optional<BetModel> optionalBetModel = betDao.findById(bet_id);
        if (optionalBetModel.isPresent()){
            BetModel betModel =  new BetModel();
            betModel.setBet_name(new_bet_name);
            BetModel betModel1  = betDao.save(betModel);
            return CompletableFuture.completedFuture(betModel1);
        } else {
            throw new  EntityNotFoundException("Error");
        }
    }


    // Delete A Bet
    public void deleteABet(Integer bet_id){
        betDao.deleteById(bet_id);
    }


    private List<BetModel> parseCSVFile(final MultipartFile file ) throws  Exception {
        List<BetModel> betModels = new ArrayList<>();
        try {
            try(final BufferedReader br =  new BufferedReader(new InputStreamReader(file.getInputStream()))){
                String line;
                while ((line = br.readLine())!= null){
                    final String[] data = line.split(",");
                    final BetModel betModel = new BetModel();
                    betModel.setBet_name(data[0]);
                    betModel.setBet_bid(Integer.valueOf(data[1]));
                    betModel.setBet_date(data[2]);
                }
                return betModels;
            }
        } catch (final IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
