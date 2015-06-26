/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw_variable;

import java.util.ArrayList;

/**
 *
 * @author barboza
 */

public class Dictionary {
    /*Armazena a lista de simbolos do dicionario*/
    protected ArrayList<Entry> entries;
    
    /*Indica a quantidade de simbolos presentes no dicionario*/
    protected long numberOfWords;
    
    /*Indica a quantidade limite de bytes que o dicionario pode armazenar no momento*/
    protected int capacity;
    
    public Dictionary() {
        /*Initializes with the ASCII table*/
        this.numberOfWords = 0;
        this.entries = new ArrayList<>();
        
        for (int i = 0; i < 256; i++) {
             Integer counter = new Integer(i);
             Entry tmpEntry = new Entry(i, String.valueOf((char) counter.intValue()));
             this.appendNewEntry(tmpEntry);
        }
    }
    
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public long getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(long numberOfWords) {
        this.numberOfWords = numberOfWords;
    }
    
    /*
     * appendNewEntry: adds an new entry.
     * 
     * given an object of the type Entry, adds it to the dictionary, 
     * at the end of the dictionary. The string defines the word to be coded
     * and the 2-bytes char defines the calculated code for the new entry.
     * 
     * @newEntry: the new entry to be added to the dictionary.
     *
     * returns: void.
     */
    public void appendNewEntry(Entry newEntry) {
        if (newEntry != null) {
            Entry key = isInTheDictionary(newEntry.getWord());
            if (key == null) {
                this.entries.add(key);
                this.numberOfWords = this.entries.size();
                this.capacity = newEntry.getCode().length;
            }
        } else {
            System.err.println("ERROR: Null pointer at appenNewEntry(Entry newEntry).");
        }
    }
    
     /*
     * appendNewEntry: adds an new entry
     * 
     * given an new string, adds it to the dictionary, 
     * at the end of the dictionary. The string defines the word to be coded
     * and the 2-bytes char defines the calculated code for the new entry.
     * 
     * @newStr: the new string which will be added at the end of the dictionary.
     *
     * returns: void.
     */
    public void appendNewEntry(String newStr) {
        if (newStr != null) {
            if (isInTheDictionary(newStr) == null) {
                Entry newEntry = new Entry(this.numberOfWords + 1, newStr);
                this.entries.add(newEntry);
                this.numberOfWords = this.entries.size();
                this.capacity = newEntry.getCode().length;
            }
        } else {
            System.err.println("ERROR: Null pointer at appenNewEntry(String newStr).");
        }
    }
    
    /*
     * appendNewEntry: adds an new entry
     * 
     * given an new string, adds it to the dictionary, 
     * at the end of the dictionary. The string defines the word to be coded
     * and the 2-bytes char defines the calculated code for the new entry.
     * 
     * @newStr: the new string which will be added at the end of the dictionary.
     *
     * returns: void.
     */
    public Entry isInTheDictionary(String key) {
        if (key == null || key == "") {
            System.err.println("Null pointer at isInTheDictionary(String key)");
            return null;
        }
        
        for (Entry tmpEntry : this.entries) {
            if (tmpEntry.getWord().equals(key)) {
                return tmpEntry;
            }
        }

        return null;
    }
    
    /*
     * isInTheDictionary: look up for an given code in the dictionary.
     * 
     * given an int of two bytes, look up for the given code at
     * the dicionary.  Performs an linear search.
     * 
     * @code: the 2-byte int to be searched at the dictionary.
     *
     * returns: the corresponding entry, if the code was found
     *          null pointer, otherwise.
     */
    public Entry isInTheDictionary(long code) {
        byte[] bincode = BitManipulator.getBytetArray(code);
        for (Entry tmpEntry : this.entries) {
            if (tmpEntry.isTheSameCode(bincode))
                return tmpEntry;
        }
        return null;
    }
}
