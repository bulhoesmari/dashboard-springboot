package com.ustoredashboard.entity;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import antlr.collections.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountDetails, Integer>{
	
	
	@Query("SELECT SUM(a.BlendedCost) FROM AccountDetails a")
	double amount();
	}