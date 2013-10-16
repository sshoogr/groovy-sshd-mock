/*
 * Copyright (C) 2011-2013 Aestas/IT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aestasit.ssh.mocks

import org.apache.sshd.server.Environment
import org.apache.sshd.server.ExitCallback

/**
 * Mock command implementation that reads expectation data upon each command execution.
 *
 * @author Andrey Adamovich
 *
 */
class MockCommand implements org.apache.sshd.server.Command {

  private ExitCallback callback
  private OutputStream err
  private OutputStream out
  private InputStream inp
  private final String command

  def MockCommand() {
    super()
  }

  def MockCommand(String command) {
    super()
    this.command = command
  }

  def void setInputStream(InputStream inp) {
    this.inp = inp
  }

  def void setOutputStream(OutputStream out) {
    this.out = out
  }

  def void setErrorStream(OutputStream err) {
    this.err = err
  }

  def void setExitCallback(ExitCallback callback) {
    this.callback = callback
  }

  def void start(Environment env) throws IOException {
    Closure commandCode
    if (command) {
      commandCode = MockSshServer.commands.find { command == it.key || command.matches(it.key) }?.value
    }
    if (commandCode) {
      commandCode(inp, out, err, callback, env)
    } else {
      err << "Unknown command: $command"
      callback.onExit(-1)
    }
  }

  def void destroy() {
  }
}
