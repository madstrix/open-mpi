package mpi;

import mpi.MPI;
import mpi.MPIException;
import mpi.Request;

public final class Prequest extends Request {

   protected Prequest(long var1) {
      super(var1);
   }

   public void start() throws MPIException {
      this.handle = this.start(this.handle);
   }

   private native long start(long var1) throws MPIException;

   public static void startAll(Prequest[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      startAll(var1);
      setHandles(var0, var1);
   }

   private static native void startAll(long[] var0) throws MPIException;
}
