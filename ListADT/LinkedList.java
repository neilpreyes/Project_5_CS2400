package ListADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements ListIteratorInterface<T>{
   private Node firstNode;
   private int  numberOfEntries;

   private void initializeDataFields(){
      firstNode = null;
      numberOfEntries = 0;
   }

   public LinkedList(){
      initializeDataFields();
   }

   public void clear(){
		initializeDataFields();
	}

   private Node getNodeAt(int givenPosition){
      Node currentNode = firstNode;

      for (int counter = 1; counter < givenPosition; counter++)
         currentNode = currentNode.getNextNode();

      return currentNode;
   }

   public void add(T newEntry){
      Node newNode = new Node(newEntry);
   
      if (isEmpty()){
         firstNode = newNode;
      }else{
         Node lastNode = getNodeAt(numberOfEntries);
         lastNode.setNextNode(newNode);
      }
      
      numberOfEntries++;
   } 

   public void add(int givenPosition, T newEntry){
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1)){
         Node newNode = new Node(newEntry);
         if (givenPosition == 1){

            newNode.setNextNode(firstNode);
            firstNode = newNode;

         }else{

            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);

         }
         numberOfEntries++;
      }else{
         throw new IndexOutOfBoundsException( "Illegal position given to add operation.");
        }
   } 

   public T remove(int givenPosition){
      T result = null;
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){

         if (givenPosition == 1){
            result = firstNode.getData();
            firstNode = firstNode.getNextNode();
         }else{
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeToRemove = nodeBefore.getNextNode();
            result = nodeToRemove.getData();
            Node nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);
         }
         numberOfEntries--;
         return result;
      }
      else
         throw new IndexOutOfBoundsException(
                   "Illegal position given to remove operation.");
   }

   public T replace(int givenPosition, T newEntry){
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         Node desiredNode = getNodeAt(givenPosition);
         T originalEntry = desiredNode.getData();
         desiredNode.setData(newEntry);
         return originalEntry;
      }
      else
         throw new IndexOutOfBoundsException(
                   "Illegal position given to replace operation.");
   }

   public T getEntry(int givenPosition){
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
         return getNodeAt(givenPosition).getData();
      }else{
         throw new IndexOutOfBoundsException(
                   "Illegal position given to getEntry operation.");
    }
   }

   public T[] toArray(){

      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries];
      
      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null)){
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++;
      }
      
      return result;
   }

   public boolean contains(T anEntry){
        boolean found = false;
        Node currentNode = firstNode;
      
        while (!found && (currentNode != null)){
            if (anEntry.equals(currentNode.getData())){
                found = true;
            }else{
                currentNode = currentNode.getNextNode();
        }
      }
      
      return found;
   }

   public boolean isEmpty(){
        boolean result;
         
        if (numberOfEntries == 0) {
         result = true;
        }else{
         result = false;
        }
         
        return result;
   } 

   public Iterator<T> iterator(){
	   return getIterator();
   }

	public Iterator<T> getIterator(){
	   return new Iterator<T>() {
          Node currentNode = firstNode;
          @Override
          public boolean hasNext() {
             return currentNode != null;
          }

          @Override
          public T next() {
             T val = currentNode.getData();
             currentNode = currentNode.getNextNode();
             return val;
          }
       };
	}
	
   public int getLength(){
      return numberOfEntries;
   }

	private class Node{

      private T    data;
      private Node next;
      
      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;
      }
      
      private Node(T dataPortion, Node nextNode)
      {
         data = dataPortion;
         next = nextNode;
      }
      
      private T getData()
      {
         return data;
      }
      
      private void setData(T newData)
      {
         data = newData;
      }
      
      private Node getNextNode()
      {
         return next;
      }
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } 
	} 
} 
