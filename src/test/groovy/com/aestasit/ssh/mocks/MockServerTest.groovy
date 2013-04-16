package com.aestasit.ssh.mocks

import static com.aestasit.ssh.mocks.MockSshServer.*
import static org.junit.Assert.*

import org.junit.Test

import com.jcraft.jsch.ChannelExec
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session

/**
 * Test for SSH server mocking functionality.
 *
 * @author Aestas/IT
 *
 */
class MockServerTest {

  private Session session

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

    // Create file expectations.
    dir('/tmp/puppet')

    // Start server.
    startSshd(2323)

    // Connect to the server
    def jsch = new JSch()
    def config = new Properties()
    config.put("StrictHostKeyChecking", "no")
    config.put("HashKnownHosts",  "yes")
    jsch.config = config
    session = jsch.getSession('john', 'localhost', 2323)
    session.connect()

    // Execute commands
    exec('ls -la', 0)
    exec('du -x', 0)
    exec('rm -rf /', 0)
    exec('mess', -1)

    // Copy file
    ChannelSftp channel = (ChannelSftp) session.openChannel("sftp")
    channel.connect()
    channel.put(new ByteArrayInputStream("data".getBytes()), "/tmp/puppet/test.txt")

    // Disconnect from the server.
    session.disconnect()

    // Stop server.
    stopSshd()

  }

  private void exec(String command, int exitStatus) {
    ChannelExec channel = (ChannelExec) session.openChannel("exec")
    channel.command = command
    channel.setPty(true)
    channel.connect()
    Thread.sleep(100)
    assertEquals(exitStatus, channel.exitStatus)
  }

}
