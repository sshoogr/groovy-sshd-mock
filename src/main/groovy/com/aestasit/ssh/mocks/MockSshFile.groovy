/*
 * Copyright (C) 2011-2020 Aestas/IT
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

import org.apache.sshd.server.SshFile

/**
 * Mock file implementation that reads expectation data upon each file operation.
 *
 * @author Andrey Adamovich
 *
 */
class MockSshFile implements SshFile {

  private final String file

  private final SshFile baseDir

  def MockSshFile(String file) {
    super()
    this.file = file
  }

  def MockSshFile(SshFile baseDir, String file) {
    super()
    this.baseDir = baseDir
    this.file = file
  }

  def String getAbsolutePath() {
    file
  }

  def String getName() {
    file
  }

  def String getOwner() {
    "root"
  }

  def boolean isDirectory() {
    MockSshServer.files[file]['isDirectory']
  }

  def boolean isFile() {
    !MockSshServer.files[file]?.getAt('isDirectory')
  }

  def boolean doesExist() {
    MockSshServer.files[file]?.getAt('doesExist')
  }

  def boolean isReadable() {
    true
  }

  def boolean isWritable() {
    true
  }

  def boolean isExecutable() {
    true
  }

  def boolean isRemovable() {
    true
  }

  def SshFile getParentFile() {
    baseDir
  }

  def long getLastModified() {
    0
  }

  def boolean setLastModified(long time) {
    true
  }

  def long getSize() {
    0
  }

  def boolean mkdir() {
    true
  }

  def boolean delete() {
    true
  }

  def boolean create() throws IOException {
    true
  }

  def void truncate() throws IOException {
  }

  def boolean move(SshFile destination) {
    true
  }

  def List<SshFile> listSshFiles() {
    new ArrayList<SshFile>()
  }

  def OutputStream createOutputStream(long offset) throws IOException {
    new ByteArrayOutputStream()
  }

  def InputStream createInputStream(long offset) throws IOException {
    new ByteArrayInputStream("data".getBytes("UTF-8"))
  }

  def void handleClose() throws IOException {
  }
}
