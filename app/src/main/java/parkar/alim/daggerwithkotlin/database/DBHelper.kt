package parkar.alim.daggerwithkotlin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import parkar.alim.daggerwithkotlin.annotations.ApplicationContext
import parkar.alim.daggerwithkotlin.annotations.DatabaseInfo
import java.sql.SQLException
import javax.inject.Inject
import javax.inject.Singleton
import android.content.ContentValues
import parkar.alim.daggerwithkotlin.models.User
import android.database.Cursor


@Singleton
class DBHelper : SQLiteOpenHelper {

    @Inject
    constructor(@ApplicationContext context: Context, @DatabaseInfo dbName: String, @DatabaseInfo version: Int) : super(context, dbName, null, version) {

    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersionCode: Int, newVersionCode: Int) {
        database.execSQL("DROP TABLE IF EXISTS " + UserContract.TABLE_NAME);
        createTable(database);
    }

    override fun onCreate(database: SQLiteDatabase) {
        createTable(database);
    }

    fun createTable(db: SQLiteDatabase) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + UserContract.TABLE_NAME + "("
                            + UserContract.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + UserContract.COLUMN_USER_NAME + " VARCHAR(20), "
                            + UserContract.COLUMN_USER_ADDRESS + " VARCHAR(50), "
                            + UserContract.COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT NOW(),"
                            + UserContract.COLUMN_USER_UPDATED_AT + " VARCHAR(10) DEFAULT NOW()" + ")"
            )

        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    public fun getUser(userId: Long): User? {
        var cursor: Cursor? = null
        try {
            val db = this.readableDatabase
            db.query(UserContract.TABLE_NAME, null, UserContract.COLUMN_USER_ID + " = ?", arrayOf(userId.toString()), null, null, null)
            cursor = db.query(UserContract.TABLE_NAME, null, UserContract.COLUMN_USER_ID + " = ?", arrayOf(userId.toString()), null, null, null)

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst()
                val user = User()
                user.id = cursor.getLong(cursor.getColumnIndex(UserContract.COLUMN_USER_ID))
                user.name = cursor.getString(cursor.getColumnIndex(UserContract.COLUMN_USER_NAME))
                user.address = cursor.getString(cursor.getColumnIndex(UserContract.COLUMN_USER_ADDRESS))
                user.createdAt = cursor.getString(cursor.getColumnIndex(UserContract.COLUMN_USER_CREATED_AT))
                user.updatedAt = cursor.getString(cursor.getColumnIndex(UserContract.COLUMN_USER_UPDATED_AT))
                return user
            } else {
                return null
            }
        } catch (e : Exception) {
            e.printStackTrace()
            return null;
        } finally {
            if (cursor != null)
                cursor.close()
        }
    }

    @Throws(Exception::class)
    public fun insertUser(user: User): Long? {
        try {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(UserContract.COLUMN_USER_NAME, user.name)
            contentValues.put(UserContract.COLUMN_USER_ADDRESS, user.address)
            return db.insert(UserContract.TABLE_NAME, null, contentValues)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

    }

    object UserContract {
        val TABLE_NAME = "user";
        val COLUMN_USER_ID = "user_id";
        val COLUMN_USER_NAME = "name";
        val COLUMN_USER_ADDRESS = "address";
        val COLUMN_USER_CREATED_AT = "created_at";
        val COLUMN_USER_UPDATED_AT = "updated_at";
    }
}