package com.keepgoing.keepserver.global.exception.damage;

import com.keepgoing.keepserver.global.exception.BusinessException;

public class DamageException extends BusinessException {
    private static final DamageException INVALID_DAMAGE = new DamageException(DamageError.INVALID_DAMAGE);
    private static final DamageException DUPLICATE = new DamageException(DamageError.DUPLICATE);
    private static final DamageException INVALID_ISSUE_TYPE = new DamageException(DamageError.INVALID_ISSUE_TYPE);

    public DamageException(DamageError error) {
        super(error);
    }

    public static DamageException invalidDamage() {
        return INVALID_DAMAGE;
    }

    public static DamageException duplicate() {
        return DUPLICATE;
    }

    public static DamageException invalidIssueType() {
        return INVALID_ISSUE_TYPE;
    }
}
