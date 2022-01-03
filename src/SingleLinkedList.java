/**
 * [SingleLinkedList].java
 * A single linked list that contains nodes linked to each other
 * @author Derek Chen
 */
class SingleLinkedList<E> {
  private Node<E> head;
  
  /**
   * add
   * Adds a new node with the given data
   * @param data, the data being added
   */
  public void add(E data) {
    Node<E> temp = head;
    
    if (head == null) {
      head = new Node<E>(data);
      return;
    }
    
    while (temp.getNext() != null) {
      temp = temp.getNext();
    }
    
    temp.setNext(new Node<E>(data));
  }
  
  /**
   * get
   * Gets the data at the given index
   * @return E, the data at the given index
   */
  public E get(int index) {
    if (index >= size()){
      return null;
    }
    
    Node<E> temp = head;
    
    if (head == null) {
      return null;
    }
    
    if (index == 0) {
      return head.getData();
    }
    
    for (int i = 0; i < index; i++) {
      temp = temp.getNext();
    }
    
    return temp.getData();
  }
  
  /**
   * indexOf
   * Gets the first index of the given data
   * @return int, the first index of the data
   */
  public int indexOf(E data) {
    Node<E> temp = head;
    int index = 0;
    
    if (head == null) {
      return -1;
    }
    
    if (head.getData().equals(data)) {
      return index;
    }
    
    while (temp.getNext() != null) {
      if (temp.getData().equals(data)) {
        return index;
      } else {
        index++;
      }
      temp = temp.getNext();
    }
    
    return -1;
  }
  
  /**
   * remove
   * Removes the node at the given index
   * @param index, the integer value of the index
   */
  public void remove(int index) {
    Node<E> temp = head;
    
    if (head == null) {
      return;
    }
    
    if (size() == 1){
      head = null;
      return;
    }
    
    for (int i = 0; i < index - 1; i++) {
      temp = temp.getNext();
    }
    
    if (temp.getNext().getNext() != null) {
      temp.setNext(temp.getNext().getNext());
    } else {
      temp.setNext(null);
    }
  }
  
  /**
   * remove
   * Removes the node with the given data
   * @param data, the data to be removed
   * @return boolean, true if removal was successful
   
  public boolean remove(E data) {
    Node<E> temp = head;
    
    if (head == null) {
      return false;
    }
    
    if (head.getData().equals(data)) {
      head = head.getNext();
      return true;
    }
    
    while (temp.getNext() != null) {
      if (temp.getNext().getData().equals(data)) {
        temp.setNext(temp.getNext().getNext());
        temp = temp.getNext();
        temp = null;
        return true;
      } else {
        temp = temp.getNext();
      }
    }
    
    return false;
  }*/
  
  /**
   * clear
   * Clears the single linked list
   */
  public void clear() {
    head = null;
  }
  
  /**
   * size
   * Gets the number of items in the list
   * @return int, the number of items in the list
   */
  public int size() {
    Node<E> temp = head;
    int size = 0;
    
    if (head == null) {
      return size;
    } else {
      size++;
    }
    
    while (temp.getNext() != null) {
      size++;
      temp = temp.getNext();
    }
    
    return size;
  }
}