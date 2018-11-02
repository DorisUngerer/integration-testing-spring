package atd.spring.server.bills;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import atd.spring.testing.exceptions.ParsingException;

public class BillTextFileLoader {
	
  public Bill createFromLineItems(List<String> lineItems) {
	  Bill bill = new Bill();
	  lineItems.forEach(lineItem->
		  extractLineItem(lineItem, bill));
	  
	  return bill;
  }
  
  public Bill loadFromFile(String filename) throws IOException {
    Bill ret = new Bill();
    BufferedReader br = new BufferedReader(new FileReader(filename));
    try {
      String line;
      while ((line = br.readLine()) !=null) {
        extractLineItem(line,ret);
      } 
    }finally {
      br.close();
    }  
    return ret;
  }

  protected void extractLineItem(String line,Bill bill) {
    Matcher m = getLinePattern().matcher(line);
    if (m.matches()) {
      String desc = m.group(getDescIndex());
      BigDecimal amount = new BigDecimal(m.group(getAmountIndex()));
      BigDecimal price = new BigDecimal(m.group(getPriceIndex()));
      String currency = m.group(getCurrencyIndex());
      bill.addItem(
          createLineItem(desc, amount, price, currency));
    } else {
      throw new ParsingException("Unclear line item:"+line);
    }
  }

  protected LineItem createLineItem(String desc, BigDecimal amount, BigDecimal price,
      String currency) {
    return new LineItem(
      desc,
      new Money(price,currency),
      amount);
  }
  
  private Pattern LINE_PATTERN=Pattern.compile("^(.*)::(\\d+\\.?\\d*)::(\\d+\\.?\\d*)::(...)$");
  protected Pattern getLinePattern() {
    return LINE_PATTERN;
  }
  
  private int getDescIndex() { return 1; }
  private int getAmountIndex() { return 2; }
  private int getPriceIndex() { return 3; }
  private int getCurrencyIndex() { return 4; }
  
}