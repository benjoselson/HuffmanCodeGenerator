import java.util.*;
public class main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int seqCount = 0; //gives each node a sequence number corresponding to the order it was created. So if two nodes are otherwise identical, the one created first should be closer to the head



        char[] charArray = input.toCharArray(); //turn input into array

        LinkedHashMap<Character, Integer> hashmap = new LinkedHashMap<Character, Integer>(); //Use linked hashmap to preserve insertion order, so I can find the next lowest occurring letter

        PriorityQueue<node> queue = new PriorityQueue<node>(new NodeComparator());


        for (char c : charArray)
        {
            hashmap.merge(c, 1, Integer::sum); //add each element in chararray to hashmap and add one to value. if there is already a number there, to avoid overwriting the current number, take the sum of one and the number already there
        }

        for (Map.Entry<Character, Integer> entry : hashmap.entrySet()) //convert all character/frequency pairs into nodes
        {
            node n = new node(entry.getKey(), entry.getValue(), seqCount);
            seqCount++;
            queue.add(n);
        }


        //build huffman tree
        while(queue.size() > 1)
        {
            node right = queue.poll(); //queue.poll removes the head of the queue
            node left = queue.poll();
            node master = new node(right.getFrequency() + left.getFrequency(), right, left, seqCount);
            seqCount++;
            queue.add(master);
        }



        HashMap<Character, String> charCodes = helperfuncs.encodeMap(queue.poll());

        String s = "";
        for(Character c : charArray)
        {
            s = s + charCodes.get(c);
            System.out.println(charCodes.get(c) + " " + c);
        }
        System.out.println(s);
    }
}