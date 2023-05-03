package com.example.taipeizoo.datamodel

data class ResponseSection(
    val result: SectionResult
)

data class SectionResult(
    val limit: UInt,
    val offset: UInt,
    val count: UInt,
    val sort: String,
    val results: List<SectionContent>
)

data class SectionContent(
    val _id: UInt,
    val _importdate: HashMap<String, String>,
    val e_no: UInt,
    val e_category: String,
    val e_name: String,
    val e_pic_url: String,
    val e_info: String,
    val e_memo: String,
    val e_geo: String,
    val e_url: String
)
