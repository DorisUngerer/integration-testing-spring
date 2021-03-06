package atd.spring.server.unittests.rules;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import atd.spring.server.bills.LineItem;
import atd.spring.server.bills.Money;
import atd.spring.server.rules.CurrencyRule;

public class FactorByCurrencyRuleTests {
  private CurrencyRule lineItemRule;
  
  @Before
  public void setUp() {
    Map<String,Float> factors = new HashMap<>();
    factors.put("CHF",1.15f);
    factors.put("EUR",0.9f);
    
    lineItemRule = new CurrencyRule(factors);
    
  }

  @Test
  public void calculation_WithFactorRuleApplied() {
    assertEquals(1.15f,lineItemRule.getMultiplier(
    		new LineItem("item",new Money(BigDecimal.ZERO,"CHF")
    				,BigDecimal.ZERO)),0.01);
  }

  @Test
  public void calculation_WithoutFactorRuleApplied() {
    assertEquals(1f,lineItemRule.getMultiplier(
    		new LineItem("item",new Money(BigDecimal.ZERO,"JND")
    				,BigDecimal.ZERO)),0.01);
  }
}
