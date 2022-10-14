public class Node {

private Record record; // Info (TTTRecord) to store in the Node
private Node nextNode; // the next connected node

public Node(Record record){
this.record = record;
this.nextNode = null;
}
public Node getNext() {
return nextNode;
}
public void setNext(Node next){
this.nextNode = next;
}
public Record getRecord() {
return record;
}
public void setRecord(Record newRecord) {
this.record = newRecord;
}
}