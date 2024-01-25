package com.aallam.openai.api.baichuan

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BCCharacter(
    /**
     * 角色 id，在百川角色创建平台创建的自定义角色或平台的预制角色
     * 当填写角色 id 时，角色名称，角色信息，用户名称，用户与角色的关系四个字段可以为空
     */
    @SerialName("character_id") public val id: Int? = null,

    /**
     * 角色名称
     * 角色 id 为空时，角色名称必填
     */
    @SerialName("character_name") public val characterName: String? = null,

    /**
     * 角色信息，包括基本信息，人物经历，人物性格，人物关系等
     * 角色 id 为空时，角色信息必填
     */
    @SerialName("character_info") public val characterInfo: String? = null,

    /**
     * 用户名称
     * 角色 id 为空时，用户名称必填
     */
    @SerialName("user_name") public val userName: String? = null,

    /**
     * 用户信息，包括用户性格，与角色的关系等，按照用户是角色的 xxx 逻辑填写
     * 角色 id 为空时，用户信息必填
     */
    @SerialName("user_info") public val userInfo: String? = null,

    @SerialName("character_icon") public val characterIcon: String? = null,
) {
    @Suppress("FunctionName")
    public companion object {
        public fun character(id: Int) : BCCharacter {
            return BCCharacter(id = id)
        }
    }

}
