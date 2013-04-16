package com.aestasit.ssh.mocks

import org.apache.sshd.server.Command
import org.apache.sshd.server.CommandFactory

/**
 * Mock command factory.
 *
 * @author Andrey Adamovich
 *
 */
class MockCommandFactory implements CommandFactory  {

  def Command createCommand(String command) {
    new MockCommand(command)
  }
}