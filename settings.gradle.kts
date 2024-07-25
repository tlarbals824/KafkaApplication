//rootProject.name = "KafkaApplication"

include(":api")
include(":domain")
include(":usecase")
include(":usecase:post-usecase")
include(":usecase:coupon-usecase")
include(":usecase:post-resolving-help-usecase")
include(":usecase:inspected-post-usecase")
include(":usecase:subscribing-post-usecase")
include(":usecase:post-search-usecase")
include(":usecase:core")
include(":common")

include(":adapter")
include(":adapter:metadata-client")
include(":adapter:mysql")
include(":adapter:kafka")
include(":adapter:chat-gpt-client")
include(":adapter:mongodb")
include(":adapter:redis")
include(":adapter:elasticsearch")


include(":worker")
include(":worker:auto-inspection-worker")
include(":worker:content-subscribing-worker")
include(":worker:content-caching-worker")
include(":worker:content-indexing-worker")
include(":worker:coupon-issuing-worker")