package main;

import dao.TodoDAO;
import model.Todo;
import util.LoggerUtil;

import java.util.List;
import java.util.Scanner;

public class TodoAppMain {

    public static void main(String[] args) {

        TodoDAO dao = new TodoDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n TODO APP - TodoAppMain.java:18");
            System.out.println("1. Add Task - TodoAppMain.java:19");
            System.out.println("2. View Tasks - TodoAppMain.java:20");
            System.out.println("3. Mark Task Completed - TodoAppMain.java:21");
            System.out.println("4. Delete Task - TodoAppMain.java:22");
            System.out.println("5. Exit - TodoAppMain.java:23");
            System.out.print("Choose option: - TodoAppMain.java:24");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {

                    case 1:
                        System.out.print("Enter task: - TodoAppMain.java:33");
                        String task = sc.nextLine();
                        dao.addTodo(new Todo(task));
                        LoggerUtil.log("Task added: " + task);
                        System.out.println("Task added! - TodoAppMain.java:37");
                        break;

                    case 2:
                        List<Todo> todos = dao.getAllTodos();
                        for (Todo t : todos) {
                            System.out.println(
                                    t.getId() + ". " +
                                    t.getTask() +
                                    " | Completed: " +
                                    t.isCompleted()
                            );
                        }
                        break;

                    case 3:
                        System.out.print("Enter task ID to complete: - TodoAppMain.java:53");
                        int cid = sc.nextInt();
                        dao.markCompleted(cid);
                        LoggerUtil.log("Task completed ID: " + cid);
                        System.out.println("Task marked completed! - TodoAppMain.java:57");
                        break;

                    case 4:
                        System.out.print("Enter task ID to delete: - TodoAppMain.java:61");
                        int did = sc.nextInt();
                        dao.deleteTodo(did);
                        LoggerUtil.log("Task deleted ID: " + did);
                        System.out.println("Task deleted! - TodoAppMain.java:65");
                        break;

                    case 5:
                        System.out.println("Exiting... - TodoAppMain.java:69");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! - TodoAppMain.java:74");
                }
            } catch (Exception e) {
                System.out.println("Error occurred! - TodoAppMain.java:77");
                e.printStackTrace();
            }
        }
    }
}