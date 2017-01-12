package mpi;

import java.nio.Buffer;
import mpi.Freeable;
import mpi.MPI;
import mpi.MPIException;

public final class Datatype implements Freeable, Cloneable {

   protected long handle;
   protected int baseType;
   protected int baseSize;
   private int lb;
   private int extent;
   private int trueLb;
   private int trueExtent;
   protected static final int NULL = 0;
   protected static final int BYTE = 1;
   protected static final int CHAR = 2;
   protected static final int SHORT = 3;
   protected static final int BOOLEAN = 4;
   protected static final int INT = 5;
   protected static final int LONG = 6;
   protected static final int FLOAT = 7;
   protected static final int DOUBLE = 8;
   protected static final int PACKED = 9;
   protected static final int INT2 = 10;
   protected static final int SHORT_INT = 11;
   protected static final int LONG_INT = 12;
   protected static final int FLOAT_INT = 13;
   protected static final int DOUBLE_INT = 14;
   protected static final int FLOAT_COMPLEX = 15;
   protected static final int DOUBLE_COMPLEX = 16;


   private static native void init();

   protected Datatype() {}

   protected void setBasic(int var1) {
      this.baseType = var1;
      this.handle = getDatatype(var1);
      this.baseSize = var1 == 0?0:this.getSize(this.handle);
   }

   protected void setBasic(int var1, Datatype var2) {
      this.baseType = var2.baseType;
      this.handle = getDatatype(var1);
      this.baseSize = var2.baseSize;
   }

   private static native long getDatatype(int var0);

   private Datatype(Datatype var1, long var2) {
      this.baseType = var1.baseType;
      this.baseSize = var1.baseSize;
      this.handle = var2;
   }

   private Datatype(int var1, int var2, long var3) {
      this.baseType = var1;
      this.baseSize = var2;
      this.handle = var3;
   }

   public int getLb() throws MPIException {
      if(this.extent == 0) {
         this.getLbExtent();
      }

      return this.lb;
   }

   public int getExtent() throws MPIException {
      if(this.extent == 0) {
         this.getLbExtent();
      }

      return this.extent;
   }

   private void getLbExtent() throws MPIException {
      MPI.check();
      int[] var1 = new int[2];
      this.getLbExtent(this.handle, var1);
      this.lb = var1[0] / this.baseSize;
      this.extent = var1[1] / this.baseSize;
   }

   private native void getLbExtent(long var1, int[] var3);

   public int getTrueLb() throws MPIException {
      if(this.trueExtent == 0) {
         this.getTrueLbExtent();
      }

      return this.trueLb;
   }

   public int getTrueExtent() throws MPIException {
      if(this.trueExtent == 0) {
         this.getTrueLbExtent();
      }

      return this.trueExtent;
   }

   private void getTrueLbExtent() throws MPIException {
      MPI.check();
      int[] var1 = new int[2];
      this.getTrueLbExtent(this.handle, var1);
      this.trueLb = var1[0] / this.baseSize;
      this.trueExtent = var1[1] / this.baseSize;
   }

   private native void getTrueLbExtent(long var1, int[] var3);

   public int getSize() throws MPIException {
      MPI.check();
      return this.getSize(this.handle) / this.baseSize;
   }

   private native int getSize(long var1);

   public void commit() throws MPIException {
      MPI.check();
      this.commit(this.handle);
   }

   private native void commit(long var1);

   public void free() throws MPIException {
      MPI.check();
      this.handle = this.free(this.handle);
   }

   private native long free(long var1) throws MPIException;

   public boolean isNull() {
      return this.handle == MPI.DATATYPE_NULL.handle;
   }

   public Datatype clone() {
      try {
         return this.dup();
      } catch (MPIException var2) {
         throw new RuntimeException(var2.getMessage());
      }
   }

   public Datatype dup() throws MPIException {
      MPI.check();
      return new Datatype(this, this.dup(this.handle));
   }

   private native long dup(long var1) throws MPIException;

   public static Datatype createContiguous(int var0, Datatype var1) throws MPIException {
      MPI.check();
      return new Datatype(var1, getContiguous(var0, var1.handle));
   }

   private static native long getContiguous(int var0, long var1);

   public static Datatype createVector(int var0, int var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      long var4 = getVector(var0, var1, var2, var3.handle);
      return new Datatype(var3, var4);
   }

   private static native long getVector(int var0, int var1, int var2, long var3) throws MPIException;

   public static Datatype createHVector(int var0, int var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      long var4 = getHVector(var0, var1, var2, var3.handle);
      return new Datatype(var3, var4);
   }

   private static native long getHVector(int var0, int var1, int var2, long var3) throws MPIException;

   public static Datatype createIndexed(int[] var0, int[] var1, Datatype var2) throws MPIException {
      MPI.check();
      long var3 = getIndexed(var0, var1, var2.handle);
      return new Datatype(var2, var3);
   }

   private static native long getIndexed(int[] var0, int[] var1, long var2) throws MPIException;

   public static Datatype createHIndexed(int[] var0, int[] var1, Datatype var2) throws MPIException {
      MPI.check();
      long var3 = getHIndexed(var0, var1, var2.handle);
      return new Datatype(var2, var3);
   }

   private static native long getHIndexed(int[] var0, int[] var1, long var2) throws MPIException;

   public static Datatype createStruct(int[] var0, int[] var1, Datatype[] var2) throws MPIException {
      MPI.check();
      long var3 = getStruct(var0, var1, var2);
      return new Datatype(MPI.BYTE, var3);
   }

   private static native long getStruct(int[] var0, int[] var1, Datatype[] var2) throws MPIException;

   public static Datatype createResized(Datatype var0, int var1, int var2) throws MPIException {
      MPI.check();
      long var3 = getResized(var0.handle, var1, var2);
      return new Datatype(var0, var3);
   }

   private static native long getResized(long var0, int var2, int var3);

   public void setName(String var1) throws MPIException {
      MPI.check();
      this.setName(this.handle, var1);
   }

   private native void setName(long var1, String var3) throws MPIException;

   public String getName() throws MPIException {
      MPI.check();
      return this.getName(this.handle);
   }

   private native String getName(long var1) throws MPIException;

   public static int createKeyval() throws MPIException {
      MPI.check();
      return createKeyval_jni();
   }

   private static native int createKeyval_jni() throws MPIException;

   public static void freeKeyval(int var0) throws MPIException {
      MPI.check();
      freeKeyval_jni(var0);
   }

   private static native void freeKeyval_jni(int var0) throws MPIException;

   public void setAttr(int var1, Object var2) throws MPIException {
      MPI.check();
      this.setAttr(this.handle, var1, MPI.attrSet(var2));
   }

   private native void setAttr(long var1, int var3, byte[] var4) throws MPIException;

   public Object getAttr(int var1) throws MPIException {
      MPI.check();
      Object var2 = this.getAttr(this.handle, var1);
      return var2 instanceof byte[]?MPI.attrGet((byte[])((byte[])var2)):var2;
   }

   private native Object getAttr(long var1, int var3) throws MPIException;

   public void deleteAttr(int var1) throws MPIException {
      MPI.check();
      this.deleteAttr(this.handle, var1);
   }

   private native void deleteAttr(long var1, int var3) throws MPIException;

   protected int getOffset(Object var1) {
      return this.baseSize * ((Buffer)var1).arrayOffset();
   }

   static {
      init();
   }
}
