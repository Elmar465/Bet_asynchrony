package com.example.Bet.model;


import jakarta.persistence.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

@Entity
@Table(name = "Bet_Table")
public class BetModel implements MultipartFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "bet_name")
    private String bet_name;


    @Column(name = "bet_date")
    private String bet_date;

    @Column(name = "bid")
    private Integer bet_bid;


    //Getter
    public Integer getId(){
        return id;
    }

    // Getter
    public String getBet_name(){
        return bet_name;
    }

    //Setter
    public void setBet_name(String bet_name){
        this.bet_name =  bet_name;
    }


    //Getter
    public String getBet_date(){
        return bet_date;
    }

    //Setter
    public void setBet_date(String bet_date){

        this.bet_date = bet_date;
    }

    //Getter
    public Integer getBet_bid(){
        return bet_bid;
    }

    public void setBet_bid(Integer bet_bid){
        this.bet_bid = bet_bid;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getOriginalFilename() {
        return "";
    }

    @Override
    public String getContentType() {
        return "";
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return new byte[0];
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public Resource getResource() {
        return MultipartFile.super.getResource();
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }

    @Override
    public void transferTo(Path dest) throws IOException, IllegalStateException {
        MultipartFile.super.transferTo(dest);
    }
}
