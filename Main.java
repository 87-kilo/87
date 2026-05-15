import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

// 学生类：实现Comparable接口，支持按学号排序
class Student implements Comparable<Student> {
    String ID, name;
    char sex;
    boolean partyMember;
    double math, chinese;
    LocalDate birthday;

    // 输入提示
    static void titleHint() {
        System.out.println("请依次输入：学号 姓名 性别 出生日期 是否党员 数学 语文");
    }

    // 输出标题行
    static void showTitle() {
        System.out.printf("%-5s %-8s %-4s %-12s %-6s %-6s %-6s\n",
                "学号", "姓名", "性别", "出生日期", "党员", "数学", "语文");
        System.out.println("-------------------------------------------------------");
    }

    // 从Scanner读取数据
    void read(Scanner sc) {
        ID = sc.next();
        name = sc.next();
        sex = sc.next().charAt(0);
        String bd = sc.next();
        birthday = LocalDate.parse(bd);
        partyMember = sc.nextBoolean();
        math = sc.nextDouble();
        chinese = sc.nextDouble();
    }

    // 格式化输出学生信息
    @Override
    public String toString() {
        String id = String.format("%-5s", ID);
        String xm = String.format("%-8s", name);
        String xb = (sex == 'F' || sex == 'f') ? "女" :
                    (sex == 'M' || sex == 'm') ? "男" : "未知";
        String dy = partyMember ? "共产党员" : "非党员";
        String sx = String.format("%-6.2f", math);
        String yw = String.format("%-6.2f", chinese);
        return id + " " + xm + " " + xb + " " + birthday + " " + dy + " " + sx + " " + yw;
    }

    // 实现Comparable接口：按学号升序排序
    @Override
    public int compareTo(Student s) {
        if (this.ID.compareTo(s.ID) < 0) {
            return -1;
        } else if (this.ID.equals(s.ID)) {
            return 0;
        } else {
            return 1;
        }
    }
}

// 班级类：管理多个学生，支持文件读取和排序
class BanJi {
    private Student[] st;    // 学生数组
    private int renShu = 0;  // 实际学生人数

    // 构造方法：指定班级最大人数
    public BanJi(int maxSize) {
        st = new Student[maxSize];
    }

    // 添加学生到数组
    public void add(Student s) {
        st[renShu++] = s;
    }

    // 从文件批量读取学生数据
    public void readFromFile(File f) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        // 跳过文件前两行标题行
        if (sc.hasNextLine()) sc.nextLine();
        if (sc.hasNextLine()) sc.nextLine();

        // 循环读取每一行学生数据
        while (sc.hasNext()) {
            Student s = new Student();
            s.read(sc);
            add(s);
        }
        sc.close();
    }

    // 按学号排序
    public void sort() {
        Arrays.sort(st, 0, renShu);
    }

    // 显示所有学生信息
    public void show() {
        Student.showTitle();
        for (int i = 0; i < renShu; i++) {
            System.out.println(st[i]);
        }
    }
}

// 主程序类
public class App {
    public static void main(String[] args) throws Exception {
        // 1. 创建班级对象，最多容纳50名学生
        BanJi bj = new BanJi(50);
        File f;

        // 2. 从文件读取数据
        System.out.println("从文件读取数据……");
        f = new File("StudentInfo.txt");
        bj.readFromFile(f);

        // 3. 显示排序前的班级信息
        System.out.println("你输入的学生信息如下：");
        bj.show();

        // 4. 按学号排序并显示结果
        System.out.print("\n按学号排序后：\n");
        bj.sort();
        bj.show();
    }
}