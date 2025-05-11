package org.gitee.orryx.core.job

import org.bukkit.entity.Player
import org.gitee.orryx.module.experience.IExperience
import org.gitee.orryx.core.key.IBindKey
import org.gitee.orryx.core.key.IGroup
import org.gitee.orryx.core.skill.IPlayerSkill
import org.gitee.orryx.dao.cache.Saveable
import org.gitee.orryx.dao.pojo.PlayerJobPO
import java.util.concurrent.CompletableFuture

interface IPlayerJob: Saveable {

    /**
     * 玩家ID
     * */
    val id: Int

    /**
     * 拥有玩家
     * */
    val player: Player

    /**
     * 职业键名
     * */
    val key: String

    /**
     * 职业配置文件
     * */
    val job: IJob

    /**
     * 绑定的组和按键
     * */
    val bindKeyOfGroup: Map<IGroup, Map<IBindKey, String?>>

    /**
     * 职业当前选择的技能组
     *
     * 默认default
     * */
    val group: String

    /**
     * 等级(30/100 Lv.6/10)中的6
     * */
    val level: Int

    /**
     * 等级(30/100 Lv.6/10)中的10
     * */
    val maxLevel: Int

    /**
     * 经验值(30/100 Lv.6/10)中的30加前六级经验
     * */
    val experience: Int

    /**
     * 当前等级下的经验值(30/100 Lv.6/10)中的30
     * */
    val experienceOfLevel: Int

    /**
     * 当前等级下的最大经验值(30/100 Lv.6/10)中的100
     * */
    val maxExperienceOfLevel: Int

    /**
     * 获得经验值
     * @param experience 经验值
     * @return 结果
     * */
    fun giveExperience(experience: Int): CompletableFuture<ExperienceResult>

    /**
     * 失去经验值
     * @param experience 经验值
     * @return 结果
     * */
    fun takeExperience(experience: Int): CompletableFuture<ExperienceResult>

    /**
     * 设置经验值
     * @param experience 经验值
     * @return 结果
     * */
    fun setExperience(experience: Int): CompletableFuture<ExperienceResult>

    /**
     * 获得等级
     * @param level 等级
     * @return 结果
     * */
    fun giveLevel(level: Int): CompletableFuture<LevelResult>

    /**
     * 失去等级
     * @param level 等级
     * @return 结果
     * */
    fun takeLevel(level: Int): CompletableFuture<LevelResult>

    /**
     * 设置等级
     * @param level 等级
     * @return 结果
     * */
    fun setLevel(level: Int): CompletableFuture<LevelResult>

    /**
     * 获取升级会带来的技能点点数
     * */
    fun getUpgradePoint(from: Int, to: Int): Int

    /**
     * 获取属性数据
     * */
    fun getAttributes(): List<String>

    /**
     * 获得经验计算器
     * @return 经验计算器
     * */
    fun getExperience(): IExperience

    /**
     * 获得最大法力值
     * @return 法力值
     * */
    fun getMaxMana(): Double

    /**
     * 获得恢复法力值
     * @return 法力值
     * */
    fun getRegainMana(): Double

    /**
     * 获得最大精力值
     * @return 精力值
     * */
    fun getMaxSpirit(): Double

    /**
     * 获得恢复精力值
     * @return 精力值
     * */
    fun getRegainSpirit(): Double


    /**
     * 设置技能组
     * @param group 已注册的技能组
     * @return 是否成功
     * */
    fun setGroup(group: String): CompletableFuture<Boolean>

    /**
     * 设置技能绑定按键
     * @param skill 技能
     * @param group 技能组
     * @param bindKey 绑定按键
     * @return 是否成功
     * */
    fun setBindKey(skill: IPlayerSkill, group: IGroup, bindKey: IBindKey): CompletableFuture<Boolean>

    /**
     * 取消技能绑定按键
     * @param skill 技能
     * @param group 技能组
     * @return 是否成功
     * */
    fun unBindKey(skill: IPlayerSkill, group: IGroup): CompletableFuture<Boolean>

    /**
     * 清除此职业数据
     * @return 是否成功
     * */
    fun clear(): CompletableFuture<Boolean>

    /**
     * 创建序列化存储数据
     * */
    fun createPO(): PlayerJobPO
}