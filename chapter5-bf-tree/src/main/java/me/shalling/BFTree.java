package me.shalling;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>简易版二叉树实现</h1>
 * @package {me.shalling}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/28 23:36
 */
public class BFTree<T extends Comparable<T>> implements Serializable, Iterable<T>, Comparable<BFTree<T>> {
  @Serial
  private static final long serialVersionUID = -4152677334247907325L;

  /**
   * 树根节点定义
   */
  private TreeNode<T> root;

  /**
   * 树节点数记录
   */
  @Getter
  private int length;

  public BFTree() {

  }

  public BFTree(TreeNode<T> root) {
    this.root = root;
  }

  /**
   * @param data 插入树中的新数据
   */
  public void insertNode(final T data) {
    // 如果为空树的情况, 那么直接将第一个节点设置为树根
    if (null == root) {
      this.root = new TreeNode<>(data, null, null);
      ++this.length;
    } else {
      TreeNode<T> current = root;
      while (true) {
        if (data.compareTo(current.getDataDomain()) == 0) {
          break;
        } else if (data.compareTo(current.getDataDomain()) < 0) {
          if (null == current.getLeftChild()) {
            current.setLeftChild(new TreeNode<>(data, null, null));
            this.length++;
            break;
          } else {
            current = current.getLeftChild();
          }
        } else {
          if (null == current.getRightChild()) {
            current.setRightChild(new TreeNode<>(data, null, null));
            this.length++;
            break;
          } else {
            current = current.getRightChild();
          }
        }
      }
    }
  }

  /**
   * 通过递归执行中序遍历
   *
   * @param start    遍历起点
   * @param consumer 数据执行器
   */
  private void dorTraverse(TreeNode<T> start, Consumer<? super T> consumer) {
    // 如果节点不为空, 那么就是递归, 直到结束
    if (null != start) {
      dorTraverse(start.getLeftChild(), consumer);
      consumer.accept(start.getDataDomain());
      dorTraverse(start.getRightChild(), consumer);
    }
  }

  /**
   * 将树中的节点存储到列表上并进行返回, 这个列表的值为只读, 不允许进行修改
   *
   * @return 树中节点升序排列的只读列表
   */
  public List<T> toList() {
    ArrayList<T> tArrayList = new ArrayList<>();
    TreeNode<T> start = this.root;
    dorTraverse(start, tArrayList::add);
    return Collections.unmodifiableList(tArrayList);
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {
      private final Iterator<T> iterator = toList().iterator();

      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      public T next() {
        return iterator.next();
      }
    };
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    TreeNode<T> start = this.root;
    dorTraverse(start, action);
  }

  @Override
  public String toString() {
    return "BFTree{" +
      "root=" + root +
      ", length=" + length +
      '}';
  }

  @Override
  public int compareTo(BFTree<T> o) {
    // 如果指向了相同的地址, 那么就是相同的树
    if (o == this) {
      return 0;
    }
    // 先判断子节点个数
    if (this.length > o.length) {
      return 1;
    } else if (this.length < o.length) {
      return -1;
    } else {
      // 两棵树节点数相同的情况, 通过比较升序节点的每个值来判断
      Object[] oNodes = o.toList().toArray();
      Object[] origin = this.toList().toArray();
      for (int i = 0; i < origin.length; i++) {
        @SuppressWarnings("unchecked")
        var comp = (T) origin[i];
        @SuppressWarnings("unchecked")
        var comp1 = (T) oNodes[i];
        // 每个节点比较大小, 只要有一个节点的大小和另一个节点不相等, 那么就立即返回结果
        if (comp.compareTo(comp1) > 0) {
          return 1;
        } else if (comp.compareTo(comp1) < 0) {
          return -1;
        }
      }
      // 如果每个节点的大小比较均相等, 那么就认为是大小相等的树
      return 0;
    }
  }
}
