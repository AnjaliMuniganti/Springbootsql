package com.ytp.springbootsql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ytp.springbootsql.dao.FlightDao;
import com.ytp.springbootsql.model.Flight;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private FlightDao flightDao;

	@PostMapping("/flightInformation")
	public List<Flight> saveFlight(@RequestBody List<Flight> flight) {
		return (List<Flight>) flightDao.saveAll(flight);
	}

	@GetMapping("/getInfo")
	public List<Flight> getInfo(){
		List<Flight> flightTotalInfo=(List<Flight>) flightDao.findAll();
		System.out.println("getting flight info:"+flightTotalInfo);
		return flightTotalInfo;
	}
	@PutMapping("/update/{id}")
	public Flight updateFlight(@RequestBody Flight newflight,@PathVariable("id") int id) {
		System.out.println("updated info:"+newflight);  
		return flightDao.save(newflight);

	}
	@DeleteMapping("/deleteInfo")
	public List<Flight> deleteFlightInfo(){
		flightDao.deleteAll();
		System.out.println("all records are deleted");
		return (List<Flight>)flightDao.findAll();
	}
	@DeleteMapping("/delete/{id}")
	public String deleteFlight(@PathVariable("id") Flight flightInfo)
	{
		flightDao.delete(flightInfo);
		System.out.println("deleted 1 record:"+flightInfo);
		return "deleted 1 record";
	}
}
