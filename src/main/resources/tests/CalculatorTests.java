package src.main.resources.tests;

import org.junit.jupiter.api.Test;
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

@DisplayName("Test our calculator app for basic arithmetic operations")
public class CalculatorTests {
  /**
   * Open the calculator app for testing.
   */
  @BeforeAll
  public void loadCalculator() {
    System.out.println("Some dummy debug information");
    CalculatorApp.open();
  }
  
  /**
   * Reset our calculator.
   *
   * Has to be done before each test to make sure we get the correct results.
   */
  @BeforeEach
  public void resetCalculator() {
    CalculatorApp.reset();
  }

  /**
   * Our calculator should be able to add two numbers.
   *
   * In this test we only use Integers for testing.
   */
  @Test
  @DisplayName("Our calculator should be able to add two numbers")
  @Tag("regression")
  @Tag("dashcalc")
  public void testThatCalculatorCanAddTwoNumbers() {
    System.out.println("Some dummy debug information");
    CalculatorUsage.enterNumber(4);
    CalculatorUsage.enterOperation("+");
    CalculatorUsage.enterNumber(5);
    CalculatorUsage.enterOperation("=");
    CalculatorUsage.assertThatResultIs(9);
    System.out.println("Some dummy debug information");
  }

  @Test
  @DisplayName("Our calculator should be able to multiply two numbers")
  @Tag("regression")
  @Tag("pointcalc")
  public void testThatCalculatorCanMultiplyTwoNumbers() {
    CalculatorUsage.enterNumber(4);
    CalculatorUsage.enterOperation("*");
    CalculatorUsage.enterNumber(5);
    CalculatorUsage.enterOperation("=");
    CalculatorUsage.assertThatResultIs(20);
  }

  @Test
  @DisplayName("Our calculator should be able to subtract two numbers")
  @Tag("regression")
  @Tag("dashcalc")
  public void testThatCalculatorCanSubtractTwoNumbers() {
    CalculatorUsage.enterNumber(5);
    CalculatorUsage.enterOperation("-");
    CalculatorUsage.enterNumber(4);
    CalculatorUsage.enterOperation("=");
    CalculatorUsage.assertThatResultIs(1);
  }

  /**
   * Our calculator should be able to divide two numbers.
   *
   * For this test we only use integers where we expect the result to be an integers as well.
   */
  @Test
  @DisplayName("Our calculator should be able to divide two numbers")
  @Tag("regression")
  @Tag("runtime:fast")
  public void testThatCalculatorCanDivideTwoNumbers() {
    CalculatorUsage.enterNumber(20);
    CalculatorUsage.enterOperation("/");
    CalculatorUsage.enterNumber(5);
    CalculatorUsage.enterOperation("=");
    CalculatorUsage.assertThatResultIs(4);
  }

  @Test
  @DisplayName("Our calculator should fail if we try to divide by zero")
  @Tag("regression")
  @Tag("negative")
  @Tag("runtime:slow")
  public void testThatCalculatorFailsIfDivisorIsNull() {
    CalculatorUsage.enterNumber(5);
    CalculatorUsage.enterOperation("/");
    CalculatorUsage.enterNumber(0);
    CalculatorUsage.enterOperation("=");
    CalculatorUsage.assertThatCalculatorShowsAnError("Div0");
  }

  @Test
  @DisplayName("Our funny developers implemented an easter egg")
  @Disabled
  public void testThatCalculatorHasImplementedAnEasterEgg() {
    CalculatorUsage.enterOperation("+");
    CalculatorUsage.enterOperation("-");
    CalculatorUsage.enterOperation("*");
    CalculatorUsage.enterOperation("/");
    CalculatorUsage.enterOperation("=");
    CalculatorUsage.assertThatCalculatorShowsAnError("Its not easter yet :-)");
  }

  @AfterEach
  public void checkThatCalculatorDidNotLogAnyErrors() {
    CalculatorUsage.assertThatCalculatorDidNotLogAnyError();
  }

  @AfterAll
  @DisplayName("After we finished all the tests, we close our calculator app")
  public void terminateCalculator() {
    System.out.println("Some dummy debug information");
    CalculatorApp.close();
  }

}

	




