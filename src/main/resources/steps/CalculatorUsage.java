package src.main.resources.steps;

public class CalculatorUsage {

    /**
     * Enter a specified number.
     */
    public static void enterNumber(Integer number) {

    }

    /**
     * Enter a arithmetic operation as a char.
     * <p>
     * Allowed operations are +, -, *, / and =
     */
    public static void enterOperation(String operation) {

    }

    /**
     * Verify that the calculator shows a given number.
     * <p>
     * This method can be used to verify results of a calculation
     */
    public static void assertThatResultIs(Integer number) {

    }

    /**
     * Verify that the calculator shows a given error message.
     * <p>
     * This method can be used to verify results of a failed calculation
     */
    public static void assertThatCalculatorShowsAnError(String message) {

    }

    /**
     * Verify that the calculator did not log any error messages.
     */
    public static void assertThatCalculatorDidNotLogAnyError() {

    }

}