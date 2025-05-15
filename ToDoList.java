import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    static final String FILE_NAME = "todolist.txt";
    static ArrayList<String> todoList = new ArrayList<>();

    public static void main(String[] args) {
        loadTodoList();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=====TO DO LIST MENU=====");
            System.out.println("1. View List");
            System.out.println("2. Add List");
            System.out.println("3. Change List");
            System.out.println("4. Delete List");
            System.out.println("5. Close");
            System.out.println("Select :");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showTodoList();
                    break;
                case 2:
                    addTodo(scanner);
                    break;
                case 3:
                    changeTodo(scanner);
                    break;
                case 4:
                    deleteTodo(scanner);
                    break;
                case 5: 
                    System.out.println("Close...");
                    break;
                default:
                    System.out.println("Options not valid");
            }
        } while (choice != 5);
        
        scanner.close();
    }

    public static void loadTodoList() {
        try{
            File file = new File(FILE_NAME);
            if (file.exists()) {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    todoList.add(reader.nextLine());
                }
                reader.close();
            }else {
            file.createNewFile();
        }
        }catch (IOException e) {
            System.out.println("there was an error reading the file...");
        }
    }

    public static void saveTodoList() {
        try{
            FileWriter writer = new FileWriter(FILE_NAME);
            for (String item : todoList){
                writer.write(item + System.lineSeparator());
                //saya ganti dari (item + "\\n") menjadi 
                //(item + System.lineSeparator()) sesuai saran gpt, tapi tidak terekam
            }
            writer.close();
        }catch (IOException e) {
            System.out.println("failed to save data...");
        }
    }

    public static void showTodoList() {
        System.out.println("List TO DO LIST");
        if (todoList.isEmpty()) {
            System.out.println("No TO DO LIST Yet");
        }else {
            for(int i = 0; i < todoList.size(); i++){
            System.out.println((i + 1)+ ". " + todoList.get(i));
            }
        }
    }

    public static void addTodo(Scanner scanner) {
        System.out.println("Enter a new list : ");
        String newItem = scanner.nextLine();
        todoList.add(newItem);
        saveTodoList();
        System.out.println("List Added : ");
    }

    public static void changeTodo(Scanner scanner) {
        showTodoList();
        System.out.println("Select the number you want to change : ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if (index >= 0 && index < todoList.size()){
            System.out.println("Enter New Value : ");
            String newValue = scanner.nextLine();
            todoList.set(index, newValue);
            saveTodoList();
            System.out.println("List Updated...");
        }else {
            System.out.println("Invalid Number");
        }
    }

    public static void deleteTodo(Scanner scanner) {
        showTodoList();
        System.out.println("Select the number you want to delete : ");
        int index = scanner.nextInt() -1;
        scanner.nextLine();
        if (index >= 0 && index < todoList.size()) {
            todoList.remove(index);
            saveTodoList();
            System.out.println("List Deleted...");
        } else{
            System.out.println("Invalid Number");
        }
    }
}