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
        DefaultMember defaultMember = new DefaultMember(id, name, membershipDate, height, weight);
        defaultMember.setMembershipNumber(id);
        defaultMember.setName(name);
        defaultMember.setMembershipDate(membershipDate);
        defaultMember.setHeight(height);
        defaultMember.setWeight(weight);
        defaultMember.setMemberType("Default");
        DBObject doc = Database.createDBObject(defaultMember);
        DB db = Database.connectToDatabase();
        DBCollection collection = db.getCollection("users");
        collection.insert(doc);
        return collection;
    }

    public static void createStudentMember(int id, String name, String membershipDate, double height, double weight, String schoolName) {
        StudentMember studentMember = new StudentMember();
        studentMember.setMembershipNumber(id);
        studentMember.setName(name);
        studentMember.setMembershipDate(membershipDate);
        studentMember.setHeight(height);
        studentMember.setWeight(weight);
        studentMember.setSchoolName(schoolName);
        studentMember.setMemberType("Student");
        DBObject doc = Database.createDBObject(studentMember);
        DB db = Database.connectToDatabase();
        DBCollection collection = db.getCollection("users");
        collection.insert(doc);
    }

    public static void createOver60Member(int id, String name, String membershipDate, double height, double weight, int age) {
        Over60Member over60Member = new Over60Member();
        over60Member.setMembershipNumber(id);
        over60Member.setName(name);
        over60Member.setMembershipDate(membershipDate);
        over60Member.setHeight(height);
        over60Member.setWeight(weight);
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
        String output = String.format("%-16s %-18s %-15s %-17s %-10s %-10s %-10s %-10s %n", "Membership No", "Membership Type", "Name",
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
            fileString.append(String.format("%-16s %-18s %-15s %-17s %-10s %-10s %-10s %-10s %n", id, memberType, name,
                    dateJoined, height, weight, schoolName, age));
        }
        printWriter.write(output + "\n" + fileString);
        printWriter.close();
    }

}


