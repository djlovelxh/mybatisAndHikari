package dj.mybatis.dao;

import dj.mybatis.entity.Employee;

public interface EmployeeDao {
    public Employee getEmployee(String name);

    public boolean updateEmployee(Employee employee);

    public static void deleteEmployee(String name) {
        throw new NullPointerException();
    }

    public Exception saveEmployee(Employee employee);
}