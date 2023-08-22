package com.jspiders.cardekho_case_study_hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Car_Details")
public class Car {
	@Id
	private  int carID ;
	private  String carName;
	private  String brandName;
	private  String fuelType;
	private  float price;
	
}
