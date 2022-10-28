package me.shalling;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>简易版二叉树实现</h1>
 * @package {me.shalling}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/28 23:36
 */
public class BFTree<T extends Comparable<T>> implements Serializable {
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
   * @param data 插入新数据
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
}

