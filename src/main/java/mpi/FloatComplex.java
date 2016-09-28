package mpi;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import mpi.MPI;

public final class FloatComplex {

   private final int offset;
   private final FloatBuffer buffer;


   private FloatComplex(FloatBuffer var1, int var2) {
      this.buffer = var1;
      this.offset = var2 * 2;
   }

   public static FloatComplex get(FloatBuffer var0) {
      return new FloatComplex(var0, 0);
   }

   public static FloatComplex get(FloatBuffer var0, int var1) {
      return new FloatComplex(var0, var1);
   }

   public static FloatComplex get(float[] var0) {
      return new FloatComplex(FloatBuffer.wrap(var0), 0);
   }

   public static FloatComplex get(float[] var0, int var1) {
      return new FloatComplex(FloatBuffer.wrap(var0), var1);
   }

   public static FloatComplex get(ByteBuffer var0) {
      return new FloatComplex(var0.asFloatBuffer(), 0);
   }

   public static FloatComplex get(ByteBuffer var0, int var1) {
      return new FloatComplex(var0.asFloatBuffer(), var1);
   }

   public float getReal() {
      return this.buffer.get(this.offset);
   }

   public float getImag() {
      return this.buffer.get(this.offset + 1);
   }

   public void putReal(float var1) {
      this.buffer.put(this.offset, var1);
   }

   public void putImag(float var1) {
      this.buffer.put(this.offset + 1, var1);
   }

   public FloatBuffer getBuffer() {
      return this.offset == 0?this.buffer:MPI.slice(this.buffer, this.offset);
   }
}
