import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    private static final String FILE = "students.txt";
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();
    private static final Set<Integer> ids = new HashSet<>();
    private static final Map<Integer, Student> byId = new HashMap<>();

    static class Student {
        int id; String name, course; double mark;
        Student(int id, String name, String course, double mark) {
            this.id=id; this.name=name; this.course=course; this.mark=mark;
        }
        public String toString() {
            return "ID: "+id+" | Name: "+name+" | Course: "+course+" | Mark: "+mark;
        }
        String toLine() { return id+"|"+name.replace("|","/")+"|"+course.replace("|","/")+"|"+mark; }
    }

    // Overloaded methods
    static Student createStudent(int id, String name, String course, double mark) {
        return new Student(id,name,course,mark);
    }
    static Student createStudent(int id, String name, String course) {
        return createStudent(id,name,course,0);
    }

    static String readText(String prompt) {
        System.out.print(prompt);
        String s=sc.nextLine().trim();
        if(s.isEmpty()) throw new IllegalArgumentException("Value cannot be empty.");
        return s;
    }
    static int readInt(String prompt) {
        System.out.print(prompt);
        int n=Integer.parseInt(sc.nextLine().trim());
        return n;
    }
    static double readDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(sc.nextLine().trim());
    }
    static void validateMark(double mark) {
        if(mark<0 || mark>100) throw new IllegalArgumentException("Mark must be between 0 and 100.");
    }

    static void addStudent() {
        try {
            int id=readInt("Student ID: ");
            if(ids.contains(id)) throw new IllegalArgumentException("Duplicate student ID.");
            String name=readText("Name: ");
            String course=readText("Course: ");
            double mark=readDouble("Mark (0-100): ");
            validateMark(mark);
            Student s=createStudent(id,name,course,mark);
            students.add(s); ids.add(id); byId.put(id,s);
            save(); System.out.println("Student added.");
        } catch(IllegalArgumentException | IOException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    static void viewStudents() {
        if(students.isEmpty()) { System.out.println("No records."); return; }
        Iterator<Student> it=students.iterator();
        while(it.hasNext()) System.out.println(it.next());
    }
    static void searchStudent() {
        try {
            int id=readInt("Student ID to search: ");
            Student s=byId.get(id);
            if(s==null) throw new NoSuchElementException("Student not found.");
            System.out.println(s);
        } catch(IllegalArgumentException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
    static void updateStudent() {
        try {
            int id=readInt("Student ID to update: ");
            Student s=byId.get(id);
            if(s==null) throw new NoSuchElementException("Student not found.");
            s.name=readText("New name: ");
            s.course=readText("New course: ");
            s.mark=readDouble("New mark: ");
            validateMark(s.mark); save();
            System.out.println("Student updated.");
        } catch(IllegalArgumentException | NoSuchElementException e) {
            System.out.println("Error: "+e.getMessage());
        } catch(IOException e) { System.out.println("File error: "+e.getMessage()); }
    }
    static void deleteStudent() {
        try {
            int id=readInt("Student ID to delete: ");
            Student s=byId.get(id);
            if(s==null) throw new NoSuchElementException("Student not found.");
            students.remove(s); ids.remove(id); byId.remove(id); save();
            System.out.println("Student deleted.");
        } catch(IllegalArgumentException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch(IOException e) { System.out.println("File error: "+e.getMessage()); }
    }

    static void load() {
        File f=new File(FILE);
        if(!f.exists()) return;
        try(BufferedReader br=new BufferedReader(new FileReader(f))) {
            String line;
            while((line=br.readLine())!=null) {
                String[] p=line.split("\\|",-1);
                if(p.length!=4) continue;
                try {
                    int id=Integer.parseInt(p[0]); double mark=Double.parseDouble(p[3]);
                    if(!ids.contains(id)) {
                        Student s=createStudent(id,p[1],p[2],mark);
                        students.add(s); ids.add(id); byId.put(id,s);
                    }
                } catch(NumberFormatException ex) {
                    System.out.println("Skipping invalid saved record.");
                }
            }
        } catch(IOException e) { System.out.println("Could not load file: "+e.getMessage()); }
    }
    // throws declares a checked exception to the caller.
    static void save() throws IOException {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(FILE))) {
            for(Student s:students) { bw.write(s.toLine()); bw.newLine(); }
        }
    }
    public static void main(String[] args) {
        load();
        while(true) {
            System.out.println("\\n=== STUDENT MANAGEMENT SYSTEM ===");
            System.out.println("1 Add  2 View  3 Search  4 Update  5 Delete  6 Exit");
            try {
                int choice=readInt("Choose: ");
                switch(choice) {
                    case 1: addStudent(); break;
                    case 2: viewStudents(); break;
                    case 3: searchStudent(); break;
                    case 4: updateStudent(); break;
                    case 5: deleteStudent(); break;
                    case 6: System.out.println("Goodbye!"); sc.close(); return;
                    default: System.out.println("Choose 1 to 6.");
                }
            } catch(NumberFormatException e) { System.out.println("Please enter a valid number."); }
        }
    }
}
