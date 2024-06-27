//rootProject.name = "KafkaApplication"

include(":api")
include(":domain")
include(":usecase")
include(":usecase:post-usecase")
include(":usecase:post-resolving-help-usecase")
include(":usecase:core")
include(":common")

include(":adapter")
include(":adapter:metadata-client")
include(":adapter:mysql")