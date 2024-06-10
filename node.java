import java.util.Comparator;
public class node
{
    private final int frequency;
    private Character character = null;
    private node left = null;
    private node right = null;
    private final int sequence;


    public node(char character, int frequency, int sequence) //leaf nodes
    {
        this.character = character;
        this.frequency = frequency;
        this.sequence = sequence;
    }

    public node(int frequency, node right, node left, int sequence) //constructor for first root nodes linking two characters
    {
        this.frequency = frequency;
        this.right = right;
        this.left = left;
        this.sequence = sequence;
    }

    public node getLeft()
    {
        return left;
    }

    public node getRight()
    {
        return right;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public Character getCharacter()
    {
        return character;
    }

    public int getSequence()
    {
        return sequence;
    }

}

// Custom comparator for the Node class to be able to sort the priority queue. The priority queue applies this when adding a node
class NodeComparator implements Comparator<node> {
    @Override
    public int compare(node n1, node n2) {
        // Compare by frequency
        if (n1.getFrequency() != n2.getFrequency()) {
            return Integer.compare(n1.getFrequency(), n2.getFrequency()); //if n1 is larger than n2 result is positive (keep going down the queue)
        }

        // If frequencies are equal and one node has a character and the other does not, the node without the character should be closer to the head
        if (n1.getCharacter() == null && n2.getCharacter() != null) {
            return -1; //n1 is considered smaller and goes closer to head
        }
        if (n1.getCharacter() != null && n2.getCharacter() == null) {
            return 1; //n1 is considered larger and goes further from the head
        }

        // If frequencies are equal, preserve the order they were added
        return Integer.compare(n1.getSequence(), n2.getSequence());
    }
}