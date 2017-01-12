package mpi;

public final class Count implements Comparable {

   private long count;

   private static native void initCount();

   public Count(long var1) {
      this.count = var1;
   }

   public long getCount() {
      return this.count;
   }

   public void setCount(long var1) {
      this.count = var1;
   }

   public boolean equals(Object var1) {
      return var1 instanceof Count && this.count == ((Count)var1).getCount();
   }

   public int compareTo(Object var1) {
      if(var1 instanceof Count) {
         if(this.count - ((Count)var1).getCount() > 0L) {
            return 1;
         }

         if(this.count - ((Count)var1).getCount() == 0L) {
            return 0;
         }
      }

      return -1;
   }

   static {
      System.loadLibrary("mpi_java");
      initCount();
   }
}
