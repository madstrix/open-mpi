package mpi;

import mpi.CartParms;
import mpi.Intracomm;
import mpi.MPI;
import mpi.MPIException;
import mpi.ShiftParms;

public final class CartComm extends Intracomm {

   private static native void init();

   protected CartComm(long var1) throws MPIException {
      super(var1);
   }

   protected CartComm(long[] var1) {
      super(var1);
   }

   public CartComm clone() {
      try {
         return this.dup();
      } catch (MPIException var2) {
         throw new RuntimeException(var2.getMessage());
      }
   }

   public CartComm dup() throws MPIException {
      MPI.check();
      return new CartComm(this.dup(this.handle));
   }

   public CartComm iDup() throws MPIException {
      MPI.check();
      return new CartComm(this.iDup(this.handle));
   }

   public CartParms getTopo() throws MPIException {
      MPI.check();
      return this.getTopo(this.handle);
   }

   private native CartParms getTopo(long var1) throws MPIException;

   public int getRank(int[] var1) throws MPIException {
      MPI.check();
      return this.getRank(this.handle, var1);
   }

   private native int getRank(long var1, int[] var3) throws MPIException;

   public int[] getCoords(int var1) throws MPIException {
      MPI.check();
      return this.getCoords(this.handle, var1);
   }

   private native int[] getCoords(long var1, int var3) throws MPIException;

   public ShiftParms shift(int var1, int var2) throws MPIException {
      MPI.check();
      return this.shift(this.handle, var1, var2);
   }

   private native ShiftParms shift(long var1, int var3, int var4) throws MPIException;

   public CartComm sub(boolean[] var1) throws MPIException {
      MPI.check();
      return new CartComm(this.sub(this.handle, var1));
   }

   private native long sub(long var1, boolean[] var3) throws MPIException;

   public int map(int[] var1, boolean[] var2) throws MPIException {
      MPI.check();
      return this.map(this.handle, var1, var2);
   }

   private native int map(long var1, int[] var3, boolean[] var4) throws MPIException;

   public static void createDims(int var0, int[] var1) throws MPIException {
      MPI.check();
      createDims_jni(var0, var1);
   }

   private static native void createDims_jni(int var0, int[] var1) throws MPIException;

   static {
      init();
   }
}
