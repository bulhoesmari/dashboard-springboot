package com.ustoredashboard.controller;

import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.ustoredashboard.entity.AccountDetails;
import com.ustoredashboard.entity.AccountRepository;
import com.univocity.parsers.common.record.Record;


@RestController
@CrossOrigin
@RequestMapping(value="/api")

public class BankController {
	
	@Autowired
	private AccountRepository service;
	
	

	@PostMapping("/upload")
	public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {
		List<AccountDetails> accountList = new ArrayList<>();
		InputStream inputStream = file.getInputStream();
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		List<Record>parseAllRecords = parser.parseAllRecords(inputStream);
		parseAllRecords.forEach(record -> {
			AccountDetails account = new AccountDetails();
			account.setProductName(record.getString("product/ProductName"));
			account.setProductCode(record.getString("lineItem/ProductCode"));
			account.setLineItemType(record.getString("lineItem/LineItemType"));
			account.setBillType(record.getString("bill/BillType"));
			account.setBillingEntity(record.getString("bill/BillingEntity"));
			account.setBillingPeriodStartDate(record.getString("bill/BillingPeriodStartDate"));
			account.setBillingPeriodEndDate(record.getString("bill/BillingPeriodEndDate"));
			account.setUnblendedRate(record.getString("lineItem/UnblendedRate"));
			account.setUnblendedCost(record.getString("lineItem/UnblendedCost"));
			account.setBlendedRate(record.getString("lineItem/BlendedRate"));
			account.setBlendedCost(record.getString("lineItem/BlendedCost"));
			accountList.add(account);
		});
		service.saveAll(accountList);
		return "Upload feito com sucesso !!!";
	}
	
<<<<<<< HEAD
	@GetMapping("/gastosMes")
	public double gastosMes() {
		return service.amount();
		
	}
	
=======

>>>>>>> b341688f97271bcf55ef718ebcd9573ea45b67f7
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
	
	
	
	
	