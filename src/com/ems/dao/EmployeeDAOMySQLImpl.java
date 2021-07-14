package com.ems.dao;

import com.ems.dto.Employee;
import com.ems.exceptions.EmployeeNotFoundException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDAOMySQLImpl implements EmployeeDAO {

    private Connection conn = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    //perform database operations
    public static final String INSERT_COMMAND = "INSERT INTO employees_tbl VALUES (?,?,?,?)";
    public static final String UPDATE_COMMAND = "UPDATE employees_tbl SET name = ?, dept=?, daysAttended=? WHERE id = ?";
    public static final String DELETE_COMMAND = "DELETE FROM employees_tbl WHERE id = ?";
    public static final String FIND_COMMAND = "SELECT * FROM employees_tbl WHERE id = ?";
    public static final String SELECT_ALL = "SELECT * FROM employees_tbl";
    

    public EmployeeDAOMySQLImpl(){
        try {
            conn = DriverManager.getConnection(EmployeeDAO.URL, EmployeeDAO.USERNAME, EmployeeDAO.PASSWORD);
        } catch (SQLException e) {
            System.out.println("Unable to establish the connection with DB");
            e.printStackTrace();
        }
    }

    @Override
    public void addEmployee(Employee e) {

        int i = 0;
        try {

            statement = conn.prepareStatement(INSERT_COMMAND);
            statement.setInt(1, e.getId());
            statement.setString(2, e.getName());
            statement.setString(3, e.getDept());
            statement.setInt(4, e.getDaysAttended());
            i = statement.executeUpdate();

        } catch (SQLException e1) {
            System.out.println("Adding record failed... Unable to execute INSERT Command");
            e1.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        if(i > 1){
            System.out.println("Record Added...");
        }

    }
    
	@Override
    public void updateEmployee(Employee e) {

        int i = 0;
        try {

            statement = conn.prepareStatement(UPDATE_COMMAND);
            
            statement.setString(1, e.getName());
            statement.setString(2, e.getDept());
            statement.setInt(3, e.getDaysAttended());
            statement.setInt(4, e.getId());
            i = statement.executeUpdate();

        } catch (SQLException e1) {
            System.out.println("Adding record failed... Unable to execute UPDATE Command");
            e1.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        if(i > 1){
            System.out.println("Record Added...");
        }

    }

    @Override
    public void deleteEmployee(int id) {
        int i = 0;

        try {
            statement = conn.prepareStatement(DELETE_COMMAND);
            statement.setInt(1, id);
            i = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete operation failed... Unable to execute DELETE Command");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if(i > 1){
            System.out.println("Record Deleted...");
        }

    }

    @Override
    public Employee findEmployee(Employee e) throws EmployeeNotFoundException {
        Employee findTemp = null;

        try {
            statement = conn.prepareStatement(FIND_COMMAND);
            statement.setInt(1, e.getId());
            resultSet = statement.executeQuery();

            if(!resultSet.next()){
                throw new EmployeeNotFoundException(e.getId());
            }

            findTemp = new Employee();
            findTemp.setName(resultSet.getString("name"));
            findTemp.setDept(resultSet.getString("dept"));
            findTemp.setDaysAttended(resultSet.getInt("daysAttended"));
            findTemp.setId(resultSet.getInt("id"));
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }

        return findTemp;
    }

    @Override
    public List<Employee> showAllEmployees() {

        Employee selectAllTemp = null;
        List<Employee> empList = new LinkedList<>();

        try {
            statement = conn.prepareStatement(SELECT_ALL);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                selectAllTemp = new Employee();
                selectAllTemp.setId(resultSet.getInt("id"));
                selectAllTemp.setName(resultSet.getString("name"));
                selectAllTemp.setDept(resultSet.getString("dept"));
                selectAllTemp.setDaysAttended(resultSet.getInt("daysAttended"));

                empList.add(selectAllTemp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }

        return empList;
    }
}
