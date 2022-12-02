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
	
	@Column(name = "lineitem_unblended_rate")
	private String UnblendedRate;
	@Column(name = "lineitem_unblended_cost")
	private String UnblendedCost;
	@Column(name = "lineitem_blended_rate")
	private String BlendedRate;
	@Column(name = "lineitem_blended_cost")
	private String BlendedCost;
	
	
	public AccountDetails() {
		super();
	}

	public String getProductName() {
		return ProductName;
	}
	
	public void setProductName(String ProductName) { this.ProductName = ProductName;}
	
	public String getProductCode() {
		return ProductCode;
	}
	public void setProductCode(String ProductCode) { this.ProductCode = ProductCode;}
	
	public String getLineItemType() {
		return LineItemType;
	}
	
	public void setLineItemType(String LineItemType) { this.LineItemType = LineItemType;}
	
	public String getLineBillType() {
		return BillType;
	}
	
	public void setBillType(String LineBillType) { this.BillType = LineBillType;}
	
	public String getBillingEntity() {
		return BillingEntity;
	}
	
	public void setBillingEntity(String BillingEntity) { this.BillingEntity = BillingEntity;} 

	public void setBillingPeriodStartDate(String billingPeriodStartDate) {
		String stringDate = billingPeriodStartDate;
		String [] parts = stringDate.split("T");
		String part1 = parts[0]; // Antes do T
		this.BillingPeriodStartDate = part1;
	}
	
	public String getBillingPeriodStartDate() {
		return (String) BillingPeriodStartDate;
	}
	
	public String getBillingPeriodEndDate() {
		return BillingPeriodEndDate;
	}
	
	public void setBillingPeriodEndDate(String billingPeriodEndDate) {
		String stringDate = billingPeriodEndDate;
		String [] parts = stringDate.split("T");
		String part1 = parts[0]; // Antes do T
		this.BillingPeriodEndDate = part1;
	}
	
	public String getUnblendedRate() {
		return UnblendedRate;
	}
	public void setUnblendedRate(String unblendedRate) {this.UnblendedRate = unblendedRate;}

	public String getUnblendedCost() {
		return UnblendedCost;
	}
	
	public void setUnblendedCost(String unblendedCost) { this.UnblendedCost = unblendedCost;}

	public String getBlendedRate() {
		return BlendedRate;
	}

	public void setBlendedRate(String blendedRate) { this.BlendedRate = blendedRate;}

	public String getBlendedCost() {
		return BlendedCost;
	}

	public void setBlendedCost(String blendedCost) { this.BlendedCost = blendedCost;}

}
	