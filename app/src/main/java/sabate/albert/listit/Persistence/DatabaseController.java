package sabate.albert.listit.Persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sabate.albert.listit.Domain.ListObject;
import sabate.albert.listit.Exceptions.ListObjectCreatorThrowable;

public class DatabaseController {

    private SQLiteDatabase database;
    private MySQLiteHelper databaseHelper;

    public DatabaseController() {
        databaseHelper = new MySQLiteHelper();
        open();
    }

    private ListObject cursorToListObject(Cursor cursor) throws ListObjectCreatorThrowable {
        ListObject listObject = new ListObject();
        for(int i = 0; i < cursor.getColumnCount(); i++) {
            switch (cursor.getColumnName(i)) {
                case MySQLiteHelper.COLUMN_ID:
                    listObject.setId(cursor.getLong(i));
                    break;
                case MySQLiteHelper.COLUMN_NAME:
                    listObject.setName(cursor.getString(i));
                    break;
                case MySQLiteHelper.COLUMN_DONE:
                    listObject.setDone(cursor.getInt(i) > 0);
                    break;
            }
        }
        return listObject;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    /* generates the listObject id */
    public long createListObject(ListObject listObject) {
        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_NAME, listObject.getName());
        values.put(MySQLiteHelper.COLUMN_DONE, listObject.getDone());

       return database.insert(MySQLiteHelper.TABLE_LIST_OBJECTS, null, values);
    }

    public void deleteListObject(Long listObjectId) {
        database.delete(databaseHelper.TABLE_LIST_OBJECTS,MySQLiteHelper.COLUMN_ID
                + "=" + listObjectId,null);
    }

    public void setListObjectDone(long id, Boolean done) {
        ContentValues values = new ContentValues();

        values.put(databaseHelper.COLUMN_DONE,done);

        database.update(databaseHelper.TABLE_LIST_OBJECTS,values,
                databaseHelper.COLUMN_ID + " = " + id,null);
    }

    public List<ListObject> getListObjects() throws ListObjectCreatorThrowable {
        List<ListObject> listObjects = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_LIST_OBJECTS,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ListObject listObject = cursorToListObject(cursor);
            listObjects.add(listObject);
            cursor.moveToNext();
        }

        cursor.close();
        return listObjects;
    }
}
