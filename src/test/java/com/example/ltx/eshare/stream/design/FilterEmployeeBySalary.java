package com.example.ltx.eshare.stream.design;

import com.example.ltx.eshare.stream.Employee;

/**
 * @Author: LiuTX
 * @Date: 2021/6/30 10:58
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean filter(Employee employee) {
        return employee.getSalary() > 5000;
    }
}
