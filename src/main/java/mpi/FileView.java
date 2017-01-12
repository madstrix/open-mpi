package mpi;

import mpi.Datatype;

public final class FileView {

   private final long disp;
   private final Datatype etype;
   private final Datatype filetype;
   private final String datarep;


   public FileView(long var1, Datatype var3, Datatype var4, String var5) {
      this.disp = var1;
      this.etype = var3;
      this.filetype = var4;
      this.datarep = var5;
   }

   public long getDisp() {
      return this.disp;
   }

   public Datatype getEType() {
      return this.etype;
   }

   public Datatype getFileType() {
      return this.filetype;
   }

   public String getDataRep() {
      return this.datarep;
   }
}
