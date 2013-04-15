package com.aestasit.ssh.mocks

import static org.junit.Assert.*

import org.junit.Test

class MockServerTest {

  @Test
  void testMockSshd() throws Exception {
    MockSshServer.startSshd()
    MockSshServer.stopSshd()
  }
}
