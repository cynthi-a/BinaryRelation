import java.util.Set;
import java.util.TreeSet;

public class BinaryRelationImpl<X, Y> implements BinaryRelation<X, Y> {

    //My implementation is based on a hash table data structure.
    //Each BinaryRelationImpl object contains an array of SLL Nodes.
    private Node<X, Y>[] buckets;

    /// Constructor ///
    @SuppressWarnings("unchecked")
    public BinaryRelationImpl(int arrayLength) {
        //Takes an integer to decide over the length of the Node array
        //as an argument and instantiates an empty hash table
        //with the given length.
        buckets = (Node<X, Y>[]) new Node<?, ?>[arrayLength];
    }

    //hash function to compute the index of the array associated with
    //a given X value
    private int hash(X xValue) {
        return Math.abs(xValue.hashCode()) % buckets.length;
    }

    @Override
    public void addPair(X xValue, Y yValue) {
        //Avg complexity: O(1)
        //Calculate the hash associated with the given X value.
        int b = hash(xValue);
        //Get the node that is associated with the hash.
        Node<X, Y> curr = buckets[b];
        //Iterate through the SLL:
        while (curr != null) {
            //If a node already contains the same X and Y value
            //pair, return without adding.
            if (xValue.equals(curr.xValue)) {
                if (curr.yValue.equals(yValue)) {
                    return;
                }
            }
            curr = curr.next;
        }
        //If the pair does not exist already, add it to the hash table
        //by instantiating a new node and putting it to the front of
        //a SLL.
        buckets[b] = new Node<>(xValue, yValue, buckets[b]);
    }

    @Override
    public void removePair(X xValue, Y yValue) {
        //Avg complexity: O(1)
        //Algorithm is similar to the one in addPair()
        //Calculate the hash associated with the given X value.
        int b = hash(xValue);
        //Get the first node of the SLL containing the given X value
        //and store it in current.
        Node current = buckets[b];
        //Keep track of the predecessor Node of current and store it
        //in pred.
        Node pred = null;
        //Iterate through the SLL:
        while (current != null) {
            //If the given value matches the one in the current Node,
            if (xValue.equals(current.xValue)) {
                //check if the Node's Y value matches the given Y value
                if (current.yValue.equals(yValue)) {
                    //If pred is null, link the first Node of the
                    //SLL to the next one.
                    if (pred == null)
                        buckets[b] = current.next;
                    //Otherwise, link pred to current's next node and terminate.
                    else
                        pred.next = current.next;
                    return;
                }
            }
            pred = current;
            current = current.next;
        }
    }

    @Override
    public boolean contains(X wantedX, Y wantedY) {
        //Avg complexity: O(1)
        //Compute the hash associated with the given X value.
        int b = hash(wantedX);
        //Get the SLL associated with the hash and store it in current.
        Node current = buckets[b];
        //Iterate through the SLL:
        while (current != null) {
            //if both the X and Y values of the current Node
            //match the given XY values, return true.
            if (wantedX.equals(current.xValue)) {
                if (current.yValue.equals(wantedY)) {
                    return true;
                }
            }
            current = current.next;
        }
        //Otherwise return false.
        return false;
    }

    @Override
    public Set getValues(X wantedX) {
        //Worst case complexity: O(n), best case: O(1)
        //Get the hash associated with the given X value
        int hash = hash(wantedX);
        //Instantiate a new TreeSet to store the values.
        TreeSet yValues = new TreeSet<Y>();
        //Get the first node of the SLL associated with the hash
        //and store it in current.
        Node current = buckets[hash];
        //Iterate through the SLL:
        while (current!=null) {
            //If the X value of the Node matches the given X value,
            if (wantedX.equals(current.xValue)) {
                //add it to the TreeSet.
                yValues.add(current.yValue);
            }
            current = current.next;
        }
        //Return the TreeSet.
        return yValues;
    }

    @Override
    public Set getXvalues(Y wantedY) {
        //Complexity: O(n)
        //Instantiate a new TreeSet to store the values.
        TreeSet xValues = new TreeSet<Y>();
        //Iterate through each Node of every SLL:
        for(Node curr : buckets) {
            while (curr != null) {
                //If the Node's Y value matches the given one,
                if (curr.yValue == wantedY) {
                    //add its X value to the TreeSet.
                    xValues.add(curr.xValue);
                }
                curr = curr.next;
            }
        }
        //Return the TreeSet
        return xValues;
    }

    @Override
    public void clear() {
        //Complexity O(n)
        //Iterate through the array list of SLLs
        for (int i = 0; i < buckets.length; i++) {
            //and set each first Node of the SLLs to null.
            buckets[i] = null;
        }
    }

    @Override
    public void removeRelationsByX(X wantedX) {
        //Complexity: O(1)
        //Get the hash associated with the given X value
        int hash = hash(wantedX);
        //If the first Node of the SLL associated with the given hash
        //contains the same X value as the given one,
        if (buckets[hash].xValue.equals(wantedX)) {
            //set that Node to null.
            buckets[hash] = null;
        }
    }

    @Override
    public void removeRelationsByY(Y wantedY) {
        //Complexity: O(n)
        //Takes advantage of the function getXvalues() which
        //returns a Set of X values that share the same Y value
        //and of the function removePair().

        //Pass the given Y value to the getXvalue() function and
        //iterate through the returned Set:
        for (Object x : getXvalues(wantedY)) {
            //For every X value, pass it as the first argument of removePair()
            //and pass the given Y value as the second.
            removePair((X) x, wantedY);
        }
    }

    public String toString() {
        //Complexity: O(n)
        StringBuilder stringBuilder = new StringBuilder();
        Node<X, Y> curr;
        //Iterate through each SLL in buckets:
        for(int i = 0; i < buckets.length; i++){
            curr = buckets[i];
            //If the first node of the SLL is not empty,
            //iterate through the SLL and
            if (curr != null) {
                while (curr != null) {
                    //append each Node's XY values to a StringBuilder object.
                    stringBuilder.append("(" + curr.xValue + ": " + curr.yValue + "), ");
                    curr = curr.next;
                }
                //Add a new line after each Node.
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /// inner class Node for X Y value pairs ///
    private static class Node<X, Y> {
        // Entries have both an X and Y value and a next node
        // Each node is a SLL
        private X xValue;
        private Y yValue;
        private Node<X, Y> next;
        private Node(X xValue, Y val, Node<X, Y> next) {
            this.xValue = xValue;
            this.yValue = val;
            this.next = next;
        }
    }
}