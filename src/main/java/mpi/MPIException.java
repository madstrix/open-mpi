package mpi;


public final class MPIException extends Exception {

   private int errorCode;
   private int errorClass;


   protected MPIException(int var1, int var2, String var3) {
      super(var3);
      this.errorCode = var1;
      this.errorClass = var2;
   }

   public MPIException(String var1) {
      super(var1);
   }

   public MPIException(Throwable var1) {
      super(var1);
      this.setStackTrace(var1.getStackTrace());
   }

   public int getErrorCode() {
      return this.errorCode;
   }

   public int getErrorClass() {
      return this.errorClass;
   }
}
