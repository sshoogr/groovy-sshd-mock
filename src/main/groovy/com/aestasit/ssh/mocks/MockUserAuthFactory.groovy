package com.aestasit.ssh.mocks

import org.apache.sshd.common.NamedFactory
import org.apache.sshd.server.UserAuth

/**
 * Mock user authentication factory.
 *
 * @author Andrey Adamovich
 *
 */
public class MockUserAuthFactory implements NamedFactory<UserAuth> {

  public UserAuth create() {
    return new MockUserAuth()
  }

  public String getName() {
    return "none"
  }
}
