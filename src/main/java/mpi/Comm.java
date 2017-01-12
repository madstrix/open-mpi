package mpi;

import java.nio.Buffer;
import mpi.Datatype;
import mpi.Errhandler;
import mpi.Freeable;
import mpi.Group;
import mpi.Info;
import mpi.Intercomm;
import mpi.MPI;
import mpi.MPIException;
import mpi.Op;
import mpi.Prequest;
import mpi.Request;
import mpi.Status;

public class Comm implements Freeable, Cloneable {

   public static final int TYPE_SHARED = 0;
   protected static final int SELF = 1;
   protected static final int WORLD = 2;
   protected long handle;
   private Request request;
   private static long nullHandle;


   private static native void init();

   protected Comm() {}

   protected Comm(long var1) {
      this.handle = var1;
   }

   protected Comm(long[] var1) {
      this.handle = var1[0];
      this.request = new Request(var1[1]);
   }

   protected final void setType(int var1) {
      this.getComm(var1);
   }

   private native void getComm(int var1);

   public Comm clone() {
      try {
         return this.dup();
      } catch (MPIException var2) {
         throw new RuntimeException(var2.getMessage());
      }
   }

   public Comm dup() throws MPIException {
      MPI.check();
      return new Comm(this.dup(this.handle));
   }

   protected final native long dup(long var1) throws MPIException;

   public Comm iDup() throws MPIException {
      MPI.check();
      return new Comm(this.iDup(this.handle));
   }

   protected final native long[] iDup(long var1) throws MPIException;

   public Comm dupWithInfo(Info var1) throws MPIException {
      MPI.check();
      return new Comm(this.dupWithInfo(this.handle, var1.handle));
   }

   protected final native long dupWithInfo(long var1, long var3) throws MPIException;

   public final Request getRequest() {
      return this.request;
   }

   public final int getSize() throws MPIException {
      MPI.check();
      return this.getSize(this.handle);
   }

   private native int getSize(long var1) throws MPIException;

   public final int getRank() throws MPIException {
      MPI.check();
      return this.getRank(this.handle);
   }

   private native int getRank(long var1) throws MPIException;

   public static int compare(Comm var0, Comm var1) throws MPIException {
      MPI.check();
      return compare(var0.handle, var1.handle);
   }

   private static native int compare(long var0, long var2) throws MPIException;

   public final void free() throws MPIException {
      MPI.check();
      this.handle = this.free(this.handle);
   }

   private native long free(long var1) throws MPIException;

   public final boolean isNull() {
      return this.handle == nullHandle;
   }

   public final void setInfo(Info var1) throws MPIException {
      MPI.check();
      this.setInfo(this.handle, var1.handle);
   }

   private native void setInfo(long var1, long var3) throws MPIException;

   public final Info getInfo() throws MPIException {
      MPI.check();
      return new Info(this.getInfo(this.handle));
   }

   private native long getInfo(long var1) throws MPIException;

   public final void disconnect() throws MPIException {
      MPI.check();
      this.handle = this.disconnect(this.handle);
   }

   private native long disconnect(long var1) throws MPIException;

   public final Group getGroup() throws MPIException {
      MPI.check();
      return new Group(this.getGroup(this.handle));
   }

   private native long getGroup(long var1);

   public final boolean isInter() throws MPIException {
      MPI.check();
      return this.isInter(this.handle);
   }

   private native boolean isInter(long var1) throws MPIException;

   public final Intercomm createIntercomm(Comm var1, int var2, int var3, int var4) throws MPIException {
      MPI.check();
      return new Intercomm(this.createIntercomm(this.handle, var1.handle, var2, var3, var4));
   }

   private native long createIntercomm(long var1, long var3, int var5, int var6, int var7) throws MPIException;

   public final void send(Object var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      if(var1 instanceof Buffer && !(var7 = ((Buffer)var1).isDirect())) {
         var6 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.send(this.handle, var1, var7, var6, var2, var3.handle, var3.baseType, var4, var5);
   }

   private native void send(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, int var10, int var11) throws MPIException;

   public final Status recv(Object var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      if(var1 instanceof Buffer && !(var7 = ((Buffer)var1).isDirect())) {
         var6 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      Status var8 = new Status();
      this.recv(this.handle, var1, var7, var6, var2, var3.handle, var3.baseType, var4, var5, var8.data);
      return var8;
   }

   private native void recv(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, int var10, int var11, long[] var12) throws MPIException;

   public final Status sendRecv(Object var1, int var2, Datatype var3, int var4, int var5, Object var6, int var7, Datatype var8, int var9, int var10) throws MPIException {
      MPI.check();
      int var11 = 0;
      int var12 = 0;
      boolean var13 = false;
      boolean var14 = false;
      if(var1 instanceof Buffer && !(var13 = ((Buffer)var1).isDirect())) {
         var11 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var6 instanceof Buffer && !(var14 = ((Buffer)var6).isDirect())) {
         var12 = var8.getOffset(var6);
         var6 = ((Buffer)var6).array();
      }

      Status var15 = new Status();
      this.sendRecv(this.handle, var1, var13, var11, var2, var3.handle, var3.baseType, var4, var5, var6, var14, var12, var7, var8.handle, var8.baseType, var9, var10, var15.data);
      return var15;
   }

   private native void sendRecv(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, int var10, int var11, Object var12, boolean var13, int var14, int var15, long var16, int var18, int var19, int var20, long[] var21) throws MPIException;

   public final Status sendRecvReplace(Object var1, int var2, Datatype var3, int var4, int var5, int var6, int var7) throws MPIException {
      MPI.check();
      int var8 = 0;
      boolean var9 = false;
      if(var1 instanceof Buffer && !(var9 = ((Buffer)var1).isDirect())) {
         var8 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      Status var10 = new Status();
      this.sendRecvReplace(this.handle, var1, var9, var8, var2, var3.handle, var3.baseType, var4, var5, var6, var7, var10.data);
      return var10;
   }

   private native void sendRecvReplace(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, int var10, int var11, int var12, int var13, long[] var14) throws MPIException;

   public final void bSend(Object var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      if(var1 instanceof Buffer && !(var7 = ((Buffer)var1).isDirect())) {
         var6 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.bSend(this.handle, var1, var7, var6, var2, var3.handle, var3.baseType, var4, var5);
   }

   private native void bSend(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, int var10, int var11) throws MPIException;

   public final void sSend(Object var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      if(var1 instanceof Buffer && !(var7 = ((Buffer)var1).isDirect())) {
         var6 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.sSend(this.handle, var1, var7, var6, var2, var3.handle, var3.baseType, var4, var5);
   }

   private native void sSend(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, int var10, int var11) throws MPIException;

   public final void rSend(Object var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      if(var1 instanceof Buffer && !(var7 = ((Buffer)var1).isDirect())) {
         var6 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.rSend(this.handle, var1, var7, var6, var2, var3.handle, var3.baseType, var4, var5);
   }

   private native void rSend(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, int var10, int var11) throws MPIException;

   public final Request iSend(Buffer var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var6 = new Request(this.iSend(this.handle, var1, var2, var3.handle, var4, var5));
      var6.addSendBufRef(var1);
      return var6;
   }

   private native long iSend(long var1, Buffer var3, int var4, long var5, int var7, int var8) throws MPIException;

   public final Request ibSend(Buffer var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var6 = new Request(this.ibSend(this.handle, var1, var2, var3.handle, var4, var5));
      var6.addSendBufRef(var1);
      return var6;
   }

   private native long ibSend(long var1, Buffer var3, int var4, long var5, int var7, int var8) throws MPIException;

   public final Request isSend(Buffer var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var6 = new Request(this.isSend(this.handle, var1, var2, var3.handle, var4, var5));
      var6.addSendBufRef(var1);
      return var6;
   }

   private native long isSend(long var1, Buffer var3, int var4, long var5, int var7, int var8) throws MPIException;

   public final Request irSend(Buffer var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var6 = new Request(this.irSend(this.handle, var1, var2, var3.handle, var4, var5));
      var6.addSendBufRef(var1);
      return var6;
   }

   private native long irSend(long var1, Buffer var3, int var4, long var5, int var7, int var8) throws MPIException;

   public final Request iRecv(Buffer var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var6 = new Request(this.iRecv(this.handle, var1, var2, var3.handle, var4, var5));
      var6.addRecvBufRef(var1);
      return var6;
   }

   private native long iRecv(long var1, Buffer var3, int var4, long var5, int var7, int var8) throws MPIException;

   public final Prequest sendInit(Buffer var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Prequest var6 = new Prequest(this.sendInit(this.handle, var1, var2, var3.handle, var4, var5));
      var6.addSendBufRef(var1);
      return var6;
   }

   private native long sendInit(long var1, Buffer var3, int var4, long var5, int var7, int var8) throws MPIException;

   public final Prequest bSendInit(Buffer var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Prequest var6 = new Prequest(this.bSendInit(this.handle, var1, var2, var3.handle, var4, var5));
      var6.addSendBufRef(var1);
      return var6;
   }

   private native long bSendInit(long var1, Buffer var3, int var4, long var5, int var7, int var8) throws MPIException;

   public final Prequest sSendInit(Buffer var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Prequest var6 = new Prequest(this.sSendInit(this.handle, var1, var2, var3.handle, var4, var5));
      var6.addSendBufRef(var1);
      return var6;
   }

   private native long sSendInit(long var1, Buffer var3, int var4, long var5, int var7, int var8) throws MPIException;

   public final Prequest rSendInit(Buffer var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Prequest var6 = new Prequest(this.rSendInit(this.handle, var1, var2, var3.handle, var4, var5));
      var6.addSendBufRef(var1);
      return var6;
   }

   private native long rSendInit(long var1, Buffer var3, int var4, long var5, int var7, int var8) throws MPIException;

   public final Prequest recvInit(Buffer var1, int var2, Datatype var3, int var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Prequest var6 = new Prequest(this.recvInit(this.handle, var1, var2, var3.handle, var4, var5));
      var6.addRecvBufRef(var1);
      return var6;
   }

   private native long recvInit(long var1, Buffer var3, int var4, long var5, int var7, int var8) throws MPIException;

   public final int pack(Object var1, int var2, Datatype var3, byte[] var4, int var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      if(var1 instanceof Buffer && !(var7 = ((Buffer)var1).isDirect())) {
         var6 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      return this.pack(this.handle, var1, var7, var6, var2, var3.handle, var4, var5);
   }

   private native int pack(long var1, Object var3, boolean var4, int var5, int var6, long var7, byte[] var9, int var10) throws MPIException;

   public final int unpack(byte[] var1, int var2, Object var3, int var4, Datatype var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      if(var3 instanceof Buffer && !(var7 = ((Buffer)var3).isDirect())) {
         var6 = var5.getOffset(var3);
         var3 = ((Buffer)var3).array();
      }

      return this.unpack(this.handle, var1, var2, var3, var7, var6, var4, var5.handle);
   }

   private native int unpack(long var1, byte[] var3, int var4, Object var5, boolean var6, int var7, int var8, long var9) throws MPIException;

   public final int packSize(int var1, Datatype var2) throws MPIException {
      MPI.check();
      return this.packSize(this.handle, var1, var2.handle);
   }

   private native int packSize(long var1, int var3, long var4) throws MPIException;

   public final Status iProbe(int var1, int var2) throws MPIException {
      MPI.check();
      return this.iProbe(this.handle, var1, var2);
   }

   private native Status iProbe(long var1, int var3, int var4) throws MPIException;

   public final Status probe(int var1, int var2) throws MPIException {
      MPI.check();
      Status var3 = new Status();
      this.probe(this.handle, var1, var2, var3.data);
      return var3;
   }

   private native void probe(long var1, int var3, int var4, long[] var5) throws MPIException;

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

   public final void setAttr(int var1, Object var2) throws MPIException {
      MPI.check();
      this.setAttr(this.handle, var1, MPI.attrSet(var2));
   }

   private native void setAttr(long var1, int var3, byte[] var4) throws MPIException;

   public final Object getAttr(int var1) throws MPIException {
      MPI.check();
      if(var1 != MPI.TAG_UB && var1 != MPI.HOST && var1 != MPI.IO && var1 != MPI.APPNUM && var1 != MPI.LASTUSEDCODE && var1 != MPI.UNIVERSE_SIZE) {
         if(var1 == MPI.WTIME_IS_GLOBAL) {
            Integer var2 = (Integer)this.getAttr_predefined(this.handle, var1);
            return var2 == null?null:Boolean.valueOf(var2.intValue() != 0);
         } else {
            return MPI.attrGet(this.getAttr(this.handle, var1));
         }
      } else {
         return this.getAttr_predefined(this.handle, var1);
      }
   }

   private native Object getAttr_predefined(long var1, int var3) throws MPIException;

   private native byte[] getAttr(long var1, int var3) throws MPIException;

   public final void deleteAttr(int var1) throws MPIException {
      MPI.check();
      this.deleteAttr(this.handle, var1);
   }

   private native void deleteAttr(long var1, int var3) throws MPIException;

   public final int getTopology() throws MPIException {
      MPI.check();
      return this.getTopology(this.handle);
   }

   private native int getTopology(long var1) throws MPIException;

   public final void abort(int var1) throws MPIException {
      MPI.check();
      this.abort(this.handle, var1);
   }

   private native void abort(long var1, int var3) throws MPIException;

   public final void setErrhandler(Errhandler var1) throws MPIException {
      MPI.check();
      this.setErrhandler(this.handle, var1.handle);
   }

   private native void setErrhandler(long var1, long var3) throws MPIException;

   public final Errhandler getErrhandler() throws MPIException {
      MPI.check();
      return new Errhandler(this.getErrhandler(this.handle));
   }

   private native long getErrhandler(long var1);

   public final void barrier() throws MPIException {
      MPI.check();
      this.barrier(this.handle);
   }

   private native void barrier(long var1) throws MPIException;

   public final Request iBarrier() throws MPIException {
      MPI.check();
      return new Request(this.iBarrier(this.handle));
   }

   private native long iBarrier(long var1) throws MPIException;

   public final void bcast(Object var1, int var2, Datatype var3, int var4) throws MPIException {
      MPI.check();
      int var5 = 0;
      boolean var6 = false;
      if(var1 instanceof Buffer && !(var6 = ((Buffer)var1).isDirect())) {
         var5 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.bcast(this.handle, var1, var6, var5, var2, var3.handle, var3.baseType, var4);
   }

   private native void bcast(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, int var10) throws MPIException;

   public final Request iBcast(Buffer var1, int var2, Datatype var3, int var4) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var5 = new Request(this.iBcast(this.handle, var1, var2, var3.handle, var4));
      var5.addSendBufRef(var1);
      return var5;
   }

   private native long iBcast(long var1, Buffer var3, int var4, long var5, int var7) throws MPIException;

   public final void gather(Object var1, int var2, Datatype var3, Object var4, int var5, Datatype var6, int var7) throws MPIException {
      MPI.check();
      int var8 = 0;
      int var9 = 0;
      boolean var10 = false;
      boolean var11 = false;
      if(var1 instanceof Buffer && !(var10 = ((Buffer)var1).isDirect())) {
         var8 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var4 instanceof Buffer && !(var11 = ((Buffer)var4).isDirect())) {
         var9 = var6.getOffset(var4);
         var4 = ((Buffer)var4).array();
      }

      this.gather(this.handle, var1, var10, var8, var2, var3.handle, var3.baseType, var4, var11, var9, var5, var6.handle, var6.baseType, var7);
   }

   public final void gather(Object var1, int var2, Datatype var3, int var4) throws MPIException {
      MPI.check();
      int var5 = 0;
      boolean var6 = false;
      if(var1 instanceof Buffer && !(var6 = ((Buffer)var1).isDirect())) {
         var5 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.gather(this.handle, (Object)null, false, 0, 0, 0L, 0, var1, var6, var5, var2, var3.handle, var3.baseType, var4);
   }

   private native void gather(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, Object var10, boolean var11, int var12, int var13, long var14, int var16, int var17) throws MPIException;

   public final Request iGather(Buffer var1, int var2, Datatype var3, Buffer var4, int var5, Datatype var6, int var7) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var4);
      Request var8 = new Request(this.iGather(this.handle, var1, var2, var3.handle, var4, var5, var6.handle, var7));
      var8.addSendBufRef(var1);
      var8.addRecvBufRef(var4);
      return var8;
   }

   public final Request iGather(Buffer var1, int var2, Datatype var3, int var4) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var5 = new Request(this.iGather(this.handle, (Buffer)null, 0, 0L, var1, var2, var3.handle, var4));
      var5.addRecvBufRef(var1);
      return var5;
   }

   private native long iGather(long var1, Buffer var3, int var4, long var5, Buffer var7, int var8, long var9, int var11) throws MPIException;

   public final void gatherv(Object var1, int var2, Datatype var3, Object var4, int[] var5, int[] var6, Datatype var7, int var8) throws MPIException {
      MPI.check();
      int var9 = 0;
      int var10 = 0;
      boolean var11 = false;
      boolean var12 = false;
      if(var1 instanceof Buffer && !(var11 = ((Buffer)var1).isDirect())) {
         var9 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var4 instanceof Buffer && !(var12 = ((Buffer)var4).isDirect())) {
         var10 = var7.getOffset(var4);
         var4 = ((Buffer)var4).array();
      }

      this.gatherv(this.handle, var1, var11, var9, var2, var3.handle, var3.baseType, var4, var12, var10, var5, var6, var7.handle, var7.baseType, var8);
   }

   public final void gatherv(Object var1, int[] var2, int[] var3, Datatype var4, int var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      if(var1 instanceof Buffer && !(var7 = ((Buffer)var1).isDirect())) {
         var6 = var4.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.gatherv(this.handle, (Object)null, false, 0, 0, 0L, 0, var1, var7, var6, var2, var3, var4.handle, var4.baseType, var5);
   }

   public final void gatherv(Object var1, int var2, Datatype var3, int var4) throws MPIException {
      MPI.check();
      int var5 = 0;
      boolean var6 = false;
      if(var1 instanceof Buffer && !(var6 = ((Buffer)var1).isDirect())) {
         var5 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.gatherv(this.handle, var1, var6, var5, var2, var3.handle, var3.baseType, (Object)null, false, 0, (int[])null, (int[])null, 0L, 0, var4);
   }

   private native void gatherv(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, Object var10, boolean var11, int var12, int[] var13, int[] var14, long var15, int var17, int var18) throws MPIException;

   public final Request iGatherv(Buffer var1, int var2, Datatype var3, Buffer var4, int[] var5, int[] var6, Datatype var7, int var8) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var4);
      Request var9 = new Request(this.iGatherv(this.handle, var1, var2, var3.handle, var4, var5, var6, var7.handle, var8));
      var9.addSendBufRef(var1);
      return var9;
   }

   public final Request iGatherv(Buffer var1, int[] var2, int[] var3, Datatype var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var6 = new Request(this.iGatherv(this.handle, (Buffer)null, 0, 0L, var1, var2, var3, var4.handle, var5));
      var6.addRecvBufRef(var1);
      return var6;
   }

   public final Request iGatherv(Buffer var1, int var2, Datatype var3, int var4) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var5 = new Request(this.iGatherv(this.handle, var1, var2, var3.handle, (Buffer)null, (int[])null, (int[])null, 0L, var4));
      var5.addSendBufRef(var1);
      return var5;
   }

   private native long iGatherv(long var1, Buffer var3, int var4, long var5, Buffer var7, int[] var8, int[] var9, long var10, int var12) throws MPIException;

   public final void scatter(Object var1, int var2, Datatype var3, Object var4, int var5, Datatype var6, int var7) throws MPIException {
      MPI.check();
      int var8 = 0;
      int var9 = 0;
      boolean var10 = false;
      boolean var11 = false;
      if(var1 instanceof Buffer && !(var10 = ((Buffer)var1).isDirect())) {
         var8 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var4 instanceof Buffer && !(var11 = ((Buffer)var4).isDirect())) {
         var9 = var6.getOffset(var4);
         var4 = ((Buffer)var4).array();
      }

      this.scatter(this.handle, var1, var10, var8, var2, var3.handle, var3.baseType, var4, var11, var9, var5, var6.handle, var6.baseType, var7);
   }

   public final void scatter(Object var1, int var2, Datatype var3, int var4) throws MPIException {
      MPI.check();
      int var5 = 0;
      boolean var6 = false;
      if(var1 instanceof Buffer && !(var6 = ((Buffer)var1).isDirect())) {
         var5 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.scatter(this.handle, var1, var6, var5, var2, var3.handle, var3.baseType, (Object)null, false, 0, 0, 0L, 0, var4);
   }

   private native void scatter(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, Object var10, boolean var11, int var12, int var13, long var14, int var16, int var17) throws MPIException;

   public final Request iScatter(Buffer var1, int var2, Datatype var3, Buffer var4, int var5, Datatype var6, int var7) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var4);
      Request var8 = new Request(this.iScatter(this.handle, var1, var2, var3.handle, var4, var5, var6.handle, var7));
      var8.addSendBufRef(var1);
      var8.addRecvBufRef(var4);
      return var8;
   }

   public final Request iScatter(Buffer var1, int var2, Datatype var3, int var4) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var5 = new Request(this.iScatter(this.handle, var1, var2, var3.handle, (Buffer)null, 0, 0L, var4));
      var5.addSendBufRef(var1);
      return var5;
   }

   private native long iScatter(long var1, Buffer var3, int var4, long var5, Buffer var7, int var8, long var9, int var11) throws MPIException;

   public final void scatterv(Object var1, int[] var2, int[] var3, Datatype var4, Object var5, int var6, Datatype var7, int var8) throws MPIException {
      MPI.check();
      int var9 = 0;
      int var10 = 0;
      boolean var11 = false;
      boolean var12 = false;
      if(var1 instanceof Buffer && !(var11 = ((Buffer)var1).isDirect())) {
         var9 = var4.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var5 instanceof Buffer && !(var12 = ((Buffer)var5).isDirect())) {
         var10 = var7.getOffset(var5);
         var5 = ((Buffer)var5).array();
      }

      this.scatterv(this.handle, var1, var11, var9, var2, var3, var4.handle, var4.baseType, var5, var12, var10, var6, var7.handle, var7.baseType, var8);
   }

   public final void scatterv(Object var1, int[] var2, int[] var3, Datatype var4, int var5) throws MPIException {
      MPI.check();
      int var6 = 0;
      boolean var7 = false;
      if(var1 instanceof Buffer && !(var7 = ((Buffer)var1).isDirect())) {
         var6 = var4.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.scatterv(this.handle, var1, var7, var6, var2, var3, var4.handle, var4.baseType, (Object)null, false, 0, 0, 0L, 0, var5);
   }

   public final void scatterv(Object var1, int var2, Datatype var3, int var4) throws MPIException {
      MPI.check();
      int var5 = 0;
      boolean var6 = false;
      if(var1 instanceof Buffer && !(var6 = ((Buffer)var1).isDirect())) {
         var5 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.scatterv(this.handle, (Object)null, false, 0, (int[])null, (int[])null, 0L, 0, var1, var6, var5, var2, var3.handle, var3.baseType, var4);
   }

   private native void scatterv(long var1, Object var3, boolean var4, int var5, int[] var6, int[] var7, long var8, int var10, Object var11, boolean var12, int var13, int var14, long var15, int var17, int var18) throws MPIException;

   public final Request iScatterv(Buffer var1, int[] var2, int[] var3, Datatype var4, Buffer var5, int var6, Datatype var7, int var8) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var5);
      Request var9 = new Request(this.iScatterv(this.handle, var1, var2, var3, var4.handle, var5, var6, var7.handle, var8));
      var9.addSendBufRef(var1);
      var9.addRecvBufRef(var5);
      return var9;
   }

   public final Request iScatterv(Buffer var1, int[] var2, int[] var3, Datatype var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var6 = new Request(this.iScatterv(this.handle, var1, var2, var3, var4.handle, (Buffer)null, 0, 0L, var5));
      var6.addSendBufRef(var1);
      return var6;
   }

   public final Request iScatterv(Buffer var1, int var2, Datatype var3, int var4) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var5 = new Request(this.iScatterv(this.handle, (Buffer)null, (int[])null, (int[])null, 0L, var1, var2, var3.handle, var4));
      var5.addRecvBufRef(var1);
      return var5;
   }

   private native long iScatterv(long var1, Buffer var3, int[] var4, int[] var5, long var6, Buffer var8, int var9, long var10, int var12) throws MPIException;

   public final void allGather(Object var1, int var2, Datatype var3, Object var4, int var5, Datatype var6) throws MPIException {
      MPI.check();
      int var7 = 0;
      int var8 = 0;
      boolean var9 = false;
      boolean var10 = false;
      if(var1 instanceof Buffer && !(var9 = ((Buffer)var1).isDirect())) {
         var7 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var4 instanceof Buffer && !(var10 = ((Buffer)var4).isDirect())) {
         var8 = var6.getOffset(var4);
         var4 = ((Buffer)var4).array();
      }

      this.allGather(this.handle, var1, var9, var7, var2, var3.handle, var3.baseType, var4, var10, var8, var5, var6.handle, var6.baseType);
   }

   public final void allGather(Object var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      int var4 = 0;
      boolean var5 = false;
      if(var1 instanceof Buffer && !(var5 = ((Buffer)var1).isDirect())) {
         var4 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.allGather(this.handle, (Object)null, false, 0, 0, 0L, 0, var1, var5, var4, var2, var3.handle, var3.baseType);
   }

   private native void allGather(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, Object var10, boolean var11, int var12, int var13, long var14, int var16) throws MPIException;

   public final Request iAllGather(Buffer var1, int var2, Datatype var3, Buffer var4, int var5, Datatype var6) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var4);
      Request var7 = new Request(this.iAllGather(this.handle, var1, var2, var3.handle, var4, var5, var6.handle));
      var7.addSendBufRef(var1);
      var7.addRecvBufRef(var4);
      return var7;
   }

   public final Request iAllGather(Buffer var1, int var2, Datatype var3) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var4 = new Request(this.iAllGather(this.handle, (Buffer)null, 0, 0L, var1, var2, var3.handle));
      var4.addRecvBufRef(var1);
      return var4;
   }

   private native long iAllGather(long var1, Buffer var3, int var4, long var5, Buffer var7, int var8, long var9) throws MPIException;

   public final void allGatherv(Object var1, int var2, Datatype var3, Object var4, int[] var5, int[] var6, Datatype var7) throws MPIException {
      MPI.check();
      int var8 = 0;
      int var9 = 0;
      boolean var10 = false;
      boolean var11 = false;
      if(var1 instanceof Buffer && !(var10 = ((Buffer)var1).isDirect())) {
         var8 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var4 instanceof Buffer && !(var11 = ((Buffer)var4).isDirect())) {
         var9 = var7.getOffset(var4);
         var4 = ((Buffer)var4).array();
      }

      this.allGatherv(this.handle, var1, var10, var8, var2, var3.handle, var3.baseType, var4, var11, var9, var5, var6, var7.handle, var7.baseType);
   }

   public final void allGatherv(Object var1, int[] var2, int[] var3, Datatype var4) throws MPIException {
      MPI.check();
      int var5 = 0;
      boolean var6 = false;
      if(var1 instanceof Buffer && !(var6 = ((Buffer)var1).isDirect())) {
         var5 = var4.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.allGatherv(this.handle, (Object)null, false, 0, 0, 0L, 0, var1, var6, var5, var2, var3, var4.handle, var4.baseType);
   }

   private native void allGatherv(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, Object var10, boolean var11, int var12, int[] var13, int[] var14, long var15, int var17) throws MPIException;

   public final Request iAllGatherv(Buffer var1, int var2, Datatype var3, Buffer var4, int[] var5, int[] var6, Datatype var7) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var4);
      Request var8 = new Request(this.iAllGatherv(this.handle, var1, var2, var3.handle, var4, var5, var6, var7.handle));
      var8.addSendBufRef(var1);
      var8.addRecvBufRef(var4);
      return var8;
   }

   public final Request iAllGatherv(Buffer var1, int[] var2, int[] var3, Datatype var4) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      Request var5 = new Request(this.iAllGatherv(this.handle, (Buffer)null, 0, 0L, var1, var2, var3, var4.handle));
      var5.addRecvBufRef(var1);
      return var5;
   }

   private native long iAllGatherv(long var1, Buffer var3, int var4, long var5, Buffer var7, int[] var8, int[] var9, long var10) throws MPIException;

   public final void allToAll(Object var1, int var2, Datatype var3, Object var4, int var5, Datatype var6) throws MPIException {
      MPI.check();
      int var7 = 0;
      int var8 = 0;
      boolean var9 = false;
      boolean var10 = false;
      if(var1 instanceof Buffer && !(var9 = ((Buffer)var1).isDirect())) {
         var7 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var4 instanceof Buffer && !(var10 = ((Buffer)var4).isDirect())) {
         var8 = var6.getOffset(var4);
         var4 = ((Buffer)var4).array();
      }

      this.allToAll(this.handle, var1, var9, var7, var2, var3.handle, var3.baseType, var4, var10, var8, var5, var6.handle, var6.baseType);
   }

   private native void allToAll(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, Object var10, boolean var11, int var12, int var13, long var14, int var16) throws MPIException;

   public final Request iAllToAll(Buffer var1, int var2, Datatype var3, Buffer var4, int var5, Datatype var6) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var4);
      Request var7 = new Request(this.iAllToAll(this.handle, var1, var2, var3.handle, var4, var5, var6.handle));
      var7.addSendBufRef(var1);
      var7.addRecvBufRef(var4);
      return var7;
   }

   private native long iAllToAll(long var1, Buffer var3, int var4, long var5, Buffer var7, int var8, long var9) throws MPIException;

   public final void allToAllv(Object var1, int[] var2, int[] var3, Datatype var4, Object var5, int[] var6, int[] var7, Datatype var8) throws MPIException {
      MPI.check();
      int var9 = 0;
      int var10 = 0;
      boolean var11 = false;
      boolean var12 = false;
      if(var1 instanceof Buffer && !(var11 = ((Buffer)var1).isDirect())) {
         var9 = var4.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var5 instanceof Buffer && !(var12 = ((Buffer)var5).isDirect())) {
         var10 = var8.getOffset(var5);
         var5 = ((Buffer)var5).array();
      }

      this.allToAllv(this.handle, var1, var11, var9, var2, var3, var4.handle, var4.baseType, var5, var12, var10, var6, var7, var8.handle, var8.baseType);
   }

   private native void allToAllv(long var1, Object var3, boolean var4, int var5, int[] var6, int[] var7, long var8, int var10, Object var11, boolean var12, int var13, int[] var14, int[] var15, long var16, int var18) throws MPIException;

   public final Request iAllToAllv(Buffer var1, int[] var2, int[] var3, Datatype var4, Buffer var5, int[] var6, int[] var7, Datatype var8) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var5);
      Request var9 = new Request(this.iAllToAllv(this.handle, var1, var2, var3, var4.handle, var5, var6, var7, var8.handle));
      var9.addSendBufRef(var1);
      var9.addRecvBufRef(var5);
      return var9;
   }

   private native long iAllToAllv(long var1, Buffer var3, int[] var4, int[] var5, long var6, Buffer var8, int[] var9, int[] var10, long var11) throws MPIException;

   public final void allToAllw(Buffer var1, int[] var2, int[] var3, Datatype[] var4, Buffer var5, int[] var6, int[] var7, Datatype[] var8) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var5);
      long[] var9 = this.convertTypeArray(var4);
      long[] var10 = this.convertTypeArray(var8);
      this.allToAllw(this.handle, var1, var2, var3, var9, var5, var6, var7, var10);
   }

   private native void allToAllw(long var1, Buffer var3, int[] var4, int[] var5, long[] var6, Buffer var7, int[] var8, int[] var9, long[] var10) throws MPIException;

   public final Request iAllToAllw(Buffer var1, int[] var2, int[] var3, Datatype[] var4, Buffer var5, int[] var6, int[] var7, Datatype[] var8) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var5);
      long[] var9 = this.convertTypeArray(var4);
      long[] var10 = this.convertTypeArray(var8);
      Request var11 = new Request(this.iAllToAllw(this.handle, var1, var2, var3, var9, var5, var6, var7, var10));
      var11.addSendBufRef(var1);
      var11.addRecvBufRef(var5);
      return var11;
   }

   private native long iAllToAllw(long var1, Buffer var3, int[] var4, int[] var5, long[] var6, Buffer var7, int[] var8, int[] var9, long[] var10) throws MPIException;

   public final void neighborAllGather(Object var1, int var2, Datatype var3, Object var4, int var5, Datatype var6) throws MPIException {
      MPI.check();
      int var7 = 0;
      int var8 = 0;
      boolean var9 = false;
      boolean var10 = false;
      if(var1 instanceof Buffer && !(var9 = ((Buffer)var1).isDirect())) {
         var7 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var4 instanceof Buffer && !(var10 = ((Buffer)var4).isDirect())) {
         var8 = var6.getOffset(var4);
         var4 = ((Buffer)var4).array();
      }

      this.neighborAllGather(this.handle, var1, var9, var7, var2, var3.handle, var3.baseType, var4, var10, var8, var5, var6.handle, var6.baseType);
   }

   private native void neighborAllGather(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, Object var10, boolean var11, int var12, int var13, long var14, int var16) throws MPIException;

   public final Request iNeighborAllGather(Buffer var1, int var2, Datatype var3, Buffer var4, int var5, Datatype var6) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var4);
      Request var7 = new Request(this.iNeighborAllGather(this.handle, var1, var2, var3.handle, var4, var5, var6.handle));
      var7.addSendBufRef(var1);
      var7.addRecvBufRef(var4);
      return var7;
   }

   private native long iNeighborAllGather(long var1, Buffer var3, int var4, long var5, Buffer var7, int var8, long var9) throws MPIException;

   public final void neighborAllGatherv(Object var1, int var2, Datatype var3, Object var4, int[] var5, int[] var6, Datatype var7) throws MPIException {
      MPI.check();
      int var8 = 0;
      int var9 = 0;
      boolean var10 = false;
      boolean var11 = false;
      if(var1 instanceof Buffer && !(var10 = ((Buffer)var1).isDirect())) {
         var8 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var4 instanceof Buffer && !(var11 = ((Buffer)var4).isDirect())) {
         var9 = var7.getOffset(var4);
         var4 = ((Buffer)var4).array();
      }

      this.neighborAllGatherv(this.handle, var1, var10, var8, var2, var3.handle, var3.baseType, var4, var11, var9, var5, var6, var7.handle, var7.baseType);
   }

   private native void neighborAllGatherv(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, Object var10, boolean var11, int var12, int[] var13, int[] var14, long var15, int var17);

   public final Request iNeighborAllGatherv(Buffer var1, int var2, Datatype var3, Buffer var4, int[] var5, int[] var6, Datatype var7) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var4);
      Request var8 = new Request(this.iNeighborAllGatherv(this.handle, var1, var2, var3.handle, var4, var5, var6, var7.handle));
      var8.addSendBufRef(var1);
      var8.addRecvBufRef(var4);
      return var8;
   }

   private native long iNeighborAllGatherv(long var1, Buffer var3, int var4, long var5, Buffer var7, int[] var8, int[] var9, long var10) throws MPIException;

   public final void neighborAllToAll(Object var1, int var2, Datatype var3, Object var4, int var5, Datatype var6) throws MPIException {
      MPI.check();
      int var7 = 0;
      int var8 = 0;
      boolean var9 = false;
      boolean var10 = false;
      if(var1 instanceof Buffer && !(var9 = ((Buffer)var1).isDirect())) {
         var7 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var4 instanceof Buffer && !(var10 = ((Buffer)var4).isDirect())) {
         var8 = var6.getOffset(var4);
         var4 = ((Buffer)var4).array();
      }

      this.neighborAllToAll(this.handle, var1, var9, var7, var2, var3.handle, var3.baseType, var4, var10, var8, var5, var6.handle, var6.baseType);
   }

   private native void neighborAllToAll(long var1, Object var3, boolean var4, int var5, int var6, long var7, int var9, Object var10, boolean var11, int var12, int var13, long var14, int var16) throws MPIException;

   public final Request iNeighborAllToAll(Buffer var1, int var2, Datatype var3, Buffer var4, int var5, Datatype var6) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var4);
      Request var7 = new Request(this.iNeighborAllToAll(this.handle, var1, var2, var3.handle, var4, var5, var6.handle));
      var7.addSendBufRef(var1);
      var7.addRecvBufRef(var4);
      return var7;
   }

   private native long iNeighborAllToAll(long var1, Buffer var3, int var4, long var5, Buffer var7, int var8, long var9);

   public final void neighborAllToAllv(Object var1, int[] var2, int[] var3, Datatype var4, Object var5, int[] var6, int[] var7, Datatype var8) throws MPIException {
      MPI.check();
      int var9 = 0;
      int var10 = 0;
      boolean var11 = false;
      boolean var12 = false;
      if(var1 instanceof Buffer && !(var11 = ((Buffer)var1).isDirect())) {
         var9 = var4.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var5 instanceof Buffer && !(var12 = ((Buffer)var5).isDirect())) {
         var10 = var8.getOffset(var5);
         var5 = ((Buffer)var5).array();
      }

      this.neighborAllToAllv(this.handle, var1, var11, var9, var2, var3, var4.handle, var4.baseType, var5, var12, var10, var6, var7, var8.handle, var8.baseType);
   }

   private native void neighborAllToAllv(long var1, Object var3, boolean var4, int var5, int[] var6, int[] var7, long var8, int var10, Object var11, boolean var12, int var13, int[] var14, int[] var15, long var16, int var18) throws MPIException;

   public final Request iNeighborAllToAllv(Buffer var1, int[] var2, int[] var3, Datatype var4, Buffer var5, int[] var6, int[] var7, Datatype var8) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var5);
      Request var9 = new Request(this.iNeighborAllToAllv(this.handle, var1, var2, var3, var4.handle, var5, var6, var7, var8.handle));
      var9.addSendBufRef(var1);
      var9.addRecvBufRef(var5);
      return var9;
   }

   private native long iNeighborAllToAllv(long var1, Buffer var3, int[] var4, int[] var5, long var6, Buffer var8, int[] var9, int[] var10, long var11) throws MPIException;

   public final void reduce(Object var1, Object var2, int var3, Datatype var4, Op var5, int var6) throws MPIException {
      MPI.check();
      var5.setDatatype(var4);
      int var7 = 0;
      int var8 = 0;
      boolean var9 = false;
      boolean var10 = false;
      if(var1 instanceof Buffer && !(var9 = ((Buffer)var1).isDirect())) {
         var7 = var4.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var2 instanceof Buffer && !(var10 = ((Buffer)var2).isDirect())) {
         var8 = var4.getOffset(var2);
         var2 = ((Buffer)var2).array();
      }

      this.reduce(this.handle, var1, var9, var7, var2, var10, var8, var3, var4.handle, var4.baseType, var5, var5.handle, var6);
   }

   public final void reduce(Object var1, int var2, Datatype var3, Op var4, int var5) throws MPIException {
      MPI.check();
      var4.setDatatype(var3);
      int var6 = 0;
      boolean var7 = false;
      if(var1 instanceof Buffer && !(var7 = ((Buffer)var1).isDirect())) {
         var6 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.reduce(this.handle, (Object)null, false, 0, var1, var7, var6, var2, var3.handle, var3.baseType, var4, var4.handle, var5);
   }

   private native void reduce(long var1, Object var3, boolean var4, int var5, Object var6, boolean var7, int var8, int var9, long var10, int var12, Op var13, long var14, int var16) throws MPIException;

   public final Request iReduce(Buffer var1, Buffer var2, int var3, Datatype var4, Op var5, int var6) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var2);
      var5.setDatatype(var4);
      Request var7 = new Request(this.iReduce(this.handle, var1, var2, var3, var4.handle, var4.baseType, var5, var5.handle, var6));
      var7.addSendBufRef(var1);
      var7.addRecvBufRef(var2);
      return var7;
   }

   public final Request iReduce(Buffer var1, int var2, Datatype var3, Op var4, int var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1);
      var4.setDatatype(var3);
      Request var6 = new Request(this.iReduce(this.handle, (Buffer)null, var1, var2, var3.handle, var3.baseType, var4, var4.handle, var5));
      var6.addSendBufRef(var1);
      return var6;
   }

   private native long iReduce(long var1, Buffer var3, Buffer var4, int var5, long var6, int var8, Op var9, long var10, int var12) throws MPIException;

   public final void allReduce(Object var1, Object var2, int var3, Datatype var4, Op var5) throws MPIException {
      MPI.check();
      var5.setDatatype(var4);
      int var6 = 0;
      int var7 = 0;
      boolean var8 = false;
      boolean var9 = false;
      if(var1 instanceof Buffer && !(var8 = ((Buffer)var1).isDirect())) {
         var6 = var4.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var2 instanceof Buffer && !(var9 = ((Buffer)var2).isDirect())) {
         var7 = var4.getOffset(var2);
         var2 = ((Buffer)var2).array();
      }

      this.allReduce(this.handle, var1, var8, var6, var2, var9, var7, var3, var4.handle, var4.baseType, var5, var5.handle);
   }

   public final void allReduce(Object var1, int var2, Datatype var3, Op var4) throws MPIException {
      MPI.check();
      var4.setDatatype(var3);
      int var5 = 0;
      boolean var6 = false;
      if(var1 instanceof Buffer && !(var6 = ((Buffer)var1).isDirect())) {
         var5 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.allReduce(this.handle, (Object)null, false, 0, var1, var6, var5, var2, var3.handle, var3.baseType, var4, var4.handle);
   }

   private native void allReduce(long var1, Object var3, boolean var4, int var5, Object var6, boolean var7, int var8, int var9, long var10, int var12, Op var13, long var14) throws MPIException;

   public final Request iAllReduce(Buffer var1, Buffer var2, int var3, Datatype var4, Op var5) throws MPIException {
      MPI.check();
      MPI.assertDirectBuffer(var1, var2);
      var5.setDatatype(var4);
      Request var6 = new Request(this.iAllReduce(this.handle, var1, var2, var3, var4.handle, var4.baseType, var5, var5.handle));
      var6.addSendBufRef(var1);
      var6.addRecvBufRef(var2);
      return var6;
   }

   public final Request iAllReduce(Buffer var1, int var2, Datatype var3, Op var4) throws MPIException {
      MPI.check();
      var4.setDatatype(var3);
      MPI.assertDirectBuffer(var1);
      Request var5 = new Request(this.iAllReduce(this.handle, (Buffer)null, var1, var2, var3.handle, var3.baseType, var4, var4.handle));
      var5.addRecvBufRef(var1);
      return var5;
   }

   private native long iAllReduce(long var1, Buffer var3, Buffer var4, int var5, long var6, int var8, Op var9, long var10) throws MPIException;

   public final void reduceScatter(Object var1, Object var2, int[] var3, Datatype var4, Op var5) throws MPIException {
      MPI.check();
      var5.setDatatype(var4);
      int var6 = 0;
      int var7 = 0;
      boolean var8 = false;
      boolean var9 = false;
      if(var1 instanceof Buffer && !(var8 = ((Buffer)var1).isDirect())) {
         var6 = var4.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var2 instanceof Buffer && !(var9 = ((Buffer)var2).isDirect())) {
         var7 = var4.getOffset(var2);
         var2 = ((Buffer)var2).array();
      }

      this.reduceScatter(this.handle, var1, var8, var6, var2, var9, var7, var3, var4.handle, var4.baseType, var5, var5.handle);
   }

   public final void reduceScatter(Object var1, int[] var2, Datatype var3, Op var4) throws MPIException {
      MPI.check();
      var4.setDatatype(var3);
      int var5 = 0;
      boolean var6 = false;
      if(var1 instanceof Buffer && !(var6 = ((Buffer)var1).isDirect())) {
         var5 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.reduceScatter(this.handle, (Object)null, false, 0, var1, var6, var5, var2, var3.handle, var3.baseType, var4, var4.handle);
   }

   private native void reduceScatter(long var1, Object var3, boolean var4, int var5, Object var6, boolean var7, int var8, int[] var9, long var10, int var12, Op var13, long var14) throws MPIException;

   public final Request iReduceScatter(Buffer var1, Buffer var2, int[] var3, Datatype var4, Op var5) throws MPIException {
      MPI.check();
      var5.setDatatype(var4);
      MPI.assertDirectBuffer(var1, var2);
      Request var6 = new Request(this.iReduceScatter(this.handle, var1, var2, var3, var4.handle, var4.baseType, var5, var5.handle));
      var6.addSendBufRef(var1);
      var6.addRecvBufRef(var2);
      return var6;
   }

   public final Request iReduceScatter(Buffer var1, int[] var2, Datatype var3, Op var4) throws MPIException {
      MPI.check();
      var4.setDatatype(var3);
      MPI.assertDirectBuffer(var1);
      Request var5 = new Request(this.iReduceScatter(this.handle, (Buffer)null, var1, var2, var3.handle, var3.baseType, var4, var4.handle));
      var5.addRecvBufRef(var1);
      return var5;
   }

   private native long iReduceScatter(long var1, Buffer var3, Object var4, int[] var5, long var6, int var8, Op var9, long var10) throws MPIException;

   public final void reduceScatterBlock(Object var1, Object var2, int var3, Datatype var4, Op var5) throws MPIException {
      MPI.check();
      var5.setDatatype(var4);
      int var6 = 0;
      int var7 = 0;
      boolean var8 = false;
      boolean var9 = false;
      if(var1 instanceof Buffer && !(var8 = ((Buffer)var1).isDirect())) {
         var6 = var4.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var2 instanceof Buffer && !(var9 = ((Buffer)var2).isDirect())) {
         var7 = var4.getOffset(var2);
         var2 = ((Buffer)var2).array();
      }

      this.reduceScatterBlock(this.handle, var1, var8, var6, var2, var9, var7, var3, var4.handle, var4.baseType, var5, var5.handle);
   }

   public final void reduceScatterBlock(Object var1, int var2, Datatype var3, Op var4) throws MPIException {
      MPI.check();
      var4.setDatatype(var3);
      int var5 = 0;
      boolean var6 = false;
      if(var1 instanceof Buffer && !(var6 = ((Buffer)var1).isDirect())) {
         var5 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      this.reduceScatterBlock(this.handle, (Object)null, false, 0, var1, var6, var5, var2, var3.handle, var3.baseType, var4, var4.handle);
   }

   private native void reduceScatterBlock(long var1, Object var3, boolean var4, int var5, Object var6, boolean var7, int var8, int var9, long var10, int var12, Op var13, long var14) throws MPIException;

   public final Request iReduceScatterBlock(Buffer var1, Buffer var2, int var3, Datatype var4, Op var5) throws MPIException {
      MPI.check();
      var5.setDatatype(var4);
      MPI.assertDirectBuffer(var1, var2);
      Request var6 = new Request(this.iReduceScatterBlock(this.handle, var1, var2, var3, var4.handle, var4.baseType, var5, var5.handle));
      var6.addSendBufRef(var1);
      var6.addRecvBufRef(var2);
      return var6;
   }

   public final Request iReduceScatterBlock(Buffer var1, int var2, Datatype var3, Op var4) throws MPIException {
      MPI.check();
      var4.setDatatype(var3);
      MPI.assertDirectBuffer(var1);
      Request var5 = new Request(this.iReduceScatterBlock(this.handle, (Buffer)null, var1, var2, var3.handle, var3.baseType, var4, var4.handle));
      var5.addRecvBufRef(var1);
      return var5;
   }

   private native long iReduceScatterBlock(long var1, Buffer var3, Buffer var4, int var5, long var6, int var8, Op var9, long var10) throws MPIException;

   public static void reduceLocal(Object var0, Object var1, int var2, Datatype var3, Op var4) throws MPIException {
      MPI.check();
      var4.setDatatype(var3);
      int var5 = 0;
      int var6 = 0;
      boolean var7 = false;
      boolean var8 = false;
      if(var0 instanceof Buffer && !(var7 = ((Buffer)var0).isDirect())) {
         var5 = var3.getOffset(var0);
         var0 = ((Buffer)var0).array();
      }

      if(var1 instanceof Buffer && !(var8 = ((Buffer)var1).isDirect())) {
         var6 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      if(var4.uf == null) {
         reduceLocal(var0, var7, var5, var1, var8, var6, var2, var3.handle, var4.handle);
      } else {
         reduceLocalUf(var0, var7, var5, var1, var8, var6, var2, var3.handle, var3.baseType, var4, var4.handle);
      }

   }

   private static native void reduceLocal(Object var0, boolean var1, int var2, Object var3, boolean var4, int var5, int var6, long var7, long var9) throws MPIException;

   private static native void reduceLocalUf(Object var0, boolean var1, int var2, Object var3, boolean var4, int var5, int var6, long var7, int var9, Op var10, long var11) throws MPIException;

   public final void setName(String var1) throws MPIException {
      MPI.check();
      this.setName(this.handle, var1);
   }

   private native void setName(long var1, String var3) throws MPIException;

   public final String getName() throws MPIException {
      MPI.check();
      return this.getName(this.handle);
   }

   private native String getName(long var1) throws MPIException;

   private long[] convertTypeArray(Datatype[] var1) {
      long[] var2 = new long[var1.length];

      for(int var3 = 0; var3 < var2.length; ++var3) {
         if(var1[var3] != null) {
            var2[var3] = var1[var3].handle;
         }
      }

      return var2;
   }

   static {
      init();
   }
}
