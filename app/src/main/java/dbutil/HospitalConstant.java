package dbutil;

/**
 * Created by animesh on 7/1/2017.
 */

public class HospitalConstant {
    public static final String DB_NAME = "Hospital";
    public static final int DB_VERSION = 1;

    public static final String TBL_DNAME = "doctor";
    public static final String COL_DID = "did";
    public static final String COL_DNAME = "name";
    public static final String COL_DADD = "address";
    public static final String COL_DPHONE = "phone";
    public static final String COL_DEMAIL = "email";
    public static final String COL_DSPECS = "specification";
    public static final String COL_DGENDER = "gender";
    public static final String COL_DAGE = "age";
    public static final String COL_DMARITAL = "marital_status";
    public static final String COL_DQUALIFICATION = "qualification";
    public static final String COL_DAVAIL = "Availability";

    public static final String TBL_DQUERY
            = "create table "+TBL_DNAME+"("+COL_DID+" integer primary key, "
            +COL_DNAME+" text, "
            +COL_DPHONE+" integer, "
            +COL_DAGE+" integer, "
            +COL_DGENDER+" text, "
            +COL_DMARITAL+" text, "
            +COL_DEMAIL+" text, "
            +COL_DADD+" text, "
            +COL_DQUALIFICATION+" text, "
            +COL_DSPECS+" text, "
            +COL_DAVAIL+" text) ";

    public static final String TBL_PNAME = "patient";
    public static final String COL_PID = "pid";
    public static final String COL_PNAME = "name";
    public static final String COL_PADD = "address";
    public static final String COL_PPHONE = "phone";
    public static final String COL_PEMAIL = "email";
    public static final String COL_PDOB = "dateofBirth";
    public static final String COL_PGENDER = "gender";
    public static final String COL_PAGE = "age";
    public static final String COL_PMARITAL = "maritalstatus";
    public static final String COL_PADHAR  = "adharnum";
    public static final String COL_PDOCNAME = "Doctorname";

    public static final String TBL_PQUERY
            = "create table "+TBL_PNAME+"("+COL_PID+" integer primary key, "
            +COL_PNAME+" text, "
            +COL_DPHONE+" integer, "
            +COL_PDOB+" text, "
            +COL_PAGE+" integer, "
            +COL_PGENDER+" text, "
            +COL_PMARITAL+" text, "
            +COL_PEMAIL+" text, "
            +COL_PADD+" text, "
            +COL_PADHAR+" integer, "
            +COL_PDOCNAME+" text) ";


    public static final String TBL_RNAME = "receptionist";
    public static final String COL_RID = "rid";
    public static final String COL_RNAME = "name";
    public static final String COL_RSHIFT = "shift";
    public static final String COL_RPHONE = "phone";
    public static final String COL_RPASS = "password";

    public static final String TBL_RQUERY
            = "create table "+TBL_RNAME+"("+COL_RID+" integer primary key, "
            +COL_RPASS+" text, "
            +COL_RNAME+" text, "
            +COL_RSHIFT+" text, "
            +COL_RPHONE+" integer) ";
}
