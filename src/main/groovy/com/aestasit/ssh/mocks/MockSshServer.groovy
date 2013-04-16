package com.aestasit.ssh.mocks

import org.apache.commons.io.FilenameUtils
import org.apache.sshd.SshServer
import org.apache.sshd.server.command.ScpCommandFactory
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider
import org.apache.sshd.server.sftp.SftpSubsystem


/**
 * Central mocking class
 *
 * @author Aestas/IT
 *
 */
final class MockSshServer {

  static SshServer sshd
  static Map files
  static Map commands

  /**
   * Starts SSH server.
   */
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

  /*
   * EXPECTATIONS
   */

  /**
   * Adds remote command expectations and behavior.
   *
   * @param pattern command pattern to match
   * @param cl closure to execute for command.
   */
  def static void command(String pattern, Closure cl) {
    commands.put(pattern, cl)
  }

  /**
   * Adds remote file expectations.
   *
   * @param path the path of the file.
   */
  def static void file(String path) {
    String fullPath = FilenameUtils.normalizeNoEndSeparator(path, true)
    dir(FilenameUtils.getPath(fullPath))
    files.put(fullPath, [
      'isDirectory': false,
      'doesExist': true
    ])
  }

  /**
   * Adds remote directory expectations.
   *
   * @param path the path of the directory.
   */
  def static void dir(String path) {
    String fullPath = FilenameUtils.normalizeNoEndSeparator(path, true)
    String nextPath = ''
    fullPath.split('/').each { pathElement ->
      nextPath += '/' + pathElement
      files.put(nextPath, [
        'isDirectory': true,
        'doesExist': true
      ])
    }
  }

  /**
   * Stops running SSH server.
   */
  def static void stopSshd() {
    sshd?.stop(true)
  }
}
