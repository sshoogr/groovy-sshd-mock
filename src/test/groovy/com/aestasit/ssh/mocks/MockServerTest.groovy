package com.aestasit.ssh.mocks

import static com.aestasit.ssh.mocks.MockSshServer.*
import static org.junit.Assert.*

import org.junit.Test

/**
 * Test for SSH server mocking functionality.
 *
 * @author Aestas/IT
 *
 */
class MockServerTest {

  @Test
  void testMockSshd() throws Exception {

    // Create command expectations.
    command('^ls.*$') { inp, out, err, callback, env ->
      out << '''total 20
drwxr-xr-x 3 1100 1100 4096 Aug  7 16:52 .
drwxr-xr-x 8 1100 1100 4096 Aug  1 17:53 ..
drwxr-xr-x 3 1100 1100 4096 Aug  7 16:49 examples
'''
      callback.onExit(0)
    }

    command('^whoami.*$') { inp, out, err, callback, env ->
      out << "root\n"
      callback.onExit(0)
    }

    command('^du.*$') { inp, out, err, callback, env ->
      out << "100\n"
      callback.onExit(0)
    }

    command('^rm.*$') { inp, out, err, callback, env ->
      out << "/tmp/test.file\n"
      callback.onExit(0)
    }

    command('timeout') { inp, out, err, callback, env ->
      Thread.sleep(2000)
      callback.onExit(0)
    }

    // Create command expectations.
    dir('/tmp/puppet')

    // Start server.
    startSshd(2323)

    // Connect to the server
    // TODO:

    // Execute commands
    // TODO:

    // Stop server.
    stopSshd()

  }
}
