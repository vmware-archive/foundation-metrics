package io.pivotal.scheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;

import io.pivotal.service.EmailService;
import io.pivotal.service.MetricService;

public class SchedulerTest {

	Scheduler scheduler;

	@Mock
	MetricService metricService;

	@Mock
	EmailService emailService;

	@Before
	public void init() {
		scheduler = new Scheduler();
	}
	
	@Test
	public void testReadRulesWhenRulesFileIsMissing() {
		BufferedReader br = mock(BufferedReader.class);
		scheduler = new Scheduler() {
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
			fail("Call should not reach here");
		}
		
	}

}
