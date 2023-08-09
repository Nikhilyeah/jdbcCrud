package dao;

import entity.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentDao extends DBConfig{
    public boolean saveStudent(Student student){
        boolean saved = false;
//        Database Create Operation
        Connection conn = null;
        try{
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL,USER,PASS);
            String sql = "insert into student_table (name,major,gender,batch)" +
                    " values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

//            Mapping Values form object to SQL query
            ps.setString(1,student.getName());
            ps.setString(2,student.getMajor());
            ps.setString(3,student.getGender());
            ps.setInt(4,student.getBatch());

            if(ps.executeUpdate()==1){
                saved  = true;
            }

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return saved;
    }

    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> sl = new ArrayList<>();
        Connection conn = null;
        try{
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL,USER,PASS);
            String sql = "select * from student_table";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setSid(rs.getInt("student_id"));
                s.setName(rs.getString("name"));
                s.setMajor(rs.getString("major"));
                s.setGender(rs.getString("gender"));
                s.setBatch(rs.getInt("batch"));
                sl.add(s);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sl;
    }

    public Student getStudentById(int id){
        Student s = null;
        Connection conn = null;
        try{
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL,USER,PASS);
            String sql = "select * from student_table where student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                s = new Student();
                s.setSid(rs.getInt("student_id"));
                s.setName(rs.getString("name"));
                s.setMajor(rs.getString("major"));
                s.setGender(rs.getString("gender"));
                s.setBatch(rs.getInt("batch"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return s;
    }

    public boolean updateStudent(Student s){
        boolean updated = false;
        Connection conn = null;
        try{
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL,USER,PASS);
            String sql = "update student table set name = ?, major=?,gender=?,batch=? where student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getName());
            ps.setString(2,s.getMajor());
            ps.setString(3,s.getGender());
            ps.setInt(4,s.getBatch());
            ps.setInt(5,s.getSid());

            if(ps.executeUpdate()==1){
                updated = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return updated;
    }

    public boolean deleteStudent(int id){
        Connection conn = null;
        boolean deleted = false;
        try{
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL,USER,PASS);
            String sql = "delete from student_table where student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            if(ps.executeUpdate()==1){
                deleted = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return deleted;
    }

}
