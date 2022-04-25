import java.io.File;

public class PrintDir {
    public static void main(String[] args) {
        File[] root_files = File.listRoots();
        File[] dir_files;
        for (File root_file : root_files) {
            System.out.println(root_file.getPath());
            dir_files = root_file.listFiles();
            if (dir_files == null) {
                System.out.println("\t*EMPTY*");
            } else {
                for (File dir_file : dir_files) {
                    if (dir_file.isDirectory()) {
                        System.out.print("\t[");
                        System.out.println(dir_file.getName() + "]");
                    } else {
                        System.out.println("\t" + dir_file.getName());
                    }

                }
            }
        }

        //File의 생성자 매개변수에는 경로를 넣을 것
        //File root = new File("C:\\Users\\kimis\\Programming");
        //print_dir(root, 0);
    }

    public static void print_dir(File root, int depth) {

        if (root == null) {
            System.out.println("\t*EMPTY*");
            return;
        }

        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }
        System.out.println(root.getName());

        if (root.isDirectory()) {
            File[] fileList = root.listFiles();
            if (fileList != null) {
                for (File subRoot : fileList) {
                    print_dir(subRoot, depth + 1);
                }
            }
        }
        return;
    }
}
