package com.example.Bet.controller;


import com.example.Bet.model.BetModel;
import com.example.Bet.service.BetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping
public class BetController {


    @Autowired
    private BetService betService;


    //Create a Bet Request
    @PostMapping(value = "/product", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity<BetModel> createARequest(@RequestParam(value = "files")MultipartFile[] file) throws  Exception {
        for (MultipartFile file1 : file){
            betService.createBet(file1);
        }
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }


    //Get All Bet Details
    @GetMapping(value = "/allBets")
    public CompletableFuture<ResponseEntity> getAllBetDetails(){
        return betService.getAllBetDetails().thenApply(ResponseEntity::ok);
    }


    //Get Single Bet Detai
    @GetMapping(value = "/getSingleBetDetail/{bet_id}")
    public  ResponseEntity<BetModel> getSingleBetDetail(@RequestParam(value = "files") MultipartFile[] file) throws  Exception {
        for(MultipartFile file1 : file){
            betService.geetSingleBetModel(file1);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    //Update Bet Detail
    @PutMapping(value = "/updateADetail/{bet_id}/{new_bet_name}")
    public ResponseEntity<String> updateBetDetails(@PathVariable Integer bet_id, @PathVariable String new_bet) {
            try {
                betService.updateABetDetails(bet_id, new_bet);
                return ResponseEntity.ok("Data Updated");
            } catch (EntityNotFoundException e) {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }

    // Delete Bet Detail
    public void  deleleteBetDetail(Integer bet_id){
        betService.deleteABet(bet_id);
    }
}
