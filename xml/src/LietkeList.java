import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }
}

public class LietkeList {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Quang Huy", 18, 9.8));
        students.add(new Student("Quốc Bo", 18, 7));
        students.add(new Student("Nguyên Khánh", 18, 8));
        students.add(new Student("Thành Nam", 18, 4));
        
        try {
            FileOutputStream out= new FileOutputStream("students.xml");
            XMLOutputFactory outfac = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = outfac.createXMLStreamWriter(out);

            writer.writeStartDocument();
            writer.writeStartElement("students");

            for (Student student : students) {
            	writer.writeCharacters("\n");
                writer.writeStartElement("student");
                writer.writeStartElement("name");
                writer.writeCharacters(student.getName());
                writer.writeEndElement();
                writer.writeStartElement("age");
                writer.writeCharacters(String.valueOf(student.getAge()));
                writer.writeEndElement();
                writer.writeStartElement("gpa");
                writer.writeCharacters(String.valueOf(student.getGpa()));
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeCharacters("\n");
            }

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();

            System.out.println("Tạo file xml thành công.");
        } catch (XMLStreamException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
