package sample;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.io.*;

public class Database {
    public static DB connectToDatabase() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        return mongoClient.getDB("test2");
    }

    public static DBObject createDBObject(DefaultMember member) {
        BasicDBObjectBuilder basicDBObjectBuilder = BasicDBObjectBuilder.start();

        basicDBObjectBuilder.append("_id", member.getMembershipNumber());
        basicDBObjectBuilder.append("member-type", member.getMemberType());
        basicDBObjectBuilder.append("name", member.getName());
        return basicDBObjectBuilder.get();
    }

    public static DBObject createDBObject(StudentMember member) {
        BasicDBObjectBuilder basicDBObjectBuilder = BasicDBObjectBuilder.start();

        basicDBObjectBuilder.append("_id", member.getMembershipNumber());
        basicDBObjectBuilder.append("member-type", member.getMemberType());
        basicDBObjectBuilder.append("name", member.getName());
        basicDBObjectBuilder.append("school-name", member.getSchoolName());
        return basicDBObjectBuilder.get();
    }

    public static DBObject createDBObject(Over60Member member) {
        BasicDBObjectBuilder basicDBObjectBuilder = BasicDBObjectBuilder.start();

        basicDBObjectBuilder.append("_id", member.getMembershipNumber());
        basicDBObjectBuilder.append("member-type", member.getMemberType());
        basicDBObjectBuilder.append("name", member.getName());
        basicDBObjectBuilder.append("age", member.getAge());
        return basicDBObjectBuilder.get();
    }

    public static DBCollection createDefaultMember(int id, String name) {
        DefaultMember defaultMember = new DefaultMember(id, name);
        defaultMember.setMembershipNumber(id);
        defaultMember.setName(name);
        defaultMember.setMemberType("Default");
        DBObject doc = Database.createDBObject(defaultMember);
        DB db = Database.connectToDatabase();
        DBCollection collection = db.getCollection("users");
        collection.insert(doc);
        return collection;
    }

    public static void createStudentMember(int id, String name, String schoolName) {
        StudentMember studentMember = new StudentMember(id, name, schoolName);
        studentMember.setMembershipNumber(id);
        studentMember.setName(name);
        studentMember.setSchoolName(schoolName);
        studentMember.setMemberType("Student");
        DBObject doc = Database.createDBObject(studentMember);
        DB db = Database.connectToDatabase();
        DBCollection collection = db.getCollection("users");
        collection.insert(doc);
    }

    public static void createOver60Member(int id, String name, int age) {
        Over60Member over60Member = new Over60Member();
        over60Member.setMembershipNumber(id);
        over60Member.setName(name);
        over60Member.setAge(age);
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
        System.out.println("-------------------------------------");
        System.out.format("%-16s %-18s %-17s %n", "Membership No","Name", "Membership Type");
        while (cursor1.hasNext() && cursor2.hasNext()){
            Object id = cursor1.next().get("_id");
            Object name = cursor2.next().get("name");
            Object memberType = cursor3.next().get("member-type");
            System.out.format("%-16s %-18s %-17s %n", id.toString(), name.toString(), memberType.toString());
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
        String output = String.format("%-16s %-18s %-17s %-18s %-15s %n", "Membership No","Name", "Membership Type", "School Name", "Age");
        while (cursor1.hasNext() || cursor2.hasNext() || cursor3.hasNext() || cursor4.hasNext()) {
            Object id = cursor1.next().get("_id");
            Object name = cursor2.next().get("name");
            Object memberType = cursor3.next().get("member-type");
            Object schoolName = cursor4.next().get("school-name");
            Object age = cursor5.next().get("age");
            fileString.append(String.format("%-16s %-18s %-17s %-18s %-15s %n", id, name, memberType, schoolName, age));
        }
        printWriter.write(output + "\n" + fileString);
        printWriter.close();
    }


}


