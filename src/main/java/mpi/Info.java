package mpi;

import mpi.Freeable;
import mpi.MPI;
import mpi.MPIException;

public final class Info implements Freeable, Cloneable {

   protected long handle;
   protected static final long NULL = getNull();


   public Info() throws MPIException {
      MPI.check();
      this.handle = this.create();
   }

   protected Info(long var1) {
      this.handle = var1;
   }

   private native long create();

   protected static Info newEnv() {
      return new Info(getEnv());
   }

   private static native long getEnv();

   private static native long getNull();

   public void set(String var1, String var2) throws MPIException {
      MPI.check();
      this.set(this.handle, var1, var2);
   }

   private native void set(long var1, String var3, String var4) throws MPIException;

   public String get(String var1) throws MPIException {
      MPI.check();
      return this.get(this.handle, var1);
   }

   private native String get(long var1, String var3) throws MPIException;

   public void delete(String var1) throws MPIException {
      MPI.check();
      this.delete(this.handle, var1);
   }

   private native void delete(long var1, String var3) throws MPIException;

   public int size() throws MPIException {
      MPI.check();
      return this.size(this.handle);
   }

   private native int size(long var1) throws MPIException;

   public String getKey(int var1) throws MPIException {
      MPI.check();
      return this.getKey(this.handle, var1);
   }

   private native String getKey(long var1, int var3) throws MPIException;

   public Info clone() {
      try {
         return this.dup();
      } catch (MPIException var2) {
         throw new RuntimeException(var2.getMessage());
      }
   }

   public Info dup() throws MPIException {
      MPI.check();
      return new Info(this.dup(this.handle));
   }

   private native long dup(long var1) throws MPIException;

   public void free() throws MPIException {
      MPI.check();
      this.handle = this.free(this.handle);
   }

   private native long free(long var1) throws MPIException;

   public boolean isNull() {
      return this.isNull(this.handle);
   }

   private native boolean isNull(long var1);

}
