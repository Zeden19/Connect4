/*
 * Author: Arjun Sharma (251240847)
 * This class is a dictionary hashtable that stores class "Record" objects using hashing
 * Date: October 19, 2022
 */

import java.util.Objects;
public class Dictionary implements DictionaryADT {
    private Node<Record>[] data;
    private int size;
    private int numRecords;


    /**
     * Constructor Class
     * @param size: the size of the hashtable
     */
    public Dictionary(int size) {
        this.size = size;
        data = new Node[size];

        // making all nodes empty
        for (int i = 0; i < size; i++) {
            data[i] = new Node<>(null);
        }

    }

    /**
     * Puts an item in the dictionary using hashing
     * @param rec: A record object that has a key and 2 int values
     * @return 0 is there were no collisions, and 1 if the added value caused a collision
     * @throws DuplicatedKeyException if the Record key has a key that was already in the hashtable
     */
    public int put(Record rec) throws DuplicatedKeyException {
        // getting the hashcode
        String key = rec.getKey();
        int hashCode = polynomialHashFunction(key);


        // Non-Collision case (if no node data)
        Node<Record> recordNode = new Node<>(rec);
        if (data[hashCode].getData() == null) {
            data[hashCode] = recordNode;
            numRecords++;
            return 0;

            // collision case
        } else {
            // searching the first node for duplicate keys
            Node<Record> current = data[hashCode];
            if (Objects.equals(current.getData().getKey(), rec.getKey())) {
                throw new DuplicatedKeyException("Key found in hashtable");}

            // searching all subsequent nodes for duplicate keys
            while (current.getNext() != null){
                current = current.getNext();
                if (Objects.equals(current.getData().getKey(), rec.getKey())) {
                    throw new DuplicatedKeyException("Key found in hashtable");}
            }

            // when we found the end node, then add to the linked list
            current.setNext(recordNode);
            numRecords++;
            return 1; }

    }


    /**
     * Removes a Record object from the hash table
     * @param key: the key of the value that the user wishes to remove
     * @throws InexistentKeyException if the key was not found in the hashtable
     */
    public void remove(String key) throws InexistentKeyException {
        // getting hashcode and index
        int hashCode = polynomialHashFunction(key);
        Node<Record> current = data[hashCode];

        // If the key was not found to be a index at all
        if (current.getData() == null) {
            throw new InexistentKeyException("Key not found in dictionary");
        }

        // if the key is found in the first node
        if (Objects.equals(current.getData().getKey(), key)) {
            if (current.getNext() == null) {data[hashCode] = new Node<>(null);}
            else {data[hashCode] = current.getNext();}
            numRecords--;
            return;

        }

        // Checking for middle found
        while (current.getNext() != null) {
            if (Objects.equals(current.getNext().getData().getKey(), key)) {
                if (current.getNext().getNext() == null) {current.setNext(new Node<>(null));} // if node was at the end
                else {current.setNext(current.getNext().getNext());} // if node was not at the end
                numRecords--;
                return;
            }
            current = current.getNext();
        }
        // if key is found at the end
        if (Objects.equals(current.getData().getKey(), key)) {
            current.setNext(new Node<>(null));
            numRecords--;

        // if key is not found
        } else {
            throw new InexistentKeyException("Key not found in dictionary");
        }
    }


    /**
     * Gets the Record object from the key
     * @param key: key of the record object that the user wishes to find
     * @return null if the object was not found, or the Record that has the key
     */
    public Record get(String key) {
        // Getting the index from the key
        Node<Record> current = data[polynomialHashFunction(key)];

        // if no nodes
        if (current.getData() == null) {return null;}

        // if in first node
        if (Objects.equals(current.getData().getKey(), key))
            {return current.getData();}

        // if more than one node, check each node in the chain
        else {
            while (current.getNext() != null) {
                if (Objects.equals(current.getData().getKey(), key)) {return current.getData();}
                current = current.getNext();
            }
            // If in the last node in the linked list
            if (Objects.equals(current.getData().getKey(), key)) {return current.getData();}

            // if no data was found in the linked list
            return null;
        }
    }


    /**
     * The number of records stored in the hash table
     * @return number of records in the hash table
     */
    public int numRecords() {
        return numRecords;
    }

    /**
     * The hash function that gives the class a array index
     * @param key: The key that will be used to get the index
     * @return the hashcode which will be used as the index
     */
    private int polynomialHashFunction(String key) {
        int keySize = key.length();
        int hashCode;

        // polynomial hash function
        hashCode = key.charAt(keySize - 1);
        for (int i = keySize - 2; i >= 0; i--) {
            hashCode = (hashCode * 31 + (int) key.charAt(i)) % size;
        }
        return hashCode;
    }

}


