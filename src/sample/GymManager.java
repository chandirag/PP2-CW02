package sample;

import java.io.IOException;

public interface GymManager {
    void addNewMember();
    void deleteExistingMember(int id);
    void printExistingMembers();
    void sort();
    void saveToFile() throws IOException;
    void openGUI();
}
