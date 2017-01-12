package mpi;

import java.nio.Buffer;
import mpi.Freeable;
import mpi.MPI;
import mpi.MPIException;
import mpi.Status;

public class Request implements Freeable {

   protected long handle;
   protected Buffer sendBuf;
   protected Buffer recvBuf;


   private static native void init();

   protected static native long getNull();

   protected Request(long var1) {
      this.handle = var1;
   }

   public void free() throws MPIException {
      if(!this.isNull()) {
         MPI.check();
         this.handle = this.free(this.handle);
      }

   }

   private native long free(long var1) throws MPIException;

   public final void cancel() throws MPIException {
      MPI.check();
      this.cancel(this.handle);
   }

   private native void cancel(long var1) throws MPIException;

   protected final void addRecvBufRef(Buffer var1) {
      this.recvBuf = var1;
   }

   protected final void addSendBufRef(Buffer var1) {
      this.sendBuf = var1;
   }

   public final boolean isNull() {
      return this.handle == 0L || this.handle == MPI.REQUEST_NULL.handle;
   }

   public final Status waitStatus() throws MPIException {
      MPI.check();
      Status var1 = new Status();
      this.handle = this.waitStatus(this.handle, var1.data);
      return var1;
   }

   private native long waitStatus(long var1, long[] var3) throws MPIException;

   public final void waitFor() throws MPIException {
      MPI.check();
      this.handle = this.waitFor(this.handle);
   }

   private native long waitFor(long var1) throws MPIException;

   public final Status testStatus() throws MPIException {
      MPI.check();
      return this.testStatus(this.handle);
   }

   private native Status testStatus(long var1) throws MPIException;

   public final Status getStatus() throws MPIException {
      MPI.check();
      return this.getStatus(this.handle);
   }

   private native Status getStatus(long var1) throws MPIException;

   public final boolean test() throws MPIException {
      MPI.check();
      return this.test(this.handle);
   }

   private native boolean test(long var1) throws MPIException;

   public static Status waitAnyStatus(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      Status var2 = new Status();
      waitAnyStatus(var1, var2.data);
      setHandles(var0, var1);
      return var2;
   }

   private static native void waitAnyStatus(long[] var0, long[] var1) throws MPIException;

   public static int waitAny(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      int var2 = waitAny(var1);
      setHandles(var0, var1);
      return var2;
   }

   private static native int waitAny(long[] var0) throws MPIException;

   public static Status testAnyStatus(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      Status var2 = testAnyStatus(var1);
      setHandles(var0, var1);
      return var2;
   }

   private static native Status testAnyStatus(long[] var0) throws MPIException;

   public static int testAny(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      int var2 = testAny(var1);
      setHandles(var0, var1);
      return var2;
   }

   private static native int testAny(long[] var0) throws MPIException;

   public static Status[] waitAllStatus(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      Status[] var2 = waitAllStatus(var1);
      setHandles(var0, var1);
      return var2;
   }

   private static native Status[] waitAllStatus(long[] var0) throws MPIException;

   public static void waitAll(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      waitAll(var1);
      setHandles(var0, var1);
   }

   private static native void waitAll(long[] var0) throws MPIException;

   public static Status[] testAllStatus(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      Status[] var2 = testAllStatus(var1);
      setHandles(var0, var1);
      return var2;
   }

   private static native Status[] testAllStatus(long[] var0) throws MPIException;

   public static boolean testAll(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      boolean var2 = testAll(var1);
      setHandles(var0, var1);
      return var2;
   }

   private static native boolean testAll(long[] var0) throws MPIException;

   public static Status[] waitSomeStatus(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      Status[] var2 = waitSomeStatus(var1);
      setHandles(var0, var1);
      return var2;
   }

   private static native Status[] waitSomeStatus(long[] var0) throws MPIException;

   public static int[] waitSome(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      int[] var2 = waitSome(var1);
      setHandles(var0, var1);
      return var2;
   }

   private static native int[] waitSome(long[] var0) throws MPIException;

   public static Status[] testSomeStatus(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      Status[] var2 = testSomeStatus(var1);
      setHandles(var0, var1);
      return var2;
   }

   private static native Status[] testSomeStatus(long[] var0) throws MPIException;

   public static int[] testSome(Request[] var0) throws MPIException {
      MPI.check();
      long[] var1 = getHandles(var0);
      int[] var2 = testSome(var1);
      setHandles(var0, var1);
      return var2;
   }

   private static native int[] testSome(long[] var0) throws MPIException;

   protected static long[] getHandles(Request[] var0) {
      long[] var1 = new long[var0.length];

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if(var0[var2] != null) {
            var1[var2] = var0[var2].handle;
         } else {
            var1[var2] = 0L;
         }
      }

      return var1;
   }

   protected static void setHandles(Request[] var0, long[] var1) {
      for(int var2 = 0; var2 < var0.length; ++var2) {
         var0[var2].handle = var1[var2];
      }

   }

   static {
      init();
   }
}
