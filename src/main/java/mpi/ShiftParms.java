package mpi;


public final class ShiftParms {

   private final int rankSource;
   private final int rankDest;


   protected ShiftParms(int var1, int var2) {
      this.rankSource = var1;
      this.rankDest = var2;
   }

   public int getRankSource() {
      return this.rankSource;
   }

   public int getRankDest() {
      return this.rankDest;
   }
}
