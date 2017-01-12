package mpi;


public final class Errhandler {

   protected long handle;


   protected static native long getFatal();

   protected static native long getReturn();

   protected Errhandler(long var1) {
      this.handle = var1;
   }
}
