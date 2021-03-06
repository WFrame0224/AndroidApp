package com.example.dictprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DictProvider extends ContentProvider {

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORDS = 1;
    private static final int WORD = 2;
    private MyDatabaseHelper dbOpenHelper;

    static {
        // 为UriMatcher注册两个Uri
        matcher.addURI(Words.AUTHORITY, "words", WORDS);
        matcher.addURI(Words.AUTHORITY, "word/#", WORD);
    }

    // 第一次调用该DictProvider时，系统先创建DictProvider对象，并回调该方法
    @Override
    public boolean onCreate() {
        dbOpenHelper = new MyDatabaseHelper(this.getContext(), "myDict.db3", 1);
        return true;
    }

    // 返回指定Uri参数对应的数据的MIME类型
    @Override
    public String getType(Uri uri) {
        switch (matcher.match(uri)) {
            // 如果操作的数据是多项记录
            case WORDS:
                return "vnd.android.cursor.dir/com.frame.dict";
            case WORD:
                return "vnd.android.cursor.item/com.frame.dict";
            default:
                throw new IllegalArgumentException("未知Uri" + uri);
        }
    }

    // 查询数据的方法
    @Override
    public Cursor query(Uri uri, String[] projection, String where, String[] whereArgs, String sortOrder) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (matcher.match(uri)) {
            // 如果Uri参数代表操作全部数据项
            case WORDS:
                // 执行查询
                return db.query("dict", projection, where,
                        whereArgs, null, null, sortOrder);
            // 如果Uri参数代表操作指定数据项
            case WORD:
                // 解析出想查询的记录ID
                long id = ContentUris.parseId(uri);
                String whereClause = Words.Word._ID + "=" + id;
                // 如果原来的where子句存在，拼接where子句
                if (where != null && !"".equals(where)) {
                    whereClause = whereClause + "and" + where;
                }
                return db.query("dict", projection, whereClause,
                        whereArgs, null, null, sortOrder);
        }

        return null;
    }

    // 插入数据方法
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        // 获取数据库实例
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (matcher.match(uri)) {
            // 如果Uri参数代表操作全部数据项
            case WORDS:
                // 插入数据，返回插入记录的ID
                long rowId = db.insert("dict", Words.Word._ID, contentValues);
                // 如果插入成功返回Uri
                if (rowId > 0) {
                    // 在已有的Uri的后面追加ID
                    Uri wordUri = ContentUris.withAppendedId(uri, rowId);
                    // 通知数据已经改变
                    getContext().getContentResolver().notifyChange(wordUri, null);
                    return wordUri;
                }
                break;
            default:
                throw new IllegalArgumentException("未知Uri:" + uri);
        }
        return null;
    }

    // 删除数据的方法
    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        // 记录删除的记录数
        int num = 0;
        // 对uri进行匹配
        switch (matcher.match(uri)) {
            // 如果Uri参数代表操作的全部数据项
            case WORDS:
                num = db.delete("dict", where, whereArgs);
                break;
            // 如果Uri参数代表操作指定数据项
            case WORD:
                // 解析出所需要删除的记录ID
                long id = ContentUris.parseId(uri);
                String whereClause = Words.Word._ID + "=" + id;
                // 如果原来的where子句存在,拼接where子句
                if (where != null && !where.equals("")){
                    whereClause = whereClause + "and" + where;
                }
                num = db.delete("dict",whereClause,whereArgs);
                break;
                default:
                    throw new IllegalArgumentException("未知Uri:" + uri);
        }
        // 通知数据已经改变
        getContext().getContentResolver().notifyChange(uri,null);
        return num;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String where, String[] whereArgs) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        // 记录所修改的记录数
        int num = 0;
        switch (matcher.match(uri)){
            // 如果Uri参数代表操作全部数据项
            case WORDS:
                num = db.update("dict",contentValues,where,whereArgs);
                break;
                // 如果Uri参数代表操作全部数据项
            case WORD:
                // 解析出想改变的记录ID
                long id = ContentUris.parseId(uri);
                String whereClause = Words.Word._ID + "=" + id;
                // 如果原来的where子句存在，拼接where子句
                if (where != null && !where.equals(""))
                {
                    whereClause = whereClause + " and " + where;
                }
                num = db.update("dict", contentValues, whereClause, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("未知Uri:" + uri);
        }
        // 通知数据已经改变
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }
}
