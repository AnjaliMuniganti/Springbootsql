package com.ytp.springbootsql.dao;

import org.springframework.data.repository.CrudRepository;

import com.ytp.springbootsql.model.Flight;

public interface FlightDao extends CrudRepository<Flight, Integer> {

		
}
