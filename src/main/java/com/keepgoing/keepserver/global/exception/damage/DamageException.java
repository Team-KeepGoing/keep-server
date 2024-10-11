package com.keepgoing.keepserver.global.exception.damage;

import com.keepgoing.keepserver.global.exception.BusinessException;

public class DamageException extends BusinessException {
    private static final DamageException INVALID_DAMAGE = new DamageException(DamageError.INVALID_DAMAGE);
    private static final DamageException DUPLICATE = new DamageException(DamageError.DUPLICATE);

    public DamageException(DamageError error) {
        super(error);
    }

    public static DamageException invalidDamage() {
        return INVALID_DAMAGE;
    }

    public static DamageException duplicate() {
        return DUPLICATE;
    }
}
