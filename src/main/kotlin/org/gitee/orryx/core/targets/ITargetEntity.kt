package org.gitee.orryx.core.targets

import org.gitee.orryx.api.adapters.IEntity

interface ITargetEntity<T>: ITarget<T> {

    val entity: IEntity
}