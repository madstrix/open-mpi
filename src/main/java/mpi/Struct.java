package mpi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import mpi.Datatype;
import mpi.MPI;
import mpi.MPIException;

public abstract class Struct {

   private int extent;
   private ArrayList fields = new ArrayList();
   private Datatype datatype;
   private Datatype[] types;
   private int[] offsets;
   private int[] lengths;
   private static final String typeMismatch = "Type mismatch";


   private void commit() throws MPIException {
      if(this.datatype == null) {
         this.createStruct();
      }

   }

   private void createStruct() throws MPIException {
      int var1 = this.fields.size();
      this.types = new Datatype[var1];
      this.offsets = new int[var1];
      this.lengths = new int[var1];

      for(int var2 = 0; var2 < var1; ++var2) {
         Struct.Field var3 = (Struct.Field)this.fields.get(var2);
         this.types[var2] = var3.type instanceof Struct?((Struct)var3.type).datatype:(Datatype)var3.type;
         this.offsets[var2] = var3.offset;
         this.lengths[var2] = var3.length;
      }

      this.datatype = Datatype.createStruct(this.lengths, this.offsets, this.types);
      this.datatype.commit();
      this.extent = this.datatype.getExtent();
   }

   public final int getExtent() throws MPIException {
      this.commit();
      return this.extent;
   }

   public final Datatype getType() throws MPIException {
      this.commit();
      return this.datatype;
   }

   protected abstract Struct.Data newData();

   private Struct.Data newData(ByteBuffer var1, int var2) {
      Struct.Data var3 = this.newData();
      var3.buffer = var1;
      var3.offset = var2;
      return var3;
   }

   public final Struct.Data getData(ByteBuffer var1) throws MPIException {
      this.commit();
      return this.newData(var1, 0);
   }

   public final Struct.Data getData(ByteBuffer var1, int var2) throws MPIException {
      this.commit();
      return this.newData(var1, var2 * this.extent);
   }

   public final Struct.Data getData(byte[] var1) throws MPIException {
      ByteBuffer var2 = ByteBuffer.wrap(var1);
      var2.order(ByteOrder.nativeOrder());
      return this.getData(var2);
   }

   public final Struct.Data getData(byte[] var1, int var2) throws MPIException {
      ByteBuffer var3 = ByteBuffer.wrap(var1);
      var3.order(ByteOrder.nativeOrder());
      return this.getData(var3, var2);
   }

   private int addField(Object var1, int var2, int var3) {
      if(this.datatype != null) {
         throw new AssertionError("The struct data type was committed.");
      } else {
         int var4 = this.extent;
         this.extent += var2 * var3;
         this.fields.add(new Struct.Field(var1, var4, var3, null));
         return var4;
      }
   }

   public final Struct setOffset(int var1) {
      if(this.datatype != null) {
         throw new AssertionError("The struct data type was committed.");
      } else if(var1 < this.extent) {
         throw new IllegalArgumentException("The offset must be greater or equal to the accumulated extent.");
      } else {
         this.extent = var1;
         return this;
      }
   }

   public final int addByte() {
      return this.addByte(1);
   }

   public final int addByte(int var1) {
      return this.addField(MPI.BYTE, 1, var1);
   }

   public final int addChar() {
      return this.addChar(1);
   }

   public final int addChar(int var1) {
      return this.addField(MPI.CHAR, 2, var1);
   }

   public final int addShort() {
      return this.addShort(1);
   }

   public final int addShort(int var1) {
      return this.addField(MPI.SHORT, 2, var1);
   }

   public final int addInt() {
      return this.addInt(1);
   }

   public final int addInt(int var1) {
      return this.addField(MPI.INT, 4, var1);
   }

   public final int addLong() {
      return this.addLong(1);
   }

   public final int addLong(int var1) {
      return this.addField(MPI.LONG, 8, var1);
   }

   public final int addFloat() {
      return this.addFloat(1);
   }

   public final int addFloat(int var1) {
      return this.addField(MPI.FLOAT, 4, var1);
   }

   public final int addDouble() {
      return this.addDouble(1);
   }

   public final int addDouble(int var1) {
      return this.addField(MPI.DOUBLE, 8, var1);
   }

   public final int addStruct(Struct var1) throws MPIException {
      return this.addStruct(var1, 1);
   }

   public final int addStruct(Struct var1, int var2) throws MPIException {
      var1.commit();
      return this.addField(var1, var1.extent, var2);
   }

   public final int addData(Datatype var1) throws MPIException {
      return this.addData(var1, 1);
   }

   public final int addData(Datatype var1, int var2) throws MPIException {
      return this.addField(var1, var1.getExtent() * var1.baseSize, var2);
   }

   private boolean validType(int var1, int var2, Datatype var3) {
      int var4 = Arrays.binarySearch(this.offsets, var1);
      return var2 >= 0 && var2 < this.lengths[var4] && var3 == this.types[var4];
   }

   private static class Field {

      private Object type;
      private int offset;
      private int length;


      private Field(Object var1, int var2, int var3) {
         this.type = var1;
         this.offset = var2;
         this.length = var3;
      }

      // $FF: synthetic method
      Field(Object var1, int var2, int var3, Object var4) {
         this(var1, var2, var3);
      }
   }

   public abstract class Data {

      private ByteBuffer buffer;
      private int offset;
      // $FF: synthetic field
      final boolean $assertionsDisabled = !Struct.class.desiredAssertionStatus();


      public final ByteBuffer getBuffer() {
         return this.offset == 0?this.buffer:MPI.slice(this.buffer, this.offset);
      }

      protected final byte getByte(int var1) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.BYTE)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.get(this.offset + var1);
         }
      }

      protected final byte getByte(int var1, int var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.BYTE)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.get(this.offset + var1 + var2);
         }
      }

      protected final void putByte(int var1, byte var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.BYTE)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.put(this.offset + var1, var2);
         }
      }

      protected final void putByte(int var1, int var2, byte var3) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.BYTE)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.put(this.offset + var1 + var2, var3);
         }
      }

      protected final char getChar(int var1) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.CHAR)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getChar(this.offset + var1);
         }
      }

      protected final char getChar(int var1, int var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.CHAR)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getChar(this.offset + var1 + var2 * 2);
         }
      }

      protected final void putChar(int var1, char var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.CHAR)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putChar(this.offset + var1, var2);
         }
      }

      protected final void putChar(int var1, int var2, char var3) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.CHAR)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putChar(this.offset + var1 + var2 * 2, var3);
         }
      }

      protected final short getShort(int var1) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.SHORT)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getShort(this.offset + var1);
         }
      }

      protected final short getShort(int var1, int var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.SHORT)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getShort(this.offset + var1 + var2 * 2);
         }
      }

      protected final void putShort(int var1, short var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.SHORT)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putShort(this.offset + var1, var2);
         }
      }

      protected final void putShort(int var1, int var2, short var3) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.SHORT)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putShort(this.offset + var1 + var2 * 2, var3);
         }
      }

      protected final int getInt(int var1) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.INT)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getInt(this.offset + var1);
         }
      }

      protected final int getInt(int var1, int var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.INT)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getInt(this.offset + var1 + var2 * 4);
         }
      }

      protected final void putInt(int var1, int var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.INT)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putInt(this.offset + var1, var2);
         }
      }

      protected final void putInt(int var1, int var2, int var3) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.INT)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putInt(this.offset + var1 + var2 * 4, var3);
         }
      }

      protected final long getLong(int var1) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.LONG)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getLong(this.offset + var1);
         }
      }

      protected final long getLong(int var1, int var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.LONG)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getLong(this.offset + var1 + var2 * 8);
         }
      }

      protected final void putLong(int var1, long var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.LONG)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putLong(this.offset + var1, var2);
         }
      }

      protected final void putLong(int var1, int var2, long var3) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.LONG)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putLong(this.offset + var1 + var2 * 8, var3);
         }
      }

      protected final float getFloat(int var1) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.FLOAT)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getFloat(this.offset + var1);
         }
      }

      protected final float getFloat(int var1, int var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.FLOAT)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getFloat(this.offset + var1 + var2 * 4);
         }
      }

      protected final void putFloat(int var1, float var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.FLOAT)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putFloat(this.offset + var1, var2);
         }
      }

      protected final void putFloat(int var1, int var2, float var3) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.FLOAT)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putFloat(this.offset + var1 + var2 * 4, var3);
         }
      }

      protected final double getDouble(int var1) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.DOUBLE)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getDouble(this.offset + var1);
         }
      }

      protected final double getDouble(int var1, int var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.DOUBLE)) {
            throw new AssertionError("Type mismatch");
         } else {
            return this.buffer.getDouble(this.offset + var1 + var2 * 8);
         }
      }

      protected final void putDouble(int var1, double var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, 0, MPI.DOUBLE)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putDouble(this.offset + var1, var2);
         }
      }

      protected final void putDouble(int var1, int var2, double var3) {
         if(!$assertionsDisabled && !Struct.this.validType(var1, var2, MPI.DOUBLE)) {
            throw new AssertionError("Type mismatch");
         } else {
            this.buffer.putDouble(this.offset + var1 + var2 * 8, var3);
         }
      }

      protected final Struct.Data getData(Struct var1, int var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var2, 0, var1.datatype)) {
            throw new AssertionError("Type mismatch");
         } else {
            return var1.newData(this.buffer, this.offset + var2);
         }
      }

      protected final Struct.Data getData(Struct var1, int var2, int var3) {
         if(!$assertionsDisabled && !Struct.this.validType(var2, var3, var1.datatype)) {
            throw new AssertionError("Type mismatch");
         } else {
            return var1.newData(this.buffer, this.offset + var2 + var3 * var1.extent);
         }
      }

      protected final ByteBuffer getBuffer(Datatype var1, int var2) {
         if(!$assertionsDisabled && !Struct.this.validType(var2, 0, var1)) {
            throw new AssertionError("Type mismatch");
         } else {
            int var3 = this.offset + var2;
            return var3 == 0?this.buffer:MPI.slice(this.buffer, var3);
         }
      }

      protected final ByteBuffer getBuffer(Datatype var1, int var2, int var3) throws MPIException {
         if(!$assertionsDisabled && !Struct.this.validType(var2, var3, var1)) {
            throw new AssertionError("Type mismatch");
         } else {
            int var4 = var1.getExtent() * var1.baseSize;
            int var5 = this.offset + var2 + var3 * var4;
            return var5 == 0?this.buffer:MPI.slice(this.buffer, var5);
         }
      }

   }
}
