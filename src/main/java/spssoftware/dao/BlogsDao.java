package spssoftware.dao;

import com.mongodb.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class BlogsDao {

    public List<String> getBlogs() {
        List<String> blogs = new LinkedList<String>();
        try {

            ServerAddress addr = new  ServerAddress("ds053317.mongolab.com",53317);
            MongoClient mongoClient = new MongoClient(addr);
            DB db = mongoClient.getDB("spssoftware");
            boolean auth = db.authenticate("spssoftware", "spssoftware".toCharArray());
            DBCollection dbCollection = db.getCollection("blogs");
//            BasicDBObject doc = new BasicDBObject("name", UUID.randomUUID().toString());
//            dbCollection.insert(doc);
            DBCursor cursor = dbCollection.find();
            if (cursor.hasNext()) {
                DBObject obj = cursor.next();
                blogs.add(obj.toString());
            }
            return blogs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
