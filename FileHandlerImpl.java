import java.io.*;
import java.util.Map;

public interface FileHandler {
    void writeToFile(FamilyTree familyTree, String filename);
    FamilyTree readFromFile(String filename);
}

public class FileHandlerImpl implements FileHandler {

    @Override
    public void writeToFile(FamilyTree familyTree, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(familyTree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FamilyTree readFromFile(String filename) {
        FamilyTree familyTree = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            familyTree = (FamilyTree) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return familyTree;
    }
}