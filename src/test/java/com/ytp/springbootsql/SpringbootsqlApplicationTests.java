package com.ytp.springbootsql;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ytp.springbootsql.controller.FlightController;
import com.ytp.springbootsql.dao.FlightDao;
import com.ytp.springbootsql.model.Flight;

@SpringBootTest
class SpringbootsqlApplicationTests {

	@Autowired
	private FlightController flightController;

	@MockBean
	private FlightDao flightDao;

	@Test
	public void getInfoTest() 
	{
		when(flightDao.findAll()).thenReturn(Stream.of(new Flight(29,"sushmitha", "hyderabad", "dubai"),
				new Flight(28,"navitha", "delhi", "hyderabad"),new Flight(27,"madhumitha", "mumbai", "delhi"),
				new Flight(26,"deekshitha", "Germany","hyderabad")).collect(Collectors.toList()));
		assertEquals(4, flightController.getInfo().size());                                                 
	}

	@Test
	public void saveFlightTest()
	{
		List<Flight> flight= new ArrayList<>(Arrays.asList(
				new Flight(30,"sushmitha", "delhi", "hyderabad"),
				new Flight(31,"maneela", "delhi", "germany")
				));
		when(flightDao.saveAll(flight)).thenReturn(flight);
		assertEquals(flight,flightController.saveFlight(flight));
	}

	@Test
	public void updateTest() 
	{
		Flight flightInfo=new Flight(30,"sushmitha", "delhi", "hyderabad");
		flightController.updateFlight(flightInfo, 30);
		verify(flightDao,times(1)).save(flightInfo);
	}

	@Test
	public void deleteFlightTest() 
	{
		Flight flightInfo=new Flight(29,"sushmitha", "hyderabad", "dubai");
		flightController.deleteFlight(flightInfo);
		verify(flightDao,times(1)).delete(flightInfo);
	}

	@Test
	public void deleteAllTest() 
	{
		Flight flightInfo=new Flight();
		flightController.deleteFlightInfo();
		verify(flightDao).deleteAll();
	}

}

