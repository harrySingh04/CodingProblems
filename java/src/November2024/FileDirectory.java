package November2024;

import java.util.*;

public class FileDirectory {


    public class Directory{

        String name;
        TreeMap<String,Directory> subDirectories;
        boolean isFile;

        public Directory(String name,boolean isFile){
            this.name = name;
            this.subDirectories  = new TreeMap<>();
            this.isFile = isFile;
        }

        public void addPath(String[] paths,int index){

            if(index == paths.length)
                return;

            String part = paths[index];

            boolean isFile = (index == paths.length-1);
            subDirectories.putIfAbsent(part, new Directory(part, isFile));

            if(!isFile)
                subDirectories.get(part).addPath(paths,index+1);

        }

        public void printStructure(StringBuilder sb, int level) {
            // Indent based on level
            if (level > 0) {
                for (int i = 1; i < level; i++) {
                    sb.append("    ");
                }
                sb.append(name).append("\n");
            }

                if (subDirectories != null) {
                    for (Directory child : subDirectories.values()) {
                        child.printStructure(sb, level + 1);
                    }
                }
            }

    }

    public String buildDirectoryStructure(List<String> filePaths) {
        Directory main = new Directory("", false);

        // Build the directory tree
        for (String path : filePaths) {
            String[] parts = path.split("/");
            main.addPath(parts, 0);
        }

        // Prepare formatted output
        StringBuilder sb = new StringBuilder();
        main.printStructure(sb, 0);
        return sb.toString();
    }

    // Test the implementation
    public static void main(String[] args) {
        List<String> filePaths = Arrays.asList(
                "root/folder1/file1.txt",
                "root/folder2/file2.txt",
                "root/folder1/subfolder1/file3.txt",
                "root/folder1/file4.txt",
                "othermain/folder5/file7.txt",
                "othermain/folder3/file9.txt",
                "othermain/folder5/file1.txt",
                "root/folder1/file1.txt"


        );

        FileDirectory f1 = new FileDirectory();

        System.out.println(f1.buildDirectoryStructure(filePaths));
    }


}
