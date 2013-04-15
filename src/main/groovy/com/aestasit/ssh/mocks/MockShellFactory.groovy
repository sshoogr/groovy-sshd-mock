package com.aestasit.ssh.mocks

import org.apache.sshd.common.Factory
import org.apache.sshd.server.Command

/**
 * Mock shell factory.
 *
 * @author Andrey Adamovich
 *
 */
class MockShellFactory implements Factory<Command> {

  public Command create() {
    return new MockCommand()
  }
}