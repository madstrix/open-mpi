package mpi;

import mpi.Struct;

public final class LongInt extends Struct {

   private final int lSize;
   private final int iOff;
   private final int iSize;


   protected LongInt(int var1, int var2, int var3) {
      this.lSize = var1;
      this.iSize = var3;
      int var4;
      switch(this.lSize) {
      case 4:
         var4 = this.addInt();
         break;
      case 8:
         var4 = this.addLong();
         break;
      default:
         throw new AssertionError("Unsupported long size: " + this.lSize);
      }

      assert var4 == 0;

      this.setOffset(var2);
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

      assert var2 == this.iOff;

   }

   protected LongInt.Data newData() {
      return new LongInt.Data();
   }


   public final class Data extends Struct.Data {

      public Data() {
         super();
      }

      public long getValue() {
         switch(LongInt.this.lSize) {
         case 4:
            return (long)this.getInt(0);
         case 8:
            return this.getLong(0);
         default:
            throw new AssertionError();
         }
      }

      public int getIndex() {
         switch(LongInt.this.iSize) {
         case 4:
            return this.getInt(LongInt.this.iOff);
         case 8:
            return (int)this.getLong(LongInt.this.iOff);
         default:
            throw new AssertionError();
         }
      }

      public void putValue(long var1) {
         switch(LongInt.this.lSize) {
         case 4:
            this.putInt(0, (int)var1);
            break;
         case 8:
            this.putLong(0, var1);
            break;
         default:
            throw new AssertionError();
         }

      }

      public void putIndex(int var1) {
         switch(LongInt.this.iSize) {
         case 4:
            this.putInt(LongInt.this.iOff, var1);
            break;
         case 8:
            this.putLong(LongInt.this.iOff, (long)var1);
            break;
         default:
            throw new AssertionError();
         }

      }
   }
}
