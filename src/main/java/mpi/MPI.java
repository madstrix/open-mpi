package mpi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import mpi.Constant;
import mpi.Datatype;
import mpi.DoubleInt;
import mpi.Errhandler;
import mpi.FloatInt;
import mpi.Group;
import mpi.Info;
import mpi.Int2;
import mpi.Intracomm;
import mpi.LongInt;
import mpi.MPIException;
import mpi.Op;
import mpi.Request;
import mpi.ShortInt;
import mpi.Version;

public final class MPI {

   private static boolean initialized;
   private static boolean finalized;
   private static byte[] buffer = null;
   private static final int MAX_PROCESSOR_NAME = 256;
   private static final ByteOrder nativeOrder = ByteOrder.nativeOrder();
   public static final Intracomm COMM_WORLD;
   public static final Intracomm COMM_SELF;
   public static final int THREAD_SINGLE;
   public static final int THREAD_FUNNELED;
   public static final int THREAD_SERIALIZED;
   public static final int THREAD_MULTIPLE;
   public static final int GRAPH;
   public static final int DIST_GRAPH;
   public static final int CART;
   public static final int ANY_SOURCE;
   public static final int ANY_TAG;
   public static final Op MAX;
   public static final Op MIN;
   public static final Op SUM;
   public static final Op PROD;
   public static final Op LAND;
   public static final Op BAND;
   public static final Op LOR;
   public static final Op BOR;
   public static final Op LXOR;
   public static final Op BXOR;
   public static final Op REPLACE;
   public static final Op NO_OP;
   public static final Op MINLOC;
   public static final Op MAXLOC;
   public static final Datatype DATATYPE_NULL;
   public static final Datatype BYTE;
   public static final Datatype CHAR;
   public static final Datatype SHORT;
   public static final Datatype BOOLEAN;
   public static final Datatype INT;
   public static final Datatype LONG;
   public static final Datatype FLOAT;
   public static final Datatype DOUBLE;
   public static final Datatype PACKED;
   public static final Datatype FLOAT_COMPLEX;
   public static final Datatype DOUBLE_COMPLEX;
   public static final Datatype INT2;
   public static final Datatype SHORT_INT;
   public static final Datatype LONG_INT;
   public static final Datatype FLOAT_INT;
   public static final Datatype DOUBLE_INT;
   public static final Int2 int2;
   public static final ShortInt shortInt;
   public static final LongInt longInt;
   public static final FloatInt floatInt;
   public static final DoubleInt doubleInt;
   public static final Request REQUEST_NULL;
   public static final Group GROUP_EMPTY;
   public static final Info INFO_ENV;
   public static final Info INFO_NULL;
   public static final int PROC_NULL;
   public static final int UNDEFINED;
   public static final int IDENT;
   public static final int CONGRUENT;
   public static final int SIMILAR;
   public static final int UNEQUAL;
   public static final int TAG_UB;
   public static final int HOST;
   public static final int IO;
   public static final int WTIME_IS_GLOBAL;
   public static final int APPNUM;
   public static final int LASTUSEDCODE;
   public static final int UNIVERSE_SIZE;
   public static final int WIN_BASE;
   public static final int WIN_SIZE;
   public static final int WIN_DISP_UNIT;
   public static final int VERSION;
   public static final int SUBVERSION;
   public static final int ROOT;
   public static final int KEYVAL_INVALID;
   public static final int BSEND_OVERHEAD;
   public static final int MAX_OBJECT_NAME;
   public static final int MAX_PORT_NAME;
   public static final int MAX_DATAREP_STRING;
   public static final int MAX_INFO_KEY;
   public static final int MAX_INFO_VAL;
   public static final int ORDER_C;
   public static final int ORDER_FORTRAN;
   public static final int DISTRIBUTE_BLOCK;
   public static final int DISTRIBUTE_CYCLIC;
   public static final int DISTRIBUTE_NONE;
   public static final int DISTRIBUTE_DFLT_DARG;
   public static final int MODE_CREATE;
   public static final int MODE_RDONLY;
   public static final int MODE_WRONLY;
   public static final int MODE_RDWR;
   public static final int MODE_DELETE_ON_CLOSE;
   public static final int MODE_UNIQUE_OPEN;
   public static final int MODE_EXCL;
   public static final int MODE_APPEND;
   public static final int MODE_SEQUENTIAL;
   public static final int DISPLACEMENT_CURRENT;
   public static final int SEEK_SET;
   public static final int SEEK_CUR;
   public static final int SEEK_END;
   public static final int MODE_NOCHECK;
   public static final int MODE_NOPRECEDE;
   public static final int MODE_NOPUT;
   public static final int MODE_NOSTORE;
   public static final int MODE_NOSUCCEED;
   public static final int LOCK_EXCLUSIVE;
   public static final int LOCK_SHARED;
   public static final Errhandler ERRORS_ARE_FATAL;
   public static final Errhandler ERRORS_RETURN;
   public static final int SUCCESS;
   public static final int ERR_BUFFER;
   public static final int ERR_COUNT;
   public static final int ERR_TYPE;
   public static final int ERR_TAG;
   public static final int ERR_COMM;
   public static final int ERR_RANK;
   public static final int ERR_REQUEST;
   public static final int ERR_ROOT;
   public static final int ERR_GROUP;
   public static final int ERR_OP;
   public static final int ERR_TOPOLOGY;
   public static final int ERR_DIMS;
   public static final int ERR_ARG;
   public static final int ERR_UNKNOWN;
   public static final int ERR_TRUNCATE;
   public static final int ERR_OTHER;
   public static final int ERR_INTERN;
   public static final int ERR_IN_STATUS;
   public static final int ERR_PENDING;
   public static final int ERR_ACCESS;
   public static final int ERR_AMODE;
   public static final int ERR_ASSERT;
   public static final int ERR_BAD_FILE;
   public static final int ERR_BASE;
   public static final int ERR_CONVERSION;
   public static final int ERR_DISP;
   public static final int ERR_DUP_DATAREP;
   public static final int ERR_FILE_EXISTS;
   public static final int ERR_FILE_IN_USE;
   public static final int ERR_FILE;
   public static final int ERR_INFO_KEY;
   public static final int ERR_INFO_NOKEY;
   public static final int ERR_INFO_VALUE;
   public static final int ERR_INFO;
   public static final int ERR_IO;
   public static final int ERR_KEYVAL;
   public static final int ERR_LOCKTYPE;
   public static final int ERR_NAME;
   public static final int ERR_NO_MEM;
   public static final int ERR_NOT_SAME;
   public static final int ERR_NO_SPACE;
   public static final int ERR_NO_SUCH_FILE;
   public static final int ERR_PORT;
   public static final int ERR_QUOTA;
   public static final int ERR_READ_ONLY;
   public static final int ERR_RMA_CONFLICT;
   public static final int ERR_RMA_SYNC;
   public static final int ERR_SERVICE;
   public static final int ERR_SIZE;
   public static final int ERR_SPAWN;
   public static final int ERR_UNSUPPORTED_DATAREP;
   public static final int ERR_UNSUPPORTED_OPERATION;
   public static final int ERR_WIN;
   public static final int ERR_LASTCODE;
   public static final int ERR_SYSRESOURCE;


   private static native Int2 newInt2();

   private static native ShortInt newShortInt();

   private static native LongInt newLongInt();

   private static native FloatInt newFloatInt();

   private static native DoubleInt newDoubleInt();

   private static native void initVersion();

   private static void initCommon() throws MPIException {
      initialized = true;
      DATATYPE_NULL.setBasic(0);
      BYTE.setBasic(1);
      CHAR.setBasic(2);
      SHORT.setBasic(3);
      BOOLEAN.setBasic(4);
      INT.setBasic(5);
      LONG.setBasic(6);
      FLOAT.setBasic(7);
      DOUBLE.setBasic(8);
      PACKED.setBasic(9);
      INT2.setBasic(10, BYTE);
      SHORT_INT.setBasic(11, BYTE);
      LONG_INT.setBasic(12, BYTE);
      FLOAT_INT.setBasic(13, BYTE);
      DOUBLE_INT.setBasic(14, BYTE);
      FLOAT_COMPLEX.setBasic(15, FLOAT);
      DOUBLE_COMPLEX.setBasic(16, DOUBLE);
      COMM_WORLD.setType(2);
      COMM_SELF.setType(1);
   }

   public static String[] Init(String[] var0) throws MPIException {
      if(initialized) {
         throw new MPIException("MPI is already initialized.");
      } else {
         String[] var1 = Init_jni(var0);
         initCommon();
         return var1;
      }
   }

   private static native String[] Init_jni(String[] var0);

   public static int InitThread(String[] var0, int var1) throws MPIException {
      if(initialized) {
         throw new MPIException("MPI is already initialized.");
      } else {
         int var2 = InitThread_jni(var0, var1);
         initCommon();
         return var2;
      }
   }

   private static native int InitThread_jni(String[] var0, int var1) throws MPIException;

   public static int queryThread() throws MPIException {
      check();
      return queryThread_jni();
   }

   private static native int queryThread_jni() throws MPIException;

   public static boolean isThreadMain() throws MPIException {
      check();
      return isThreadMain_jni();
   }

   private static native boolean isThreadMain_jni() throws MPIException;

   public static void Finalize() throws MPIException {
      check();
      Finalize_jni();
      finalized = true;
   }

   private static native void Finalize_jni() throws MPIException;

   public static double wtime() throws MPIException {
      check();
      return wtime_jni();
   }

   private static native double wtime_jni();

   public static double wtick() throws MPIException {
      check();
      return wtick_jni();
   }

   private static native double wtick_jni();

   public static Version getVersion() {
      return getVersionJNI();
   }

   private static native Version getVersionJNI();

   public static String getLibVersion() {
      return getLibVersionJNI();
   }

   private static native String getLibVersionJNI();

   public static String getProcessorName() throws MPIException {
      check();
      byte[] var0 = new byte[256];
      int var1 = getProcessorName(var0);
      return new String(var0, 0, var1);
   }

   private static native int getProcessorName(byte[] var0);

   public static native boolean isInitialized() throws MPIException;

   public static native boolean isFinalized() throws MPIException;

   public static void attachBuffer(byte[] var0) throws MPIException {
      check();
      buffer = var0;
      attachBuffer_jni(var0);
   }

   private static native void attachBuffer_jni(byte[] var0);

   public static byte[] detachBuffer() throws MPIException {
      check();
      detachBuffer_jni(buffer);
      byte[] var0 = buffer;
      buffer = null;
      return var0;
   }

   private static native void detachBuffer_jni(byte[] var0);

   public static void pControl(int var0, Object var1) {}

   protected static void check() throws MPIException {
      if(!initialized) {
         throw new MPIException("MPI is not initialized.");
      } else if(finalized) {
         throw new MPIException("MPI is finalized.");
      }
   }

   protected static byte[] attrSet(Object var0) throws MPIException {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         ObjectOutputStream var4 = new ObjectOutputStream(var1);
         var4.writeObject(var0);
         var4.close();
         return var1.toByteArray();
      } catch (IOException var3) {
         MPIException var2 = new MPIException(var3);
         var2.setStackTrace(var3.getStackTrace());
         throw var2;
      }
   }

   protected static Object attrGet(byte[] var0) throws MPIException {
      if(var0 == null) {
         return null;
      } else {
         try {
            ByteArrayInputStream var1 = new ByteArrayInputStream(var0);
            ObjectInputStream var2 = new ObjectInputStream(var1);
            Object var3 = var2.readObject();
            var2.close();
            return var3;
         } catch (ClassNotFoundException var4) {
            throw new MPIException(var4);
         } catch (IOException var5) {
            throw new MPIException(var5);
         }
      }
   }

   public static ByteBuffer newByteBuffer(int var0) {
      ByteBuffer var1 = ByteBuffer.allocateDirect(var0);
      var1.order(nativeOrder);
      return var1;
   }

   public static CharBuffer newCharBuffer(int var0) {
      assert var0 <= 1073741823;

      ByteBuffer var1 = ByteBuffer.allocateDirect(var0 * 2);
      var1.order(nativeOrder);
      return var1.asCharBuffer();
   }

   public static ShortBuffer newShortBuffer(int var0) {
      assert var0 <= 1073741823;

      ByteBuffer var1 = ByteBuffer.allocateDirect(var0 * 2);
      var1.order(nativeOrder);
      return var1.asShortBuffer();
   }

   public static IntBuffer newIntBuffer(int var0) {
      assert var0 <= 536870911;

      ByteBuffer var1 = ByteBuffer.allocateDirect(var0 * 4);
      var1.order(nativeOrder);
      return var1.asIntBuffer();
   }

   public static LongBuffer newLongBuffer(int var0) {
      assert var0 <= 268435455;

      ByteBuffer var1 = ByteBuffer.allocateDirect(var0 * 8);
      var1.order(nativeOrder);
      return var1.asLongBuffer();
   }

   public static FloatBuffer newFloatBuffer(int var0) {
      assert var0 <= 536870911;

      ByteBuffer var1 = ByteBuffer.allocateDirect(var0 * 4);
      var1.order(nativeOrder);
      return var1.asFloatBuffer();
   }

   public static DoubleBuffer newDoubleBuffer(int var0) {
      assert var0 <= 268435455;

      ByteBuffer var1 = ByteBuffer.allocateDirect(var0 * 8);
      var1.order(nativeOrder);
      return var1.asDoubleBuffer();
   }

   protected static void assertDirectBuffer(Buffer var0) {
      if(!var0.isDirect()) {
         throw new IllegalArgumentException("The buffer must be direct.");
      }
   }

   protected static void assertDirectBuffer(Buffer var0, Buffer var1) {
      if(!var0.isDirect()) {
         throw new IllegalArgumentException("The send buffer must be direct.");
      } else if(!var1.isDirect()) {
         throw new IllegalArgumentException("The recv. buffer must be direct.");
      }
   }

   protected static boolean isDirectBuffer(Object var0) {
      return var0 instanceof Buffer && ((Buffer)var0).isDirect();
   }

   protected static boolean isHeapBuffer(Object var0) {
      return var0 instanceof Buffer && !((Buffer)var0).isDirect();
   }

   public static ByteBuffer slice(ByteBuffer var0, int var1) {
      return ((ByteBuffer)var0.clear().position(var1)).slice().order(nativeOrder);
   }

   public static CharBuffer slice(CharBuffer var0, int var1) {
      return ((CharBuffer)var0.clear().position(var1)).slice();
   }

   public static ShortBuffer slice(ShortBuffer var0, int var1) {
      return ((ShortBuffer)var0.clear().position(var1)).slice();
   }

   public static IntBuffer slice(IntBuffer var0, int var1) {
      return ((IntBuffer)var0.clear().position(var1)).slice();
   }

   public static LongBuffer slice(LongBuffer var0, int var1) {
      return ((LongBuffer)var0.clear().position(var1)).slice();
   }

   public static FloatBuffer slice(FloatBuffer var0, int var1) {
      return ((FloatBuffer)var0.clear().position(var1)).slice();
   }

   public static DoubleBuffer slice(DoubleBuffer var0, int var1) {
      return ((DoubleBuffer)var0.clear().position(var1)).slice();
   }

   public static ByteBuffer slice(byte[] var0, int var1) {
      return ByteBuffer.wrap(var0, var1, var0.length - var1).slice().order(nativeOrder);
   }

   public static CharBuffer slice(char[] var0, int var1) {
      return CharBuffer.wrap(var0, var1, var0.length - var1).slice();
   }

   public static ShortBuffer slice(short[] var0, int var1) {
      return ShortBuffer.wrap(var0, var1, var0.length - var1).slice();
   }

   public static IntBuffer slice(int[] var0, int var1) {
      return IntBuffer.wrap(var0, var1, var0.length - var1).slice();
   }

   public static LongBuffer slice(long[] var0, int var1) {
      return LongBuffer.wrap(var0, var1, var0.length - var1).slice();
   }

   public static FloatBuffer slice(float[] var0, int var1) {
      return FloatBuffer.wrap(var0, var1, var0.length - var1).slice();
   }

   public static DoubleBuffer slice(double[] var0, int var1) {
      return DoubleBuffer.wrap(var0, var1, var0.length - var1).slice();
   }

   static {
      System.loadLibrary("mpi_java");
      DATATYPE_NULL = new Datatype();
      BYTE = new Datatype();
      CHAR = new Datatype();
      SHORT = new Datatype();
      BOOLEAN = new Datatype();
      INT = new Datatype();
      LONG = new Datatype();
      FLOAT = new Datatype();
      DOUBLE = new Datatype();
      PACKED = new Datatype();
      INT2 = new Datatype();
      SHORT_INT = new Datatype();
      LONG_INT = new Datatype();
      FLOAT_INT = new Datatype();
      DOUBLE_INT = new Datatype();
      FLOAT_COMPLEX = new Datatype();
      DOUBLE_COMPLEX = new Datatype();
      int2 = newInt2();
      shortInt = newShortInt();
      longInt = newLongInt();
      floatInt = newFloatInt();
      doubleInt = newDoubleInt();
      MAX = new Op(1);
      MIN = new Op(2);
      SUM = new Op(3);
      PROD = new Op(4);
      LAND = new Op(5);
      BAND = new Op(6);
      LOR = new Op(7);
      BOR = new Op(8);
      LXOR = new Op(9);
      BXOR = new Op(10);
      MINLOC = new Op(11);
      MAXLOC = new Op(12);
      REPLACE = new Op(13);
      NO_OP = new Op(14);
      GROUP_EMPTY = new Group(Group.getEmpty());
      REQUEST_NULL = new Request(Request.getNull());
      INFO_ENV = Info.newEnv();
      INFO_NULL = new Info(Info.NULL);
      Constant var0 = new Constant();
      THREAD_SINGLE = var0.THREAD_SINGLE;
      THREAD_FUNNELED = var0.THREAD_FUNNELED;
      THREAD_SERIALIZED = var0.THREAD_SERIALIZED;
      THREAD_MULTIPLE = var0.THREAD_MULTIPLE;
      GRAPH = var0.GRAPH;
      DIST_GRAPH = var0.DIST_GRAPH;
      CART = var0.CART;
      ANY_SOURCE = var0.ANY_SOURCE;
      ANY_TAG = var0.ANY_TAG;
      PROC_NULL = var0.PROC_NULL;
      UNDEFINED = var0.UNDEFINED;
      IDENT = var0.IDENT;
      CONGRUENT = var0.CONGRUENT;
      SIMILAR = var0.SIMILAR;
      UNEQUAL = var0.UNEQUAL;
      TAG_UB = var0.TAG_UB;
      HOST = var0.HOST;
      IO = var0.IO;
      WTIME_IS_GLOBAL = var0.WTIME_IS_GLOBAL;
      APPNUM = var0.APPNUM;
      LASTUSEDCODE = var0.LASTUSEDCODE;
      UNIVERSE_SIZE = var0.UNIVERSE_SIZE;
      WIN_BASE = var0.WIN_BASE;
      WIN_SIZE = var0.WIN_SIZE;
      WIN_DISP_UNIT = var0.WIN_DISP_UNIT;
      VERSION = var0.VERSION;
      SUBVERSION = var0.SUBVERSION;
      ROOT = var0.ROOT;
      KEYVAL_INVALID = var0.KEYVAL_INVALID;
      BSEND_OVERHEAD = var0.BSEND_OVERHEAD;
      MAX_OBJECT_NAME = var0.MAX_OBJECT_NAME;
      MAX_PORT_NAME = var0.MAX_PORT_NAME;
      MAX_DATAREP_STRING = var0.MAX_DATAREP_STRING;
      MAX_INFO_KEY = var0.MAX_INFO_KEY;
      MAX_INFO_VAL = var0.MAX_INFO_VAL;
      ORDER_C = var0.ORDER_C;
      ORDER_FORTRAN = var0.ORDER_FORTRAN;
      DISTRIBUTE_BLOCK = var0.DISTRIBUTE_BLOCK;
      DISTRIBUTE_CYCLIC = var0.DISTRIBUTE_CYCLIC;
      DISTRIBUTE_NONE = var0.DISTRIBUTE_NONE;
      DISTRIBUTE_DFLT_DARG = var0.DISTRIBUTE_DFLT_DARG;
      MODE_CREATE = var0.MODE_CREATE;
      MODE_RDONLY = var0.MODE_RDONLY;
      MODE_WRONLY = var0.MODE_WRONLY;
      MODE_RDWR = var0.MODE_RDWR;
      MODE_DELETE_ON_CLOSE = var0.MODE_DELETE_ON_CLOSE;
      MODE_UNIQUE_OPEN = var0.MODE_UNIQUE_OPEN;
      MODE_EXCL = var0.MODE_EXCL;
      MODE_APPEND = var0.MODE_APPEND;
      MODE_SEQUENTIAL = var0.MODE_SEQUENTIAL;
      DISPLACEMENT_CURRENT = var0.DISPLACEMENT_CURRENT;
      SEEK_SET = var0.SEEK_SET;
      SEEK_CUR = var0.SEEK_CUR;
      SEEK_END = var0.SEEK_END;
      MODE_NOCHECK = var0.MODE_NOCHECK;
      MODE_NOPRECEDE = var0.MODE_NOPRECEDE;
      MODE_NOPUT = var0.MODE_NOPUT;
      MODE_NOSTORE = var0.MODE_NOSTORE;
      MODE_NOSUCCEED = var0.MODE_NOSUCCEED;
      LOCK_EXCLUSIVE = var0.LOCK_EXCLUSIVE;
      LOCK_SHARED = var0.LOCK_SHARED;
      ERRORS_ARE_FATAL = new Errhandler(Errhandler.getFatal());
      ERRORS_RETURN = new Errhandler(Errhandler.getReturn());
      COMM_WORLD = new Intracomm();
      COMM_SELF = new Intracomm();
      SUCCESS = var0.SUCCESS;
      ERR_BUFFER = var0.ERR_BUFFER;
      ERR_COUNT = var0.ERR_COUNT;
      ERR_TYPE = var0.ERR_TYPE;
      ERR_TAG = var0.ERR_TAG;
      ERR_COMM = var0.ERR_COMM;
      ERR_RANK = var0.ERR_RANK;
      ERR_REQUEST = var0.ERR_REQUEST;
      ERR_ROOT = var0.ERR_ROOT;
      ERR_GROUP = var0.ERR_GROUP;
      ERR_OP = var0.ERR_OP;
      ERR_TOPOLOGY = var0.ERR_TOPOLOGY;
      ERR_DIMS = var0.ERR_DIMS;
      ERR_ARG = var0.ERR_ARG;
      ERR_UNKNOWN = var0.ERR_UNKNOWN;
      ERR_TRUNCATE = var0.ERR_TRUNCATE;
      ERR_OTHER = var0.ERR_OTHER;
      ERR_INTERN = var0.ERR_INTERN;
      ERR_IN_STATUS = var0.ERR_IN_STATUS;
      ERR_PENDING = var0.ERR_PENDING;
      ERR_ACCESS = var0.ERR_ACCESS;
      ERR_AMODE = var0.ERR_AMODE;
      ERR_ASSERT = var0.ERR_ASSERT;
      ERR_BAD_FILE = var0.ERR_BAD_FILE;
      ERR_BASE = var0.ERR_BASE;
      ERR_CONVERSION = var0.ERR_CONVERSION;
      ERR_DISP = var0.ERR_DISP;
      ERR_DUP_DATAREP = var0.ERR_DUP_DATAREP;
      ERR_FILE_EXISTS = var0.ERR_FILE_EXISTS;
      ERR_FILE_IN_USE = var0.ERR_FILE_IN_USE;
      ERR_FILE = var0.ERR_FILE;
      ERR_INFO_KEY = var0.ERR_INFO_KEY;
      ERR_INFO_NOKEY = var0.ERR_INFO_NOKEY;
      ERR_INFO_VALUE = var0.ERR_INFO_VALUE;
      ERR_INFO = var0.ERR_INFO;
      ERR_IO = var0.ERR_IO;
      ERR_KEYVAL = var0.ERR_KEYVAL;
      ERR_LOCKTYPE = var0.ERR_LOCKTYPE;
      ERR_NAME = var0.ERR_NAME;
      ERR_NO_MEM = var0.ERR_NO_MEM;
      ERR_NOT_SAME = var0.ERR_NOT_SAME;
      ERR_NO_SPACE = var0.ERR_NO_SPACE;
      ERR_NO_SUCH_FILE = var0.ERR_NO_SUCH_FILE;
      ERR_PORT = var0.ERR_PORT;
      ERR_QUOTA = var0.ERR_QUOTA;
      ERR_READ_ONLY = var0.ERR_READ_ONLY;
      ERR_RMA_CONFLICT = var0.ERR_RMA_CONFLICT;
      ERR_RMA_SYNC = var0.ERR_RMA_SYNC;
      ERR_SERVICE = var0.ERR_SERVICE;
      ERR_SIZE = var0.ERR_SIZE;
      ERR_SPAWN = var0.ERR_SPAWN;
      ERR_UNSUPPORTED_DATAREP = var0.ERR_UNSUPPORTED_DATAREP;
      ERR_UNSUPPORTED_OPERATION = var0.ERR_UNSUPPORTED_OPERATION;
      ERR_WIN = var0.ERR_WIN;
      ERR_LASTCODE = var0.ERR_LASTCODE;
      ERR_SYSRESOURCE = var0.ERR_SYSRESOURCE;
      initVersion();
   }
}
