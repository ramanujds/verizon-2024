import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Serialization

//        Trainee trainee = new Trainee(1, "John", "john@uyahoo.com");
//
//        FileOutputStream fileOutputStream = new FileOutputStream("trainee.obj");
//
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//
//        objectOutputStream.writeObject(trainee);

        // Deserialization

        FileInputStream fileInputStream = new FileInputStream("trainee.obj");

        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Trainee trainee = (Trainee) objectInputStream.readObject();

        System.out.println(trainee);

    }

    }
