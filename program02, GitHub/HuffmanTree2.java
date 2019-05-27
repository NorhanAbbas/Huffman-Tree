import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Arrays;

import java.util.ArrayList;
import java.lang.String;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Author: Norhan Abbas
 * Date:   26th of May, 2019
 *
 * Overview: This program is a Huffman Tree Implementation
 *           1- It takes the input from a file
 *           2- Construct a frequency table for the different characters the input is composed of
 *           3- Based on the frequency table, it creates a Huffman tree and a bitmap
 *           4- Using the Bitmap, it encodes the input
 *           5- Then, decode it again
 *                  Which means your input and output file have to be exactly the same
 *                  Otherwise, there is something wrong going on
 */
//************************************************************************************************
    // a node class
class HuffmanNode {

    int value;
    char c;

    HuffmanNode left;
    HuffmanNode right;


}
//************************************************************************************************
// comparator to compare 2 nodes based on their frequency
class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y)
    {

        return x.value - y.value;
    }
}

//************************************************************************************************
/*class Pair
{
    // global variables of this class

    char letter;
    int frequency;

    // constructor has type of data that is required
    Pair(char letter, int frequency)
    {
        // initialize the input variable from main
        // function to the global variable of the class
        this.letter = letter;
        this.frequency = frequency;
    }

    public char getChar(){
        return this.letter;
        //return letter;
    }

    public int getFreq(){
        return this.frequency;
    }
}*/
//************************************************************************************************
public class HuffmanTree2 {

    // a class to count how many times each letter has been repeated
    // the array of frequencies is initially filled with 1s
    //      As each letter has been repeated at least one
    // We split the input into characters and put them in a character array
    // THEN, we iterate through the whole array of characters to count how many times each character has been repeated

    public static int[] getFrequ(char[] cArray, int total) {

        int[] freq = new int[cArray.length];
        Arrays.fill(freq, 1);

        //ArrayList<Pair> list = new ArrayList<>();


        for (int i = 0; i < total - 1; i++) {   // iterating through the whole array except for the last one
            for (int k = 0; k < total; k++) {

                if (i == k) {            // in order NOT to count the same letter at the same index twice
                    k++;
                }
                if (cArray[i] == cArray[k]) {    // same letter found
                    freq[i] += 1;                // the frequency of one of them is incremented

                }
            }
        }


        for (int k = 0; k < total - 1; k++) {      // this loop is mainly for the last character,
                                                    // just in case it was unique, we wouldn't have compared it to rest with the previous loop
            if (cArray[total - 1] == cArray[k]) {   // the last character was found somewhere else
                freq[total - 1] += 1;               // Then the frequency of the last character was incremented
            }
        }
        return freq;
    }

//************************************************************************************************

    // this function return 2 arrays
    // an array with the characters in the input WITH NO REPETITION
    // another array with the corresponding frequencies to each character in the character array

 /*   public static void noRepeat(char[] ch, int[] cf) {

        ArrayList<Character> charAl = new ArrayList<>();
        ArrayList<Integer> freqAl = new ArrayList<>();

        for (int i = 0; i < cf.length - 1; i++) {
            for (int k = 0; k < cf.length; k++) {

                if (i == k) {
                    k++;
                }

                if (ch[i] == ch[k]) {
                    cf[i] = cf[k];
                    ch[k] = ' ';
                }

            }

        }

        for (int i = 0; i< cf.length; i++) {
            if (ch[i] != ' ') {

                charAl.add(ch[i]);
                freqAl.add(cf[i]);
            }
        }

        char[] charArray = new char[charAl.size()];
        int[] freqArray = new int[freqAl.size()];

        for (int k = 0; k < charAl.size(); k++) {
            charArray[k] = charAl.get(k);
        }

        for (int k = 0; k < freqAl.size(); k++) {
            freqArray[k] = freqAl.get(k);
        }


        for (char x : charArray) {
            System.out.println(x);
        }

        for (int y : freqArray) {
            System.out.println(y);
        }
    }*/
//************************************************************************************************
    // This function returns an array with all of the characters in the input
    // WITHOUT REPEATING any character twice

    public static char[] noRepeat_char(char[] ch) {

        ArrayList<Character> charAl = new ArrayList<>();
        for (int i = 0; i < ch.length - 1; i++) {
            for (int k = 0; k < ch.length; k++) {

                if (i == k) {           // in order NOT to compare the same letter at the same index
                    k++;
                }

                if (ch[i] == ch[k]) {  // the same character was found
                    ch[k] = ' ';       // replace one of them with just SPACE
                }
            }
        }

        for (int i = 0; i< ch.length; i++) {     // then, we iterate through our array

            if (ch[i] != ' ') {                  // characters exist only once within the array now, whenever they're repeated, a SPACE exists
                charAl.add(ch[i]);               // so if the character is not space, we add it to our array list
            }
        }


        char[] charArray = new char[charAl.size()];   // convert the array list to array, it's easier to start with array list since we are not fully aware of the size of our characters
        for (int k = 0; k < charAl.size(); k++) {     // RECALL: array lists can be expanded
            charArray[k] = charAl.get(k);
        }
/*        for (char x : charArray) {                  // if you would like to print the different characters to make sure nothing is repeated
            System.out.println(x);
        }*/

        return charArray;
    }
//************************************************************************************************
    // This function returns the frequencies corresponding to the characters
    // WITHOUT REPETITION
    // SAME LOGIC AS noRepeat_char

    public static int[] noRepeat_freq(char[] ch, int[] cf) {


        ArrayList<Integer> freqAl = new ArrayList<>();

        for (int i = 0; i < cf.length - 1; i++) {
            for (int k = 0; k < cf.length; k++) {

                if (i == k) {
                    k++;
                }

                if (ch[i] == ch[k]) {
                    cf[i] = cf[k];
                    ch[k] = ' ';
                }

            }

        }

        for (int i = 0; i< cf.length; i++) {
            if (ch[i] != ' ') {
                freqAl.add(cf[i]);
            }
        }

        int[] freqArray = new int[freqAl.size()];
        for (int k = 0; k < freqAl.size(); k++) {
            freqArray[k] = freqAl.get(k);
        }


/*        for (char x : charArray) {
            System.out.println(x);
        }*/


/*        for (int y : freqArray) {
            System.out.println(y);
        }*/


        return freqArray;

    }

//************************************************************************************************
    public static void printCode(HuffmanNode root, String s, ArrayList<Character> ch, ArrayList<String> in) {

        // base case, if it is just a leaf node
        // we print it
            if (root.left == null && root.right == null) {

            // c is the character in the node
            System.out.println(root.c + "--" + s);
            return;
        }
        // whenever we go left, add "0" to the Huff code
        // whenever we go right, add "1" to the Huff code
        // recursive calls
        printCode(root.left, s + "0", ch, in);
        printCode(root.right, s + "1", ch, in);
    }

//************************************************************************************************
    // This function does what printCode does,
    // The only difference is printCode, print the Huff code table
    // WHILE THIS ONE just returns an array list with the characters
    public static ArrayList<Character> saveChar(HuffmanNode root, String s, ArrayList<Character> characters) {

        if (root.left == null && root.right == null) {

            characters.add(root.c);
            //System.out.println(root.c);
            //System.out.println(s);
            //System.out.println("SHHHH");
            return characters;
        }
        // recursive calls
        saveChar(root.left, s + "0", characters);
        saveChar(root.right, s + "1", characters);
        return characters;
    }

//*******************************************************************************************

    // This function does what saveChar does
    // BUT THIS ONE returns an array list of bitcodes corresponsing to the characters in the characters array list
    public static ArrayList<String> saveFreq(HuffmanNode root, String s, ArrayList<String> huffCode) {

        if (root.left == null && root.right == null) {

            huffCode.add(s);
            return huffCode;
        }
        // recursive calls
        saveFreq(root.left, s + "0", huffCode);
        saveFreq(root.right, s + "1", huffCode);
        return huffCode;
    }
//*******************************************************************************************
    // This function encodes the string input
    // returns a stringBuilder with the binary code
    public static StringBuilder printEncode(String[]f, char[]ch, String msg) {

        char[] charArray = msg.toCharArray();    // convert the string input into character array
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {   // iterate through the whole character array, which is the string input

            for (int y = 0; y < ch.length; y++) {      // then, iterate through the array of characters, the array of characters we previoulsy have that contains all of the characters WITHOUT REPETITION

                if (charArray[i] == ch[y]) {           // if a character from the string input matched a character from the other character array

                    sb.append(f[y]);                   // we add its binary code to our string builder
                }
            }
        }

        return sb;
        }
//*******************************************************************************************
    // This functions adjusts removing an element from an array given its index

    public static char[] removeElement (char[] c, int index) {
        if (c == null || index < 0 || index >= c.length) {
            return c;
        }

        char[] another = new char[c.length - 1];

        for (int i = 0, k =0; i < c.length; i++) {
            if (i == index) {
                continue;
            }
            another[k++] = c[i];
        }

        return another;
    }
//*******************************************************************************************
    // This function decodes the encoded binary message

    public static StringBuilder printDecode(String[] f, char[]ch, StringBuilder b)
    {
        StringBuilder sb = new StringBuilder();
        StringBuilder returnSb = new StringBuilder();

        char[] array = new char[b.length()];
        b.getChars(0, b.length(), array, 0);   // convert the stringBuilder into an array

/*
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println(array.length);*/

        for (int i=0; i < array.length; i++) {   // iterate through the array of the binary encoded string
            sb.append(array[i]);                 // add an element from the array to our new stringBuilder
            //System.out.println(sb);
            for (int y=0; y < f.length; y++) {    // iterate through the array with the bitcode for the different characters

                if (sb.toString().equals(f[y]) ) {   // if the stringBuilder matches one from the bitcodes
                    //System.out.print(ch[y]);         // we print the character refered by that code
                    returnSb.append(ch[y]);
                    //sb.delete(1, i+1);

                    //System.out.println(sb);

                    //array = removeElement(array, i);

                    for (int z=0; z < i+1; z++) {         // remove the bitcode, which was decoded, found in the binary encoded message
                        array = removeElement(array, 0);
                    }

                    sb.setLength(0);                 //  reset the stringBuilder to null
                    i = -1;
                }
            }
        }
        return returnSb;
    }
//*******************************************************************************************

    public static void main(String[] args) {

        int n;
        char[] charArray;
        char[] charArray2;
        int[] charfreq;
        int[] charfreq2;

        ArrayList<Character> charList = new ArrayList<>();
        ArrayList<String> huffCodeList = new ArrayList<>();

        //huffCodeList = saveFreq(HuffmanNode root, String s);
        //charList = saveChar(HuffmanNode root, String s);

        try {
            // Use Scanner object to read from my files
            Scanner fileInput = new Scanner(new File("Input.txt"));

            try {
                PrintWriter outputFile = new PrintWriter(new FileWriter("output.txt"));

                while (fileInput.hasNext()) // check for next string
            {
                // save the next string into the following variable
                String message = fileInput.nextLine();

                // print the input
                System.out.println("The input is :  "+message+"\n");
                charArray = message.toCharArray();      // convert it into an array of characters

                charfreq = getFrequ(charArray, charArray.length);

                charArray2 = noRepeat_char(charArray);

                //System.out.println("here");


                charfreq2 = noRepeat_freq(charArray, charfreq);
                n = charfreq2.length;

                System.out.println("The characters with their corresponsing frequencies: ");
                System.out.println(Arrays.toString(charArray2));
                System.out.println(Arrays.toString(charfreq2)+"\n");

                System.out.println("Frequency table");
                    for (int t = 0; t < charArray2.length; t++) {
                       System.out.println(charArray2[t]+"     "+charfreq2[t]);
        }
                    System.out.println("\n");


                PriorityQueue<HuffmanNode> q
                        = new PriorityQueue<HuffmanNode>(n, new MyComparator());

                for (int i = 0; i < n; i++) {

                    // create a Huffman node object
                    // then, add it to the priority queue
                    HuffmanNode hfn = new HuffmanNode();

                    hfn.c = charArray2[i];
                    hfn.value = charfreq2[i];

                    hfn.left = null;
                    hfn.right = null;

                    // add the huffman node to the queue
                    q.add(hfn);
                }

                // create a root node
                HuffmanNode root = null;


                // determine the 2 nodes with minimum values
                while (q.size() > 1) {

                    // first min node
                    HuffmanNode x = q.peek();
                    q.poll();

                    // second min node
                    HuffmanNode y = q.peek();
                    q.poll();

                    // new node f
                    HuffmanNode f = new HuffmanNode();


                    // assign values to the f node.
                    f.value = x.value + y.value;
                    f.c = '-';

                    // first extracted node
                    f.left = x;

                    // second extracted node
                    f.right = y;

                    // the node becomes the root
                    root = f;

                    // then, add it to our priority queue
                    q.add(f);
                }



                ArrayList<String> huffCode = new ArrayList<>();
                ArrayList<Character> characters = new ArrayList<>();

                charList = saveChar(root, "", characters);
                huffCodeList = saveFreq(root, "", huffCode);


                //System.out.println(huffCodeList.size());
                System.out.println("Code table");
                printCode(root, "", charList, huffCodeList);

               char[] charListl = new char[charList.size()];
                for (int k = 0; k < charList.size(); k++) {
                    charListl[k] = charList.get(k);
                    //System.out.println("there");
                }


                String[] huffCodeListl = new String[huffCodeList.size()];

                for (int p = 0; p < huffCodeList.size(); p++) {
                    huffCodeListl[p] = huffCodeList.get(p);
                }


                StringBuilder sb = new StringBuilder();
                sb = printEncode(huffCodeListl, charListl, message);

                System.out.println("\nEncoded message");
                System.out.println(sb+"\n");


                StringBuilder gb = new StringBuilder();
                gb = printDecode(huffCodeListl, charListl, sb);

                System.out.println("\nDecoded message");
                System.out.println(gb+"\n");
                //System.out.println(printDecode(huffCodeListl, charListl, sb)+"\n");


                outputFile.println(gb);

            }

                outputFile.close();

            } catch (IOException exc) {
                System.out.println("There was a problem opening the file for output");
            }


            fileInput.close();


        }catch (FileNotFoundException exc) {
            System.out.println("IDIOT, you screwed up (•̀o•́)ง");
            System.out.println("In other words, there was a problem opening the input file");
        }
    }

}

