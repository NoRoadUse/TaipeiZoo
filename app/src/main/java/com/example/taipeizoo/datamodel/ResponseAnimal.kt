package com.example.taipeizoo.datamodel

data class ResponseAnimal(
    val result: AnimalResult
)

data class AnimalResult(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: String,
    val results: List<AnimalContent>
)

data class AnimalContent(
    val _id: Int,
    val _importdate: HashMap<String, String>,
    val a_name_ch: String,
    val a_summary: String,
    val a_keywords: String,
    val a_alsoknown: String,
    val a_geo: String,
    val a_location: String,
    val a_poigroup: String,
    val a_name_en: String,
    val a_name_latin: String,
    val a_phylum: String,
    val a_class: String,
    val a_order: String,
    val a_family: String,
    val a_conservation: String,
    val a_distribution: String,
    val a_habitat: String,
    val a_feature: String,
    val a_behavior: String,
    val a_diet: String,
    val a_crisis: String,
    val a_interpretation: String,
    val a_theme_name: String,
    val a_theme_url: String,
    val a_adopt: String,
    val a_code: String,
    val a_pic01_alt: String,
    val a_pic01_url: String,
    val a_pic02_alt: String,
    val a_pic02_url: String,
    val a_pic03_alt: String,
    val a_pic03_url: String,
    val a_pic04_alt: String,
    val a_pic04_url: String,
    val a_pdf01_alt: String,
    val a_pdf01_url: String,
    val a_pdf02_alt: String,
    val a_pdf02_url: String,
    val a_voice01_alt: String,
    val a_voice01_url: String,
    val a_voice02_alt: String,
    val a_voice02_url: String,
    val a_voice03_alt: String,
    val a_voice03_url: String,
    val a_vedio_url: String,
    val a_update: String,
    val a_cid: String
)