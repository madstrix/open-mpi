package mpi;

import mpi.Struct;

public final class FloatInt extends Struct {

   private final int iOff;
   private final int iSize;


   protected FloatInt(int var1, int var2) {
      int var3 = this.addFloat();

      assert var3 == 0;

      this.iSize = var2;
      this.setOffset(var1);
      switch(this.iSize) {
      case 4:
         this.iOff = this.addInt();
         break;
      case 8:
         this.iOff = this.addLong();
         break;
      default:
         throw new AssertionError("Unsupported int size: " + this.iSize);
      }

      assert var1 == this.iOff;

   }

   protected FloatInt.Data newData() {
      return new FloatInt.Data();
   }


   public final class Data extends Struct.Data {

      public Data() {
         super();
      }

      public float getValue() {
         return this.getFloat(0);
      }

      public int getIndex() {
         switch(FloatInt.this.iSize) {
         case 4:
            return this.getInt(FloatInt.this.iOff);
         case 8:
            return (int)this.getLong(FloatInt.this.iOff);
         default:
            throw new AssertionError();
         }
      }

      public void putValue(float var1) {
         this.putFloat(0, var1);
      }

      public void putIndex(int var1) {
         switch(FloatInt.this.iSize) {
         case 4:
            this.putInt(FloatInt.this.iOff, var1);
            break;
         case 8:
            this.putLong(FloatInt.this.iOff, (long)var1);
            break;
         default:
            throw new AssertionError();
         }

      }
   }
}
