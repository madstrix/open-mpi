package mpi;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import mpi.Datatype;
import mpi.MPIException;

public abstract class UserFunction {

   public void call(Object var1, Object var2, int var3, Datatype var4) throws MPIException {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public void call(ByteBuffer var1, ByteBuffer var2, int var3, Datatype var4) throws MPIException {
      switch(var4.baseType) {
      case 1:
         this.vCall(var1, var2, var3, var4);
         break;
      case 2:
         this.vCall(var1.asCharBuffer(), var2.asCharBuffer(), var3, var4);
         break;
      case 3:
         this.vCall(var1.asShortBuffer(), var2.asShortBuffer(), var3, var4);
         break;
      case 4:
      default:
         throw new IllegalArgumentException("Unsupported datatype.");
      case 5:
         this.vCall(var1.asIntBuffer(), var2.asIntBuffer(), var3, var4);
         break;
      case 6:
         this.vCall(var1.asLongBuffer(), var2.asLongBuffer(), var3, var4);
         break;
      case 7:
         this.vCall(var1.asFloatBuffer(), var2.asFloatBuffer(), var3, var4);
         break;
      case 8:
         this.vCall(var1.asDoubleBuffer(), var2.asDoubleBuffer(), var3, var4);
         break;
      case 9:
         this.vCall(var1, var2, var3, var4);
      }

   }

   private void vCall(ByteBuffer var1, ByteBuffer var2, int var3, Datatype var4) throws MPIException {
      int var5 = var4.getExtent();
      byte[] var6 = new byte[var3 * var5];
      byte[] var7 = new byte[var3 * var5];
      var1.get(var6);
      var2.get(var7);
      this.call((Object)var6, (Object)var7, var3, var4);
      var2.clear();
      var2.put(var7);
   }

   private void vCall(CharBuffer var1, CharBuffer var2, int var3, Datatype var4) throws MPIException {
      int var5 = var4.getExtent();
      char[] var6 = new char[var3 * var5];
      char[] var7 = new char[var3 * var5];
      var1.get(var6);
      var2.get(var7);
      this.call((Object)var6, (Object)var7, var3, var4);
      var2.clear();
      var2.put(var7);
   }

   private void vCall(ShortBuffer var1, ShortBuffer var2, int var3, Datatype var4) throws MPIException {
      int var5 = var4.getExtent();
      short[] var6 = new short[var3 * var5];
      short[] var7 = new short[var3 * var5];
      var1.get(var6);
      var2.get(var7);
      this.call((Object)var6, (Object)var7, var3, var4);
      var2.clear();
      var2.put(var7);
   }

   private void vCall(IntBuffer var1, IntBuffer var2, int var3, Datatype var4) throws MPIException {
      int var5 = var4.getExtent();
      int[] var6 = new int[var3 * var5];
      int[] var7 = new int[var3 * var5];
      var1.get(var6);
      var2.get(var7);
      this.call((Object)var6, (Object)var7, var3, var4);
      var2.clear();
      var2.put(var7);
   }

   private void vCall(LongBuffer var1, LongBuffer var2, int var3, Datatype var4) throws MPIException {
      int var5 = var4.getExtent();
      long[] var6 = new long[var3 * var5];
      long[] var7 = new long[var3 * var5];
      var1.get(var6);
      var2.get(var7);
      this.call((Object)var6, (Object)var7, var3, var4);
      var2.clear();
      var2.put(var7);
   }

   private void vCall(FloatBuffer var1, FloatBuffer var2, int var3, Datatype var4) throws MPIException {
      int var5 = var4.getExtent();
      float[] var6 = new float[var3 * var5];
      float[] var7 = new float[var3 * var5];
      var1.get(var6);
      var2.get(var7);
      this.call((Object)var6, (Object)var7, var3, var4);
      var2.clear();
      var2.put(var7);
   }

   private void vCall(DoubleBuffer var1, DoubleBuffer var2, int var3, Datatype var4) throws MPIException {
      int var5 = var4.getExtent();
      double[] var6 = new double[var3 * var5];
      double[] var7 = new double[var3 * var5];
      var1.get(var6);
      var2.get(var7);
      this.call((Object)var6, (Object)var7, var3, var4);
      var2.clear();
      var2.put(var7);
   }
}
