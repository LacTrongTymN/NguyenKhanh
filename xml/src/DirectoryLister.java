import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DirectoryLister {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập đường dẫn thư mục: ");
        String directoryPath = scanner.nextLine();
        scanner.close();

        try {
            FileWriter writer = new FileWriter("directory_tree.xml");
           
            listDirectory(new File(directoryPath), writer, 1);
           
            writer.close();
            System.out.println("Đã liệt kê cây thư mục dưới dạng file XML.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    private static void listDirectory(File directory, FileWriter writer, int depth) throws IOException {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("\t");
        }

        writer.write(indent + "<" + directory.getName() + ">\n");

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listDirectory(file, writer, depth + 1);
                } else {
                    writer.write(indent + "\t<file>" + file.getName() + "</file>\n");
                }
            }
        }

        writer.write(indent + "</" + directory.getName() + ">\n");
    }
}