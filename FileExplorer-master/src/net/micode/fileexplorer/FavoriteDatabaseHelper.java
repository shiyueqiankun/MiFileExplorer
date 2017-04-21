/*
 * Copyright (c) 2010-2011, The MiCode Open Source Community (www.micode.net)
 *
 * This file is part of FileExplorer.
 *
 * FileExplorer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FileExplorer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SwiFTP.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.micode.fileexplorer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * �洢favorite���ݣ������ݿ� SQLiteOpenHelper��һ�������������ݿ�Ͱ汾�Ĺ����ࡣ
 * ͨ���̳в����ط���������ʵ���������Լ���Favorite���CRUD��
 * ��ô�о���FileOperationHelper���ƣ���Ȼ��CRUD��ֻ����1�������ݿ��еģ�1�����ļ��ġ�
 */
public class FavoriteDatabaseHelper extends SQLiteOpenHelper {
	// ����6���ֶ������ݿ�����ֺͰ汾�š�������ֺ�3���ֶ�
	private final static String DATABASE_NAME = "file_explorer";

	private final static int DATABASE_VERSION = 1;

	private final static String TABLE_NAME = "favorite";

	public final static String FIELD_ID = "_id";

	public final static String FIELD_TITLE = "title";

	public final static String FIELD_LOCATION = "location";

	private boolean firstCreate;
	// ���ݿ�仯��ʱ�򣬻�֪ͨ����������
	private FavoriteDatabaseListener mListener;

	private static FavoriteDatabaseHelper instance;

	public interface FavoriteDatabaseListener {
		void onFavoriteDatabaseChanged();
	}

	// ������췽��������ľ�̬���ʵ���ķ�������̫��г��~
	// էһ������Ϊ�ǵ���ģʽ�أ�ʵ����~
	public FavoriteDatabaseHelper(Context context,
			FavoriteDatabaseListener listener) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		instance = this;
		mListener = listener;
	}

	// ����ط��о�ֻ�Ƿ���洢��һ�����ʵ���������ܱ�֤�����ֻ��1��ʵ��
	public static FavoriteDatabaseHelper getInstance() {
		return instance;
	}

	// ���ݿⴴ����1��sql
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "Create table " + TABLE_NAME + "(" + FIELD_ID
				+ " integer primary key autoincrement," + FIELD_TITLE
				+ " text, " + FIELD_LOCATION + " text );";
		db.execSQL(sql);
		firstCreate = true;
	}

	// ������ʱ��ֱ��ɾ����ǰ�����ݿ⣬������ڵĻ�
	// �汾�ţ�û���ϰ�
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = " DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}

	// �Ƿ�Ϊ��1�δ���
	public boolean isFirstCreate() {
		return firstCreate;
	}

	// �ж�1���ļ�·���Ƿ��Ѿ����ڣ�����˵�Ƿ���Favorite�ļ�
	public boolean isFavorite(String path) {
		String selection = FIELD_LOCATION + "=?";
		String[] selectionArgs = new String[] { path };
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs,
				null, null, null);
		if (cursor == null)
			return false;
		boolean ret = cursor.getCount() > 0;
		cursor.close();
		return ret;
	}

	// ���Favorite����α�
	public Cursor query() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query(TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}

	// ����һ����¼
	public long insert(String title, String location) {
		if (isFavorite(location))
			return -1;

		SQLiteDatabase db = this.getWritableDatabase();
		long ret = db.insert(TABLE_NAME, null, createValues(title, location));
		mListener.onFavoriteDatabaseChanged();
		return ret;
	}
	// ����id��ɾ��һ����¼�������Ҫ��Ȼ��֪ͨ��ؼ�����
	public void delete(long id, boolean notify) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = FIELD_ID + "=?";
		String[] whereValue = { Long.toString(id) };
		db.delete(TABLE_NAME, where, whereValue);

		if (notify)
			mListener.onFavoriteDatabaseChanged();
	}

	// ����λ��ɾ��1����¼��һ��֪ͨ��ؼ�����
	public void delete(String location) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = FIELD_LOCATION + "=?";
		String[] whereValue = { location };
		db.delete(TABLE_NAME, where, whereValue);
		mListener.onFavoriteDatabaseChanged();
	}

	//����1����¼ 
	public void update(int id, String title, String location) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = FIELD_ID + "=?";
		String[] whereValue = { Integer.toString(id) };
		db.update(TABLE_NAME, createValues(title, location), where, whereValue);
		mListener.onFavoriteDatabaseChanged();
	}

	private ContentValues createValues(String title, String location) {
		ContentValues cv = new ContentValues();
		cv.put(FIELD_TITLE, title);
		cv.put(FIELD_LOCATION, location);
		return cv;
	}
}
