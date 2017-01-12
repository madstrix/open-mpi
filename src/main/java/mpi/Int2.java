package mpi;

import mpi.Struct;

public final class Int2 extends Struct {

   private final int iOff;
   private final int iSize;


   protected Int2(int var1, int var2) {
      this.iSize = var2;
      int var3 = this.addIntField();

      assert var3 == 0;

      this.setOffset(var1);
      this.iOff = this.addIntField();

      assert var1 == this.iOff;

   }

   private int addIntField() {
      switch(this.iSize) {
      case 4:
         return this.addInt();
      case 8:
         return this.addLong();
      default:
         throw new AssertionError("Unsupported int size: " + this.iSize);
      }
   }

   protected Int2.Data newData() {
      return new Int2.Data();
   }


   public final class Data extends Struct.Data {

      public Data() {
         super();
      }

      public int getValue() {
         return this.get(0);
      }

      public int getIndex() {
         return this.get(Int2.this.iOff);
      }

      public void putValue(int var1) {
         this.put(0, var1);
      }

      public void putIndex(int var1) {
         this.put(Int2.this.iOff, var1);
      }

      private int get(int var1) {
         switch(Int2.this.iSize) {
         case 4:
            return this.getInt(var1);
         case 8:
            return (int)this.getLong(var1);
         default:
            throw new AssertionError();
         }
      }

      private void put(int var1, int var2) {
         switch(Int2.this.iSize) {
         case 4:
            this.putInt(var1, var2);
            break;
         case 8:
            this.putLong(var1, (long)var2);
            break;
         default:
            throw new AssertionError();
         }

      }
   }
}
