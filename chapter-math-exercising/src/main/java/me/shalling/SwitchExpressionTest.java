package me.shalling;

import me.shalling.entity.Foo;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author Shalling
 * @version v0.01
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2023/2/19 0:46
 */
public class SwitchExpressionTest {
  public static void main(String[] args) {
    // Object qux = new Qux();
    // @SuppressWarnings("DataFlowIssue")
    // Foo foo = (Foo) qux; // NO WAY, ERROR
    // foo.log();
    var scanner1 = new Scanner(System.in);
    var o = new SwitchExpressionTest().test(scanner1.nextLine());
    String typeName = o.getClass().getTypeName();
    System.out.println(typeName);
    System.out.println(o);
  }

  public <T> Object test(T s) {
    return switch (s) {
      case Double d -> {
        System.out.println(d);
        yield d * 100;
      }
      case Integer i -> i * 1000;
      case String str -> str.repeat(12);
      case Foo ignored -> Stream.generate(Foo::new).limit(12).toList();
      default -> 233;
    };
  }
}
