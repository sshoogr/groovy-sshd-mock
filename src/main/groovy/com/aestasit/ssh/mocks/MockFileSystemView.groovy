package com.aestasit.ssh.mocks

import org.apache.sshd.server.FileSystemView
import org.apache.sshd.server.SshFile

/**
 * Mock file system view.
 *
 * @author Andrey Adamovich
 *
 */
class MockFileSystemView implements FileSystemView {

  def SshFile getFile(String file) {
    new MockSshFile(file)
  }

  def SshFile getFile(SshFile baseDir, String file) {
    new MockSshFile(baseDir, file)
  }
}
