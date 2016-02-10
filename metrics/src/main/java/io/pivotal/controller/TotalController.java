package io.pivotal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.domain.Total;
import io.pivotal.service.TotalsService;

@RestController
@RequestMapping(value = "totals")
public class TotalController {

	@Autowired
	TotalsService totalsService;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public List<Total> getTotals() {
		return totalsService.getTotals();
	}

}
