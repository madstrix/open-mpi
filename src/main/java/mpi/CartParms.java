package mpi;


public final class CartParms {

   private final int[] dims;
   private final boolean[] periods;
   private final int[] coords;


   protected CartParms(int[] var1, boolean[] var2, int[] var3) {
      this.dims = var1;
      this.periods = var2;
      this.coords = var3;
   }

   public int getDimCount() {
      return this.dims.length;
   }

   public int getDim(int var1) {
      return this.dims[var1];
   }

   public boolean getPeriod(int var1) {
      return this.periods[var1];
   }

   public int getCoord(int var1) {
      return this.coords[var1];
   }
}
