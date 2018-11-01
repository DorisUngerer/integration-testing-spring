package atd.spring.testing.integrationtests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import atd.spring.testing.compliance.RegulationMonitor;
import atd.spring.testing.compliance.TrafficRegulator;
import atd.spring.testing.configuration.RegulationControllerConfiguration;
import atd.spring.testing.gateway.RegulationController;



@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {RegulationControllerConfiguration.class})
public class RegulationControllerTests {

	@Autowired RegulationController controller;
	@MockBean TrafficRegulator mockRegulator;

	
	@Test
	public void whenAddingRules_andMonitorIsOn_RulesAreApplied() {
		RegulationMonitor.getRegulator().StartMonitoring();
		controller.applyRules();
		verify(mockRegulator).apply();
	}
	
	@Test
	public void whenAddingRules_andMonitorIsOff_RulesAreNotApplied() {
		RegulationMonitor.getRegulator().StopMonitoring();
		controller.applyRules();
		verify(mockRegulator, never()).apply();
	
	}
	
	
}
