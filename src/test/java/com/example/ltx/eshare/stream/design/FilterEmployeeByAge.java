package com.example.ltx.eshare.stream.design;

import com.example.ltx.eshare.stream.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: LiuTX
 * @Date: 2021/6/30 10:36
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean filter(Employee employee) {
        return employee.getAge() >= 30;
    }

    /**
     * （优化）设计模式的魅力
     *
     * @param list        需要过滤的列
     * @param myPredicate 传入一个过滤标准的对象
     * @return 筛选后的数据
     */
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> myPredicate) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if (myPredicate.filter(employee)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    @Test
    public void test4() {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 19, 55555.55),
                new Employee("王五", 30, 6666.66),
                new Employee("赵六", 25, 8888.88),
                new Employee("小七", 16, 4888.88));
        List<Employee> employees1 = this.filterEmployee(employees, new FilterEmployeeByAge());
        List<Employee> employees2 = this.filterEmployee(employees, new FilterEmployeeBySalary());
        List<Employee> employeesByLambda = this.filterEmployee(employees, (e) -> e.getSalary() > 5000);

        employees.forEach(it -> it.getName());
    }
}
