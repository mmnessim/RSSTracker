package com.mnessim.researchtrackerkmp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val title: String,
    val link: String,
    val guid: String,
    val atomLink: String,
    val description: String,
    val creator: String,
    val pubDate: String,
    val categories: List<String>,
    val mediaContentUrl: String?,
    val mediaContentHeight: Int?,
    val mediaContentWidth: Int?,
    val mediaCredit: String?,
    val mediaDescription: String?
)

val placeholderArticle = Article(
    title = "Long Carrier Deployment Projects Strength in U.S. Pressure Campaign on Venezuela, and Carries Costs",
    link = "https://www.nytimes.com/2025/12/24/us/politics/aircraft-carrier-deployment-caribbean-costs.html",
    guid = "https://www.nytimes.com/2025/12/24/us/politics/aircraft-carrier-deployment-caribbean-costs.html",
    atomLink = "https://www.nytimes.com/2025/12/24/us/politics/aircraft-carrier-deployment-caribbean-costs.html",
    description = "The U.S.S. Ford has been deployed for six months, now in the Caribbean as part of President Trump’s pressure campaign on Venezuela. Maintenance woes and strains on sailors will likely mount.",
    creator = "John Ismay and Eric Schmitt",
    pubDate = "Wed, 24 Dec 2025 14:43:06 +0000",
    categories = listOf(
        "United States Defense and Military Forces",
        "Ships and Shipping"
    ),
    mediaContentUrl = "https://static01.nyt.com/images/2025/12/24/multimedia/24dc-ford-02-mbtv/24dc-ford-02-mbtv-mediumSquareAt3X.jpg",
    mediaContentHeight = 1543,
    mediaContentWidth = 1543,
    mediaCredit = "Jonathan Klein/Agence France-Presse — Getty Images",
    mediaDescription = "The aircraft carrier Gerald R. Ford in the North Sea in September. Neither the Navy nor the Pentagon has said when the ship’s deployment in the Caribbean will end."
)