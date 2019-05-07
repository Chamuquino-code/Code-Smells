/** Sets the interpreter to enforce access to private members.  The result is {@code false} if
   * the remote JVM is unavailable or if an exception occurs.  Blocks until the interpreter is connected.
   */
public boolean setEnforcePrivateAccess(boolean enforce) {
    InterpreterJVMRemoteI remote = _state.value().interpreter(false);
    if (remote == null) {
        return false;
    }
    try {
        remote.setEnforcePrivateAccess(enforce);
        return true;
    } catch (RemoteException e) {
        _handleRemoteException(e);
        return false;
    }
}
