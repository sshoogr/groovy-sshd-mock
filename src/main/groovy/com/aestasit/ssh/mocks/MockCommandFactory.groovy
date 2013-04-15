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

  public Command createCommand(String command) {
    return new MockCommand(command)
  }
}