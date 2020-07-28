package sample;

import java.io.IOException;

public interface GymManager {
    public void addNewMember();
    public void deleteExistingMember(int id);
    public void printExistingMembers();
    public void sort();
    public void saveToFile() throws IOException;
}
