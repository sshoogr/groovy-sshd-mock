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

  public MockSshFile(String file) {
    super()
    this.file = file
  }

  public MockSshFile(SshFile baseDir, String file) {
    super()
    this.baseDir = baseDir
    this.file = file
  }

  public String getAbsolutePath() {
    return file
  }

  public String getName() {
    return file
  }

  public String getOwner() {
    return "root"
  }

  public boolean isDirectory() {
    return MockSshServer.files[file]['isDirectory']
  }

  public boolean isFile() {
    return !MockSshServer.files[file]?.getAt('isDirectory')
  }

  public boolean doesExist() {
    return MockSshServer.files[file]?.getAt('doesExist')
  }

  public boolean isReadable() {
    return true
  }

  public boolean isWritable() {
    return true
  }

  public boolean isExecutable() {
    return true
  }

  public boolean isRemovable() {
    return true
  }

  public SshFile getParentFile() {
    return baseDir
  }

  public long getLastModified() {
    return 0
  }

  public boolean setLastModified(long time) {
    return true
  }

  public long getSize() {
    return 0
  }

  public boolean mkdir() {
    return true
  }

  public boolean delete() {
    return true
  }

  public boolean create() throws IOException {
    return true
  }

  public void truncate() throws IOException {
  }

  public boolean move(SshFile destination) {
    return true
  }

  public List<SshFile> listSshFiles() {
    return new ArrayList<SshFile>()
  }

  public OutputStream createOutputStream(long offset) throws IOException {
    return new ByteArrayOutputStream()
  }

  public InputStream createInputStream(long offset) throws IOException {
    return new ByteArrayInputStream("data".getBytes("UTF-8"))
  }

  public void handleClose() throws IOException {
  }
}
