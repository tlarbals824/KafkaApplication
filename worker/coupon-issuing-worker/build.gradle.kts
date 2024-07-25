dependencies{
    implementation(project(":usecase:core"))
    implementation(project(":usecase:coupon-usecase"))

    implementation(project(":adapter:redis"))
    implementation(project(":adapter:mysql"))
}