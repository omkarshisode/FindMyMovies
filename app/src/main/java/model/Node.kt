package model

data class Node(
    val __typename: String,
    val author: Author,
    val authorRating: Int,
    val submissionDate: String,
    val summary: Summary,
    val text: Text
)