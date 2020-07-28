package sample;

import com.mongodb.*;
import org.bson.types.ObjectId;

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
        DBObject doc = Database.createDBObject(defaultMember);
        DB db = Database.connectToDatabase();
        DBCollection collection = db.getCollection("users");
        collection.insert(doc);
        return collection;
    }

    public static void createStudentMember(int id, String name, String schoolName) {
        StudentMember studentMember = new StudentMember(id, name, schoolName);
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
        over60Member.memberType = "Over60";
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
}


