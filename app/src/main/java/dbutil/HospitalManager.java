package dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by animesh on 7/2/2017.
 */

public class HospitalManager {
    Context context;
    HospitalHelper sh;
    SQLiteDatabase sb;

    public HospitalManager(Context context){
        this.context = context;
        sh = new HospitalHelper(context, HospitalConstant.DB_NAME, null, HospitalConstant.DB_VERSION);
    }
    public SQLiteDatabase openDB(){
        sb = sh.getWritableDatabase();
        return sb;
    }

    public void closeDB(){
        sh.close();
    }


}
