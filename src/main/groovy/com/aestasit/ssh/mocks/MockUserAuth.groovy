package com.aestasit.ssh.mocks

import org.apache.sshd.common.util.Buffer
import org.apache.sshd.server.UserAuth
import org.apache.sshd.server.session.ServerSession

/**
 * Mock user authentication that authenticates any user.
 *
 * @author Andrey Adamovich
 *
 */
class MockUserAuth implements UserAuth {

  def Boolean auth(ServerSession session, String username, Buffer buffer) {
    true
  }
}
