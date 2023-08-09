package dao;

import entity.Employee;


import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao extends DBConfig{
    public Employee getEmployeeById(int id){
        Employee e = null;
        Connection conn = null;

        try{
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL,USER,PASS);
            String sql = "select * from Employee_DB where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                e = new Employee();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setAddress(rs.getString("address"));
                e.setSalary(rs.getInt("salary"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return e;
    }

    public ArrayList<Employee> getAllEmployee(){
        ArrayList<Employee> el = new ArrayList<>();
        Connection conn = null;

        try{
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL,USER,PASS);
            String sql = "select * from Employee_DB";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setAddress(rs.getString("address"));
                emp.setSalary(rs.getInt("salary"));
                el.add(emp);

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return el;
    }

    public boolean addEmployee(Employee emp){
        boolean added = false;
        Connection conn = null;
        try{
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL,USER,PASS);
            String sql = "insert into Employee_DB (name,address,salary) " +
                    "values(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,emp.getName());
            ps.setString(2,emp.getAddress());
            ps.setInt(3,emp.getSalary());

            if(ps.executeUpdate()==1){
                added = true;
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return added;
    }

    public boolean updateEmployee(Employee emp) {
        boolean updated = false;
        Connection conn = null;

        try {
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "UPDATE Employee_DB SET name=?, address=?, salary=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getAddress());
            ps.setInt(3, emp.getSalary());
            ps.setInt(4, emp.getId());

            if (ps.executeUpdate() == 1) {
                updated = true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated;
    }


    public boolean deleteEmployee(int id){
        boolean deleted = false;
        Connection conn = null;

        try{
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL,USER,PASS);
            String sql = "delete from Employee_DB where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            if(ps.executeUpdate()==1){
                deleted = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return deleted;
    }
}
