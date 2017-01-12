package mpi;

import mpi.Count;
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

   public Count getElementsX(Datatype var1) throws MPIException {
      MPI.check();
      byte var2 = 0;
      int var9 = var2 + 1;
      int var3 = (int)this.data[var2];
      int var4 = (int)this.data[var9++];
      int var5 = (int)this.data[var9++];
      int var6 = (int)this.data[var9++];
      long var7 = this.data[var9++];
      return this.getElementsX(var3, var4, var5, var6, var7, var1.handle);
   }

   private native Count getElementsX(int var1, int var2, int var3, int var4, long var5, long var7) throws MPIException;

   public void setElements(Datatype var1, int var2) throws MPIException {
      MPI.check();
      byte var3 = 0;
      int var10 = var3 + 1;
      int var4 = (int)this.data[var3];
      int var5 = (int)this.data[var10++];
      int var6 = (int)this.data[var10++];
      int var7 = (int)this.data[var10++];
      long var8 = this.data[var10++];
      this.data[4] = (long)this.setElements(var4, var5, var6, var7, var8, var1.handle, var2);
   }

   private native int setElements(int var1, int var2, int var3, int var4, long var5, long var7, int var9) throws MPIException;

   public void setElementsX(Datatype var1, Count var2) throws MPIException {
      MPI.check();
      byte var3 = 0;
      int var10 = var3 + 1;
      int var4 = (int)this.data[var3];
      int var5 = (int)this.data[var10++];
      int var6 = (int)this.data[var10++];
      int var7 = (int)this.data[var10++];
      long var8 = this.data[var10++];
      this.data[4] = this.setElementsX(var4, var5, var6, var7, var8, var1.handle, var2.getCount());
   }

   private native long setElementsX(int var1, int var2, int var3, int var4, long var5, long var7, long var9) throws MPIException;

   public void setCancelled(boolean var1) throws MPIException {
      MPI.check();
      byte var2 = 0;
      int var9 = var2 + 1;
      int var3 = (int)this.data[var2];
      int var4 = (int)this.data[var9++];
      int var5 = (int)this.data[var9++];
      int var6 = (int)this.data[var9++];
      long var7 = this.data[var9++];
      if(var1) {
         this.setCancelled(var3, var4, var5, var6, var7, 1);
         this.data[3] = 1L;
      } else {
         this.setCancelled(var3, var4, var5, var6, var7, 0);
         this.data[3] = 0L;
      }

   }

   private native void setCancelled(int var1, int var2, int var3, int var4, long var5, int var7) throws MPIException;

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
