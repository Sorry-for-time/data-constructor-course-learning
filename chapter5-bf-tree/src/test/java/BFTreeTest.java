import me.shalling.BFTree;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>自定义二叉树测试</h1>
 * @package {PACKAGE_NAME}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/28 23:46
 */
public class BFTreeTest {
  /**
   * 二叉树创建, 插入新节点测试
   */
  @Test
  public void insertNodeTest() {
    BFTree<Integer> integerBFTree = new BFTree<>();
    List<Integer> list = Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).toList();
    list.forEach(e -> System.out.print(e + "\t"));
    list.forEach(integerBFTree::insertNode);
    System.out.println();
    System.out.println();
    System.out.println(integerBFTree);
  }

  @Test
  public void traversTest() {
    BFTree<Integer> integerBFTree = new BFTree<>();
    // fill data
    Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).forEach(integerBFTree::insertNode);
    // use foreach
    integerBFTree.forEach(e -> System.out.print(e + "\t"));
    System.out.println();
    // use iterator
    for (Integer integer : integerBFTree) {
      System.out.print(integer + "\t");
    }
  }

  @Test
  public void toListAndToStringTest() {
    BFTree<Integer> integerBFTree = new BFTree<>();
    // fill data
    Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).forEach(integerBFTree::insertNode);
    System.out.println(integerBFTree.toList());
    System.out.println(integerBFTree);
  }

  /**
   * 比较两棵树的大小(自定义比较)
   */
  @Test
  public void compareToTest() {
    BFTree<Integer> integerBFTree = new BFTree<>();
    BFTree<Integer> integerBFTree1 = new BFTree<>();
    // fill data
    Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).forEach(integerBFTree::insertNode);
    Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).forEach(integerBFTree1::insertNode);

    System.out.println(integerBFTree.compareTo(integerBFTree)); // 0
    System.out.println(integerBFTree.compareTo(integerBFTree1));
    System.out.println(integerBFTree1.compareTo(integerBFTree));
  }

  /**
   * 将树置空设置
   */
  @Test
  public void clearTreeTest() {
    BFTree<Integer> integerBFTree = new BFTree<>();
    Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).forEach(integerBFTree::insertNode);
    System.out.println("树的节点数: " + integerBFTree.getLength());
    System.out.println(integerBFTree);
    integerBFTree.clear();
    System.out.println("树的节点数" + integerBFTree.getLength());
    System.out.println(integerBFTree);
  }

  /**
   * 使用递归取得树的高度以及使用使用递归的方式插入新的节点
   */
  @Test
  public void insertNodeByRecursionTest() {
    var bfTree = new BFTree<Integer>();
    var root = bfTree.getRoot();
    final var arr = new int[]{23, 42, 11, 20, 10, 33, 43, 9, 13, 8, 7, 6, 6, 6, 23};
    Arrays.stream(arr).forEachOrdered(e -> bfTree.insertNodeByRecursion(root, e));
    System.out.println(bfTree.toList());
    System.out.printf("树的高度: %d", bfTree.getTreeHeight(bfTree.getRoot(), 0)); // 树的高度: 7
  }
}
