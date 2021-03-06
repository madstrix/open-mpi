package mpi;


class Constant {

   protected int THREAD_SINGLE;
   protected int THREAD_FUNNELED;
   protected int THREAD_SERIALIZED;
   protected int THREAD_MULTIPLE;
   protected int GRAPH;
   protected int DIST_GRAPH;
   protected int CART;
   protected int ANY_SOURCE;
   protected int ANY_TAG;
   protected int PROC_NULL;
   protected int UNDEFINED;
   protected int IDENT;
   protected int CONGRUENT;
   protected int SIMILAR;
   protected int UNEQUAL;
   protected int TAG_UB;
   protected int HOST;
   protected int IO;
   protected int WTIME_IS_GLOBAL;
   protected int APPNUM;
   protected int LASTUSEDCODE;
   protected int UNIVERSE_SIZE;
   protected int WIN_BASE;
   protected int WIN_SIZE;
   protected int WIN_DISP_UNIT;
   protected int VERSION;
   protected int SUBVERSION;
   protected int ROOT;
   protected int KEYVAL_INVALID;
   protected int BSEND_OVERHEAD;
   protected int MAX_OBJECT_NAME;
   protected int MAX_PORT_NAME;
   protected int MAX_DATAREP_STRING;
   protected int MAX_INFO_KEY;
   protected int MAX_INFO_VAL;
   protected int ORDER_C;
   protected int ORDER_FORTRAN;
   protected int DISTRIBUTE_BLOCK;
   protected int DISTRIBUTE_CYCLIC;
   protected int DISTRIBUTE_NONE;
   protected int DISTRIBUTE_DFLT_DARG;
   protected int MODE_CREATE;
   protected int MODE_RDONLY;
   protected int MODE_WRONLY;
   protected int MODE_RDWR;
   protected int MODE_DELETE_ON_CLOSE;
   protected int MODE_UNIQUE_OPEN;
   protected int MODE_EXCL;
   protected int MODE_APPEND;
   protected int MODE_SEQUENTIAL;
   protected int DISPLACEMENT_CURRENT;
   protected int SEEK_SET;
   protected int SEEK_CUR;
   protected int SEEK_END;
   protected int MODE_NOCHECK;
   protected int MODE_NOPRECEDE;
   protected int MODE_NOPUT;
   protected int MODE_NOSTORE;
   protected int MODE_NOSUCCEED;
   protected int LOCK_EXCLUSIVE;
   protected int LOCK_SHARED;
   protected int SUCCESS;
   protected int ERR_BUFFER;
   protected int ERR_COUNT;
   protected int ERR_TYPE;
   protected int ERR_TAG;
   protected int ERR_COMM;
   protected int ERR_RANK;
   protected int ERR_REQUEST;
   protected int ERR_ROOT;
   protected int ERR_GROUP;
   protected int ERR_OP;
   protected int ERR_TOPOLOGY;
   protected int ERR_DIMS;
   protected int ERR_ARG;
   protected int ERR_UNKNOWN;
   protected int ERR_TRUNCATE;
   protected int ERR_OTHER;
   protected int ERR_INTERN;
   protected int ERR_IN_STATUS;
   protected int ERR_PENDING;
   protected int ERR_ACCESS;
   protected int ERR_AMODE;
   protected int ERR_ASSERT;
   protected int ERR_BAD_FILE;
   protected int ERR_BASE;
   protected int ERR_CONVERSION;
   protected int ERR_DISP;
   protected int ERR_DUP_DATAREP;
   protected int ERR_FILE_EXISTS;
   protected int ERR_FILE_IN_USE;
   protected int ERR_FILE;
   protected int ERR_INFO_KEY;
   protected int ERR_INFO_NOKEY;
   protected int ERR_INFO_VALUE;
   protected int ERR_INFO;
   protected int ERR_IO;
   protected int ERR_KEYVAL;
   protected int ERR_LOCKTYPE;
   protected int ERR_NAME;
   protected int ERR_NO_MEM;
   protected int ERR_NOT_SAME;
   protected int ERR_NO_SPACE;
   protected int ERR_NO_SUCH_FILE;
   protected int ERR_PORT;
   protected int ERR_QUOTA;
   protected int ERR_READ_ONLY;
   protected int ERR_RMA_CONFLICT;
   protected int ERR_RMA_SYNC;
   protected int ERR_SERVICE;
   protected int ERR_SIZE;
   protected int ERR_SPAWN;
   protected int ERR_UNSUPPORTED_DATAREP;
   protected int ERR_UNSUPPORTED_OPERATION;
   protected int ERR_WIN;
   protected int ERR_LASTCODE;
   protected int ERR_SYSRESOURCE;


   protected Constant() {
      this.setConstant();
   }

   private native void setConstant();
}
