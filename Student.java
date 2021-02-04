import java.io.Serializable;

public class Student implements Serializable {
    public String name;
    public String surname;
    public String index_number;

    public Student(String name, String surname, String index_number){
        this.name = name;
        this.surname= surname;
        this.index_number = index_number;
    }

    public String toString(){
        return name+" "+surname+" "+index_number;
    }
}
