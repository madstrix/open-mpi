package mpi;

import mpi.Struct;

public final class ShortInt extends Struct {

   private final int sSize;
   private final int iOff;
   private final int iSize;


   protected ShortInt(int var1, int var2, int var3) {
      this.sSize = var1;
      this.iSize = var3;
      int var4;
      switch(this.sSize) {
      case 2:
         var4 = this.addShort();
         break;
      case 4:
         var4 = this.addInt();
         break;
      case 8:
         var4 = this.addLong();
         break;
      default:
         throw new AssertionError("Unsupported short size: " + this.sSize);
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

   protected ShortInt.Data newData() {
      return new ShortInt.Data();
   }


   public final class Data extends Struct.Data {

      public Data() {
         super();
      }

      public short getValue() {
         switch(ShortInt.this.sSize) {
         case 2:
            return this.getShort(0);
         case 4:
            return (short)this.getInt(0);
         case 8:
            return (short)((int)this.getLong(0));
         default:
            throw new AssertionError();
         }
      }

      public int getIndex() {
         switch(ShortInt.this.iSize) {
         case 4:
            return this.getInt(ShortInt.this.iOff);
         case 8:
            return (int)this.getLong(ShortInt.this.iOff);
         default:
            throw new AssertionError();
         }
      }

      public void putValue(short var1) {
         switch(ShortInt.this.sSize) {
         case 2:
            this.putShort(0, var1);
            break;
         case 4:
            this.putInt(0, var1);
            break;
         case 8:
            this.putLong(0, (long)var1);
            break;
         default:
            throw new AssertionError();
         }

      }

      public void putIndex(int var1) {
         switch(ShortInt.this.iSize) {
         case 4:
            this.putInt(ShortInt.this.iOff, var1);
            break;
         case 8:
            this.putLong(ShortInt.this.iOff, (long)var1);
            break;
         default:
            throw new AssertionError();
         }

      }
   }
}
