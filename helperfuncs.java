import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashMap;

public class helperfuncs {
    public static HashMap<Character, String> encodeMap(node root)
    {
        LinkedHashMap<Character, String> charCodes = new LinkedHashMap<Character, String>();
        ArrayList<String> values = getPaths(root);
        for (String s : values) {
            Character key = s.charAt(s.length() - 1);
            String value = s.substring(0, s.length() - 1);

            charCodes.put(key, value); //Update the hashmap so that each key now corresponds to its encoded form
        }

        return charCodes;
    }


    private static ArrayList<String> getPaths(node root)
    {
        ArrayList<String> paths = new ArrayList<String>(); //Arraylist containing all paths
        String s = ""; //Blank string that will hold individual paths
        traversePaths(root, s, paths, "");
        return paths;
    }

    private static void traversePaths(node root, String s, ArrayList<String> paths, String side)
    {

        if (root.getLeft() == null && root.getRight() == null) //If leaf, end recursion and append final side and character to answer
        {

            s = s + side;
            s = s + root.getCharacter();
            paths.add(s);
        }
        else //If node, append the correct number marking if we went left or right and continue recursion below
        {
            s = s + side;

        }

        if(root.getLeft() != null) //We are iterating into left node so add 0 to indicate going left
        {
            traversePaths(root.getLeft(), s, paths, "0");
        }

        if(root.getRight() != null) //We are iterating into right node so add 1 to indicate going right
        {
            traversePaths(root.getRight(), s, paths, "1");
        }
    }
}