package mpi;

import mpi.Comm;
import mpi.Group;
import mpi.Info;
import mpi.Intracomm;
import mpi.MPI;
import mpi.MPIException;

public final class Intercomm extends Comm {

   protected Intercomm(long var1) {
      super(var1);
   }

   protected Intercomm(long[] var1) {
      super(var1);
   }

   public Intercomm clone() {
      try {
         return this.dup();
      } catch (MPIException var2) {
         throw new RuntimeException(var2.getMessage());
      }
   }

   public Intercomm dup() throws MPIException {
      MPI.check();
      return new Intercomm(this.dup(this.handle));
   }

   public Intercomm iDup() throws MPIException {
      MPI.check();
      return new Intercomm(this.iDup(this.handle));
   }

   public Intercomm dupWithInfo(Info var1) throws MPIException {
      MPI.check();
      return new Intercomm(this.dupWithInfo(this.handle, var1.handle));
   }

   public int getRemoteSize() throws MPIException {
      MPI.check();
      return this.getRemoteSize_jni();
   }

   private native int getRemoteSize_jni() throws MPIException;

   public Group getRemoteGroup() throws MPIException {
      MPI.check();
      return new Group(this.getRemoteGroup_jni());
   }

   private native long getRemoteGroup_jni();

   public Intracomm merge(boolean var1) throws MPIException {
      MPI.check();
      return new Intracomm(this.merge_jni(var1));
   }

   private native long merge_jni(boolean var1);

   public static Intercomm getParent() throws MPIException {
      MPI.check();
      return new Intercomm(getParent_jni());
   }

   private static native long getParent_jni() throws MPIException;
}
