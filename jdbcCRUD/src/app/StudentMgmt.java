package app;

import dao.StudentDao;
import entity.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentMgmt {

    public void menu(){

        System.out.println("Welcome to Student Management System");
        System.out.println("1) Add New Student");
        System.out.println("2) Display all Students");
        System.out.println("3) Display Selected Students");
        System.out.println("4) Update Student Records");
        System.out.println("5) Delete Student Records");
        System.out.println("6) Exit");
        System.out.println("----- Enter your choice -----");
        System.out.print(" >");

    }

    public static void main(String[] args) {
        StudentMgmt sm = new StudentMgmt();
        StudentDao dao = new StudentDao();
        Scanner sc = new Scanner(System.in);

        do {
            sm.menu();

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
//                Collect Student Details
                    Student st = new Student();
                    sc.nextLine();
                    System.out.println("Enter student name : ");
                    st.setName(sc.nextLine());
                    System.out.println("Enter Major : ");
                    st.setMajor(sc.nextLine());
                    System.out.println("Enter Gender : ");
                    st.setGender(sc.nextLine());
                    System.out.println("Enter Batch : ");
                    st.setBatch(sc.nextInt());
                    if (dao.saveStudent(st)) {
                        System.out.println("Student Record has been Added Successfully");
                    } else {
                        System.out.println("Failed to Add Student Record");
                    }
                }

                case 2 -> {
                    // Display student records
                    ArrayList<Student> studentList = dao.getAllStudents();
                    for (Student s : studentList) {
                        System.out.println(s);
                    }
                }
                case 3 -> {
//                Display individual student records
                    System.out.println("Enter an id of Student: ");
                    int id = sc.nextInt();
                    Student student = dao.getStudentById(id);
                    if (student != null) {
                        System.out.println("Student Details :");
                        System.out.println(student);
                    } else {
                        System.out.println("Record was not found !!");
                    }
                }
//

                case 4 -> {
//                Display individual record
                    System.out.println("Enter the student id you would like to update: ");
                    int id = sc.nextInt();
                    Student student = dao.getStudentById(id);
                    if (student != null) {
                        System.out.println("Student details ");
                        System.out.println(student);
//                    Ask the user if they wish to update the record
                        System.out.println("Would you like to update the record?");
                        System.out.println("Yes / No");
                        if (sc.next().charAt(0) == 'y') {
                            sc.nextLine();
                            System.out.println("Update name ?(y/n)");
                            if (sc.next().charAt(0) == 'y') {
                                student.setName(sc.nextLine());
                            }
                            System.out.println("Update major ?(y/n)");
                            if (sc.next().charAt(0) == 'y') {
                                student.setName(sc.nextLine());
                            }
                            System.out.println("Update gender ?(y/n)");
                            if (sc.next().charAt(0) == 'y') {
                                student.setGender(sc.nextLine());
                            }
                            System.out.println("Update batch ?(y/n)");
                            if (sc.next().charAt(0) == 'y') {
                                student.setBatch(sc.nextInt());
                            }
                            if (dao.updateStudent(student)) {
                                System.out.println("Record has been updated Successfullu");
                            } else {
                                System.out.println("Record updating was failed !");
                            }
                        }
                    } else {
                        System.out.println("Record was not found !!!");

                    }
                }

                case 5 -> {
                    int id;
                    System.out.println("Enter student id to delete record");
                    id = sc.nextInt();
                    Student s = dao.getStudentById(id);
                    if (s != null) {
                        System.out.println("Student details :");
                        System.out.println(s);
                        System.out.println("Are you sure you want to delete the id(y/n)");
                        if (sc.next().charAt(0) == 'y') {
                            if (dao.deleteStudent(id)) {
                                System.out.println("Record deleted successfully");
                            } else {
                                System.out.println("Record deletion failed");
                            }
                        }
                    }
                }
                case 6 -> {
                    System.out.println("Thank you for using this system");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid input please try again");
                }
            }

            System.out.println("Would you like to continue (y/n)");
            sc.nextLine();
        } while (sc.next().charAt(0) == 'y');
    }
}
