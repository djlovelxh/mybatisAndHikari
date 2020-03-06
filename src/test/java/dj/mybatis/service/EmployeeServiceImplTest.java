package dj.mybatis.service;

import dj.mybatis.dao.EmployeeDao;
import dj.mybatis.entity.Employee;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ EmployeeDao.class })
public class EmployeeServiceImplTest {
    @Mock
    public EmployeeDao employeeDao;

    @Mock
    EmployeeServiceImpl employeeServiceImpl;

    // @Before注解的方法会在调用测试方法前执行初始化动作
    @Before
    public void initMocks() {
        // 创建模拟对象EmployeeDao的实例
        employeeDao = mock(EmployeeDao.class);
        // 将模拟对象赋给业务类实例
        employeeServiceImpl = new EmployeeServiceImpl(employeeDao);
    }

    @Test
    public void getEmployeeTest() {
        String name = "scott";
        Employee employee = new Employee();
        employee.setName("scott");
        employee.setSalary(8888.0);
        // 定义当执行employeeDao.getEmployee(name)方法时始终返回employee对象，相当于实现了employeeDao的这个方法
        when(employeeDao.getEmployee(name)).thenReturn(employee);
        /*
         * 下面测试我们想要单元测试的employeeServiceImpl.getEmployee(name)方法 我们已经屏蔽了该方法对employeeDao.getEmployee(name)的调用，相当于解除了依赖
         * 这样我们只需要关心employeeServiceImpl.getEmployee(name)方法的逻辑是否存在问题
         */
        Employee employee2 = employeeServiceImpl.getEmployee(name);
        System.out.println(employee2.getSalary());
    }

    @Test
    public void updateEmployeeTest() {
        Employee employee = new Employee();
        employee.setName("tiger");
        employee.setSalary(99999.0);
        when(employeeDao.updateEmployee(anyObject())).thenReturn(true);
        Employee employee2 = new Employee();
        employee.setName("scott");
        employee.setSalary(99999.0);
        Boolean boolean1 = employeeServiceImpl.updateEmployee(employee2);
        System.out.println(boolean1);
    }

    @Test
    public void deleteEmployeeTest() {
        String name = "haha";
        // 因为这里调用的是静态方法，因此使用PowerMockito.mockStatic(EmployeeDao.class);来模拟静态类
        PowerMockito.mockStatic(EmployeeDao.class);
        // 使用doNothing()定义执行下面一句语句时什么也不做
        PowerMockito.doNothing().when(EmployeeDao.class);
        // 这一句由于上面的doNothing()即使会抛异常也不会再抛
        EmployeeDao.deleteEmployee(name);
        // 因此employeeServiceImpl.deleteEmployee(name)执行时也没有发现异常返回true
        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
        Assert.assertTrue(employeeServiceImpl.deleteEmployee(name));
    }

    @Test
    public void throwDeleteExceptionTest() {
        String name = "haha";
        PowerMockito.mockStatic(EmployeeDao.class);
        // doThrow()定义下面一句语句会抛出异常
        PowerMockito.doThrow(new NullPointerException()).when(EmployeeDao.class);
        EmployeeDao.deleteEmployee(name);
        // 因此employeeServiceImpl.deleteEmployee(name)执行会返回false
        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
        TestCase.assertTrue(employeeServiceImpl.deleteEmployee(name));
    }

    @Test
    public void saveEmployeeTest() {
        Employee employee = new Employee();
        employee.setName("scott");
        employee.setSalary(8888.0);
        // 打桩，定义方法返回的值
        when(employeeDao.saveEmployee(employee)).thenReturn(new NullPointerException());
        // 这里doNothing()没有生效，原因是这里调用的是实例方法，不是静态方法
        PowerMockito.doNothing().when(employeeDao).saveEmployee(employee);
        employeeServiceImpl.saveEmployee(employee);
    }

    @Test
    public void throwSaveEmployeeTest() {
        Employee employee = new Employee();
        employee.setName("scott");
        employee.setSalary(8888.0);
        when(employeeDao.saveEmployee(employee)).thenReturn(new NullPointerException());
        // 这里的doThrow()实际没有生效，因为这里调用的是实例方法，不是静态方法，因此不存在使下一句语句抛异常的作用
        PowerMockito.doThrow(new NullPointerException()).when(employeeDao).saveEmployee(employee);
        employeeServiceImpl.saveEmployee(employee);
    }
}