package com.aestasit.ssh.mocks

import org.apache.sshd.common.NamedFactory
import org.apache.sshd.server.UserAuth

/**
 * Mock user authentication factory.
 *
 * @author Andrey Adamovich
 *
 */
class MockUserAuthFactory implements NamedFactory<UserAuth> {

  def UserAuth create() {
    new MockUserAuth()
  }

  def String getName() {
    "none"
  }
}
