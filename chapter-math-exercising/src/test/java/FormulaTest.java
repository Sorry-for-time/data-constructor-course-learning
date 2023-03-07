import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import static me.shalling.FormulaParser.getFormulaSuffixTokenList;
import static me.shalling.FormulaParser.reduceTokenList;

/**
 * @author Shalling
 * @version v0.01
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2023/3/7 14:55
 */
public class FormulaTest {
  @RepeatedTest(value = 1)
  public void formulaParseAndReduceTest() {
    String mathText1 = "3 * 4 + 2 * -3 * ( -3 - 2 ) * 4 - ( 12 * -3 )";
    var value1 = 3 * 4 + 2 * -3 * (-3 - 2) * 4 - (12 * -3);
    Assertions.assertEquals(value1, reduceTokenList(getFormulaSuffixTokenList(mathText1, " ")));
    System.out.println(value1);

    String mathText2 = "2 * -3 - 6 * -2 * -1 - ( 1 - -3 ) * 3 - -4 * 9";
    var value2 = 2 * -3 - 6 * -2 * -1 - (1 - -3) * 3 - -4 * 9;
    Assertions.assertEquals(value2, reduceTokenList(getFormulaSuffixTokenList(mathText2, " ")));
    System.out.println(value2);

    String mathText3 = "2 * ( ( 4 - 1 ) * -3 - -12 + ( 3 * -3 - 9 ) - 12 * -3 - -2 - 8 * 32 ) - 12 * -2";
    var value3 = 2 * ((4 - 1) * -3 - -12 + (3 * -3 - 9) - 12 * -3 - -2 - 8 * 32) - 12 * -2;
    Assertions.assertEquals(value3, reduceTokenList(getFormulaSuffixTokenList(mathText3, " ")));
    System.out.println(value3);
  }
}
