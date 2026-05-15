import java.time.LocalDate;
import java.util.Scanner;

// 学生类：包含学号、姓名、性别、出生日期、是否党员、语文、数学成绩
class Student {
    String ID, name;
    char sex;
    boolean partyMember;
    double math, chinese;
    LocalDate birthday;  // 用日期类型代替原来的age

    // 无变化：输出数据输入提示
    static void titleHint() {
        System.out.println("请依次输入：学号 姓名 性别 出生日期 是否党员 数学 语文");
    }

    // 作为打印输出时的标题行
    static void showTitle() {
        System.out.printf("%-5s %-8s %-4s %-12s %-6s %-6s %-6s\n",
                "学号", "姓名", "性别", "出生日期", "党员", "数学", "语文");
        System.out.println("-------------------------------------------------------");
    }

    // 从Scanner对象读取数据，读取次序与titleHint()相同
    void read(Scanner sc) {
        // 读取学号、姓名、性别
        ID = sc.next();
        name = sc.next();
        sex = sc.next().charAt(0);

        // 读取出生日期字符串，并转换为LocalDate类型
        String bd = sc.next();
        birthday = LocalDate.parse(bd);

        // 读取是否党员、数学、语文成绩
        partyMember = sc.nextBoolean();
        math = sc.nextDouble();
        chinese = sc.nextDouble();
    }

    // 格式化输出学生信息
    public String toString() {
        String id, xm, xb, dy, sx, yw;

        // 学号左对齐，占5个字符宽度
        id = String.format("%-5s", ID);

        // 姓名占8个字符，左对齐，不足部分用空格补充（1个汉字占2个字符宽度）
        xm = String.format("%-8s", name);

        // 性别转换：F/f→女，M/m→男
        xb = (sex == 'F' || sex == 'f') ? "女" :
             (sex == 'M' || sex == 'm') ? "男" : "未知";

        // 党员状态转换：true→共产党员，false→非党员
        dy = (partyMember) ? "共产党员" : "非党员";

        // 数学成绩：占6个字符，保留2位小数，左对齐
        sx = String.format("%-6.2f", math);

        // 语文成绩：占6个字符，保留2位小数，左对齐
        yw = String.format("%-6.2f", chinese);

        // 拼接成完整的格式化字符串
        return id + " " + xm + " " + xb + " " + birthday + " " + dy + " " + sx + " " + yw;
    }
}

// 主程序测试类
public class StudentTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Student student = new Student();
        Student.titleHint();  // 输出输入提示
        student.read(sc);    // 读取用户输入的学生信息

        Student.showTitle();  // 输出标题行
        System.out.println(student); // 调用toString()方法格式化输出

        sc.close();
    }
}