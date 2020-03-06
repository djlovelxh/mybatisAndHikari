package dj.mybatis.service;

import dj.mybatis.dao.EmployeeDao;
import dj.mybatis.entity.Employee;

public class EmployeeServiceImpl {
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl() {

    }

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee getEmployee(String name) {
        return employeeDao.getEmployee(name);
    }

    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    public boolean deleteEmployee(String name) {
        try {
            EmployeeDao.deleteEmployee(name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveEmployee(Employee employee) {
        try {
            employeeDao.saveEmployee(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}