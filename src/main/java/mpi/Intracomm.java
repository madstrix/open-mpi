package mpi;

import java.nio.Buffer;
import mpi.CartComm;
import mpi.Comm;
import mpi.Datatype;
import mpi.GraphComm;
import mpi.Group;
import mpi.Info;
import mpi.Intercomm;
import mpi.MPI;
import mpi.MPIException;
import mpi.Op;
import mpi.Request;

public class Intracomm extends Comm {

   protected Intracomm() {}

   protected Intracomm(long var1) {
      super(var1);
   }

   protected Intracomm(long[] var1) {
      super(var1);
   }

   public Intracomm clone() {
      try {
         return this.dup();
      } catch (MPIException var2) {
         throw new RuntimeException(var2.getMessage());
      }
   }

   public Intracomm dup() throws MPIException {
      MPI.check();
      return new Intracomm(this.dup(this.handle));
   }

   public Intracomm iDup() throws MPIException {
      MPI.check();
      return new Intracomm(this.iDup(this.handle));
   }

   public Intracomm dupWithInfo(Info var1) throws MPIException {
      MPI.check();
      return new Intracomm(this.dupWithInfo(this.handle, var1.handle));
   }

   public final Intracomm split(int var1, int var2) throws MPIException {
      MPI.check();
      return new Intracomm(this.split(this.handle, var1, var2));
   }

   private native long split(long var1, int var3, int var4) throws MPIException;

   public final Intracomm splitType(int var1, int var2, Info var3) throws MPIException {
      MPI.check();
      return new Intracomm(this.splitType(this.handle, var1, var2, var3.handle));
   }

   private native long splitType(long var1, int var3, int var4, long var5) throws MPIException;

   public final Intracomm create(Group var1) throws MPIException {
      MPI.check();
      return new Intracomm(this.create(this.handle, var1.handle));
   }

   private native long create(long var1, long var3);

   public final Intracomm createGroup(Group var1, int var2) throws MPIException {
      MPI.check();
      return new Intracomm(this.createGroup(this.handle, var1.handle, var2));
   }

   private native long createGroup(long var1, long var3, int var5);

   public final CartComm createCart(int[] var1, boolean[] var2, boolean var3) throws MPIException {
      MPI.check();
      return new CartComm(this.createCart(this.handle, var1, var2, var3));
   }

   private native long createCart(long var1, int[] var3, boolean[] var4, boolean var5) throws MPIException;

   public final GraphComm createGraph(int[] var1, int[] var2, boolean var3) throws MPIException {
      MPI.check();
      return new GraphComm(this.createGraph(this.handle, var1, var2, var3));
   }

   private native long createGraph(long var1, int[] var3, int[] var4, boolean var5) throws MPIException;

   public final GraphComm createDistGraph(int[] var1, int[] var2, int[] var3, int[] var4, Info var5, boolean var6) throws MPIException {
      MPI.check();
      return new GraphComm(this.createDistGraph(this.handle, var1, var2, var3, var4, var5.handle, var6, true));
   }

   public final GraphComm createDistGraph(int[] var1, int[] var2, int[] var3, Info var4, boolean var5) throws MPIException {
      MPI.check();
      return new GraphComm(this.createDistGraph(this.handle, var1, var2, var3, (int[])null, var4.handle, var5, false));
   }

   private native long createDistGraph(long var1, int[] var3, int[] var4, int[] var5, int[] var6, long var7, boolean var9, boolean var10) throws MPIException;

   public final GraphComm createDistGraphAdjacent(int[] var1, int[] var2, int[] var3, int[] var4, Info var5, boolean var6) throws MPIException {
      MPI.check();
      return new GraphComm(this.createDistGraphAdjacent(this.handle, var1, var2, var3, var4, var5.handle, var6, true));
   }

   public final GraphComm createDistGraphAdjacent(int[] var1, int[] var2, Info var3, boolean var4) throws MPIException {
      MPI.check();
      return new GraphComm(this.createDistGraphAdjacent(this.handle, var1, (int[])null, var2, (int[])null, var3.handle, var4, false));
   }

   private native long createDistGraphAdjacent(long var1, int[] var3, int[] var4, int[] var5, int[] var6, long var7, boolean var9, boolean var10) throws MPIException;

   public final void scan(Object var1, Object var2, int var3, Datatype var4, Op var5) throws MPIException {
      MPI.check();
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

      var5.setDatatype(var4);
      this.scan(this.handle, var1, var8, var6, var2, var9, var7, var3, var4.handle, var4.baseType, var5, var5.handle);
   }

   public final void scan(Object var1, int var2, Datatype var3, Op var4) throws MPIException {
      MPI.check();
      int var5 = 0;
      boolean var6 = false;
      if(var1 instanceof Buffer && !(var6 = ((Buffer)var1).isDirect())) {
         var5 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      var4.setDatatype(var3);
      this.scan(this.handle, (Object)null, false, 0, var1, var6, var5, var2, var3.handle, var3.baseType, var4, var4.handle);
   }

   private native void scan(long var1, Object var3, boolean var4, int var5, Object var6, boolean var7, int var8, int var9, long var10, int var12, Op var13, long var14) throws MPIException;

   public final Request iScan(Buffer var1, Buffer var2, int var3, Datatype var4, Op var5) throws MPIException {
      MPI.check();
      var5.setDatatype(var4);
      MPI.assertDirectBuffer(var1, var2);
      Request var6 = new Request(this.iScan(this.handle, var1, var2, var3, var4.handle, var4.baseType, var5, var5.handle));
      var6.addSendBufRef(var1);
      var6.addRecvBufRef(var2);
      return var6;
   }

   public final Request iScan(Buffer var1, int var2, Datatype var3, Op var4) throws MPIException {
      MPI.check();
      var4.setDatatype(var3);
      MPI.assertDirectBuffer(var1);
      Request var5 = new Request(this.iScan(this.handle, (Buffer)null, var1, var2, var3.handle, var3.baseType, var4, var4.handle));
      var5.addSendBufRef(var1);
      return var5;
   }

   private native long iScan(long var1, Buffer var3, Buffer var4, int var5, long var6, int var8, Op var9, long var10) throws MPIException;

   public final void exScan(Object var1, Object var2, int var3, Datatype var4, Op var5) throws MPIException {
      MPI.check();
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

      var5.setDatatype(var4);
      this.exScan(this.handle, var1, var8, var6, var2, var9, var7, var3, var4.handle, var4.baseType, var5, var5.handle);
   }

   public final void exScan(Object var1, int var2, Datatype var3, Op var4) throws MPIException {
      MPI.check();
      int var5 = 0;
      boolean var6 = false;
      if(var1 instanceof Buffer && !(var6 = ((Buffer)var1).isDirect())) {
         var5 = var3.getOffset(var1);
         var1 = ((Buffer)var1).array();
      }

      var4.setDatatype(var3);
      this.exScan(this.handle, (Object)null, false, 0, var1, var6, var5, var2, var3.handle, var3.baseType, var4, var4.handle);
   }

   private native void exScan(long var1, Object var3, boolean var4, int var5, Object var6, boolean var7, int var8, int var9, long var10, int var12, Op var13, long var14) throws MPIException;

   public final Request iExScan(Buffer var1, Buffer var2, int var3, Datatype var4, Op var5) throws MPIException {
      MPI.check();
      var5.setDatatype(var4);
      MPI.assertDirectBuffer(var1, var2);
      Request var6 = new Request(this.iExScan(this.handle, var1, var2, var3, var4.handle, var4.baseType, var5, var5.handle));
      var6.addSendBufRef(var1);
      var6.addRecvBufRef(var2);
      return var6;
   }

   public final Request iExScan(Buffer var1, int var2, Datatype var3, Op var4) throws MPIException {
      MPI.check();
      var4.setDatatype(var3);
      MPI.assertDirectBuffer(var1);
      Request var5 = new Request(this.iExScan(this.handle, (Buffer)null, var1, var2, var3.handle, var3.baseType, var4, var4.handle));
      var5.addRecvBufRef(var1);
      return var5;
   }

   private native long iExScan(long var1, Buffer var3, Buffer var4, int var5, long var6, int var8, Op var9, long var10) throws MPIException;

   public static String openPort() throws MPIException {
      MPI.check();
      return openPort(Info.NULL);
   }

   public static String openPort(Info var0) throws MPIException {
      MPI.check();
      return openPort(var0.handle);
   }

   private static native String openPort(long var0) throws MPIException;

   public static void closePort(String var0) throws MPIException {
      MPI.check();
      closePort_jni(var0);
   }

   private static native void closePort_jni(String var0) throws MPIException;

   public final Intercomm accept(String var1, int var2) throws MPIException {
      MPI.check();
      return new Intercomm(this.accept(this.handle, var1, Info.NULL, var2));
   }

   public final Intercomm accept(String var1, Info var2, int var3) throws MPIException {
      MPI.check();
      return new Intercomm(this.accept(this.handle, var1, var2.handle, var3));
   }

   private native long accept(long var1, String var3, long var4, int var6) throws MPIException;

   public final Intercomm connect(String var1, int var2) throws MPIException {
      MPI.check();
      return new Intercomm(this.connect(this.handle, var1, Info.NULL, var2));
   }

   public final Intercomm connect(String var1, Info var2, int var3) throws MPIException {
      MPI.check();
      return new Intercomm(this.connect(this.handle, var1, var2.handle, var3));
   }

   private native long connect(long var1, String var3, long var4, int var6) throws MPIException;

   public static void publishName(String var0, String var1) throws MPIException {
      MPI.check();
      publishName(var0, Info.NULL, var1);
   }

   public static void publishName(String var0, Info var1, String var2) throws MPIException {
      MPI.check();
      publishName(var0, var1.handle, var2);
   }

   private static native void publishName(String var0, long var1, String var3) throws MPIException;

   public static void unpublishName(String var0, String var1) throws MPIException {
      MPI.check();
      unpublishName(var0, Info.NULL, var1);
   }

   public static void unpublishName(String var0, Info var1, String var2) throws MPIException {
      MPI.check();
      unpublishName(var0, var1.handle, var2);
   }

   private static native void unpublishName(String var0, long var1, String var3) throws MPIException;

   public static String lookupName(String var0) throws MPIException {
      MPI.check();
      return lookupName(var0, Info.NULL);
   }

   public static String lookupName(String var0, Info var1) throws MPIException {
      MPI.check();
      return lookupName(var0, var1.handle);
   }

   private static native String lookupName(String var0, long var1) throws MPIException;

   public final Intercomm spawn(String var1, String[] var2, int var3, Info var4, int var5, int[] var6) throws MPIException {
      MPI.check();
      return new Intercomm(this.spawn(this.handle, var1, var2, var3, var4.handle, var5, var6));
   }

   private native long spawn(long var1, String var3, String[] var4, int var5, long var6, int var8, int[] var9) throws MPIException;

   public final Intercomm spawnMultiple(String[] var1, String[][] var2, int[] var3, Info[] var4, int var5, int[] var6) throws MPIException {
      MPI.check();
      long[] var7 = new long[var4.length];

      for(int var8 = 0; var8 < var4.length; ++var8) {
         var7[var8] = var4[var8].handle;
      }

      return new Intercomm(this.spawnMultiple(this.handle, var1, var2, var3, var7, var5, var6));
   }

   private native long spawnMultiple(long var1, String[] var3, String[][] var4, int[] var5, long[] var6, int var7, int[] var8) throws MPIException;
}
