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
