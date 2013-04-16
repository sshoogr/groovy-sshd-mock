package com.aestasit.ssh.mocks

import org.apache.sshd.common.Session
import org.apache.sshd.server.FileSystemFactory
import org.apache.sshd.server.FileSystemView

/**
 * Mock file system factory.
 *
 * @author Andrey Adamovich
 *
 */
class MockFileSystemFactory implements FileSystemFactory {

  def FileSystemView createFileSystemView(Session session) throws IOException {
    new MockFileSystemView()
  }
}
