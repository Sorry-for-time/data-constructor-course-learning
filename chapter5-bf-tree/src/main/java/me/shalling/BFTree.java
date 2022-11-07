package me.shalling;

import lombok.Getter;
import lombok.NonNull;

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
   * 置空树
   */
  public void clear() {
    this.root = null;
    this.length = 0;
  }

  /**
   * 当前树是否为空
   *
   * @return 如果树为空, 返回 true, 否则返回 false
   */
  public boolean isEmpty() {
    return null == this.root;
  }

  /**
   * 删除树中匹配的节点
   *
   * @param matchValue 进行匹配的值
   * @return 被删除节点数据域的值
   */
  public void delete(@NonNull T matchValue) {
    // 0.如果是一棵空树, 则直接返回, 什么也不做
    if (this.isEmpty()) {
      return;
    }

    // 删除的节点是根节点的情况
    if (this.root.getDataDomain().compareTo(matchValue) == 0) {
      // 1.树中只存在一个根节点, 左右子树均为空的情况
      if (this.root.getLeftChild() == null && this.root.getRightChild() == null) {
        this.root = null;
      }
      // 2.左子树不为空, 右子树为空情况
      else if (this.root.getLeftChild() != null && this.root.getRightChild() == null) {
        this.root = this.root.getLeftChild();
      }
      // 3.左子树为空, 右子树不为空情况
      else if (this.root.getLeftChild() == null && this.root.getRightChild() != null) {
        this.root = this.root.getRightChild();
      }
      // 4.左右子树均不为空的情况
      else {
        var leftChild = this.root.getLeftChild();
        // 取得左子树的右叶子节点
        while (leftChild.getRightChild() != null) {
          leftChild = leftChild.getRightChild();
        }
        // 将根节点右子树移动到根节点左子树的右叶子节点上
        leftChild.setRightChild(this.root.getRightChild());
        // 更新 root 节点的引用
        this.root = this.root.getLeftChild();
      }
      // 更新节点记录
      --this.length;
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
