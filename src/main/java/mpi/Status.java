package mpi;

import mpi.Datatype;
import mpi.MPI;
import mpi.MPIException;

public final class Status {

   protected final long[] data = new long[6];


   private static native void init();

   public int getCount(Datatype var1) throws MPIException {
      MPI.check();
      byte var2 = 0;
      int var9 = var2 + 1;
      int var3 = (int)this.data[var2];
      int var4 = (int)this.data[var9++];
      int var5 = (int)this.data[var9++];
      int var6 = (int)this.data[var9++];
      long var7 = this.data[var9++];
      return this.getCount(var3, var4, var5, var6, var7, var1.handle);
   }

   private native int getCount(int var1, int var2, int var3, int var4, long var5, long var7) throws MPIException;

   public boolean isCancelled() throws MPIException {
      MPI.check();
      byte var1 = 0;
      int var8 = var1 + 1;
      int var2 = (int)this.data[var1];
      int var3 = (int)this.data[var8++];
      int var4 = (int)this.data[var8++];
      int var5 = (int)this.data[var8++];
      long var6 = this.data[var8++];
      return this.isCancelled(var2, var3, var4, var5, var6);
   }

   private native boolean isCancelled(int var1, int var2, int var3, int var4, long var5) throws MPIException;

   public int getElements(Datatype var1) throws MPIException {
      MPI.check();
      byte var2 = 0;
      int var9 = var2 + 1;
      int var3 = (int)this.data[var2];
      int var4 = (int)this.data[var9++];
      int var5 = (int)this.data[var9++];
      int var6 = (int)this.data[var9++];
      long var7 = this.data[var9++];
      return this.getElements(var3, var4, var5, var6, var7, var1.handle);
   }

   private native int getElements(int var1, int var2, int var3, int var4, long var5, long var7) throws MPIException;

   public int getSource() {
      return (int)this.data[0];
   }

   public int getTag() {
      return (int)this.data[1];
   }

   public int getError() {
      return (int)this.data[2];
   }

   public int getIndex() {
      return (int)this.data[5];
   }

   static {
      init();
   }
}
