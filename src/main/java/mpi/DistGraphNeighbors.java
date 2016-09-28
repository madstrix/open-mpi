package mpi;


public final class DistGraphNeighbors {

   private final int[] sources;
   private final int[] sourceWeights;
   private final int[] destinations;
   private final int[] destWeights;
   private final boolean weighted;


   protected DistGraphNeighbors(int[] var1, int[] var2, int[] var3, int[] var4, boolean var5) {
      this.sources = var1;
      this.sourceWeights = var2;
      this.destinations = var3;
      this.destWeights = var4;
      this.weighted = var5;
   }

   public int getInDegree() {
      return this.sources.length;
   }

   public int getOutDegree() {
      return this.destinations.length;
   }

   public boolean isWeighted() {
      return this.weighted;
   }

   public int getSource(int var1) {
      return this.sources[var1];
   }

   public int getSourceWeight(int var1) {
      return this.sourceWeights[var1];
   }

   public int getDestination(int var1) {
      return this.destinations[var1];
   }

   public int getDestinationWeight(int var1) {
      return this.destWeights[var1];
   }
}
