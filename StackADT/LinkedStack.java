package StackADT;

import java.util.EmptyStackException;

public final class LinkedStack<T> implements StackInterface<T> 
{
    private Node topNode; 

    public LinkedStack()
    {
        topNode = null;
    } 

    public void push(T newEntry)
    {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    public T peek()
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return topNode.getData();
        }
    }

    public T pop()
    {
        T top = peek();
        topNode = topNode.getNextNode();
        
        return top;
    }

    @Override
    public boolean isEmpty() 
    {
        return topNode == null;
    } 

    @Override
    public void clear() 
    {
        topNode = null;
    } 

    private class Node
    {
        private T    data; 
        private Node next; 

        public Node(T dataPortion)
        {
           this(dataPortion, null);
        } 

        public Node(T dataPortion, Node nextNode)
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