package io.pivotal.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.domain.Metrics;
import io.pivotal.service.MetricService;

public class MetricsControllerTest {
	
	@InjectMocks
	MetricsController metricsController;
	
	@Mock
	MetricService metricService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetMetrics() {
		Metrics metrics = mock(Metrics.class);
		when(metricService.getMetrics()).thenReturn(metrics);
		
		Metrics allMetrics = metricsController.getAllMetrics();
		assertNotNull(allMetrics);
		assertEquals(metrics, allMetrics);
		verify(metricService, times(1)).getMetrics();
	}

}
