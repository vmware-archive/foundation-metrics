package io.pivotal.scheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.pivotal.domain.Attribute;
import io.pivotal.domain.CustomJobMetric;
import io.pivotal.domain.FixedAttribute;
import io.pivotal.domain.JobDetail;
import io.pivotal.domain.Metrics;
import io.pivotal.domain.VMMetric;
import io.pivotal.service.EmailService;
import io.pivotal.service.MetricService;

public class SchedulerTest {

	@Mock
	MetricService metricService;

	@Mock
	EmailService emailService;

	@InjectMocks
	Scheduler scheduler;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testReadRulesWhenRulesFileIsMissing() {
		BufferedReader br = mock(BufferedReader.class);
		Scheduler scheduler = new Scheduler() {
			@Override
			BufferedReader getBufferedReader() {
				return br;
			}
		};

		try {
			when(br.ready()).thenThrow(new IOException());
			scheduler.readRules();
			assertEquals(scheduler.jobRulesMap.size(), 0);
			verify(br, times(0)).readLine();
		} catch (IOException e) {
			fail("Call should not reach here as the usecase is valid");
		}

	}

	@Test
	public void testReadRulesWithCorrectFile() {
		scheduler.readRules();
		assertEquals(scheduler.jobRulesMap.size(), 3);
	}

	@Test
	public void testCheckSystemHealthForARuleThatHasTwoAttributesIsSatisfied() {
		scheduler.readRules();
		assertEquals(scheduler.jobRulesMap.size(), 3);

		Metrics metrics = mock(Metrics.class);
		List<CustomJobMetric> customJobMetrics = new ArrayList<>();
		CustomJobMetric customJobMetric = mock(CustomJobMetric.class);
		JobDetail jobDetail = mock(JobDetail.class);
		customJobMetrics.add(customJobMetric);
		List<Attribute> attributes = new ArrayList<>();
		attributes.add(new Attribute("opentsdb.nozzle.rep.CapacityTotalMemory", "16048.0"));
		attributes.add(new Attribute("opentsdb.nozzle.rep.CapacityRemainingMemory", "2224.0"));

		when(jobDetail.getJob()).thenReturn("diego_cell-partition-7c63a80c4c417fc7eec5");
		when(metricService.getMetrics()).thenReturn(metrics);
		when(customJobMetric.getJobDetail()).thenReturn(jobDetail);
		when(metrics.getCustomJobMetrics()).thenReturn(customJobMetrics);
		when(customJobMetric.getCustomAttributes()).thenReturn(attributes);

		scheduler.checkSystemHealth();
		String errorMsgs = scheduler.getErrors();
		assertNotEquals(errorMsgs, null);
		assertNotEquals(errorMsgs.length(), 0);

		verify(metricService, times(1)).getMetrics();
		verify(emailService, times(1)).sendEmail(anyString());
	}

	@Test
	public void testCheckSystemHealthForARuleThatHasTwoAttributesIsNotSatisfied() {
		scheduler.readRules();
		assertEquals(scheduler.jobRulesMap.size(), 3);

		Metrics metrics = mock(Metrics.class);
		List<CustomJobMetric> customJobMetrics = new ArrayList<>();
		CustomJobMetric customJobMetric = mock(CustomJobMetric.class);
		JobDetail jobDetail = mock(JobDetail.class);
		customJobMetrics.add(customJobMetric);
		List<Attribute> attributes = new ArrayList<>();
		attributes.add(new Attribute("opentsdb.nozzle.rep.CapacityTotalMemory", "16048.0"));
		attributes.add(new Attribute("opentsdb.nozzle.rep.CapacityRemainingMemory", "10024.0"));

		when(jobDetail.getJob()).thenReturn("diego_cell-partition-7c63a80c4c417fc7eec5");
		when(metricService.getMetrics()).thenReturn(metrics);
		when(customJobMetric.getJobDetail()).thenReturn(jobDetail);
		when(metrics.getCustomJobMetrics()).thenReturn(customJobMetrics);
		when(customJobMetric.getCustomAttributes()).thenReturn(attributes);

		scheduler.checkSystemHealth();
		String errorMsgs = scheduler.getErrors();
		assertNotEquals(errorMsgs, null);
		assertEquals(errorMsgs.length(), 0);

		verify(metricService, times(1)).getMetrics();
		verify(emailService, times(0)).sendEmail(anyString());
	}

	@Test
	public void testCheckSystemHealthForARuleThatHasOneAttributeIsSatisfied() {
		scheduler.readRules();
		assertEquals(scheduler.jobRulesMap.size(), 3);

		Metrics metrics = mock(Metrics.class);
		List<CustomJobMetric> customJobMetrics = new ArrayList<>();
		CustomJobMetric customJobMetric = mock(CustomJobMetric.class);
		JobDetail jobDetail = mock(JobDetail.class);
		customJobMetrics.add(customJobMetric);
		List<Attribute> attributes = new ArrayList<>();
		attributes.add(new Attribute("opentsdb.nozzle.router__0.responses.5xx", "7"));

		when(jobDetail.getJob()).thenReturn("router-partition-7c63a80c4c417fc7eec5");
		when(metricService.getMetrics()).thenReturn(metrics);
		when(customJobMetric.getJobDetail()).thenReturn(jobDetail);
		when(metrics.getCustomJobMetrics()).thenReturn(customJobMetrics);
		when(customJobMetric.getCustomAttributes()).thenReturn(attributes);
		
		scheduler.checkSystemHealth();
		String errorMsgs = scheduler.getErrors(); 
		assertNotEquals(errorMsgs, null);
		assertNotEquals(errorMsgs.length(), 0);

		verify(metricService, times(1)).getMetrics();
		verify(emailService, times(1)).sendEmail(anyString());
	}

	@Test
	public void testCheckSystemHealthForARuleThatHasOneAttributeIsNotSatisfied() {
		scheduler.readRules();
		assertEquals(scheduler.jobRulesMap.size(), 3);

		Metrics metrics = mock(Metrics.class);
		List<CustomJobMetric> customJobMetrics = new ArrayList<>();
		CustomJobMetric customJobMetric = mock(CustomJobMetric.class);
		JobDetail jobDetail = mock(JobDetail.class);
		customJobMetrics.add(customJobMetric);
		List<Attribute> attributes = new ArrayList<>();
		attributes.add(new Attribute("opentsdb.nozzle.router__0.responses.5xx", "3"));

		when(jobDetail.getJob()).thenReturn("router-partition-7c63a80c4c417fc7eec5");
		when(metricService.getMetrics()).thenReturn(metrics);
		when(customJobMetric.getJobDetail()).thenReturn(jobDetail);
		when(metrics.getCustomJobMetrics()).thenReturn(customJobMetrics);
		when(customJobMetric.getCustomAttributes()).thenReturn(attributes);

		scheduler.checkSystemHealth();
		String errorMsgs = scheduler.getErrors();
		assertNotEquals(errorMsgs, null);
		assertEquals(errorMsgs.length(), 0);

		verify(metricService, times(1)).getMetrics();
		verify(emailService, times(0)).sendEmail(anyString());
	}
	
	@Test
	public void testCheckSystemHealthForAJobThatIsFailing() {
		Metrics metrics = mock(Metrics.class);
		List<VMMetric> vmMetrics = new ArrayList<>();
		VMMetric vmMetric = mock(VMMetric.class);
		JobDetail jobDetail = mock(JobDetail.class);
		vmMetrics.add(vmMetric);

		when(jobDetail.getJob()).thenReturn("diego_cell-partition-7c63a80c4c417fc7eec5");
		when(jobDetail.getDeployment()).thenReturn("cf-a38c164ac6169b64cc8a");
		when(jobDetail.getIp()).thenReturn("192.168.0.22");
		when(jobDetail.getIndex()).thenReturn("0");
		FixedAttribute fixedAttribute = mock(FixedAttribute.class);

		when(fixedAttribute.getSystem_healthy()).thenReturn("0.0");
		when(vmMetric.getFixedAttribute()).thenReturn(fixedAttribute);
		when(metricService.getMetrics()).thenReturn(metrics);
		when(metrics.getVmMetrics()).thenReturn(vmMetrics);
		when(vmMetric.getJobDetail()).thenReturn(jobDetail);
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(jobDetail.getDeployment()).append(":").append(jobDetail.getJob()).append(":")
				.append(jobDetail.getIndex()).append(":").append(jobDetail.getIp()).append("\n");

		scheduler.checkSystemHealth();
		String errorMsgs = scheduler.getErrors();
		assertNotEquals(errorMsgs, null);
		assertNotEquals(errorMsgs.length(), 0);
		assertEquals(stringBuilder.toString(), errorMsgs);

		verify(metricService, times(1)).getMetrics();
		verify(emailService, times(1)).sendEmail(anyString());
	}

}
