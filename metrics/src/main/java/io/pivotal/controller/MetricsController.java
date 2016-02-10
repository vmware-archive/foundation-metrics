package io.pivotal.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.domain.Metrics;
import io.pivotal.service.MetricService;

@RestController
@RequestMapping("/metrics")
public class MetricsController {
	@Autowired
	MetricService metricService;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public Metrics getAllMetrics() {
		System.out.println("Got a request - " + new Date());
		Metrics metrics = metricService.getMetrics();
		System.out.println("Sending response - " + new Date());
		return metrics;
	}
}
