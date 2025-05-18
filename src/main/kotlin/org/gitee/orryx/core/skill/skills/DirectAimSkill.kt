package org.gitee.orryx.core.skill.skills

import org.gitee.orryx.core.skill.SkillLoaderManager
import org.gitee.orryx.utils.DIRECT_AIM
import taboolib.module.configuration.Configuration
import taboolib.module.kether.Script

class DirectAimSkill(
    key: String,
    configuration: Configuration
) : AbstractCastSkillLoader(key, configuration), IAim {

    override val type = DIRECT_AIM

    val aimSizeAction: String = options.getString("AimSizeAction", "5")!!

    override val aimMinAction: String
        get() = aimSizeAction

    override val aimMaxAction: String
        get() = aimSizeAction

    override val aimRadiusAction: String = options.getString("AimRadiusAction", "10")!!

    override val script: Script? = SkillLoaderManager.loadScript(this)

    override val extendScripts: Map<String, Script?> = SkillLoaderManager.loadExtendScript(this)
}