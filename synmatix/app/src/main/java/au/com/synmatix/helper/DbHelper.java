package au.com.synmatix.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mahnoor on 06-Jan-18.
 */

public class DbHelper extends SQLiteOpenHelper {
    private Context mycontext;

    //data base path
    private String DB_PATH = "/data/data/au.com.synmatix/databases/synmatix_db .db";
    // batabase name
    private static String DB_NAME = "synmatix_db .db";
    // database tabels
    private static String TB_home = "about_us";
    private static String TB_synmatix = "why_synmatix";
    private static String TB_services = "services";
    private static String TB_sDetail = "services_detail";
    private static String TB_subpackages = "sub_packages";
    private static String sub_pakgdetail = "subpackage_detail";








    public SQLiteDatabase myDataBase;


    public DbHelper(Context context) throws IOException {
        super(context, DB_NAME, null, 1);
        this.mycontext = context;
    }

    // to create database
    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if (dbexist) {
            System.out.println(" Database exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    // check database
    private boolean checkdatabase() {
        //SQLiteDatabase checkdb = null;
        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE) != null;
            checkdb = dbfile.exists();
        } catch (SQLiteException e) {
            System.out.println("Database doesn't exist");
        }

        return checkdb;
    }

    private void copydatabase() throws IOException {

        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer)) > 0) {
            myoutput.write(buffer, 0, length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();

    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READONLY);


    }

    // close data base
    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // This will return a cursor containing database records of category
    public Cursor about_us() {

        Cursor c;
        c = myDataBase.query(TB_home, null, null, null, null, null, null);
        return c;
    }
    public Cursor yUs() {

        Cursor c;
        c = myDataBase.query(TB_synmatix, null, null, null, null, null, null);
        return c;
    }
    public Cursor services() {

        Cursor c;
        c = myDataBase.query(TB_services, null, null, null, null, null, null);
        return c;
    }
    public Cursor services_detail(String id) {

        Cursor c2;
        String query = "SELECT * FROM " + TB_sDetail + " WHERE s_id=" + id + "";
        c2 = myDataBase.rawQuery(query, null);
        return c2;
    }
    public Cursor packages() {

        Cursor c2;
        String query = "SELECT * FROM " + TB_subpackages + " WHERE pkg_id=" + 2 + "";
        c2 = myDataBase.rawQuery(query, null);
        return c2;}

    public Cursor designs() {

        Cursor c2;
        String query = "SELECT * FROM " + TB_subpackages + " WHERE pkg_id=" + 3 + "";
        c2 = myDataBase.rawQuery(query, null);
        return c2;}
    public Cursor Mobile() {

        Cursor c2;
        String query = "SELECT * FROM " + TB_subpackages + " WHERE pkg_id=" + 4 + "";
        c2 = myDataBase.rawQuery(query, null);
        return c2;}
    public Cursor webservices() {

        Cursor c2;
        String query = "SELECT * FROM " + TB_subpackages + " WHERE pkg_id=" + 1 + "";
        c2 = myDataBase.rawQuery(query, null);
        return c2;}


    public Cursor sub_pakgDetail(String id) {

        Cursor c2;
        String query = "SELECT * FROM " + sub_pakgdetail + " WHERE subpkg_id=" + id + "";
        c2 = myDataBase.rawQuery(query, null);
        return c2;
    }
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
    }
}
