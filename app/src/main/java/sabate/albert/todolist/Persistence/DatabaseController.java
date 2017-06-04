package sabate.albert.todolist.Persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sabate.albert.todolist.Domain.Tag;
import sabate.albert.todolist.Exceptions.TagCreatorThrowable;

public class DatabaseController {

    private SQLiteDatabase database;
    private MySQLiteHelper databaseHelper;

    public DatabaseController() {
        databaseHelper = new MySQLiteHelper();
        open();
    }

    private Tag cursorToTag(Cursor cursor) throws TagCreatorThrowable {
        Tag tag = new Tag();
        for(int i = 0; i < cursor.getColumnCount(); i++) {
            switch (cursor.getColumnName(i)) {
                case MySQLiteHelper.COLUMN_ID:
                    tag.setId(cursor.getLong(i));
                    break;
                case MySQLiteHelper.COLUMN_NAME:
                    tag.setName(cursor.getString(i));
                    break;
                case MySQLiteHelper.COLUMN_DONE:
                    tag.setDone(cursor.getInt(i) > 0);
                    break;
            }
        }
        return tag;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    /* generates the tag id */
    public long createTag (Tag tag) {
        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_NAME, tag.getName());
        values.put(MySQLiteHelper.COLUMN_DONE, tag.getDone());

       return database.insert(MySQLiteHelper.TABLE_TAG, null, values);
    }

    public void deleteTag (Long tagId) {
        database.delete(databaseHelper.TABLE_TAG,MySQLiteHelper.COLUMN_ID
                + "=" + tagId,null);
    }

    public void setTagDone(long id, Boolean done) {
        ContentValues values = new ContentValues();

        values.put(databaseHelper.COLUMN_DONE,done);

        database.update(databaseHelper.TABLE_TAG,values,
                databaseHelper.COLUMN_DONE + " = " + id,null);
    }

    public List<Tag> getTags() throws TagCreatorThrowable {
        List<Tag> tags = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_TAG,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tag tag = cursorToTag(cursor);
            tags.add(tag);
            cursor.moveToNext();
        }

        cursor.close();
        return tags;
    }
}
