package mpi;

import mpi.Freeable;
import mpi.MPI;
import mpi.MPIException;

public final class Group implements Freeable {

   protected long handle;
   private static long nullHandle;


   private static native void init();

   protected static native long getEmpty();

   protected Group(long var1) {
      this.handle = var1;
   }

   public int getSize() throws MPIException {
      MPI.check();
      return this.getSize(this.handle);
   }

   private native int getSize(long var1) throws MPIException;

   public int getRank() throws MPIException {
      MPI.check();
      return this.getRank(this.handle);
   }

   private native int getRank(long var1) throws MPIException;

   public void free() throws MPIException {
      MPI.check();
      this.handle = this.free(this.handle);
   }

   private native long free(long var1);

   public boolean isNull() {
      return this.handle == nullHandle;
   }

   public static int[] translateRanks(Group var0, int[] var1, Group var2) throws MPIException {
      MPI.check();
      return translateRanks(var0.handle, var1, var2.handle);
   }

   private static native int[] translateRanks(long var0, int[] var2, long var3) throws MPIException;

   public static int compare(Group var0, Group var1) throws MPIException {
      MPI.check();
      return compare(var0.handle, var1.handle);
   }

   private static native int compare(long var0, long var2) throws MPIException;

   public static Group union(Group var0, Group var1) throws MPIException {
      MPI.check();
      return new Group(union(var0.handle, var1.handle));
   }

   private static native long union(long var0, long var2);

   public static Group intersection(Group var0, Group var1) throws MPIException {
      MPI.check();
      return new Group(intersection(var0.handle, var1.handle));
   }

   private static native long intersection(long var0, long var2);

   public static Group difference(Group var0, Group var1) throws MPIException {
      MPI.check();
      return new Group(difference(var0.handle, var1.handle));
   }

   private static native long difference(long var0, long var2);

   public Group incl(int[] var1) throws MPIException {
      MPI.check();
      return new Group(this.incl(this.handle, var1));
   }

   private native long incl(long var1, int[] var3);

   public Group excl(int[] var1) throws MPIException {
      MPI.check();
      return new Group(this.excl(this.handle, var1));
   }

   private native long excl(long var1, int[] var3);

   public Group rangeIncl(int[][] var1) throws MPIException {
      MPI.check();
      return new Group(this.rangeIncl(this.handle, var1));
   }

   private native long rangeIncl(long var1, int[][] var3);

   public Group rangeExcl(int[][] var1) throws MPIException {
      MPI.check();
      return new Group(this.rangeExcl(this.handle, var1));
   }

   private native long rangeExcl(long var1, int[][] var3);

   static {
      init();
   }
}
