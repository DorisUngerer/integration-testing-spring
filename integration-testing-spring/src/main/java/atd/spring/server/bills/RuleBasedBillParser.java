package atd.spring.server.bills;

import java.math.BigDecimal;

import atd.spring.server.rules.CalculationRule;

public class RuleBasedBillParser extends BillParser {
  CalculationRule rules; 
  
  public RuleBasedBillParser (CalculationRule rules) {
    this.rules = rules;
  }
  
  protected LineItem createRuleBasedLineItem(String desc, BigDecimal amount, BigDecimal price,
      String currency) {
    return new RuleBasedLineItem(
      desc,
      new Money(price,currency),
      amount,
      rules);
  }

}
