package com.taskagile.app.domain.common.model;

import java.io.Serializable;

public abstract class AbstractBaseId implements Serializable {

  private static final long serialVersionUID = -8145762552145950632L;

  private long id;

  public AbstractBaseId(long id) {
    this.id = id;
  }

  public long value() {
    return this.id;
  }

  public boolean isValid() {
    return id > 0;
  }
}
