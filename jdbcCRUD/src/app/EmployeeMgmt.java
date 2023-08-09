package app;

import dao.EmployeeDao;
import entity.Employee;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeMgmt {
    Scanner sc = new Scanner(System.in);
    int choice;
    EmployeeDao dao = new EmployeeDao();

    void menu(){

        System.out.println("Welcome to EmployeeMgmt System");
        System.out.println("1) Add Employee data");
        System.out.println("2) Show Employee details");
        System.out.println("3) Show Student by ID");
        System.out.println("4) Update Record");
        System.out.println("5) Delete Record");
        System.out.println("6) Exit");

        action(sc.nextInt());
    }

    void action(int choice){
        switch (choice){
            case 1 ->{
                Employee employee = new Employee();
                sc.nextLine();
                System.out.println("Name :");
                employee.setName(sc.nextLine());
                System.out.println("Address :");
                employee.setAddress(sc.next());
                System.out.println("Salary");
                employee.setSalary(sc.nextInt());
                if(dao.addEmployee(employee)){
                    System.out.println("Employee Added Successfully");
                } else {
                    System.out.println("Failed to add Employee");
                }
            }

            case 2 ->{
                ArrayList<Employee> el = dao.getAllEmployee();
                for (Employee emp : el){
                    System.out.println(emp);
                }
            }
            case 3 ->{
                Employee employee = new Employee();
                System.out.println("Enter Id: ");
                employee = dao.getEmployeeById(sc.nextInt());
                System.out.println(employee);
            }
            case 4-> {
                System.out.println("Enter id of the record you would like to update:");
                int id = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                Employee employee = dao.getEmployeeById(id);
                if (employee != null) {
                    System.out.println("Employee Details:");
                    System.out.println(employee);
                    System.out.println("Would you like to update this record? (y/n)");
                    if (sc.next().charAt(0) == 'y') {
                        System.out.println("presses");
                        sc.nextLine(); // Consume the newline character
                        System.out.println("Update Name? (y/n)");
                        if (sc.nextLine().charAt(0) == 'y') {
                            System.out.println("New Name:");
                            employee.setName(sc.nextLine());
//                            sc.nextLine();
                        }
//                        sc.nextLine(); // Consume the newline character
                        System.out.println("Update Address? (y/n)");
                        if (sc.next().charAt(0) == 'y') {
                            System.out.println("New Address:");
                            sc.nextLine(); // Consume the newline character
                            employee.setAddress(sc.nextLine());

                        }

                        System.out.println("Update Salary? (y/n)");
                        if (sc.next().charAt(0) == 'y') {
                            sc.nextLine(); // Consume the newline character
                            System.out.println("New Salary:");
                            employee.setSalary(sc.nextInt());

                        }
                        if (dao.updateEmployee(employee)) {
                            System.out.println("Update Successful");
                            System.out.println("Updated Details:");
                            System.out.println(employee);
                        } else {
                            System.out.println("Failed to Update");
                        }
                    }
                } else {
                    System.out.println("Employee not found");
                }

            }
            case 5 -> {
                System.out.println("Enter the record id you would like to delete :");
                int id = sc.nextInt();
                Employee employee = dao.getEmployeeById(id);
                if(employee!=null){
                    System.out.println("Employee Details");
                    System.out.println(employee);
                    System.out.println("Confirm Deletion (y/n) :");
                    if(sc.next().charAt(0)=='y'){
                        if(dao.deleteEmployee(id)){
                            System.out.println("Record Deleted Successfully");
                        } else {
                            System.out.println("Deletion failed");
                        }
                    }
                } else {
                    System.out.println("Employee not found");
                }
            }



            case 6 ->{
                System.out.println("Please Visit Again");
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid Input !!!");
            }
        }
        System.out.println("Would you like to see the menu again ? (y/n)");
        if(sc.next().charAt(0)=='y'){
            menu();
        } else {
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        EmployeeMgmt e1 = new EmployeeMgmt();
        e1.menu();


    }
}
