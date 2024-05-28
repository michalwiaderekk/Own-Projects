public abstract class UserInterface <T>{
    private T obj;
    private String basePath = "C:\\Users\\rwiad\\Desktop\\Uczelnia\\modul_edycyjny\\";
    public UserInterface(T obj){
        this.obj = obj;
    }
    public UserInterface(){}
    public abstract void showOptions();
    public T getObj(){
        return this.obj;
    }
    public void setObj(T obj){
        this.obj = obj;
    }
    public String getBasePath(){
        return this.basePath;
    }
}
