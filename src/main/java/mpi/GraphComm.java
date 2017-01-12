package mpi;

import mpi.DistGraphNeighbors;
import mpi.GraphParms;
import mpi.Info;
import mpi.Intracomm;
import mpi.MPI;
import mpi.MPIException;

public final class GraphComm extends Intracomm {

   private static native void init();

   protected GraphComm(long var1) throws MPIException {
      super(var1);
   }

   protected GraphComm(long[] var1) {
      super(var1);
   }

   public GraphComm clone() {
      try {
         return this.dup();
      } catch (MPIException var2) {
         throw new RuntimeException(var2.getMessage());
      }
   }

   public GraphComm dup() throws MPIException {
      MPI.check();
      return new GraphComm(this.dup(this.handle));
   }

   public GraphComm iDup() throws MPIException {
      MPI.check();
      return new GraphComm(this.iDup(this.handle));
   }

   public GraphComm dupWithInfo(Info var1) throws MPIException {
      MPI.check();
      return new GraphComm(this.dupWithInfo(this.handle, var1.handle));
   }

   public GraphParms getDims() throws MPIException {
      MPI.check();
      return this.getDims(this.handle);
   }

   private native GraphParms getDims(long var1) throws MPIException;

   public int[] getNeighbors(int var1) throws MPIException {
      MPI.check();
      return this.getNeighbors(this.handle, var1);
   }

   private native int[] getNeighbors(long var1, int var3) throws MPIException;

   public DistGraphNeighbors getDistGraphNeighbors() throws MPIException {
      MPI.check();
      return this.getDistGraphNeighbors(this.handle);
   }

   private native DistGraphNeighbors getDistGraphNeighbors(long var1) throws MPIException;

   public int map(int[] var1, int[] var2) throws MPIException {
      MPI.check();
      return this.map(this.handle, var1, var2);
   }

   private native int map(long var1, int[] var3, int[] var4) throws MPIException;

   static {
      init();
   }
}
