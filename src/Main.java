import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true){
            System.out.println("Co chcesz zrobić?");
            System.out.println("\033[0;31m1. Stworzyć nowe polecenie\033[0m"); // DONE
            System.out.println("\033[0;32m2. Modyfikować istniejące polecenie\033[0m"); // DONE
            System.out.println("\033[0;31m3. Liczba rozwiazan nadeslanych przez studenta\033[0m"); // DONE
            System.out.println("\033[0;32m4. Wglad we wszystkie zadania po kolei\033[0m"); // TO DO
            System.out.println("\033[0;31m5. Liczba zadan nadeslanych przez studenta\033[0m"); // DONE
            System.out.println("\033[0;32m6. Sekwencyjne wyswietlanie rozwiazan\033[0m"); // TO DO
            System.out.print("Opcja (1 - 6 kazde inne to koniec):");
            Scanner scanner = new Scanner(System.in);
            int opcja = scanner.nextInt();
            switch(opcja){
                case 1 : {
                    CreateTask createTask = new CreateTask();
                    break;
                }
                case 2 : {
                    System.out.print("Podaj ścieżke do pliku:");
                    String path = scanner.nextLine();
                    File file = new File(path);
                    if(!file.exists()){
                        System.out.println("---!BRAK PLIKU!---");
                        break;
                    }
                    ModifyTask modifyTask = new ModifyTask(new Task(path));
                    modifyTask.showOptions();
                    modifyTask.execute();
                    break;
                }
                case 3 : {
                    scanner.nextLine();
                    System.out.print("Podaj imie i nazwisko studenta:");
                    String studentName = scanner.nextLine();
                    Solution solution = new Solution(studentName);
                    solution.loadFile();
                    System.out.print("Podaj nazwe zadania:");
                    String taskName = scanner.nextLine();
                    System.out.println("Liczba rozwiazan zadania: " + taskName + " to:" + solution.getSolutionsNumber(taskName));
                    break;
                }
                case 4 : {
                    break;
                }
                case 5 : {
                    scanner.nextLine();
                    System.out.print("Podaj imie i nazwisko studenta:");
                    String studentName = scanner.nextLine();
                    Solution solution = new Solution(studentName);
                    solution.loadFile();
                    System.out.println("Student nadeslal " + solution.getTaskNumber() + " zadan");
                    break;
                }
                case 6 : {
                    scanner.nextLine();
                    System.out.print("Podaj imie i nazwisko studenta:");
                    String studentName = scanner.nextLine();
                    System.out.print("Podaj nazwe zadania:");
                    String taskName = scanner.nextLine();
                    ViewSolutionsSequentially viewSolutions = new ViewSolutionsSequentially(studentName,taskName);
                    int option = 3;
                    do{
                        viewSolutions.showOptions();
                        do{
                            option = scanner.nextInt();
                        }while(!viewSolutions.validOption(option));
                        viewSolutions.setOption(option);
                    }while(option != 3);
                    break;
                }
                default : {
                    System.out.println("---!BŁĄD!---");
                }
            }
            break;
        }
    }
}