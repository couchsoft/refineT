package src.main.resources.tests;

import org.junit.jupiter.api.Test;
import org.refinet.steps.AccountingApp;
import org.refinet.steps.CalculatorApp;
import org.refinet.steps.CalculatorUsage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;

@DisplayName("Test our AccountingSystem")
public class AccountingTests {
	
  /**
   * Open the Accounting app for testing.
   */
  @BeforeAll
  public void loadAccounting() {
    System.out.println("Some dummy debug information");
    AccountingApp.open();
  }
  
  /**
   * Check IBAN for EU Standard
   *
   * 
   */
  @Test
  @DisplayName("Our Accouting app need a correct iban")
  public void testThatCorrectIban() {
    System.out.println("Some dummy debug information");
  }
  
  /**
   * 
   * Clear input fields 
   * 
   */
  @AfterEach
  public void ClearAccouting() {
	  AccountingApp.clear();
  }

  @AfterAll
  @DisplayName("After we finished all the tests, we close our accouting app")
  public void terminateAccounting() {
    System.out.println("Some dummy debug information");
    AccountingApp.close();
  }

}

	




