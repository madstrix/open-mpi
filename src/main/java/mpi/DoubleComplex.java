package mpi;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import mpi.MPI;

public final class DoubleComplex {

   private final int offset;
   private final DoubleBuffer buffer;


   private DoubleComplex(DoubleBuffer var1, int var2) {
      this.buffer = var1;
      this.offset = var2 * 2;
   }

   public static DoubleComplex get(DoubleBuffer var0) {
      return new DoubleComplex(var0, 0);
   }

   public static DoubleComplex get(DoubleBuffer var0, int var1) {
      return new DoubleComplex(var0, var1);
   }

   public static DoubleComplex get(double[] var0) {
      return new DoubleComplex(DoubleBuffer.wrap(var0), 0);
   }

   public static DoubleComplex get(double[] var0, int var1) {
      return new DoubleComplex(DoubleBuffer.wrap(var0), var1);
   }

   public static DoubleComplex get(ByteBuffer var0) {
      return new DoubleComplex(var0.asDoubleBuffer(), 0);
   }

   public static DoubleComplex get(ByteBuffer var0, int var1) {
      return new DoubleComplex(var0.asDoubleBuffer(), var1);
   }

   public double getReal() {
      return this.buffer.get(this.offset);
   }

   public double getImag() {
      return this.buffer.get(this.offset + 1);
   }

   public void putReal(double var1) {
      this.buffer.put(this.offset, var1);
   }

   public void putImag(double var1) {
      this.buffer.put(this.offset + 1, var1);
   }

   public DoubleBuffer getBuffer() {
      return this.offset == 0?this.buffer:MPI.slice(this.buffer, this.offset);
   }
}
