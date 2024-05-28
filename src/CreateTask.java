import java.util.ArrayList;
import java.util.Scanner;

public class CreateTask
        extends UserInterface<Task>
        implements Command{
    private Scanner scanner = new Scanner(System.in);
    public CreateTask() {
        super();
        execute();
    }

    @Override
    public void showOptions() {}


    @Override
    public void execute() {
        System.out.print("Podaj nazwe polecenia:\n");
        String taskName = scanner.nextLine();
        taskName.replace(' ', '_');
        ArrayList<String> taskContent = new ArrayList<>();
        System.out.println("ABY ZAKOŃCZYĆ WPISYWANIE WPISZ ###");
        String line = "";
        while(true){
            line = scanner.nextLine();
            if(!line.equals("###")){
                taskContent.add(line);
            } else{
                break;
            }
        }
        super.setObj(new Task(taskName, taskContent, super.getBasePath() + "Tasks\\" + taskName + ".txt"));
        super.getObj().createFile();
    }
}
