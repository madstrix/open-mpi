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

public final class Win implements Freeable {

   private long handle;


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

   private int getBaseType(Datatype var1, Datatype var2) {
      int var3 = var1.baseType;
      if(var3 != var2.baseType) {
         throw new IllegalArgumentException("Both datatype arguments must be constructed from the same predefined datatype.");
      } else {
         return var3;
      }
   }

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
}
