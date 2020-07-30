package sample;

import com.mongodb.*;

import java.io.*;
import java.util.ArrayList;

public class Database {

    // Connect to local mongodb Database
    public static DB connect() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        return mongoClient.getDB("test2");
    }


    public static DBObject createMongoDocument(DefaultMember defaultMember) {
        BasicDBObjectBuilder defaultMemberDocument = BasicDBObjectBuilder.start();
        defaultMemberDocument.append("_id", defaultMember.getMembershipNumber());
        defaultMemberDocument.append("member-type", defaultMember.getMemberType());
        defaultMemberDocument.append("membership-date", defaultMember.getMembershipDate());
        defaultMemberDocument.append("name", defaultMember.getName());
        defaultMemberDocument.append("height", defaultMember.getHeight());
        defaultMemberDocument.append("weight", defaultMember.getWeight());
        return defaultMemberDocument.get();
    }

    public static DBObject createMongoDocument(StudentMember studentMember) {
        BasicDBObjectBuilder studentMemberDocument = BasicDBObjectBuilder.start();
        studentMemberDocument.append("_id", studentMember.getMembershipNumber());
        studentMemberDocument.append("member-type", studentMember.getMemberType());
        studentMemberDocument.append("membership-date", studentMember.getMembershipDate());
        studentMemberDocument.append("name", studentMember.getName());
        studentMemberDocument.append("height", studentMember.getHeight());
        studentMemberDocument.append("weight", studentMember.getWeight());
        studentMemberDocument.append("school-name", studentMember.getSchoolName());
        return studentMemberDocument.get();
    }

    public static DBObject createMongoDocument(Over60Member over60Member) {
        BasicDBObjectBuilder over60MemberDocument = BasicDBObjectBuilder.start();
        over60MemberDocument.append("_id", over60Member.getMembershipNumber());
        over60MemberDocument.append("member-type", over60Member.getMemberType());
        over60MemberDocument.append("membership-date", over60Member.getMembershipDate());
        over60MemberDocument.append("name", over60Member.getName());
        over60MemberDocument.append("height", over60Member.getHeight());
        over60MemberDocument.append("weight", over60Member.getWeight());
        over60MemberDocument.append("age", over60Member.getAge());
        return over60MemberDocument.get();
    }

    // Creates a default member instance and adds it to a mongoDB collection
    public static DBCollection createDefaultMember(int id, String name, String membershipDate, double height, double weight) {
        DefaultMember defaultMember = new DefaultMember(id, name, membershipDate, height, weight, "Default");
        defaultMember.setMemberType("Default");
        DBObject doc = Database.createMongoDocument(defaultMember);
        DB db = Database.connect();
        DBCollection collection = db.getCollection("users");
        collection.insert(doc);
        return collection;
    }

    // Creates a student member instance and adds it to a mongoDB collection
    public static void createStudentMember(int id, String name, String membershipDate, double height, double weight, String schoolName) {
        StudentMember studentMember = new StudentMember(id, name, membershipDate, height, weight, schoolName, "Student");
        studentMember.setMemberType("Student");
        DBObject doc = Database.createMongoDocument(studentMember);
        DB db = Database.connect();
        DBCollection collection = db.getCollection("users");
        collection.insert(doc);
    }

    // Creates a over 60 member instance and adds it to a mongoDB collection
    public static void createOver60Member(int id, String name, String membershipDate, double height, double weight, int age) {
        Over60Member over60Member = new Over60Member(id, name, membershipDate, height, weight, age, "Over60");
        over60Member.setMemberType("Over60");
        DBObject doc = Database.createMongoDocument(over60Member);
        DB db = Database.connect();
        DBCollection collection = db.getCollection("users");
        collection.insert(doc);
    }

    // Finds mongodb document by object id and returns it
    public static DBObject findDocumentById(int id) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        DB db = Database.connect();
        DBCollection collection = db.getCollection("users");
        return collection.findOne(query);
    }


    public static void sortAndPrintExistingMembers(String object, int order){
        DB db = Database.connect();
        DBCollection col = db.getCollection("users");
        DBCursor idCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor memberTypeCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor nameCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor dateJoinedCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor heightCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor weightCursor = col.find().sort(new BasicDBObject(object,order));
        System.out.println("-------------------------------------");
        System.out.format("%-16s %-18s %-15s %-17s %-10s %-10s %n", "Membership No","Membership Type", "Name", "Date Joined", "Height", "Weight");
        while (idCursor.hasNext() && memberTypeCursor.hasNext()){
            Object id = idCursor.next().get("_id");
            Object memberType = memberTypeCursor.next().get("member-type");
            Object name = nameCursor.next().get("name");
            Object dateJoined = dateJoinedCursor.next().get("membership-date");
            Object height = heightCursor.next().get("height");
            Object weight = weightCursor.next().get("weight");
            System.out.format("%-16s %-18s %-15s %-17s %-10s %-10s %n", id.toString(), memberType.toString(), name.toString(), dateJoined.toString(), height.toString(), weight.toString());
        }
        System.out.println();
    }

    // Save all data in the database to external text file
    public static void saveToFile(String fileName, String object, int order) throws IOException {
        File file = new File(fileName);
        PrintWriter printWriter = new PrintWriter(file);
        DB db = Database.connect();
        StringBuilder fileString= new StringBuilder();
        DBCollection col = db.getCollection("users");
        DBCursor idCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor nameCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor memberTypeCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor dateJoinedCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor heightCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor weightCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor schoolNameCursor = col.find().sort(new BasicDBObject(object,order));
        DBCursor ageCursor = col.find().sort(new BasicDBObject(object,order));
        String output = String.format("%-16s %-18s %-15s %-17s %-10s %-10s %-15s %-10s %n", "Membership No", "Membership Type", "Name",
                "Date Joined", "Height", "Weight", "School Name", "Age");
        while (idCursor.hasNext() || nameCursor.hasNext() || memberTypeCursor.hasNext() || dateJoinedCursor.hasNext() ||
                heightCursor.hasNext() || weightCursor.hasNext() || schoolNameCursor.hasNext() || ageCursor.hasNext()) {
            Object id = idCursor.next().get("_id");
            Object name = nameCursor.next().get("name");
            Object memberType = memberTypeCursor.next().get("member-type");
            Object dateJoined = dateJoinedCursor.next().get("membership-date");
            Object height = heightCursor.next().get("height");
            Object weight = weightCursor.next().get("weight");
            Object schoolName = schoolNameCursor.next().get("school-name");
            Object age = ageCursor.next().get("age");
            fileString.append(String.format("%-16s %-18s %-15s %-17s %-10s %-10s %-15s %-10s %n", id, memberType, name,
                    dateJoined, height, weight, schoolName, age));
        }
        printWriter.write(output + "\n" + fileString);
        printWriter.close();
    }

    // Gets value of field '_id' and adds them to an ArrayList
    public static ArrayList<Integer> printID() {
        DB db = Database.connect();
        DBCollection col = db.getCollection("users");
        DBCursor nameCursor = col.find();
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (nameCursor.hasNext()) {
            arrayList.add(Integer.parseInt(nameCursor.next().get("_id").toString()));
        }
        return  arrayList;
    }

    // Gets value of field 'name' and adds them to an ArrayList
    public static ArrayList<String> printNames() {
        return createStringListArray("name");
    }

    // Gets value of field 'membership-date' and adds them to an ArrayList
    public static ArrayList<String> printDateJoined() {
        return createStringListArray("membership-date");
    }

    // Gets value of field 'height' and adds them to an ArrayList
    public static ArrayList<Double> printHeight() {
        return createDoubleListArray("height");
    }

    // Gets value of field 'weight' and adds them to an ArrayList
    public static ArrayList<Double> printWeight() {
        return createDoubleListArray("weight");
    }

    // Gets value of field 'member-type' and adds them to an ArrayList
    public static ArrayList<String> printMemberType() {
        return createStringListArray("member-type");
    }



    // Gets current count of entered members
    public static int getCount() {
        DB db = Database.connect();
        DBCollection col = db.getCollection("users");
        return (int) col.count();
    }

    // Gets search query from user and returns it
    public static DBObject readNameTest(String name) {
        DBObject dbObject = new BasicDBObject("name", name).append("name", new BasicDBObject("$regex", name));
        return dbObject;
    }

    // Returns a string array with results matching passed object and key
    public static ArrayList<String> stringSearch(DBObject object, String key){
        DB db = Database.connect();
        DBCollection col = db.getCollection("users");
        DBCursor dbCursor = col.find(object);
        ArrayList<String> list = new ArrayList<>();
        while (dbCursor.hasNext()) {
            String searched = dbCursor.next().get(key).toString();
            list.add(searched);
        }
        return list;
    }

    // Returns a integer array with results matching passed object and key
    public static ArrayList<Integer> intSearch(DBObject object, String key){
        DB db = Database.connect();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find(object);
        ArrayList<Integer> foundNames = new ArrayList<>();
        while (cursor.hasNext()) {
            int searched = (int) cursor.next().get(key);
            foundNames.add(searched);
        }
        return foundNames;
    }

    // Returns a double array with results matching passed object and key
    public static ArrayList<Double> doubleSearch(DBObject object, String key){
        DB db = Database.connect();
        DBCollection col = db.getCollection("users");
        DBCursor cursor = col.find(object);
        ArrayList<Double> foundNames = new ArrayList<>();
        while (cursor.hasNext()) {
            double searched = (double) cursor.next().get(key);
            foundNames.add(searched);
        }
        return foundNames;
    }

    // Convert entered String to Title Case
    public static String toTitleCase(String word) {
        StringBuilder titleCase = new StringBuilder(word.length());
        boolean nextTitleCase = true;
        for (char character : word.toLowerCase().toCharArray()) {
            if (!Character.isLetterOrDigit(character)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                character = Character.toTitleCase(character);
                nextTitleCase = false;
            }
            titleCase.append(character);
        }
        return titleCase.toString();
    }



    private static ArrayList<String> createStringListArray(String fieldName) {
        DB db = Database.connect();
        DBCollection col = db.getCollection("users");
        DBCursor dbCursor = col.find();
        ArrayList<String> arrayList = new ArrayList<>();
        while (dbCursor.hasNext()) {
            arrayList.add(dbCursor.next().get(fieldName).toString());
        }
        return arrayList;
    }


    private static ArrayList<Double> createDoubleListArray(String fieldName) {
        DB db = Database.connect();
        DBCollection col = db.getCollection("users");
        DBCursor dbCursor = col.find();
        ArrayList<Double> arrayList = new ArrayList<>();
        while (dbCursor.hasNext()) {
            arrayList.add(Double.parseDouble(dbCursor.next().get(fieldName).toString()));
        }
        return arrayList;
    }
}



