package org.gitee.orryx.core.skill

import taboolib.module.kether.Script

interface ICastSkill: ISkill {

    /**
     * 技能运行脚本
     * */
    val actions: String

    /**
     * 技能拓展运行脚本
     * */
    val extendActions: Map<String, String>

    /**
     * 技能运行前检测脚本
     * */
    val castCheckAction: String?

    /**
     * 技能运行脚本
     * */
    val script: Script?

    /**
     * 技能拓展子脚本
     * */
    val extendScripts: Map<String, Script?>
}