package main.java.November2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpTree {


    public class Employee{

        Integer id;
        String name;

        List<Employee> subordinates;

        public Employee(Integer id , String name){
            this.id = id;
            this.name = name;
            subordinates = new ArrayList<>();
        }
    }

    public void printHierachy(String input){
        Map<Integer, Employee> employeeMap = new HashMap<>();
        Employee root = buildHierachy(input, employeeMap);

        printTreeHierachy(root, 0);
    }

    public void printTreeHierachy(Employee employee, int level){

        // Print the current employee with appropriate indentation
        System.out.println(" ".repeat(level * 2) + employee.name);
        for (Employee subordinate : employee.subordinates) {
            printTreeHierachy(subordinate, level + 1);
        }

    }

    public Employee buildHierachy(String input, Map<Integer,Employee> employeeMap){

        Map<Integer, List<Integer>> managerToSuborderdinates = new HashMap<>();
        Employee root = null;

        String[] records = input.split(",\\s*");

        for(String s: records){

            String[] parts = s.split(":");

            if(parts.length != 3)
                continue;

            Integer id = Integer.parseInt(parts[0]);
            String name =  parts[1];
            Integer managerId = Integer.parseInt(parts[2]);

            employeeMap.putIfAbsent(id, new Employee(id,name));

            if(managerId != 0){

                managerToSuborderdinates.putIfAbsent(managerId, new ArrayList<>());
                managerToSuborderdinates.get(managerId).add(id);
            }
            else
                root = employeeMap.get(id);

        }

        for(Map.Entry<Integer, List<Integer>> entries: managerToSuborderdinates.entrySet()){

            Integer managerId = entries.getKey();
            Employee manager  = employeeMap.get(managerId);

            if (manager != null) {

                List<Integer> subordinatedId  = entries.getValue();

                for(Integer id: subordinatedId){
                    Employee subordinate = employeeMap.get(id);
                    if(subordinate != null){
                        manager.subordinates.add(employeeMap.get(id));
                    }

                }

            }

        }

        return root;
    }



    public static void main(String[] args){

        String input = "1:max:4, 2:ann:0, 3:alb:2, 4:edmond:2";
        EmpTree e1  = new EmpTree();

        e1.printHierachy(input);


    }
}
