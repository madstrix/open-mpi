package mpi;


public final class Version {

   private final int version;
   private final int subVersion;


   protected Version(int var1, int var2) {
      this.version = var1;
      this.subVersion = var2;
   }

   public int getVersion() {
      return this.version;
   }

   public int getSubVersion() {
      return this.subVersion;
   }
}
