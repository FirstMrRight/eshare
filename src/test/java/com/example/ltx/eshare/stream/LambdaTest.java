package com.example.ltx.eshare.stream;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: LiuTX
 * @Date: 2021/6/30 9:31
 */
@Slf4j
public class LambdaTest {
    @Test
    public void testComparator() {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 19, 55555.55),
                new Employee("王五", 30, 6666.66),
                new Employee("赵六", 25, 8888.88));

        List<Employee> employeeList = filterEmployeesByAge(employees);
        for (Employee employee : employeeList) {
            log.info(employee.getName());
        }

    }

    List<Employee> filterEmployeesByAge(List<Employee> list) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getAge() >= 30) {
                employees.add(employee);
            }
        }
        return employees;
    }
}

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Employee implements Serializable {
    private static final long serialVersionUID = -9079722457749166858L;
    private String name;
    private Integer age;
    private Double salary;
}

