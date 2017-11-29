package dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by animesh on 7/2/2017.
 */

public class HospitalHelper extends SQLiteOpenHelper {
    Context context;

    public HospitalHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HospitalConstant.TBL_DQUERY);
        Toast.makeText(context, "Doctor Table Created", Toast.LENGTH_LONG).show();
        db.execSQL(HospitalConstant.TBL_PQUERY);
        Toast.makeText(context, "Patient Table Created", Toast.LENGTH_LONG).show();
        db.execSQL(HospitalConstant.TBL_RQUERY);
        Toast.makeText(context, "Receptionist Table Created", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
