package com.example.ltx.eshare.stream.jdk8;

import com.example.ltx.eshare.stream.Employee;
import com.google.common.base.Supplier;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                new Employee("赵六", 25, 8888.88),
                new Employee("赵六", 25, 8888.88));

        List<Employee> employeeList = filterEmployeesByAge(employees);
        for (Employee employee : employeeList) {
            log.info(employee.getName());
        }

        List<Employee> collect = employees.stream().filter(employee -> employee.getAge() >= 25).collect(Collectors.toList());
        System.out.println(collect);
        List<Employee> distinctList = employees.stream().distinct().collect(Collectors.toList());

        List<Employee> parallelDistinct = employees.parallelStream().distinct().collect(Collectors.toList());

        List<Employee> limitList = employees.stream().limit(6).collect(Collectors.toList());
        double sum = employees.stream().mapToDouble(Employee::getSalary).sum();

        List<Employee> synchronizedList = Collections.synchronizedList(employees.parallelStream().distinct().collect(Collectors.toList()));

        Optional<Double> reduce = employees.stream().map(Employee::getSalary).reduce(Double::sum);

        employees.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));

        String.valueOf(new Integer(new Byte("1")));
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

        TreeSet<Integer> treeSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        treeSet.comparator();
    }

    //消费型接口,对象实例方法
    @Test
    public void test4() {
        //情况1
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("我使用lambda");
        //使用方法引用
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("我使用方法引用的方式输出");
    }

    @Test
    public void test5() {
        //供给型接口Supplier<T> T.get()获取返回值,getName
        Employee emp = new Employee("Tom", 20, 10000.1);
        Supplier<String> stringCopier = () -> emp.getName();
        System.out.println(stringCopier.get());

        Supplier<String> spl1 = emp::getName;
        System.out.println(spl1.get());
    }

    //Comparator中的int compare(T t1, T t2)
    //Integer中的int compare(T t1,T t2)
    @Test
    public void test6() {
        Comparator<Integer> tConsumer = (t1, t2) -> Integer.compare(t1, t2);
        System.out.println(tConsumer.compare(1, 100));

        Comparator<Integer> compare = Integer::compare;
        System.out.println(compare.compare(1, 100));
    }

    //函数式接口Function中的R apply(T t)
    //Math中的Long round(Double d)
    @Test
    public void test7() {
        Function<Double, Long> func = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        System.out.println(func.apply(100.1));

        Function<Double, Long> func1 = d -> Math.round(d);
        System.out.println(func1.apply(100.1));

        Function<Double, Long> func2 = Math::round;
        System.out.println(func2.apply(100.1));
    }

    //类::实例方法
    @Test
    public void test8() {
        //比较它的两个参数的顺序。 当第一个参数小于、等于或大于第二个参数时，返回一个负整数、零或正整数
        Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
        System.out.println(comparator.compare("abc", "bac"));

        Comparator<String> func = String::compareTo;
        System.out.println(func.compare("abc", "bac"));
    }

    //断定型接口 BiPredicate中的boolean test(T t1,T t2)
    //String中的boolean t1.equals(t2)
    @Test
    public void test9() {
        BiPredicate<String, String> pred1 = (s1, s2) -> s1.equals(s2);
        System.out.println(pred1.test("abc", "abd"));

        BiPredicate<String, String> pred2 = String::equals;
        System.out.println(pred2.test("abc", "abd"));

    }

    @Test
    public void test10() {
        Employee emp = new Employee("Tom", 20, 10000.1);
        Function<Employee, String> func1 = e -> e.getName();
        System.out.println(func1.apply(emp));

        Function<Employee, String> func2 = Employee::getName;
        System.out.println(func2.apply(emp));
    }

    /**
     * Stream并发测试
     */
    @Test
    public void test11() {

    }

    private static List<Integer> list1 = new ArrayList<>();
    private static List<Integer> list2 = new ArrayList<>();
    private static List<Integer> list3 = new ArrayList<>();
    private static List<Integer> list4 = new ArrayList<>();
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        //串行流
        IntStream.range(0, 10000).forEach(list1::add);
        //并行流
        IntStream.range(0, 10000).parallel().forEach(list2::add);

        //使用锁
        IntStream.range(0, 10000).forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } finally {
                lock.unlock();
            }
        });

        log.info("[{串行执行的大小}]" + list1.size());
        log.info("{[并行执行的大小]}" + list2.size());
        log.info("{[加锁并行执行的大小]}" + list1.size());
    }

}



