package atd.spring.server.compliance;

import java.io.IOException;
import java.math.BigDecimal;

import atd.spring.server.bills.Bill;
import atd.spring.server.bills.LineItem;
import atd.spring.server.bills.RuleBasedBillParser;
import atd.spring.server.compliance.logging.Registrar;
import atd.spring.server.rules.CompositeLineItemRule;
import atd.spring.server.rules.CalculationRule;

public class ComplianceRuledBasedBillParser  extends RuleBasedBillParser{
  private Registrar registrar;
  
  public ComplianceRuledBasedBillParser(CompositeLineItemRule ruleManager, Registrar trafficRegulator) {
    super(ruleManager);
    this.registrar = trafficRegulator;
  }
  


	@Override
  protected LineItem createRuleBasedLineItem(String desc, BigDecimal amount, 
      BigDecimal price, String currency) {
    LineItem ret = super.createRuleBasedLineItem(desc, amount, price, currency);
    registrar.documentLineItem(ret);
    return ret;
  }
  
}
