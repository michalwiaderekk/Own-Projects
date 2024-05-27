import java.io.File;
import java.util.Scanner;

public class ModifyTask
        extends UserInterface<Task>
        implements Command{
    private Scanner scanner = new Scanner(System.in);
    private Modification type;
    private Task task;
    public ModifyTask(Task task){
        this.task = task;
    }
    public void setType(Modification type){
        this.type = type;
    }
    @Override
    public void showOptions() {
        System.out.println("Co chcesz zrobić?");
        System.out.println("\033[0;31m1. Modyfikować linie\033[0m");
        System.out.println("\033[0;32m2. Dodać nową linię\033[0m");
        int option = scanner.nextInt();
        this.setType(option == 1 ? Modification.MODIFY : Modification.ADD);
    }
    @Override
    public void execute() {
        switch (this.type){
            case MODIFY -> {
                System.out.println("Którą linie chcesz zmienić?");
                int lineNumber = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Wprowadź nową zawartość:\n");
                String newLine = scanner.nextLine();
                task.modifyLine(lineNumber,newLine);
                task.createFile();
            }
            case ADD -> {
                System.out.println("Wprowadź nową zawartość:\n");
                String newLine = scanner.nextLine();
                task.addLine(newLine);
                task.createFile();
            }
        }
    }
}
