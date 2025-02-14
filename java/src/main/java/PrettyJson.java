package main.java;

import java.util.LinkedHashMap;
import java.util.Map;

public class PrettyJson {
    public static void main(String[] args) {
        String jsonString = "{\"key1\":\"val1\",\"key2\":[\"item1\",{\"item2key\":1.2}]}";
        String jsonString2 = "{\"person\":{\"name\":\"Alice\",\"age\":25,\"address\":{\"street\":\"123 Main St\",\"city\":\"Los Angeles\",\"state\":\"CA\"}}}";
        String jsonString3 = "{\"company\":\"TechCorp\",\"departments\":[{\"name\":\"Engineering\",\"employees\":[{\"name\":\"Eve\",\"role\":\"Software Engineer\"},{\"name\":\"Frank\",\"role\":\"DevOps Engineer\"}]},{\"name\":\"Marketing\",\"employees\":[{\"name\":\"Grace\",\"role\":\"Content Strategist\"}]}]}";
        String jsonString4 = "{\"product\":\"Laptop\",\"price\":1500,\"features\":[\"16GB RAM\",\"512GB SSD\",\"Touchscreen\"]}";

        //System.out.println(prettyJson(jsonString));
        //System.out.println(prettyJson(jsonString2));
        //System.out.println(prettyJson(jsonString3));
        //System.out.println(prettyJson(jsonString4));

        String paths = "/folder1/subfolder1/file1.txt;/folder1/subfolder2/file2.txt;/folder2/subfolder1/file3.txt";

        TreeNode node = getPaths(paths);

        printTree(node,"");


    }

    public static class TreeNode {
        public String name;

        public Map<String, TreeNode> children = new LinkedHashMap<>();

        public TreeNode(String name){
            this.name = name;
        }
    }

    public static TreeNode getPaths(String paths){
        String[] nodes = paths.split(";");

        TreeNode node = new TreeNode("");
        for(String folder: nodes){
           addPath(node,folder.split("/"));
        }

        return node;
    }

    private static void addPath(TreeNode node, String[] paths) {

        if(paths.length == 0)
            return;




        TreeNode parent = node;

        for(int i=0;i<paths.length;i++){
            if(paths[i].length() == 0)
                continue;
            String currentElement = paths[i];
            TreeNode child = parent.children.get(currentElement);

            if(child == null){
                child = new TreeNode(currentElement);
                parent.children.put(currentElement,child);
            }
            parent = child;
        }

    }

    private static void printTree(TreeNode node, String indent) {
        System.out.println(indent + node.name);
        for (TreeNode child : node.children.values()) {
            printTree(child, indent + "    ");
        }
    }

    public static String prettyJson(String json) {

        int indent = 0;
        StringBuilder result = new StringBuilder("");
        boolean isQuote = false;

        for (int i = 0; i < json.length(); i++) {

            char ch = json.charAt(i);

            switch (ch) {

                case '"':
                    if(i > 0 && json.charAt(i-1) != '\\'){
                        isQuote = !isQuote;
                    }
                    result.append(ch);

                    break;
                case '{':
                case '[':
                    result.append(ch);
                    if (!isQuote) {
                        result.append("\n");
                        indent++;
                        addIndentation(result, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isQuote) {
                        result.append("\n");
                        indent--;
                        addIndentation(result, indent);
                    }
                    result.append(ch);
                    break;
                case ':':
                    result.append(ch);
                    if (!isQuote) {
                        result.append(" ");
                    }
                    break;

                case ',':
                    result.append(ch);
                    if (!isQuote) {
                        result.append("\n");
                        addIndentation(result, indent);
                    }
                    break;
                default:
                    result.append(ch);
                    break;

            }
        }

        return result.toString();
    }


    private static void addIndentation(StringBuilder result, int indent) {

        for(int i = 0;i<indent;i++){
            result.append("\t");
        }
    }


}

