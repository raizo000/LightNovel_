package com.example.jenov.manga.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.binhnguyen.lightnovel.Model.LinkModel

/**
 * Created by jenov on 12/15/2017.
 */
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "testing_db.db", null, 1) {
    companion object {
        val TABLE_MANGA: String = "TABLE_MANGA"
        val ID: String = "ID_"
        val LINK: String = "LINK"
        val NGAYDANG: String = "MANGA"
    }

    val MANGA_DATABASE_CREATE =
            "CREATE TABLE if not exists " + TABLE_MANGA + " (" +
                    "${ID} integer PRIMARY KEY autoincrement," +
                    "${LINK} text," +
                    "${NGAYDANG} text" +
                    ")"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(MANGA_DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    //Here inserting student data.
    fun insertMangatData(link: String, manga: String): Long {
        val values = ContentValues()
        values.put(LINK, link)
        values.put(NGAYDANG, manga)
        return getWritableDatabase().insert(TABLE_MANGA, null, values)
    }

    fun getAllData(): MutableList<LinkModel> {
        val stuList: MutableList<LinkModel> = mutableListOf<LinkModel>()
        val cursor: Cursor = getReadableDatabase().query(TABLE_MANGA, arrayOf(ID, LINK, NGAYDANG), null, null, null, null, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        val name: String = cursor.getString(cursor.getColumnIndex(LINK))
                        val age: String = cursor.getString(cursor.getColumnIndex(NGAYDANG))
                        stuList.add(LinkModel(name, age))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return stuList
    }

    //Getting student/students data using where clause
    fun getParticularMangaData(name: String): MutableList<LinkModel> {
        val MangaList: MutableList<LinkModel> = mutableListOf<LinkModel>()
        val db = this.readableDatabase
        val selectQuery = "SELECT DISTINCT link FROM $TABLE_MANGA WHERE $LINK = '$name'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.count != 0) {
                cursor.moveToFirst()
                if (cursor.count > 0) {
                    do {
                        val link: String = cursor.getString(cursor.getColumnIndex(LINK))
                        val manga: String = ""//cursor.getString(cursor.getColumnIndex(NGAYDANG))
                        MangaList.add(LinkModel(link, manga))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }

        return MangaList
    }

}