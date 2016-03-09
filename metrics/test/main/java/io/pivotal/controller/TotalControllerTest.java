package io.pivotal.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.domain.Total;
import io.pivotal.service.TotalsService;


public class TotalControllerTest {
	@InjectMocks
	TotalController totalController;

	@Mock
	TotalsService totalsService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetTotals() {
		List<Total> totals = new ArrayList<>();
		totals.add(mock(Total.class));
		when(totalsService.getTotals()).thenReturn(totals);
		
		List<Total> totalsList = totalController.getTotals();
		
		assertNotNull(totalsList);
		assertEquals(1, totalsList.size());
		verify(totalsService, times(1)).getTotals();
	}
}
