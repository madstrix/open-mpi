package mpi;

import mpi.MPIException;

public interface Freeable {

   void free() throws MPIException;
}
