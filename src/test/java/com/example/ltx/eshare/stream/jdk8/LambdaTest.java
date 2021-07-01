package com.example.ltx.eshare.stream.jdk8;

import com.example.ltx.eshare.stream.Employee;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

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


    @Test
    public void test3() {
        TreeSet<Integer> ts = new TreeSet();
        ts.add(1);
        ts.add(2);
        ts.add(3);
        ts.add(4);
        ts.add(5);

        TreeSet<Integer> treeSet= new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });
        treeSet.comparator();
    }

}



