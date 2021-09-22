/*
 * Tencent is pleased to support the open source community by making BK-CI 蓝鲸持续集成平台 available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * BK-CI 蓝鲸持续集成平台 is licensed under the MIT license.
 *
 * A copy of the MIT License is included in this file.
 *
 *
 * Terms of the MIT License:
 * ---------------------------------------------------
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.tencent.devops.worker.common.api.quality

import com.tencent.devops.common.api.pojo.Result
import com.tencent.devops.worker.common.api.AbstractBuildResourceApi
import com.tencent.devops.worker.common.logger.LoggerService
import okhttp3.MediaType
import okhttp3.RequestBody
import org.slf4j.LoggerFactory

class QualityGatewayResourceApi : QualityGatewaySDKApi, AbstractBuildResourceApi() {
    companion object {
        private val logger = LoggerFactory.getLogger(QualityGatewayResourceApi::class.java)
    }

    override fun saveScriptHisMetadata(elementType: String, data: Map<String, String>): Result<String> {
        try {
            val path = "/ms/quality/api/build/metadata/saveHisMetadata?elementType=$elementType"
            val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                objectMapper.writeValueAsString(data))
            val request = buildPost(path, requestBody)
            val responseContent = request(request, "保存脚本元数据失败")
            return Result(responseContent)
        } catch (ignore: Exception) {
            LoggerService.addErrorLine("保存脚本元数据失败: ${ignore.message}")
            logger.warn("saveScriptHisMetadata|${ignore.message}", ignore)
        }
        return Result("")
    }
}
