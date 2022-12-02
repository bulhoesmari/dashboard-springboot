package com.ustoredashboard.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ustoredashboard.entity.AccountDetails;
import com.ustoredashboard.entity.AccountRepository;



public class BankController {

	@Autowired
	private AccountRepository service;
	
	@GetMapping("/gastosMes")
	public double gastosMes() {
		return service.amount();
		
	}
	
	@GetMapping("/{choosenDate}")
	public double choosedDataAmount(@PathVariable Date choosenDate) throws Exception {
	    List<AccountDetails> account = service.findAll();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = dateFormat.format(choosenDate);
	    double amount = 0;
	    for ( AccountDetails a : account ) {
            if (a.getBillingPeriodStartDate().equals(dataFormatada)) {
            	amount+=Double.parseDouble(a.getBlendedCost());
            }
	    
	    }	
	    
	    DecimalFormat df = new DecimalFormat("#.##");
        df.format(amount);
	    return amount;	
		
	}
}
