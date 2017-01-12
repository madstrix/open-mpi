package mpi;

import mpi.Struct;

public final class DoubleInt extends Struct {

   private final int iOff;
   private final int iSize;


   protected DoubleInt(int var1, int var2) {
      int var3 = this.addDouble();

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

   protected DoubleInt.Data newData() {
      return new DoubleInt.Data();
   }


   public final class Data extends Struct.Data {

      public Data() {
         super();
      }

      public double getValue() {
         return this.getDouble(0);
      }

      public int getIndex() {
         switch(DoubleInt.this.iSize) {
         case 4:
            return this.getInt(DoubleInt.this.iOff);
         case 8:
            return (int)this.getLong(DoubleInt.this.iOff);
         default:
            throw new AssertionError();
         }
      }

      public void putValue(double var1) {
         this.putDouble(0, var1);
      }

      public void putIndex(int var1) {
         switch(DoubleInt.this.iSize) {
         case 4:
            this.putInt(DoubleInt.this.iOff, var1);
            break;
         case 8:
            this.putLong(DoubleInt.this.iOff, (long)var1);
            break;
         default:
            throw new AssertionError();
         }

      }
   }
}
