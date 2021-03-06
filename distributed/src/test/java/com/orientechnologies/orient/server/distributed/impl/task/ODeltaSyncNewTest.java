package com.orientechnologies.orient.server.distributed.impl.task;

import com.orientechnologies.orient.core.tx.OTransactionSequenceStatus;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ODeltaSyncNewTest {

  @Test
  public void testSerializationDeserialization() throws IOException {
    OTransactionSequenceStatus state = new OTransactionSequenceStatus(new long[] { 1, 2, 3, 4, 5, 6 });
    OSyncDatabaseNewDeltaTask task = new OSyncDatabaseNewDeltaTask(state);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    task.toStream(new DataOutputStream(outputStream));
    OSyncDatabaseNewDeltaTask syncTask = new OSyncDatabaseNewDeltaTask();
    syncTask.fromStream(new DataInputStream(new ByteArrayInputStream(outputStream.toByteArray())), null);
    assertEquals(syncTask.getLastState(), state);
  }
}
