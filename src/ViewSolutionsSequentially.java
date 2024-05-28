import javax.swing.text.View;

public class ViewSolutionsSequentially
        extends UserInterface<Solution>
        implements Command{
    private int id;
    private int option;
    private String taskName;
    public ViewSolutionsSequentially(String studentName,String taskName){
        super(new Solution(studentName));
        super.getObj().loadFile();
        this.taskName = taskName;
        this.id = 1;
    }
    @Override
    public void showOptions() {
        execute();
        if(id == 1){
            System.out.println("\033[0;31m2. Nastepne\033[0m");
            System.out.println("\033[0;32m3. Zakoncz\033[0m");
        } else if (id == super.getObj().getSolutionsNumber(taskName)) {
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
                System.out.println(super.getObj().viewSolution(super.getBasePath() + super.getObj().getStudentName() + "\\" + this.taskName + "\\" + this.id + ".txt"));
                break;
            }
            case 2 : {
                id++;
                System.out.println(super.getObj().viewSolution(super.getBasePath() + super.getObj().getStudentName() + "\\" + this.taskName + "\\" + this.id + ".txt"));
                break;
            }
            default: {
                System.out.println(super.getObj().viewSolution(super.getBasePath() + super.getObj().getStudentName() + "\\" + this.taskName + "\\" + this.id + ".txt"));
            }
        }
    }

    public boolean validOption(int option){
        if (option != 1 && option != 2 && option != 3) {
            System.out.println("GATE 1");
            return false;
        } else if(this.id == 1 && option == 1){
            System.out.println("GATE 2");
            return false;
        } else if (this.id == super.getObj().getSolutionsNumber(taskName) && option == 2) {
            System.out.println("GATE 3");
            return false;
        }
        return true;
    }
    public void setOption(int option){
        this.option = option;
    }
}
