package com.aestasit.ssh.mocks

import org.apache.sshd.SshServer
import org.apache.sshd.server.command.ScpCommandFactory
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider
import org.apache.sshd.server.sftp.SftpSubsystem

class MockSshServer {

  static SshServer sshd
  static Map files
  static Map commands

  def static void startSshd(int defaultPort = 2222) {
    sshd = SshServer.setUpDefaultServer()
    sshd.with {
      port = defaultPort
      keyPairProvider = new SimpleGeneratorHostKeyProvider()
      commandFactory = new ScpCommandFactory( new MockCommandFactory() )
      shellFactory = new MockShellFactory()
      userAuthFactories = [
        new MockUserAuthFactory()
      ]
      fileSystemFactory = new MockFileSystemFactory()
      subsystemFactories = [
        new SftpSubsystem.Factory()
      ]
    }
    sshd.start()
  }

  def static void stopSshd() {
    sshd?.stop(true)
  }
}
