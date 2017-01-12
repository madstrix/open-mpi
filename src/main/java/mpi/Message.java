package mpi;

import java.nio.Buffer;
import mpi.Comm;
import mpi.Datatype;
import mpi.MPI;
import mpi.MPIException;
import mpi.Request;
import mpi.Status;

public final class Message {

   protected long handle;
   private static long NULL;
   private static long NO_PROC;


   private static native void init();

   public Message() {
      this.handle = NULL;
   }

   public boolean isNull() {
      return this.handle == NULL;
   }

   public boolean isNoProc() {
      return this.handle == NO_PROC;
   }

   public Status mProbe(int var1, int var2, Comm var3) throws MPIException {
      MPI.check();
      Status var4 = new Status();
      this.handle = this.mProbe(var1, var2, var3.handle, var4.data);
      return var4;
   }

   private native long mProbe(int var1, int var2, long var3, long[] var5) throws MPIException;

   public Status imProbe(int var1, int var2, Comm var3) throws MPIException {
      MPI.check();
      return this.imProbe(var1, var2, var3.handle);
   }

   private native Status imProbe(int var1, int var2, long var3) throws MPIException;

   public Status mRecv(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      int var4 = 0;
      boolean var5 = false;
      Status var6 = new Status();
      if(var1 instanceof Buffer && !(var5 = ((Buffer)var1).isDirect())) {
         var4 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.handle = this.mRecv(this.handle, var1, var5, var4, var2, var3.handle, var3.baseType, var6.data);
      return var6;
   }

   private native long mRecv(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, long[] var10) throws MPIException;

   public Request imRecv(Buffer var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var4 = new Request(this.imRecv(this.handle, var1, var2, var3.handle));
      var4.addRecvBufRef(var1);
      return var4;
   }

   private native long imRecv(long var1, Object var3, int var4, long var5) throws MPIException;

   static {
      init();
   }
}
