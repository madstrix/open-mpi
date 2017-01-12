package mpi;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import mpi.Comm;
import mpi.Datatype;
import mpi.Errhandler;
import mpi.Freeable;
import mpi.Group;
import mpi.Info;
import mpi.MPI;
import mpi.MPIException;
import mpi.Op;
import mpi.Request;

public final class Win implements Freeable {

   private long handle;
   public static final int WIN_NULL = 0;
   public static final int FLAVOR_PRIVATE = 0;
   public static final int FLAVOR_SHARED = 1;


   public Win(Buffer var1, int var2, int var3, Info var4, Comm var5) throws MPIException {
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The buffer must be direct.");
      } else {
         byte var6;
         if(var1 instanceof ByteBuffer) {
            var6 = 1;
         } else if(!(var1 instanceof CharBuffer) && !(var1 instanceof ShortBuffer)) {
            if(!(var1 instanceof IntBuffer) && !(var1 instanceof FloatBuffer)) {
               if(!(var1 instanceof LongBuffer) && !(var1 instanceof DoubleBuffer)) {
                  throw new AssertionError();
               }

               var6 = 8;
            } else {
               var6 = 4;
            }
         } else {
            var6 = 2;
         }

         int var7 = var2 * var6;
         int var8 = var3 * var6;
         this.handle = this.createWin(var1, var7, var8, var4.handle, var5.handle);
      }
   }

   private native long createWin(Buffer var1, int var2, int var3, long var4, long var6) throws MPIException;

   public Win(int var1, int var2, Info var3, Comm var4, Buffer var5, int var6) throws MPIException {
      if(!var5.isDirect()) {
         throw new IllegalArgumentException("The buffer must be direct.");
      } else {
         byte var7;
         if(var5 instanceof ByteBuffer) {
            var7 = 1;
         } else if(!(var5 instanceof CharBuffer) && !(var5 instanceof ShortBuffer)) {
            if(!(var5 instanceof IntBuffer) && !(var5 instanceof FloatBuffer)) {
               if(!(var5 instanceof LongBuffer) && !(var5 instanceof DoubleBuffer)) {
                  throw new AssertionError();
               }

               var7 = 8;
            } else {
               var7 = 4;
            }
         } else {
            var7 = 2;
         }

         int var8 = var1 * var7;
         int var9 = var2 * var7;
         if(var6 == 0) {
            this.handle = this.allocateWin(var8, var9, var3.handle, var4.handle, var5);
         } else if(var6 == 1) {
            this.handle = this.allocateSharedWin(var8, var9, var3.handle, var4.handle, var5);
         }

      }
   }

   private native long allocateWin(int var1, int var2, long var3, long var5, Buffer var7) throws MPIException;

   private native long allocateSharedWin(int var1, int var2, long var3, long var5, Buffer var7) throws MPIException;

   public Win(Info var1, Comm var2) throws MPIException {
      this.handle = this.createDynamicWin(var1.handle, var2.handle);
   }

   private native long createDynamicWin(long var1, long var3) throws MPIException;

   private int getBaseType(Datatype var1, Datatype var2) {
      int var3 = var1.baseType;
      if(var3 != var2.baseType) {
         throw new IllegalArgumentException("Both datatype arguments must be constructed from the same predefined datatype.");
      } else {
         return var3;
      }
   }

   public void attach(Buffer var1, int var2) throws MPIException {
      MPI.check();
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The buffer must be direct.");
      } else {
         byte var3;
         if(var1 instanceof ByteBuffer) {
            var3 = 1;
         } else if(!(var1 instanceof CharBuffer) && !(var1 instanceof ShortBuffer)) {
            if(!(var1 instanceof IntBuffer) && !(var1 instanceof FloatBuffer)) {
               if(!(var1 instanceof LongBuffer) && !(var1 instanceof DoubleBuffer)) {
                  throw new AssertionError();
               }

               var3 = 8;
            } else {
               var3 = 4;
            }
         } else {
            var3 = 2;
         }

         int var4 = var2 * var3;
         this.attach(this.handle, var1, var4);
      }
   }

   private native void attach(long var1, Buffer var3, int var4) throws MPIException;

   public void detach(Buffer var1) throws MPIException {
      MPI.check();
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The buffer must be direct.");
      } else {
         this.detach(this.handle, var1);
      }
   }

   private native void detach(long var1, Buffer var3) throws MPIException;

   public Group getGroup() throws MPIException {
      MPI.check();
      return new Group(this.getGroup(this.handle));
   }

   private native long getGroup(long var1) throws MPIException;

   public void put(Buffer var1, int var2, Datatype var3, int var4, int var5, int var6, Datatype var7) throws MPIException {
      MPI.check();
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The origin must be direct buffer.");
      } else {
         this.put(this.handle, var1, var2, var3.handle, var4, var5, var6, var7.handle, this.getBaseType(var3, var7));
      }
   }

   private native void put(long var1, Buffer var3, int var4, long var5, int var7, int var8, int var9, long var10, int var12) throws MPIException;

   public void get(Buffer var1, int var2, Datatype var3, int var4, int var5, int var6, Datatype var7) throws MPIException {
      MPI.check();
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The origin must be direct buffer.");
      } else {
         this.get(this.handle, var1, var2, var3.handle, var4, var5, var6, var7.handle, this.getBaseType(var3, var7));
      }
   }

   private native void get(long var1, Buffer var3, int var4, long var5, int var7, int var8, int var9, long var10, int var12) throws MPIException;

   public void accumulate(Buffer var1, int var2, Datatype var3, int var4, int var5, int var6, Datatype var7, Op var8) throws MPIException {
      MPI.check();
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The origin must be direct buffer.");
      } else {
         this.accumulate(this.handle, var1, var2, var3.handle, var4, var5, var6, var7.handle, var8, var8.handle, this.getBaseType(var3, var7));
      }
   }

   private native void accumulate(long var1, Buffer var3, int var4, long var5, int var7, int var8, int var9, long var10, Op var12, long var13, int var15) throws MPIException;

   public void fence(int var1) throws MPIException {
      MPI.check();
      this.fence(this.handle, var1);
   }

   private native void fence(long var1, int var3) throws MPIException;

   public void start(Group var1, int var2) throws MPIException {
      MPI.check();
      this.start(this.handle, var1.handle, var2);
   }

   private native void start(long var1, long var3, int var5) throws MPIException;

   public void complete() throws MPIException {
      MPI.check();
      this.complete(this.handle);
   }

   private native void complete(long var1) throws MPIException;

   public void post(Group var1, int var2) throws MPIException {
      MPI.check();
      this.post(this.handle, var1.handle, var2);
   }

   private native void post(long var1, long var3, int var5) throws MPIException;

   public void waitFor() throws MPIException {
      MPI.check();
      this.waitFor(this.handle);
   }

   private native void waitFor(long var1) throws MPIException;

   public boolean test() throws MPIException {
      MPI.check();
      return this.test(this.handle);
   }

   private native boolean test(long var1) throws MPIException;

   public void lock(int var1, int var2, int var3) throws MPIException {
      MPI.check();
      this.lock(this.handle, var1, var2, var3);
   }

   private native void lock(long var1, int var3, int var4, int var5) throws MPIException;

   public void unlock(int var1) throws MPIException {
      MPI.check();
      this.unlock(this.handle, var1);
   }

   private native void unlock(long var1, int var3) throws MPIException;

   public void setErrhandler(Errhandler var1) throws MPIException {
      MPI.check();
      this.setErrhandler(this.handle, var1.handle);
   }

   private native void setErrhandler(long var1, long var3) throws MPIException;

   public void callErrhandler(int var1) throws MPIException {
      this.callErrhandler(this.handle, var1);
   }

   private native void callErrhandler(long var1, int var3) throws MPIException;

   public static int createKeyval() throws MPIException {
      MPI.check();
      return createKeyval_jni();
   }

   private static native int createKeyval_jni() throws MPIException;

   public static void freeKeyval(int var0) throws MPIException {
      MPI.check();
      freeKeyval_jni(var0);
   }

   private static native void freeKeyval_jni(int var0) throws MPIException;

   public void setAttr(int var1, Object var2) throws MPIException {
      MPI.check();
      this.setAttr(this.handle, var1, MPI.attrSet(var2));
   }

   private native void setAttr(long var1, int var3, byte[] var4) throws MPIException;

   public Object getAttr(int var1) throws MPIException {
      MPI.check();
      Object var2 = this.getAttr(this.handle, var1);
      return var2 instanceof byte[]?MPI.attrGet((byte[])((byte[])var2)):var2;
   }

   private native Object getAttr(long var1, int var3) throws MPIException;

   public void deleteAttr(int var1) throws MPIException {
      MPI.check();
      this.deleteAttr(this.handle, var1);
   }

   private native void deleteAttr(long var1, int var3) throws MPIException;

   public void free() throws MPIException {
      MPI.check();
      this.handle = this.free(this.handle);
   }

   private native long free(long var1) throws MPIException;

   public Info getInfo() throws MPIException {
      MPI.check();
      return new Info(this.getInfo(this.handle));
   }

   private native long getInfo(long var1) throws MPIException;

   public void setInfo(Info var1) throws MPIException {
      MPI.check();
      this.setInfo(this.handle, var1.handle);
   }

   private native void setInfo(long var1, long var3) throws MPIException;

   public final Request rPut(Buffer var1, int var2, Datatype var3, int var4, int var5, int var6, Datatype var7) throws MPIException {
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The origin must be direct buffer.");
      } else {
         Request var8 = new Request(this.rPut(this.handle, var1, var2, var3.handle, var4, var5, var6, var7.handle, this.getBaseType(var3, var7)));
         var8.addSendBufRef(var1);
         return var8;
      }
   }

   private native long rPut(long var1, Buffer var3, int var4, long var5, int var7, int var8, int var9, long var10, int var12) throws MPIException;

   public final Request rGet(Buffer var1, int var2, Datatype var3, int var4, int var5, int var6, Datatype var7) throws MPIException {
      MPI.check();
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The origin must be direct buffer.");
      } else {
         Request var8 = new Request(this.rGet(this.handle, var1, var2, var3.handle, var4, var5, var6, var7.handle, this.getBaseType(var3, var7)));
         var8.addRecvBufRef(var1);
         return var8;
      }
   }

   private native long rGet(long var1, Buffer var3, int var4, long var5, int var7, int var8, int var9, long var10, int var12) throws MPIException;

   public Request rAccumulate(Buffer var1, int var2, Datatype var3, int var4, int var5, int var6, Datatype var7, Op var8) throws MPIException {
      MPI.check();
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The origin must be direct buffer.");
      } else {
         Request var9 = new Request(this.rAccumulate(this.handle, var1, var2, var3.handle, var4, var5, var6, var7.handle, var8, var8.handle, this.getBaseType(var3, var7)));
         var9.addSendBufRef(var1);
         return var9;
      }
   }

   private native long rAccumulate(long var1, Buffer var3, int var4, long var5, int var7, int var8, int var9, long var10, Op var12, long var13, int var15) throws MPIException;

   public void getAccumulate(Buffer var1, int var2, Datatype var3, Buffer var4, int var5, Datatype var6, int var7, int var8, int var9, Datatype var10, Op var11) throws MPIException {
      MPI.check();
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The origin must be direct buffer.");
      } else {
         this.getAccumulate(this.handle, var1, var2, var3.handle, var4, var5, var6.handle, var7, var8, var9, var10.handle, var11, var11.handle, this.getBaseType(var3, var10));
      }
   }

   private native void getAccumulate(long var1, Buffer var3, int var4, long var5, Buffer var7, int var8, long var9, int var11, int var12, int var13, long var14, Op var16, long var17, int var19) throws MPIException;

   public Request rGetAccumulate(Buffer var1, int var2, Datatype var3, Buffer var4, int var5, Datatype var6, int var7, int var8, int var9, Datatype var10, Op var11) throws MPIException {
      MPI.check();
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The origin must be direct buffer.");
      } else {
         Request var12 = new Request(this.rGetAccumulate(this.handle, var1, var2, var3.handle, var4, var5, var6.handle, var7, var8, var9, var10.handle, var11, var11.handle, this.getBaseType(var3, var10)));
         var12.addRecvBufRef(var1);
         return var12;
      }
   }

   private native long rGetAccumulate(long var1, Buffer var3, int var4, long var5, Buffer var7, int var8, long var9, int var11, int var12, int var13, long var14, Op var16, long var17, int var19) throws MPIException;

   public void lockAll(int var1) throws MPIException {
      MPI.check();
      this.lockAll(this.handle, var1);
   }

   private native void lockAll(long var1, int var3) throws MPIException;

   public void unlockAll() throws MPIException {
      MPI.check();
      this.unlockAll(this.handle);
   }

   private native void unlockAll(long var1) throws MPIException;

   public void sync() throws MPIException {
      MPI.check();
      this.sync(this.handle);
   }

   private native void sync(long var1) throws MPIException;

   public void flush(int var1) throws MPIException {
      MPI.check();
      this.flush(this.handle, var1);
   }

   private native void flush(long var1, int var3) throws MPIException;

   public void flushAll() throws MPIException {
      MPI.check();
      this.flushAll(this.handle);
   }

   private native void flushAll(long var1) throws MPIException;

   public void compareAndSwap(Buffer var1, Buffer var2, Buffer var3, Datatype var4, int var5, int var6) throws MPIException {
      MPI.check();
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The origin must be direct buffer.");
      } else {
         this.compareAndSwap(this.handle, var1, var2, var3, var4.handle, var5, var6);
      }
   }

   private native void compareAndSwap(long var1, Buffer var3, Buffer var4, Buffer var5, long var6, int var8, int var9) throws MPIException;

   public void fetchAndOp(Buffer var1, Buffer var2, Datatype var3, int var4, int var5, Op var6) throws MPIException {
      MPI.check();
      if(!var1.isDirect()) {
         throw new IllegalArgumentException("The origin must be direct buffer.");
      } else {
         this.fetchAndOp(this.handle, var1, var2, var3.handle, var4, var5, var6, var6.handle, this.getBaseType(var3, var3));
      }
   }

   private native void fetchAndOp(long var1, Buffer var3, Buffer var4, long var5, int var7, int var8, Op var9, long var10, int var12) throws MPIException;

   public void flushLocal(int var1) throws MPIException {
      MPI.check();
      this.flushLocal(this.handle, var1);
   }

   private native void flushLocal(long var1, int var3) throws MPIException;

   public void flushLocalAll() throws MPIException {
      MPI.check();
      this.flushLocalAll(this.handle);
   }

   private native void flushLocalAll(long var1) throws MPIException;

   public String getName() throws MPIException {
      MPI.check();
      return this.getName(this.handle);
   }

   private native String getName(long var1) throws MPIException;

   public void setName(String var1) throws MPIException {
      MPI.check();
      this.setName(this.handle, var1);
   }

   private native void setName(long var1, String var3) throws MPIException;
}
