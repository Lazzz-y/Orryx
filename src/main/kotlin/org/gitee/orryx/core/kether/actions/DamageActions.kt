package org.gitee.orryx.core.kether.actions

import org.bukkit.entity.LivingEntity
import org.gitee.orryx.api.events.damage.DamageType
import org.gitee.orryx.compat.DefaultAttributeBridge
import org.gitee.orryx.compat.IAttributeBridge
import org.gitee.orryx.core.targets.ITargetEntity
import org.gitee.orryx.module.wiki.Action
import org.gitee.orryx.module.wiki.Type
import org.gitee.orryx.utils.*
import taboolib.common.util.unsafeLazy
import taboolib.module.kether.KetherParser
import taboolib.module.kether.script

object DamageActions {

    internal val Default by unsafeLazy { DefaultAttributeBridge() }

    @KetherParser(["damage"], namespace = ORRYX_NAMESPACE, shared = true)
    private fun damageAction() = combinationParser(
        Action.new("属性系统", "攻击目标", "damage", true)
            .description("攻击目标，支持接入属性系统")
            .addEntry("攻击数值", Type.DOUBLE)
            .addEntry("攻击是否接入属性系统", Type.BOOLEAN)
            .addContainerEntry("攻击目标")
            .addEntry("攻击来源", Type.CONTAINER, true, "@self", head = "source")
            .addEntry("攻击类型", Type.STRING, true, "PHYSICS", head = "type")
    ) {
        it.group(
            double(),
            bool(),
            theyContainer(),
            command("source", then = container()).option(),
            command("type", then = text()).option()
        ).apply(it) { damage, attribute, container, source, type ->
            future {
                val sources = source.orElse(self())
                val damageType = type?.uppercase()?.let { it1 -> DamageType.valueOf(it1) } ?: DamageType.PHYSICS
                ensureSync {
                    val attacker = sources.firstInstance<ITargetEntity<LivingEntity>>().getSource()
                    if (attribute) {
                        container!!.forEachInstance<ITargetEntity<*>> { target ->
                            target.entity.getBukkitLivingEntity()?.let { entity ->
                                IAttributeBridge.INSTANCE.damage(
                                    attacker,
                                    entity,
                                    damage,
                                    damageType,
                                    script()
                                )
                            }
                        }
                    } else {
                        container!!.forEachInstance<ITargetEntity<*>> { target ->
                            target.entity.getBukkitLivingEntity()?.let { entity ->
                                Default.damage(
                                    attacker,
                                    entity,
                                    damage,
                                    damageType,
                                    script()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}