package com.ustoredashboard.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AccountDetails {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "product_name")
	private String ProductName;
	@Column(name = "product_code")
	private String ProductCode;
	@Column(name = "line_item_type")
	private String LineItemType;
	
	@Column(name = "bill_type")
	private String BillType;
	@Column(name = "billing_entity")
	private String BillingEntity;
	@Column(name = "billing_period_start_date")
	private String BillingPeriodStartDate;
	
	@Column(name = "billing_period_end_date")
	private String BillingPeriodEndDate;
}
