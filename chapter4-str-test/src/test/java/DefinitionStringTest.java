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
  private final static char[] chars = {'h', 'o', 'w', ' ', 'h', 'o', 'w'};
  private final static DefinitionString originStr = new DefinitionString(chars);

  /**
   * 构建自定义字符串和重写 toSting() 方法测试
   */
  @Test
  public void buildAndToStringTest() {
    DefinitionString definitionString = new DefinitionString(originStr);
    System.out.println(definitionString);
  }

  /**
   * replace 方法测试
   */
  @Test
  public void replaceTest() {
    DefinitionString replaceStr = originStr.replace('w', 'W');
    System.out.println(replaceStr);
  }

  /**
   * replaceAll 方法测试
   */
  @Test
  public void replaceAllTest() {
    DefinitionString replaceStr = originStr.replaceAll('h', 'H');
    System.out.println(replaceStr);
  }

  @Test
  public void insertIntoByLocationTest() {
    DefinitionString newStr = originStr.insertIntoByLocation(originStr.length() - 2, originStr);
    System.out.println(newStr);
  }

  /**
   * 判断单个字符在自定义字符串中首次出现的位置
   */
  @Test
  public void indexOfCharTest() {
    System.out.println(originStr.indexOf('w'));
  }

  /**
   * 取得子串主字符串中首次出现的位置
   */
  @Test
  public void indexOfDefineStringTest() {
    final DefinitionString compareStr = new DefinitionString(new char[]{' ', 'h', 'o'});
    System.out.println(originStr.indexOf(compareStr)); // 3
    final DefinitionString compareStr1 = new DefinitionString(new char[]{' ', 'h', 'e'});
    System.out.println(originStr.indexOf(compareStr1)); // -1
    final DefinitionString compareStr2 = new DefinitionString(new char[]{'h', 'o'});
    System.out.println(originStr.indexOf(compareStr2)); // 0
  }

  /**
   * 取得自定义字符串的原始数组拷贝
   */
  @Test
  public void toCharArrayTest() {
    System.out.println(originStr.toCharArray());
  }

  /**
   * 截取子串测试
   */
  @Test
  public void subStrTest() {
    System.out.println(originStr.subStr(1, 5));
  }

  /**
   * 将截取的子串以字符数组的方式返回
   */
  @Test
  public void subStrArrayTest() {
    System.out.println(originStr.subStrArray(1, 5));
  }

  /**
   * 字符串拼接测试
   */
  @Test
  public void concatTest() {
    System.out.println(originStr.concat(originStr));
  }
}
