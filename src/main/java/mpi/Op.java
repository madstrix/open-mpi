package mpi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import mpi.Datatype;
import mpi.Freeable;
import mpi.MPIException;
import mpi.UserFunction;

public final class Op implements Freeable {

   protected final UserFunction uf;
   private boolean commute;
   private Datatype datatype;
   protected long handle;


   private static native void init();

   protected Op(int var1) {
      this.getOp(var1);
      this.uf = null;
      this.commute = true;
   }

   private native void getOp(int var1);

   public Op(UserFunction var1, boolean var2) {
      this.handle = 0L;
      this.uf = var1;
      this.commute = var2;
   }

   protected void setDatatype(Datatype var1) {
      this.datatype = var1;
   }

   protected void call(Object var1, Object var2, int var3) throws MPIException {
      if(this.datatype.baseType == 4) {
         this.uf.call(var1, var2, var3, this.datatype);
      } else {
         this.uf.call(((ByteBuffer)var1).order(ByteOrder.nativeOrder()), ((ByteBuffer)var2).order(ByteOrder.nativeOrder()), var3, this.datatype);
      }

   }

   public boolean isCommutative() {
      return this.commute;
   }

   public native void free() throws MPIException;

   public native boolean isNull();

   static {
      init();
   }
}
