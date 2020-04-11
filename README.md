# groovy-sshd-mock

![GitHub Workflow Status](https://github.com/sshoogr/groovy-sshd-mock/workflows/Build/badge.svg)
![ASL2 Licensed](http://img.shields.io/badge/license-ASL2-blue.svg)
![Latest Version](https://api.bintray.com/packages/sshoogr/sshoogr/groovy-sshd-mock/images/download.svg)


## Overview

The `groovy-sshd-mock` is a simple Groovy-based library to start a mock SSH server within your unit tests. It allows defining command 
and file expectations.

## Usage

First of all you need to statically import `MockSshServer` class to get access to the library functionality: 

    import static com.aestasit.ssh.mocks.MockSshServer.*

Then you can use `command`, `file` and `dir` methods to define SSH server expectations:

    // Create command expectations.
    command('^ls.*$') { inp, out, err, callback, env ->
      out << '''total 20
    drwxr-xr-x 3 1100 1100 4096 Aug  7 16:52 .
    drwxr-xr-x 8 1100 1100 4096 Aug  1 17:53 ..
    drwxr-xr-x 3 1100 1100 4096 Aug  7 16:49 examples
    '''
      callback.onExit(0)
    }

    // Create file expectations.
    dir('/tmp/puppet')

As you can notice, the closure passed to the `command` method takes 5 parameters that allow influencing the 
behavior of the mocked remote command. 

The `inp`, `out`, `err` parameters are command's standard input, standard output and standard error streams. 
The `callback` parameter allows setting exit code for the mocked command to return. The `env` parameter gives you 
access to command's environment variables, which will most likely will be useless in a mocked environment.  

After that is done you can start the mock server on a specific port (e.g. `2323`) and execute your SSH code against that:

    // Start server.
    startSshd(2323)

    // Execute SSH test code (connect, execute remote commands, transfer filers, disconnect).
    ...

To keep things clean you can also eventually stop the server:

    // Stop server.
    stopSshd()


