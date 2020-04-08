package me.Stefan923.UltimateDonors.Commands.Exceptions;

public class MissingPermissionException extends Exception {
    public MissingPermissionException() {
        super("");
    }

    public MissingPermissionException(final String string) {
        super(string);
    }
}
