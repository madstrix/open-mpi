package mpi;

import java.nio.Buffer;
import mpi.Comm;
import mpi.Datatype;
import mpi.FileView;
import mpi.Group;
import mpi.Info;
import mpi.MPI;
import mpi.MPIException;
import mpi.Request;
import mpi.Status;

public final class File {

   private long handle;
   private FileView view;
   private Status beginStatus;


   public File(Comm var1, String var2, int var3) throws MPIException {
      this.view = new FileView(0L, MPI.BYTE, MPI.BYTE, "native");
      MPI.check();
      this.handle = this.open(var1.handle, var2, var3, Info.NULL);
   }

   public File(Comm var1, String var2, int var3, Info var4) throws MPIException {
      this.view = new FileView(0L, MPI.BYTE, MPI.BYTE, "native");
      MPI.check();
      this.handle = this.open(var1.handle, var2, var3, var4.handle);
   }

   private native long open(long var1, String var3, int var4, long var5) throws MPIException;

   public void close() throws MPIException {
      MPI.check();
      this.handle = this.close(this.handle);
   }

   private native long close(long var1) throws MPIException;

   public static void delete(String var0) throws MPIException {
      MPI.check();
      delete(var0, Info.NULL);
   }

   public static void delete(String var0, Info var1) throws MPIException {
      MPI.check();
      delete(var0, var1.handle);
   }

   private static native void delete(String var0, long var1) throws MPIException;

   public void setSize(long var1) throws MPIException {
      MPI.check();
      this.setSize(this.handle, var1);
   }

   private native void setSize(long var1, long var3) throws MPIException;

   public void preallocate(long var1) throws MPIException {
      MPI.check();
      this.preallocate(this.handle, var1);
   }

   private native void preallocate(long var1, long var3) throws MPIException;

   public long getSize() throws MPIException {
      MPI.check();
      return this.getSize(this.handle);
   }

   private native long getSize(long var1) throws MPIException;

   public Group getGroup() throws MPIException {
      MPI.check();
      return new Group(this.getGroup(this.handle));
   }

   private native long getGroup(long var1) throws MPIException;

   public int getAMode() throws MPIException {
      MPI.check();
      return this.getAMode(this.handle);
   }

   private native int getAMode(long var1) throws MPIException;

   public void setInfo(Info var1) throws MPIException {
      MPI.check();
      this.setInfo(this.handle, var1.handle);
   }

   private native void setInfo(long var1, long var3) throws MPIException;

   public Info getInfo() throws MPIException {
      MPI.check();
      return new Info(this.getInfo(this.handle));
   }

   private native long getInfo(long var1) throws MPIException;

   public void setView(long var1, Datatype var3, Datatype var4, String var5) throws MPIException {
      MPI.check();
      this.setView(this.handle, var1, var3.handle, var4.handle, var5, Info.NULL);
      this.view = new FileView(var1, var3, var4, var5);
   }

   public void setView(long var1, Datatype var3, Datatype var4, String var5, Info var6) throws MPIException {
      MPI.check();
      this.setView(this.handle, var1, var3.handle, var4.handle, var5, var6.handle);
      this.view = new FileView(var1, var3, var4, var5);
   }

   private native void setView(long var1, long var3, long var5, long var7, String var9, long var10) throws MPIException;

   public FileView getView() {
      return this.view;
   }

   public Status readAt(long var1, Object var3, int var4, Datatype var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      Status var8 = new Status();
      if(var3 instanceof Buffer && !(var7 = ((Buffer)var3).isDirect())) {
         var6 = var5.getOffset(var3);
         var3 = ((Buffer)var3).array();
      }

      this.readAt(this.handle, var1, var3, var7, var6, var4, var5.handle, var5.baseType, var8.data);
      return var8;
   }

   private native void readAt(long var1, long var3, Object var5, boolean var6, int var7, int var8, long var9, int var11, long[] var12) throws MPIException;

   public Status readAtAll(long var1, Object var3, int var4, Datatype var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      Status var8 = new Status();
      if(var3 instanceof Buffer && !(var7 = ((Buffer)var3).isDirect())) {
         var6 = var5.getOffset(var3);
         var3 = ((Buffer)var3).array();
      }

      this.readAtAll(this.handle, var1, var3, var7, var6, var4, var5.handle, var5.baseType, var8.data);
      return var8;
   }

   private native void readAtAll(long var1, long var3, Object var5, boolean var6, int var7, int var8, long var9, int var11, long[] var12) throws MPIException;

   public Status writeAt(long var1, Object var3, int var4, Datatype var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      Status var8 = new Status();
      if(var3 instanceof Buffer && !(var7 = ((Buffer)var3).isDirect())) {
         var6 = var5.getOffset(var3);
         var3 = ((Buffer)var3).array();
      }

      this.writeAt(this.handle, var1, var3, var7, var6, var4, var5.handle, var5.baseType, var8.data);
      return var8;
   }

   private native void writeAt(long var1, long var3, Object var5, boolean var6, int var7, int var8, long var9, int var11, long[] var12) throws MPIException;

   public Status writeAtAll(long var1, Object var3, int var4, Datatype var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      Status var8 = new Status();
      if(var3 instanceof Buffer && !(var7 = ((Buffer)var3).isDirect())) {
         var6 = var5.getOffset(var3);
         var3 = ((Buffer)var3).array();
      }

      this.writeAtAll(this.handle, var1, var3, var7, var6, var4, var5.handle, var5.baseType, var8.data);
      return var8;
   }

   private native void writeAtAll(long var1, long var3, Object var5, boolean var6, int var7, int var8, long var9, int var11, long[] var12) throws MPIException;

   public Request iReadAt(long var1, Buffer var3, int var4, Datatype var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var3);
      Request var6 = new Request(this.iReadAt(this.handle, var1, var3, var4, var5.handle));
      var6.addRecvBufRef(var3);
      return var6;
   }

   private native long iReadAt(long var1, long var3, Buffer var5, int var6, long var7) throws MPIException;

   public Request iWriteAt(long var1, Buffer var3, int var4, Datatype var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var3);
      Request var6 = new Request(this.iWriteAt(this.handle, var1, var3, var4, var5.handle));
      var6.addSendBufRef(var3);
      return var6;
   }

   private native long iWriteAt(long var1, long var3, Buffer var5, int var6, long var7) throws MPIException;

   public Status read(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      int var4 = 0;
      boolean var5 = false;
      Status var6 = new Status();
      if(var1 instanceof Buffer && !(var5 = ((Buffer)var1).isDirect())) {
         var4 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.read(this.handle, var1, var5, var4, var2, var3.handle, var3.baseType, var6.data);
      return var6;
   }

   private native void read(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, long[] var10) throws MPIException;

   public Status readAll(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      int var4 = 0;
      boolean var5 = false;
      Status var6 = new Status();
      if(var1 instanceof Buffer && !(var5 = ((Buffer)var1).isDirect())) {
         var4 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.readAll(this.handle, var1, var5, var4, var2, var3.handle, var3.baseType, var6.data);
      return var6;
   }

   private native void readAll(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, long[] var10) throws MPIException;

   public Status write(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      int var4 = 0;
      boolean var5 = false;
      Status var6 = new Status();
      if(var1 instanceof Buffer && !(var5 = ((Buffer)var1).isDirect())) {
         var4 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.write(this.handle, var1, var5, var4, var2, var3.handle, var3.baseType, var6.data);
      return var6;
   }

   private native void write(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, long[] var10) throws MPIException;

   public Status writeAll(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      int var4 = 0;
      boolean var5 = false;
      Status var6 = new Status();
      if(var1 instanceof Buffer && !(var5 = ((Buffer)var1).isDirect())) {
         var4 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.writeAll(this.handle, var1, var5, var4, var2, var3.handle, var3.baseType, var6.data);
      return var6;
   }

   private native void writeAll(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, long[] var10) throws MPIException;

   public Request iRead(Buffer var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var4 = new Request(this.iRead(this.handle, var1, var2, var3.handle));
      var4.addRecvBufRef(var1);
      return var4;
   }

   private native long iRead(long var1, Buffer var3, int var4, long var5) throws MPIException;

   public Request iWrite(Buffer var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var4 = new Request(this.iWrite(this.handle, var1, var2, var3.handle));
      var4.addSendBufRef(var1);
      return var4;
   }

   private native long iWrite(long var1, Buffer var3, int var4, long var5) throws MPIException;

   public void seek(long var1, int var3) throws MPIException {
      MPI.check();
      this.seek(this.handle, var1, var3);
   }

   private native void seek(long var1, long var3, int var5) throws MPIException;

   public long getPosition() throws MPIException {
      MPI.check();
      return this.getPosition(this.handle);
   }

   private native long getPosition(long var1) throws MPIException;

   public long getByteOffset(long var1) throws MPIException {
      MPI.check();
      return this.getByteOffset(this.handle, var1);
   }

   private native long getByteOffset(long var1, long var3) throws MPIException;

   public Status readShared(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      int var4 = 0;
      boolean var5 = false;
      Status var6 = new Status();
      if(var1 instanceof Buffer && !(var5 = ((Buffer)var1).isDirect())) {
         var4 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.readShared(this.handle, var1, var5, var4, var2, var3.handle, var3.baseType, var6.data);
      return var6;
   }

   private native void readShared(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, long[] var10) throws MPIException;

   public Status writeShared(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      int var4 = 0;
      boolean var5 = false;
      Status var6 = new Status();
      if(var1 instanceof Buffer && !(var5 = ((Buffer)var1).isDirect())) {
         var4 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.writeShared(this.handle, var1, var5, var4, var2, var3.handle, var3.baseType, var6.data);
      return var6;
   }

   private native void writeShared(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, long[] var10) throws MPIException;

   public Request iReadShared(Buffer var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var4 = new Request(this.iReadShared(this.handle, var1, var2, var3.handle));
      var4.addRecvBufRef(var1);
      return var4;
   }

   private native long iReadShared(long var1, Buffer var3, int var4, long var5) throws MPIException;

   public Request iWriteShared(Buffer var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var4 = new Request(this.iWriteShared(this.handle, var1, var2, var3.handle));
      var4.addSendBufRef(var1);
      return var4;
   }

   private native long iWriteShared(long var1, Buffer var3, int var4, long var5) throws MPIException;

   public Status readOrdered(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      int var4 = 0;
      boolean var5 = false;
      Status var6 = new Status();
      if(var1 instanceof Buffer && !(var5 = ((Buffer)var1).isDirect())) {
         var4 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.readOrdered(this.handle, var1, var5, var4, var2, var3.handle, var3.baseType, var6.data);
      return var6;
   }

   private native void readOrdered(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, long[] var10) throws MPIException;

   public Status writeOrdered(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      int var4 = 0;
      boolean var5 = false;
      Status var6 = new Status();
      if(var1 instanceof Buffer && !(var5 = ((Buffer)var1).isDirect())) {
         var4 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.writeOrdered(this.handle, var1, var5, var4, var2, var3.handle, var3.baseType, var6.data);
      return var6;
   }

   private native void writeOrdered(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, long[] var10) throws MPIException;

   public void seekShared(long var1, int var3) throws MPIException {
      MPI.check();
      this.seekShared(this.handle, var1, var3);
   }

   private native void seekShared(long var1, long var3, int var5) throws MPIException;

   public long getPositionShared() throws MPIException {
      MPI.check();
      return this.getPositionShared(this.handle);
   }

   private native long getPositionShared(long var1) throws MPIException;

   public void readAtAllBegin(long var1, Object var3, int var4, Datatype var5) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var3)) {
         this.readAtAllBegin(this.handle, var1, var3, var4, var5.handle);
      } else {
         int var6 = 0;
         Status var7 = new Status();
         if(MPI.isHeapBuffer(var3)) {
            var6 = var5.getOffset(var3);
            var3 = ((Buffer)var3).array();
         }

         this.readAtAll(this.handle, var1, var3, false, var6, var4, var5.handle, var5.baseType, var7.data);
         this.beginStatus = var7;
      }

   }

   private native void readAtAllBegin(long var1, long var3, Object var5, int var6, long var7) throws MPIException;

   public Status readAtAllEnd(Object var1) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var1)) {
         Status var2 = new Status();
         this.readAtAllEnd(this.handle, var1, var2.data);
         return var2;
      } else {
         return this.getBeginStatus();
      }
   }

   private native void readAtAllEnd(long var1, Object var3, long[] var4) throws MPIException;

   public void writeAtAllBegin(long var1, Object var3, int var4, Datatype var5) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var3)) {
         this.writeAtAllBegin(this.handle, var1, var3, var4, var5.handle);
      } else {
         int var6 = 0;
         Status var7 = new Status();
         if(MPI.isHeapBuffer(var3)) {
            var6 = var5.getOffset(var3);
            var3 = ((Buffer)var3).array();
         }

         this.writeAtAll(this.handle, var1, var3, false, var6, var4, var5.handle, var5.baseType, var7.data);
         this.beginStatus = var7;
      }

   }

   private native void writeAtAllBegin(long var1, long var3, Object var5, int var6, long var7) throws MPIException;

   public Status writeAtAllEnd(Object var1) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var1)) {
         Status var2 = new Status();
         this.writeAtAllEnd(this.handle, var1, var2.data);
         return var2;
      } else {
         return this.getBeginStatus();
      }
   }

   private native void writeAtAllEnd(long var1, Object var3, long[] var4) throws MPIException;

   public void readAllBegin(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var1)) {
         this.readAllBegin(this.handle, var1, var2, var3.handle);
      } else {
         int var4 = 0;
         Status var5 = new Status();
         if(MPI.isHeapBuffer(var1)) {
            var4 = var3.getOffset(var1);
            var1 = ((Buffer)var1).array();
         }

         this.readAll(this.handle, var1, false, var4, var2, var3.handle, var3.baseType, var5.data);
         this.beginStatus = var5;
      }

   }

   private native void readAllBegin(long var1, Object var3, int var4, long var5) throws MPIException;

   public Status readAllEnd(Object var1) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var1)) {
         Status var2 = new Status();
         this.readAllEnd(this.handle, var1, var2.data);
         return var2;
      } else {
         return this.getBeginStatus();
      }
   }

   private native void readAllEnd(long var1, Object var3, long[] var4) throws MPIException;

   public void writeAllBegin(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var1)) {
         this.writeAllBegin(this.handle, var1, var2, var3.handle);
      } else {
         int var4 = 0;
         Status var5 = new Status();
         if(MPI.isHeapBuffer(var1)) {
            var4 = var3.getOffset(var1);
            var1 = ((Buffer)var1).array();
         }

         this.writeAll(this.handle, var1, false, var4, var2, var3.handle, var3.baseType, var5.data);
         this.beginStatus = var5;
      }

   }

   private native void writeAllBegin(long var1, Object var3, int var4, long var5) throws MPIException;

   public Status writeAllEnd(Object var1) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var1)) {
         Status var2 = new Status();
         this.writeAllEnd(this.handle, var1, var2.data);
         return var2;
      } else {
         return this.getBeginStatus();
      }
   }

   private native void writeAllEnd(long var1, Object var3, long[] var4) throws MPIException;

   public void readOrderedBegin(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var1)) {
         this.readOrderedBegin(this.handle, var1, var2, var3.handle);
      } else {
         int var4 = 0;
         Status var5 = new Status();
         if(MPI.isHeapBuffer(var1)) {
            var4 = var3.getOffset(var1);
            var1 = ((Buffer)var1).array();
         }

         this.readOrdered(this.handle, var1, false, var4, var2, var3.handle, var3.baseType, var5.data);
         this.beginStatus = var5;
      }

   }

   private native void readOrderedBegin(long var1, Object var3, int var4, long var5) throws MPIException;

   public Status readOrderedEnd(Object var1) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var1)) {
         Status var2 = new Status();
         this.readOrderedEnd(this.handle, var1, var2.data);
         return var2;
      } else {
         return this.getBeginStatus();
      }
   }

   private native void readOrderedEnd(long var1, Object var3, long[] var4) throws MPIException;

   public void writeOrderedBegin(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var1)) {
         this.writeOrderedBegin(this.handle, var1, var2, var3.handle);
      } else {
         int var4 = 0;
         Status var5 = new Status();
         if(MPI.isHeapBuffer(var1)) {
            var4 = var3.getOffset(var1);
            var1 = ((Buffer)var1).array();
         }

         this.writeOrdered(this.handle, var1, false, var4, var2, var3.handle, var3.baseType, var5.data);
         this.beginStatus = var5;
      }

   }

   private native void writeOrderedBegin(long var1, Object var3, int var4, long var5) throws MPIException;

   public Status writeOrderedEnd(Object var1) throws MPIException {
      MPI.check();
      if(MPI.isDirectBuffer(var1)) {
         Status var2 = new Status();
         this.writeOrderedEnd(this.handle, var1, var2.data);
         return var2;
      } else {
         return this.getBeginStatus();
      }
   }

   private native void writeOrderedEnd(long var1, Object var3, long[] var4) throws MPIException;

   private Status getBeginStatus() {
      Status var1 = this.beginStatus;
      this.beginStatus = null;
      return var1;
   }

   public int getTypeExtent(Datatype var1) throws MPIException {
      MPI.check();
      return this.getTypeExtent(this.handle, var1.handle) / var1.baseSize;
   }

   private native int getTypeExtent(long var1, long var3) throws MPIException;

   public void setAtomicity(boolean var1) throws MPIException {
      MPI.check();
      this.setAtomicity(this.handle, var1);
   }

   private native void setAtomicity(long var1, boolean var3) throws MPIException;

   public void sync() throws MPIException {
      MPI.check();
      this.sync(this.handle);
   }

   private native void sync(long var1) throws MPIException;
}
