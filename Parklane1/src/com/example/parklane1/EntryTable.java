package com.example.parklane1;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryTable {

	MGbPAHelper helper;

	public EntryTable(Context context) {
		helper = new MGbPAHelper(context);

	}
	/*public int update(String vrn,String slot)
	{
		// update PRK SET PkSlot="xyz" where VRN="abc";
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(MGbPAHelper.PkSlot, slot);
		String[] whereArgs={vrn};
		int count=db.update(MGbPAHelper.TABLE_NAME, cv, MGbPAHelper.VRN+" =? ", whereArgs);
		
		return count;
	}*/
	public int deleteRow(String x)
	{
		
		//delete *from PRK where VRN='xyz';
		SQLiteDatabase db = helper.getWritableDatabase();
		String[] whereArgs={x};
		int count=db.delete(MGbPAHelper.TABLE_NAME,  MGbPAHelper.VRN+" =? ", whereArgs);
		
		return count;
	}

	public long insertData(String vrn, String ip) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(MGbPAHelper.VRN, vrn);
		cv.put(MGbPAHelper.IP_Address, ip);
		//cv.put(MGbPAHelper.PkSlot, pslot);
		long id = db.insert(MGbPAHelper.TABLE_NAME, null, cv);
		return id;
	}

	/*public String getAllData() {
		SQLiteDatabase db = helper.getWritableDatabase();
		String[] col = { MGbPAHelper.VRN, MGbPAHelper.IP_Address,
				MGbPAHelper.PkSlot, MGbPAHelper.Time };
		Cursor c = db.query(MGbPAHelper.TABLE_NAME, col, null, null, null,
				null, null);
		StringBuffer buff=new StringBuffer();
		while(c.moveToNext())
		{
			
			String v=c.getString(0);
			String i=c.getString(1);
			String p=c.getString(2);
			String t=c.getString(3);
			buff.append(v+" "+i+" "+p+" "+t);
		}

		return buff.toString();
	}*/
	/*public String getSlotData(String vrn)
	{
		//select PkSlot from PRK where VRN='xyz';
		SQLiteDatabase db = helper.getWritableDatabase();
		
		String[] col = {MGbPAHelper.PkSlot};
		
		Cursor c = db.query(MGbPAHelper.TABLE_NAME, col, MGbPAHelper.VRN+" = '"+vrn+"'", null, null,
				null, null);
		
		StringBuffer buff=new StringBuffer();
		while(c.moveToNext())
		{
			
			int colno=c.getColumnIndex(MGbPAHelper.PkSlot);
			String p=c.getString(colno);
			buff.append(p);
		}

		return buff.toString();
		
	}
*/
	static class MGbPAHelper extends SQLiteOpenHelper {

		private static final String DATABASE_NAME = "SMJ";
		private static final String TABLE_NAME = "Entry";
		private static final int DATABASE_VERSION = 1;
		// private static final String PID = "_id";
		private static final String VRN = "VRN";
		private static final String IP_Address = "IP";
		//private static final String PkSlot = "SLOT";
		private static final String Time = "CREATEDAT";
		private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
				+ "(" + VRN + " VARCHAR(255) NOT NULL," + IP_Address
				+ " VARCHAR(255)  NOT NULL,"
				+ Time + " DATETIME DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY("
				+ VRN + "," + IP_Address + "));";
		private static final String DROP_TABLE = "DROP TABLE IF EXISTS "
				+ TABLE_NAME + ";";
		private Context context;

		public MGbPAHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			this.context = context;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

			try {
				db.execSQL(CREATE_TABLE);
				Debugg.msg(context, "Table Created");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Debugg.msg(context, "" + e);
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(DROP_TABLE);
				Debugg.msg(context, "Table Updated");
				onCreate(db);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Debugg.msg(context, "" + e);
			}

		}

	}
}