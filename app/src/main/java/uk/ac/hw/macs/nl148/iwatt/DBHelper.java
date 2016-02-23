package uk.ac.hw.macs.nl148.iwatt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by mrnel on 05/02/2016.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final  String DATABASE_NAME = "newstudent.db";
    private static final  int DATABASE_VERSION = 1;
    private Dao<Student, Integer> studentDao = null;
    private Dao<LocalProgramme, Integer> programmeDao = null;
    private RuntimeExceptionDao<Student , Integer> runtimeExceptionStudentDao = null;
    private RuntimeExceptionDao<LocalProgramme , Integer> runtimeExceptionProgrammeDao = null;

    public DBHelper(Context context)
    {
        super(context , DATABASE_NAME ,null , DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource,LocalProgramme.class);
            TableUtils.createTable(connectionSource, Student.class);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Student.class,true);
            TableUtils.dropTable(connectionSource, LocalProgramme.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Student , Integer> getStudentDao() throws SQLException {
        if(studentDao == null){
            studentDao = getDao(Student.class);
        }
        return studentDao;
    }

    public Dao<LocalProgramme , Integer> getProgrammeDao() throws SQLException {
        if(programmeDao == null){


            programmeDao = getDao(LocalProgramme.class);
        }
        return programmeDao;
    }

    public RuntimeExceptionDao<Student, Integer> getStudentExceptionDao(){
        if(runtimeExceptionStudentDao == null)
        {
            runtimeExceptionStudentDao = getRuntimeExceptionDao(Student.class);
        }
        return  runtimeExceptionStudentDao;
    }

    public RuntimeExceptionDao<LocalProgramme, Integer> getProgrammeExceptionDao(){
        if(runtimeExceptionProgrammeDao == null)
        {
            runtimeExceptionProgrammeDao = getRuntimeExceptionDao(LocalProgramme.class);
        }
        return  runtimeExceptionProgrammeDao;
    }
}
