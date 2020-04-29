import java.util.LinkedList;
import java.util.List;

public class LinkList {

/*
Time Complexity: O(N^2)
Space Complexity: O(2)
*/
    public static void removingDup (LinkedList<String> list){
        int firstIndex, lastIndex, i = 0;
        int size = list.size();

        while (i < size) {
            firstIndex = list.indexOf(list.get(i));
            lastIndex = list.lastIndexOf(list.get(i));
            while (firstIndex != lastIndex) {
                list.removeLastOccurrence(list.get(i));
                lastIndex = list.lastIndexOf(list.get(i));
                size--;
            }
            i++;
        }
    }

    public static void main(String args[])
    {
        LinkedList<String> object = new LinkedList<String>();
        // Example 1 of list messages: [D, A, E, B, D, A, F, B, B, E, D, C, A, J, K, C, C, C, C, C] = 20 elements but only 8 unique elements, removing others

        object.add("E");
        object.add("B");
        object.addFirst("A");  // simulating
        object.add("D");
        object.add("A");
        object.add("F");
        object.add("B");
        object.add("B");
        object.add("E");
        object.add("D");
        object.add("C");
        object.addLast("D");  // simulating
        object.add("A");
        object.add("J");
        object.add("K");
        object.add("C");
        object.add("C");
        object.add("C");
        object.add("C");
        object.add("C");

        System.out.println("Linked list : " + object);
        removingDup(object);
        System.out.println("Linked list : " + object);
    }
}
