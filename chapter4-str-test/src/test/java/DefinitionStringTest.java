import me.shalling.DefinitionString;
import org.junit.jupiter.api.Test;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>DefinitionStringTest</h1>
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/24 15:38
 */
public class DefinitionStringTest {
  @Test
  public void definitionStringTest() {
    DefinitionString str = new DefinitionString(new char[]{'h', 'o', 'o', '2', 'w', '?'});
    System.out.println(str);
    DefinitionString insertStr = str
      .insertIntoDefineLocation(1, new DefinitionString(new char[]{'w', 'h', 'a', 't'}));
    System.out.println(insertStr);
    DefinitionString newStr = str.replace('h', 'e');
    System.out.println(newStr.replaceAll('o', 'H'));
  }
}
