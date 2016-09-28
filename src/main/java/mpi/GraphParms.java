package mpi;


public final class GraphParms {

   private final int[] index;
   private final int[] edges;


   protected GraphParms(int[] var1, int[] var2) {
      this.index = var1;
      this.edges = var2;
   }

   public int getIndexCount() {
      return this.index.length;
   }

   public int getIndex(int var1) {
      return this.index[var1];
   }

   public int getEdgeCount() {
      return this.edges.length;
   }

   public int getEdge(int var1) {
      return this.edges[var1];
   }
}
