import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ViewTasks
        extends UserInterface<Task>
        implements Command{
    private int id = 0;
    private int option = 0;
    private ArrayList<Task> tasks = new ArrayList<>();
    public ViewTasks(){
        Path studentPath = Paths.get(super.getBasePath(), "polecenia");

        try {
            if (Files.exists(studentPath) && Files.isDirectory(studentPath)) {
                Files.walk(studentPath)
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".txt"))
                        .forEach(path -> {
                            String folder = path.getParent().getFileName().toString();
                            String fileName = path.getFileName().toString();
                            Task temp = new Task(fileName,super.getBasePath() + folder + "\\" + fileName);
                            temp.loadFile();
                            this.tasks.add(temp);
                        });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void showOptions() {
        execute();
        if(id == 0){
            System.out.println("\033[0;31m2. Nastepne\033[0m");
            System.out.println("\033[0;32m3. Zakoncz\033[0m");
        } else if (id == tasks.size() - 1) {
            System.out.println("\033[0;31m1. Poprzednie\033[0m");
            System.out.println("\033[0;32m3. Zakoncz\033[0m");
        } else {
            System.out.println("\033[0;31m1. Poprzednie\033[0m");
            System.out.println("\033[0;32m2. Nastepne\033[0m");
            System.out.println("\033[0;31m3. Zakoncz\033[0m");
        }
    }
    @Override
    public void execute() {
        switch (option){
            case 1 : {
                id--;
                System.out.println("TASK:" + this.tasks.get(id).getName());
                System.out.print(this.tasks.get(id).toString());
                break;
            }
            case 2 : {
                id++;
                System.out.println("TASK:" + this.tasks.get(id).getName());
                System.out.print(this.tasks.get(id).toString());
                break;
            }
            default: {
                System.out.println("TASK:" + this.tasks.get(id).getName());
                System.out.print(this.tasks.get(id).toString());
            }
        }
    }
    public boolean validOption(int option){
        if (option != 1 && option != 2 && option != 3) {
            System.out.println("GATE 1");
            return false;
        } else if(this.id == 0 && option == 1){
            System.out.println("GATE 2");
            return false;
        } else if (this.id == tasks.size() - 1 && option == 2) {
            System.out.println("GATE 3");
            return false;
        }
        return true;
    }
    public void setOption(int option){
        this.option = option;
    }
}
