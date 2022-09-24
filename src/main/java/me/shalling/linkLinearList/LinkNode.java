package me.shalling.linkLinearList;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>链表实现顺序表的节点定义</h1>
 * @package {me.shalling.linkLinearList}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/9/24 18:46
 */
public class LinkNode<T> {
  private T dataItem;

  private LinkNode<T> next;


  public LinkNode() {
  }

  public LinkNode(T dataItem, LinkNode<T> next) {
    this.dataItem = dataItem;
    this.next = next;
  }

  public T getDataItem() {
    return dataItem;
  }

  public void setDataItem(T dataItem) {
    this.dataItem = dataItem;
  }

  public LinkNode<T> getNext() {
    return next;
  }

  public void setNext(LinkNode<T> next) {
    this.next = next;
  }


  @Override
  public String toString() {
    return "LinkNode{" +
            "dataItem=" + dataItem +
            ", next=" + next.getDataItem() + // 防递归引用
            '}';
  }
}

