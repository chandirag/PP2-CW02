package sample;

import com.mongodb.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Database {
    public static DB connectToDatabase() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        return mongoClient.getDB("test2");
    }

    public static DBObject createDBObject(DefaultMember member) {
        BasicDBObjectBuilder basicDBObjectBuilder = BasicDBObjectBuilder.start();
        basicDBObjectBuilder.append("_id", member.getMembershipNumber());
        basicDBObjectBuilder.append("member-type", member.getMemberType());
        basicDBObjectBuilder.append("membership-date", member.getMembershipDate());
        basicDBObjectBuilder.append("name", member.getName());
        basicDBObjectBuilder.append("height", member.getHeight());
        basicDBObjectBuilder.append("weight", member.getWeight());
        return basicDBObjectBuilder.get();
    }

    public static DBObject createDBObject(StudentMember member) {
        BasicDBObjectBuilder basicDBObjectBuilder = BasicDBObjectBuilder.start();
        basicDBObjectBuilder.append("_id", member.getMembershipNumber());
        basicDBObjectBuilder.append("member-type", member.getMemberType());
        basicDBObjectBuilder.append("membership-date", member.getMembershipDate());
        basicDBObjectBuilder.append("name", member.getName());
        basicDBObjectBuilder.append("height", member.getHeight());
        basicDBObjectBuilder.append("weight", member.getWeight());
        basicDBObjectBuilder.append("school-name", member.getSchoolName());
        return basicDBObjectBuilder.get();
    }

    public static DBObject createDBObject(Over60Member member) {
        BasicDBObjectBuilder basicDBObjectBuilder = BasicDBObjectBuilder.start();
        basicDBObjectBuilder.append("_id", member.getMembershipNumber());
        basicDBObjectBuilder.append("member-type", member.getMemberType());
        basicDBObjectBuilder.append("membership-date", member.getMembershipDate());
        basicDBObjectBuilder.append("name", member.getName());
        basicDBObjectBuilder.append("height", member.getHeight());
        basicDBObjectBuilder.append("weight", member.getWeight());
        basicDBObjectBuilder.append("age", member.getAge());
        return basicDBObjectBuilder.get();
    }

    public static DBCollection createDefaultMember(int id, String name, String membershipDate, double height, double weight) {
        DefaultMember defaultMember = new DefaultMember(id, name, membershipDate, height, weight, "Default");
        defaultMember.setMemberType("Default");
        DBObject doc = Database.createDBObject(defaultMember);
        DB db = Database.connectToDatabase();
        DBCollection collection = db.getCollection("users");
        collection.insert(doc);
        return collection;
    }

    public static void createStudentMember(int id, String name, String membershipDate, double height, double weight, String schoolName) {
        StudentMember studentMember = new StudentMember(id, name, membershipDate, height, weight, schoolName, "Student");
        studentMember.setMemberType("Student");
        DBObject doc = Database.createDBObject(studentMember);
        DB db = Database.connectToDatabase();
        DBCollection collection = db.getCollection("users");
        collection.insert(doc);
    }

    public static void createOver60Member(int id, String name, String membershipDate, double height, double weight, int age) {
        Over60Member over60Member = new Over60Member(id, name, membershipDate, height, weight, age, "Over60");
        over60Member.setMemberType("Over60");
        DBObject doc = Database.createDBObject(over60Member);
        DB db = Database.connectToDatabase();
        DBCollection collection = db.getCollection("users");
        collection.insert(doc);
    }

    public static DBObject findDocumentById(int id) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);

        DB db = Database.connectToDatabase();
        DBCollection collection = db.getCollection("users");
        return collection.findOne(query);
    }

    public static void sortAndPrintExistingMembers(String object, int number){
        DB db = Database.connectToDatabase();
        DBCollection col = db.getCollection("users");
        DBCursor cursor1 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor2 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor3 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor4 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor5 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor6 = col.find().sort(new BasicDBObject(object,number));
        System.out.println("-------------------------------------");
        System.out.format("%-16s %-18s %-15s %-17s %-10s %-10s %n", "Membership No","Membership Type", "Name", "Date Joined", "Height", "Weight");
        while (cursor1.hasNext() && cursor2.hasNext()){
            Object id = cursor1.next().get("_id");
            Object memberType = cursor2.next().get("member-type");
            Object name = cursor3.next().get("name");
            Object dateJoined = cursor4.next().get("membership-date");
            Object height = cursor5.next().get("height");
            Object weight = cursor6.next().get("weight");
            System.out.format("%-16s %-18s %-15s %-17s %-10s %-10s %n", id.toString(), memberType.toString(), name.toString(), dateJoined.toString(), height.toString(), weight.toString());
        }
        System.out.println();
    }

    public static void saveToFile(String fileName, String object, int number) throws IOException {
        File file = new File(fileName);
        PrintWriter printWriter = new PrintWriter(file);
        DB db = Database.connectToDatabase();
        StringBuilder fileString= new StringBuilder();
        DBCollection col = db.getCollection("users");
        DBCursor cursor1 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor2 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor3 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor4 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor5 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor6 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor7 = col.find().sort(new BasicDBObject(object,number));
        DBCursor cursor8 = col.find().sort(new BasicDBObject(object,number));
        String output = String.format("%-16s %-18s %-15s %-17s %-10s %-10s %-15s %-10s %n", "Membership No", "Membership Type", "Name",
                "Date Joined", "Height", "Weight", "School Name", "Age");
        while (cursor1.hasNext() || cursor2.hasNext() || cursor3.hasNext() || cursor4.hasNext() ||
                cursor5.hasNext() || cursor6.hasNext() || cursor7.hasNext() || cursor8.hasNext()) {
            Object id = cursor1.next().get("_id");
            Object name = cursor2.next().get("name");
            Object memberType = cursor3.next().get("member-type");
            Object dateJoined = cursor4.next().get("membership-date");
            Object height = cursor5.next().get("height");
            Object weight = cursor6.next().get("weight");
            Object schoolName = cursor7.next().get("school-name");
            Object age = cursor8.next().get("age");
            fileString.append(String.format("%-16s %-18s %-15s %-17s %-10s %-10s %-15s %-10s %n", id, memberType, name,
                    dateJoined, height, weight, schoolName, age));
        }
        printWriter.write(output + "\n" + fileString);
        printWriter.close();
    }

    public static ArrayList<Integer> printID() {
        DB db = Database.connectToDatabase();
        DBCollection col = db.getCollection("users");
        DBCursor nameCursor = col.find();
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (nameCursor.hasNext()) {
            arrayList.add(Integer.parseInt(nameCursor.next().get("_id").toString()));
        }
        return  arrayList;
    }

    public static ArrayList<String> printNames() {
        return createStringListArray("name");
    }

    public static ArrayList<String> printDateJoined() {
        return  createStringListArray("membership-date");
    }

    public static ArrayList<Double> printHeight() {
        return  createDoubleListArray("height");
    }

    public static ArrayList<Double> printWeight() {
        return  createDoubleListArray("weight");
    }

    public static ArrayList<String> printMemberType() {
        return  createStringListArray("member-type");
    }

    public static ArrayList<String> createStringListArray(String fieldName) {
        DB db = Database.connectToDatabase();
        DBCollection col = db.getCollection("users");
        DBCursor dbCursor = col.find();
        ArrayList<String> arrayList = new ArrayList<>();
        while (dbCursor.hasNext()) {
            arrayList.add(dbCursor.next().get(fieldName).toString());
        }
        return arrayList;
    }

    public static ArrayList<Double> createDoubleListArray(String fieldName) {
        DB db = Database.connectToDatabase();
        DBCollection col = db.getCollection("users");
        DBCursor dbCursor = col.find();
        ArrayList<Double> arrayList = new ArrayList<>();
        while (dbCursor.hasNext()) {
            arrayList.add(Double.parseDouble(dbCursor.next().get(fieldName).toString()));
        }
        return arrayList;
    }

    public static int getCount(){
        DB db = Database.connectToDatabase();
        DBCollection col = db.getCollection("users");
        return (int) col.count();
    }

    public static DBObject readNameTest(String name){
        DB db = Database.connectToDatabase();
        DBCollection col = db.getCollection("users");
        DBObject query = BasicDBObjectBuilder.start().add("name", name).get();
        return query;
    }



    public static ArrayList<String> stringSearch(DBObject object, String key){
        DB db = Database.connectToDatabase();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find(object);
        ArrayList<String> foundNames = new ArrayList<>();
        while (cursor.hasNext()) {
            String searched = cursor.next().get(key).toString();
            foundNames.add(searched);
        }
        return foundNames;
    }

    public static ArrayList<Integer> intSearch(DBObject object, String key){
        DB db = Database.connectToDatabase();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find(object);
        ArrayList<Integer> foundNames = new ArrayList<>();
        while (cursor.hasNext()) {
            int searched = (int) cursor.next().get(key);
            foundNames.add(searched);
        }
        return foundNames;
    }

    public static ArrayList<Double> doubleSearch(DBObject object, String key){
        DB db = Database.connectToDatabase();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find(object);
        ArrayList<Double> foundNames = new ArrayList<>();
        while (cursor.hasNext()) {
            double searched = (double) cursor.next().get(key);
            foundNames.add(searched);
        }
        return foundNames;
    }

    public static String toTitleCase(String input) {

        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c : input.toLowerCase().toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }
            titleCase.append(c);
        }

        return titleCase.toString();
    }
}



