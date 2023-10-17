package com.erwan.demo6.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.erwan.demo6.repos.ConsommateursRepo;

public class ConsommateursService {

    @Autowired
    private ConsommateursRepo repo;

    public void test() {
        System.out.println(repo);
    }
}
